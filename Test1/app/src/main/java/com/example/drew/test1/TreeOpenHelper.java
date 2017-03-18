package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jen on 16/03/2017.
 */

public class TreeOpenHelper extends SQLiteOpenHelper {

    //DATABASE DATA NAMES// --------------------------------------------------
    private static final String NULL_VALUE = "\0";
    private static final String KEY_PRIMARY = "_id";
    private static final String DATABASE_NAME = "wcc.db";
    private static final int DATABASE_VERSION = 3;

    //TREE DATA NAMES// ------------------------------------------------------
    private static final String TREE_TABLE_NAME = "TREES";
    private static final String TREE_DBH_KEY = "DBH";
    private static final String TREE_SPECIES_KEY = "SPECIES";
    private static final String TREE_STORAGE_FACTOR_KEY = "STORAGE_FACTOR";
    private static final String TREE_MATERIAL_TYPE_KEY = "MATERIAL_TYPE";
    private static final String TREE_QUADRAT_ID_KEY = "QUADRAT_ID";
    private static final String TREE_TABLE_CREATE =
            "CREATE TABLE " + TREE_TABLE_NAME + " (" +
                    KEY_PRIMARY + " SERIAL PRIMARY KEY, " +
                    TREE_DBH_KEY + " DOUBLE PRECISION, " +
                    TREE_SPECIES_KEY + " TEXT, " +
                    TREE_STORAGE_FACTOR_KEY + " TEXT, " +
                    TREE_MATERIAL_TYPE_KEY + " TEXT, " +
                    TREE_QUADRAT_ID_KEY + "INT references QUADRATS(" + KEY_PRIMARY + ");";

    //CLASS VARIABLES// ---------------------------------------------------
    int id; //Referenced Tree ID

    //CONSTRUCTOR// -------------------------------------------------------
    TreeOpenHelper(Context context, int id) throws  TreeNotFoundException{
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.id = id;
        if(!this.exists()){
            throw new TreeNotFoundException();
        }

    }

    //OPENHELPER// --------------------------------------------------------
    /**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(TREE_TABLE_CREATE);
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
        database.execSQL("DROP TABLE IF EXISTS " + TREE_TABLE_NAME);
        onCreate(database);
    }
    public void addTreeTo(TreeImage tree, int id){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TREE_DBH_KEY, tree.getDbh());
        values.put(TREE_SPECIES_KEY, tree.getSpecies().getName());

        if (tree.getStorageFactor() == null) {
            values.put(TREE_STORAGE_FACTOR_KEY, NULL_VALUE);
        } else {
            values.put(TREE_STORAGE_FACTOR_KEY, tree.getStorageFactor().toString());
        }

        if (tree.getMaterialType() == null) {
            values.put(TREE_MATERIAL_TYPE_KEY, NULL_VALUE);
        } else {
            values.put(TREE_MATERIAL_TYPE_KEY, tree.getMaterialType().toString());
        }

        values.put(TREE_QUADRAT_ID_KEY, id);

        database.insert(TREE_TABLE_NAME, null, values);
        database.close();
    }

    //MUTATORS// -----------------------------------------------------
    public void setDbh(double dbh){
        String updateCommand = "UPDATE "+ TREE_TABLE_NAME + " SET " +
                TREE_DBH_KEY + "=" + dbh +
                " WHERE " + KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    public void setSpecies(Species species){
        if (species == null){
            return;
        }

        String updateCommand = "UPDATE "+ TREE_TABLE_NAME + " SET " +
                TREE_SPECIES_KEY + "=" + species.getName() +
                " WHERE " + KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    public void setStorageFactor(StorageFactor storageFactor){
        String updateCommand = "UPDATE "+ TREE_TABLE_NAME + " SET " +
                TREE_SPECIES_KEY + "=" + storageFactor.getName() +
                " WHERE " + KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    public void setMaterialType(MaterialType materialType){
        String updateCommand = "UPDATE "+ TREE_TABLE_NAME + " SET " +
                TREE_SPECIES_KEY + "=" + materialType.getName() +
                " WHERE " + KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    //ACCESSORS// ------------------------------------------------------
    public double getDbh(){
        String selectQuery = "SELECT * FROM " + TREE_TABLE_NAME +
                " WHERE " + KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        return cursor.getDouble(1);
    }

    //HELPERS// ------------------------------------------------------
    /**
     * Open for testing
     * @return
     */
    boolean exists(){
        String selectQuery = "SELECT EXISTS(SELECT * FROM " + TREE_TABLE_NAME +
                " WHERE " + KEY_PRIMARY + "=" + this.id + " LIMIT 1);";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        return cursor.getInt(0) == 1;
    }

}
