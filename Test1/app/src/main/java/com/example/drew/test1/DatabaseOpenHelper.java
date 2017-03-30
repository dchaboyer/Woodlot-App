package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu on 23/03/2017.
 *
 * WCCC OPEN HELPER
 *
 * Prototypical class with default functions to be included for any class that will
 * be accessing the SQLite database.
 *
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper{

    private final String SQL_COMMAND_SELECT_lAST_ROW_ID = "SELECT last_insert_rowid();";

    //CONSTRUCTOR// -------------------------------------------------------
    DataBaseOpenHelper(Context context){
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
        database.execSQL(SQLiteConstants.STAND_TABLE_CREATE);
        database.execSQL(SQLiteConstants.WOODLOT_TABLE_CREATE);
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
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.STAND_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.WOODLOT_TABLE_NAME);
        onCreate(database);
    }

    //MUTATORS// -----------------------------------------------------
    public void addTreeToQuadrat(TreeImage tree, int quadratId){
        SQLiteDatabase database = getWritableDatabase();

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

    public void setQuadratCoordinates(Coordinate coordinates, int quadratId){
        execUpdate(SQLiteConstants.QUADRAT_TABLE_NAME, quadratId,
                SQLiteConstants.QUADRAT_X_COORDINATE_KEY, coordinates.getX());
        execUpdate(SQLiteConstants.QUADRAT_TABLE_NAME, quadratId,
                SQLiteConstants.QUADRAT_Y_COORDINATE_KEY, coordinates.getY());
    }


    public void addQuadratToStand(QuadratImage quadratImage, int standId){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Coordinate coordinate = quadratImage.getCoordinate();

        values.put(SQLiteConstants.QUADRAT_X_COORDINATE_KEY, coordinate.getX());
        values.put(SQLiteConstants.QUADRAT_Y_COORDINATE_KEY, coordinate.getY());
        values.put(SQLiteConstants.QUADRAT_STAND_ID_KEY, standId);

        database.insert(SQLiteConstants.QUADRAT_TABLE_NAME, null, values);
        Cursor cursor = database.rawQuery(SQL_COMMAND_SELECT_lAST_ROW_ID, null);
        int quadratId = cursor.getInt(0);

        for(TreeImage treeImage : quadratImage.getTrees()){
            addTreeToQuadrat(treeImage, quadratId);
        }

        database.close();
    }

    public void setStandSpecies(Species species, int rank, int standId){
        execUpdate(SQLiteConstants.STAND_TABLE_NAME, standId,
                standSpeciesKey(rank), species.getName());
    }

    public void setStandAge(int age, int standId){
        execUpdate(SQLiteConstants.STAND_TABLE_NAME, standId,
                SQLiteConstants.STAND_AGE_KEY, age);
    }

    public void setStandHeight(int height, int standId){
        execUpdate(SQLiteConstants.STAND_TABLE_NAME, standId,
                SQLiteConstants.STAND_HEIGHT_KEY, height);
    }

    //ACCESSORS// ------------------------------------------------------
    public List<TreeImage> getTreeImagesFromQuadrat(int quadratId){
        Cursor cursor = compileSelectionCursor(SQLiteConstants.TREE_TABLE_NAME,
                                                SQLiteConstants.TREE_QUADRAT_ID_KEY, quadratId);

        List<TreeImage> trees = new LinkedList<TreeImage>();

        while(cursor.moveToNext()){
            TreeImage tree = extractTreeImage(cursor);
            trees.add(tree);
        }

        cursor.close();
        return trees;
    }

    public TreeImage getTreeImageFromQuadrat(int relativeID, int quadratId){
        Cursor cursor = compileRelativeSelectionCursor(SQLiteConstants.TREE_TABLE_NAME,
                                                        SQLiteConstants.TREE_QUADRAT_ID_KEY,
                                                        quadratId, relativeID);

        cursor.moveToFirst();
        TreeImage tree = extractTreeImage(cursor);

        cursor.close();
        return tree;
    }

    public Coordinate getQuadratCoordinates(int quadratId){
        Cursor cursor = compileSelectionCursor(SQLiteConstants.QUADRAT_TABLE_NAME,
                                                SQLiteConstants.KEY_PRIMARY, quadratId);

        cursor.moveToFirst();
        double x = cursor.getDouble(SQLiteConstants.QUADRAT_X_COORDINATE_COLUMN);
        double y = cursor.getDouble(SQLiteConstants.QUADRAT_Y_COORDINATE_COLUMN);

        cursor.close();
        return new Coordinate(x,y);
    }

    //HELPERS// ------------------------------------------------------
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL(SQLiteConstants.ACTIVATE_FOREIGN_KEY_SUPPORT);
        }
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param treeCursor cursor to extract image from
     * @return treeImage resultant tree image
     */
    private TreeImage extractTreeImage(Cursor treeCursor){
        int id = treeCursor.getInt(SQLiteConstants.TREE_PRIMARY_KEY_COLUMN); //TODO: refactoring so all tree parsing is handled by another class
        double dbh = treeCursor.getDouble(SQLiteConstants.TREE_DBH_COLUMN);
        Species species = InputParser.parseSpecies(treeCursor.getString(SQLiteConstants.TREE_SPECIES_COLUMN));
        StorageFactor storageFactor = InputParser.parseStorageFactor(treeCursor.getString(SQLiteConstants.TREE_STORAGE_FACTOR_COLUMN));
        MaterialType materialType = InputParser.parseMaterialType(treeCursor.getString(SQLiteConstants.TREE_MATERIAL_TYPE_COLUMN));
        int quadratId = treeCursor.getInt(SQLiteConstants.TREE_QUADRAT_ID_COLUMN);

        return new TreeImage(dbh, species, storageFactor, materialType, id, quadratId);
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param quadratCursor cursor to extract image from
     * @return treeImage resultant tree image
     */
    private QuadratImage extractQuadratImage(Cursor quadratCursor){
        int quadratId = quadratCursor.getInt(SQLiteConstants.QUADRAT_PRIMARY_KEY_COLUMN);
        double xCoord = quadratCursor.getDouble(SQLiteConstants.QUADRAT_X_COORDINATE_COLUMN);
        double yCoord = quadratCursor.getDouble(SQLiteConstants.QUADRAT_Y_COORDINATE_COLUMN);
        int standID = quadratCursor.getInt(SQLiteConstants.QUADRAT_STAND_ID_COLUMN);

        QuadratImage quadratImage = new QuadratImage(quadratId, new Coordinate(xCoord, yCoord), standID);

        for (TreeImage treeImage : getTreeImagesFromQuadrat(quadratId)){
            quadratImage.addTree(treeImage);
        }

        return quadratImage;
    }

    /**
     * Returns correct key to acces SQLite database given the common species rank
     *
     * @param rank - the position in the list of common species we are trying to get at
     * @return correct SQLite Key for the corresponding species data.
     */
    private String standSpeciesKey(int rank){
        switch (rank){
            case 1:
                return SQLiteConstants.STAND_SPECIES_1_KEY;
            case 2:
                return SQLiteConstants.STAND_SPECIES_2_KEY;
            case 3:
                return SQLiteConstants.STAND_SPECIES_3_KEY;
            case 4:
                return SQLiteConstants.STAND_SPECIES_4_KEY;
            case 5:
                return SQLiteConstants.STAND_SPECIES_5_KEY;
            default:
                throw new InvalidSpeciesRankException();
        }
    }

    private Cursor compileSelectionCursor(String tableName, String fieldKey, int value){
        String selectQuery = "SELECT * FROM " + tableName +
                " WHERE " + fieldKey + "=" + value + ";";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private Cursor compileRelativeSelectionCursor(String tableName, String parentIdKey, int parentId, int childIndex){
        String selectQuery = "SELECT * FROM " + tableName +
                " WHERE " + parentIdKey + "=" + parentId +
                " ORDER BY " + SQLiteConstants.KEY_PRIMARY +
                " LIMIT 1 OFFSET " + childIndex + ";";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, int value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                SQLiteConstants.KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, String value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                SQLiteConstants.KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, double value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                SQLiteConstants.KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }
}
