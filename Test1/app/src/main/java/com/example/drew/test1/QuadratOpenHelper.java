package com.example.drew.test1;
import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

//TODO: BETTER MAKE ERRORS N STUFF

/**
 * Created by Mathieu Belzile-Ha on 14/03/2017.
 *
 * TREE OPEN HELPER
 *
 * CLASS WITH PURPOSE TO FACILITATE NAVIGATION OF QUADRAT TABLE.
 *
 */

public class QuadratOpenHelper extends SQLiteOpenHelper{

    //CONSTRUCTOR// -------------------------------------------------------
    QuadratOpenHelper(Context context){
        super(context, SQLiteConstants.DATABASE_NAME, null, SQLiteConstants.DATABASE_VERSION);
    }

    //OPENHELPER// --------------------------------------------------------
    /**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(SQLiteConstants.TREE_TABLE_CREATE);
        database.execSQL(SQLiteConstants.QUADRAT_TABLE_CREATE);
    }

    /**
     * Method called on database upgrade.
     * Right now, just drops current tables and creates new ones.
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.TREE_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.QUADRAT_TABLE_NAME);
        onCreate(database);
    }

    //MUTATORS// -----------------------------------------------------
    public void addTree(TreeImage tree, int quadratId) throws QuadratNotFoundException{
        try{
            existenceCheck(quadratId);
        } catch (QuadratNotFoundException e){
            e.printStackTrace();
            throw e;
        }

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SQLiteConstants.TREE_DBH_KEY, tree.getDbh());
        values.put(SQLiteConstants.TREE_SPECIES_KEY, tree.getSpecies().getName());

        if (tree.getStorageFactor() == null) {
            values.put(SQLiteConstants.TREE_STORAGE_FACTOR_KEY, SQLiteConstants.NULL_VALUE);
        } else {
            values.put(SQLiteConstants.TREE_STORAGE_FACTOR_KEY, tree.getStorageFactor().getName());
        }

        if (tree.getMaterialType() == null) {
            values.put(SQLiteConstants.TREE_MATERIAL_TYPE_KEY, SQLiteConstants.NULL_VALUE);
        } else {
            values.put(SQLiteConstants.TREE_MATERIAL_TYPE_KEY, tree.getMaterialType().getName());
        }

        values.put(SQLiteConstants.TREE_QUADRAT_ID_KEY, quadratId);

        database.insert(SQLiteConstants.TREE_TABLE_NAME, null, values);
        database.close();
    }

    public void setCoordinates(Coordinate coordinates, int quadratId) throws QuadratNotFoundException{
        try{
            existenceCheck(quadratId);
        } catch (QuadratNotFoundException e){
            e.printStackTrace();
            throw e;
        }

        String updateCommand = "UPDATE "+ SQLiteConstants.QUADRAT_TABLE_NAME + " SET " +
                SQLiteConstants.QUADRAT_X_COORDINATE_KEY + "=" + coordinates.getX() + ", " +
                SQLiteConstants.QUADRAT_Y_COORDINATE_KEY + "=" + coordinates.getY() +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    //ACCESSORS// ------------------------------------------------------
    public List<TreeImage> getTrees(int quadratId)  throws QuadratNotFoundException{
        try{
            existenceCheck(quadratId);
        } catch (QuadratNotFoundException e){
            e.printStackTrace();
            throw e;
        }

        List<TreeImage> trees = new LinkedList<TreeImage>();
        String selectQuery = "SELECT * FROM " + SQLiteConstants.TREE_TABLE_NAME +
                " WHERE " + SQLiteConstants.TREE_QUADRAT_ID_KEY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){
            TreeImage tree = extractTreeImage(cursor);

            if (tree != null){
                trees.add(tree);
            }
        }

        cursor.close();
        return trees;
    }

    public TreeImage getTree(int relativeID, int quadratId) throws QuadratNotFoundException, TableIndexOutOfBoundsException{
        try{
            existenceCheck(quadratId);
        } catch (QuadratNotFoundException e){
            e.printStackTrace();
            throw e;
        }

        String selectQuery = "SELECT * FROM " + SQLiteConstants.TREE_TABLE_NAME +
                " WHERE " + SQLiteConstants.TREE_QUADRAT_ID_KEY + "=" + quadratId +
                " ORDER BY " + SQLiteConstants.KEY_PRIMARY +
                " LIMIT 1 OFFSET " + relativeID + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.getCount() == 0){
            throw new TableIndexOutOfBoundsException();
        }

        cursor.moveToFirst();

        try {
            TreeImage tree = extractTreeImage(cursor);
            return tree;
        } catch(InvalidTableFormatException e){
            e.printStackTrace();
            throw e;
        }
    }

    public Coordinate getCoordinates(int quadratId) throws QuadratNotFoundException{
        try{
            existenceCheck(quadratId);
        } catch (QuadratNotFoundException e){
            e.printStackTrace();
            throw e;
        }
        String selectQuery = "SELECT * FROM " + SQLiteConstants.QUADRAT_TABLE_NAME +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            if (cursor.getType(1) == SQLiteConstants.FIELD_TYPE_FLOAT
                    && cursor.getType(2) == SQLiteConstants.FIELD_TYPE_FLOAT) {
                double x = cursor.getDouble(1);
                double y = cursor.getDouble(2);

                return new Coordinate(x,y);
            }
        }

        return null;
    }

    //HELPERS// ------------------------------------------------------

    /**
     * Asserts if a given quadrat exists or not
     * @return
     */
    public boolean exists(int quadratId){
        String selectQuery = "SELECT EXISTS(SELECT * FROM " + SQLiteConstants.QUADRAT_TABLE_NAME +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + quadratId + " LIMIT 1);";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        return cursor.getInt(0) == 1;
    }

    /**
     * Helper method that throws a QuadratNotFoundException if the quadrat
     * indicated by quadratId does not exist
     * @param quadratId
     * @throws QuadratNotFoundException
     */
    private void existenceCheck(int quadratId) throws QuadratNotFoundException{
        if(!exists(quadratId)){
            throw new QuadratNotFoundException();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL(SQLiteConstants.ACTIVATE_FOREIGN_KEY_SUPPORT);
        }
    }

    /**
     * Helper method to validate tree cursor
     * @param treeCursor
     * @return treeCursor is safe to be read as a tree cursor
     */
    private boolean validTreeCursor(Cursor treeCursor){
        if (treeCursor.getColumnCount() != SQLiteConstants.TREE_NUM_COLUMNS){
            return false;
        }
        return (treeCursor.getType(SQLiteConstants.TREE_PRIMARY_KEY_COLUMN) == SQLiteConstants.TREE_PRIMARY_KEY_TYPE
                && treeCursor.getType(SQLiteConstants.TREE_DBH_COLUMN) == SQLiteConstants.TREE_DBH_TYPE
                && treeCursor.getType(SQLiteConstants.TREE_SPECIES_COLUMN) == SQLiteConstants.TREE_SPECIES_TYPE
                && treeCursor.getType(SQLiteConstants.TREE_STORAGE_FACTOR_COLUMN) == SQLiteConstants.TREE_STORAGE_FACTOR_TYPE
                && treeCursor.getType(SQLiteConstants.TREE_MATERIAL_TYPE_COLUMN) == SQLiteConstants.TREE_MATERIAL_TYPE_TYPE
                && treeCursor.getType(SQLiteConstants.TREE_QUADRAT_ID_COLUMN) == SQLiteConstants.TREE_QUADRAT_ID_TYPE);
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param treeCursor
     * @return treeInfo
     */
    private TreeImage extractTreeImage(Cursor treeCursor)throws InvalidTableFormatException {

        if(!validTreeCursor(treeCursor)){
            throw new InvalidTableFormatException();
        }

        int id = treeCursor.getInt(SQLiteConstants.TREE_PRIMARY_KEY_COLUMN); //TODO: refactoring so all tree parsing is handled by another class
        double dbh = treeCursor.getDouble(SQLiteConstants.TREE_DBH_COLUMN);
        Species species = InputParser.parseSpecies(treeCursor.getString(SQLiteConstants.TREE_SPECIES_COLUMN));
        StorageFactor storageFactor = InputParser.parseStorageFactor(treeCursor.getString(SQLiteConstants.TREE_STORAGE_FACTOR_COLUMN));
        MaterialType materialType = InputParser.parseMaterialType(treeCursor.getString(SQLiteConstants.TREE_MATERIAL_TYPE_COLUMN));
        int quadratId = treeCursor.getInt(SQLiteConstants.TREE_QUADRAT_ID_COLUMN);

        return new TreeImage(dbh, species, storageFactor, materialType, id, quadratId);
    }

    /**
     * Helper method to validate quadrat cursor
     * @param quadratCursor
     * @return quadratCursor is safe to be read as a quadrat cursor
     */
    private boolean validQuadratCursor(Cursor quadratCursor){
        if (quadratCursor.getColumnCount() != SQLiteConstants.QUADRAT_NUM_COLUMNS){
            return false;
        }
        return (quadratCursor.getType(SQLiteConstants.QUADRAT_PRIMARY_KEY_COLUMN) == SQLiteConstants.QUADRAT_PRIMARY_KEY_TYPE
                && quadratCursor.getType(SQLiteConstants.QUADRAT_X_COORDINATE_COLUMN) == SQLiteConstants.QUADRAT_X_COORDINATE_TYPE
                && quadratCursor.getType(SQLiteConstants.QUADRAT_Y_COORDINATE_COLUMN) == SQLiteConstants.QUADRAT_Y_COORDINATE_TYPE);
    }
}