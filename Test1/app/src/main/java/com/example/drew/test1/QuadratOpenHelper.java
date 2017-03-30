package com.example.drew.test1;
import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Mathieu Belzile-Ha on 14/03/2017.
 *
 * QUADRAT OPEN HELPER
 *
 * CLASS WITH PURPOSE TO FACILITATE NAVIGATION OF QUADRAT TABLE.
 *
 */

//add compelted bool val
/**
public class QuadratOpenHelper extends DataBaseOpenHelper {

    //CONSTRUCTOR// -------------------------------------------------------
    QuadratOpenHelper(Context context){
        super(context);
    }

    //MUTATORS// -----------------------------------------------------
    public void addTreeToQuadrat(TreeImage tree, int quadratId){
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

    public void setQuadratCoordinates(Coordinate coordinates, int quadratId){
        String updateCommand = "UPDATE "+ SQLiteConstants.QUADRAT_TABLE_NAME + " SET " +
                SQLiteConstants.QUADRAT_X_COORDINATE_KEY + "=" + coordinates.getX() + ", " +
                SQLiteConstants.QUADRAT_Y_COORDINATE_KEY + "=" + coordinates.getY() +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    //ACCESSORS// ------------------------------------------------------
    public List<TreeImage> getTreeImagesFromQuadrat(int quadratId){
        String selectQuery = "SELECT * FROM " + SQLiteConstants.TREE_TABLE_NAME +
                " WHERE " + SQLiteConstants.TREE_QUADRAT_ID_KEY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<TreeImage> trees = new LinkedList<TreeImage>();

        while(cursor.moveToNext()){
            TreeImage tree = extractTreeImage(cursor);
            trees.add(tree);
        }

        cursor.close();
        return trees;
    }

    public TreeImage getTree(int relativeID, int quadratId){
        String selectQuery = "SELECT * FROM " + SQLiteConstants.TREE_TABLE_NAME +
                " WHERE " + SQLiteConstants.TREE_QUADRAT_ID_KEY + "=" + quadratId +
                " ORDER BY " + SQLiteConstants.KEY_PRIMARY +
                " LIMIT 1 OFFSET " + relativeID + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        TreeImage tree = extractTreeImage(cursor);

        cursor.close();
        return tree;
    }

    public Coordinate getQuadratCoordinates(int quadratId){
        String selectQuery = "SELECT * FROM " + SQLiteConstants.QUADRAT_TABLE_NAME +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + quadratId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

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
     * @param treeCursor
     * @return treeInfo
     *//**
    private TreeImage extractTreeImage(Cursor treeCursor){
        int id = treeCursor.getInt(SQLiteConstants.TREE_PRIMARY_KEY_COLUMN); //TODO: refactoring so all tree parsing is handled by another class
        double dbh = treeCursor.getDouble(SQLiteConstants.TREE_DBH_COLUMN);
        Species species = InputParser.parseSpecies(treeCursor.getString(SQLiteConstants.TREE_SPECIES_COLUMN));
        StorageFactor storageFactor = InputParser.parseStorageFactor(treeCursor.getString(SQLiteConstants.TREE_STORAGE_FACTOR_COLUMN));
        MaterialType materialType = InputParser.parseMaterialType(treeCursor.getString(SQLiteConstants.TREE_MATERIAL_TYPE_COLUMN));
        int quadratId = treeCursor.getInt(SQLiteConstants.TREE_QUADRAT_ID_COLUMN);

        return new TreeImage(dbh, species, storageFactor, materialType, id, quadratId);
    }
}*/