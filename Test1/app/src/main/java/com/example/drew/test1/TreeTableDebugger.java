/*
package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

*/
/**
 * Created by Mathieu Belzile-Ha on 16/03/2017.
 *//*


public class TreeTableDebugger extends SQLiteOpenHelper{
    //CONSTRUCTOR// -------------------------------------------------------
    TreeTableDebugger(Context context) {
        super(context, SQLiteConstants.DATABASE_NAME, null, SQLiteConstants.DATABASE_VERSION);
    }

    //OPENHELPER// --------------------------------------------------------
    */
/**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param database
     *//*

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(SQLiteConstants.TREE_TABLE_CREATE);
    }

    */
/**
     * Method called on database upgrade.
     * Right now, just drops current tables and creates new ones.
     * @param database
     * @param oldVersion
     * @param newVersion
     *//*

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.TREE_TABLE_NAME);
        onCreate(database);
    }

    public void reset(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.TREE_TABLE_NAME);
        onCreate(database);
    }

    public void addTree(TreeImage tree, int quadratId){

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

    //ACCESSORS// ------------------------------------------------------
    public List<TreeImage> getTrees(){
        List<TreeImage> trees = new LinkedList<TreeImage>();
        String selectQuery = "SELECT * FROM " + SQLiteConstants.TREE_TABLE_NAME + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){
            double dbh = cursor.getDouble(1);
            Species species = InputParser.parseSpecies(cursor.getString(2));
            StorageFactor storageFactor = InputParser.parseStorageFactor(cursor.getString(3));
            MaterialType materialType = InputParser.parseMaterialType(cursor.getString(4));
            int id = cursor.getInt(0);
            int parentId = cursor.getInt(5);
            TreeImage tree = new TreeImage(dbh, species, storageFactor, materialType, id, parentId);

            trees.add(tree);
        }

        cursor.close();
        return trees;
    }

    //HELPERS// ------------------------------------------------------
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL(SQLiteConstants.ACTIVATE_FOREIGN_KEY_SUPPORT);
        }
    }
}*/
