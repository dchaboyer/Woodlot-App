package com.example.drew.test1;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;

/**
 * Created by Mathieu Belzile-Ha on 14/03/2017.
 *
 * DATABASE OPEN HELPER
 *
 * CLASS WITH PURPOSE TO FACILITATE NAVIGATION OF THE SQLITE DATABASE.
 *
 */

public class XDatabaseOpenHelper extends SQLiteOpenHelper{

    //GENERAL DATA NAMES// ----------------------------------------------
    private static final String NULL_VALUE = "\0";
    private static final String KEY_PRIMARY = "ID";
    private static final String DATABASE_NAME = "wcc.db";
    private static final int DATABASE_VERSION = 1;

    //WOODLOT DATA NAMES// ----------------------------------------------
    private static final String WOODLOT_TABLE_NAME = "WOODLOTS";
    private static final String WOODLOT_NAME_KEY = "NAME";

    private static final String WOODLOT_TABLE_CREATE =
            "CREATE TABLE " + WOODLOT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " SERIAL PRIMARY KEY, " +
                    WOODLOT_NAME_KEY + " TEXT);";

    //STAND DATA NAMES// -----------------------------------------------
    private static final String STAND_TABLE_NAME = "STANDS";
    private static final String STAND_SPECIES_KEY = "SPECIES";
    private static final String STAND_AREA_KEY = "AREA";
    private static final String STAND_AGE_KEY = "AGE";
    private static final String STAND_HEIGHT_KEY = "HEIGHT";
    private static final String STAND_NOTES_KEY = "NOTES";

    private static final String STAND_TABLE_CREATE =
            "CREATE TABLE " + STAND_TABLE_NAME + " (" +
                    KEY_PRIMARY + " SERIAL PRIMARY KEY, " +
                    STAND_AREA_KEY + " DOUBLE PRECISION, " +
                    STAND_SPECIES_KEY + " TEXT, " +
                    STAND_AGE_KEY + " INT, " +
                    STAND_HEIGHT_KEY + " DOUBLE PRECISION, " +
                    STAND_NOTES_KEY + " TEXT);";

    //QUADRAT DATA NAMES// -------------------------------------------
    private static final String QUADRAT_TABLE_NAME = "QUADRATS";

    private static final String QUADRAT_TABLE_CREATE =
            "CREATE TABLE " + QUADRAT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " SERIAL PRIMARY KEY);";

    //TREE DATA NAMES// -----------------------------------------------
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

    //CONSTRUCTOR// ------------------------------------------------
    XDatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //SQLITE// ------------------------------------------------------
    /**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TREE_TABLE_CREATE);
        db.execSQL(STAND_TABLE_CREATE);
        db.execSQL(QUADRAT_TABLE_CREATE);
        db.execSQL(TREE_TABLE_CREATE);
    }

    /**
     * Method called database upgrade.
     * Right now, just drops current tables and creates new ones.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + WOODLOT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STAND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUADRAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TREE_TABLE_NAME);

        onCreate(db);
    }

    //MUTATORS// -----------------------------------------------
    public void addTree(Tree tree, int quadratId){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TREE_DBH_KEY, tree.getDbh());
        values.put(TREE_SPECIES_KEY, tree.getSpecies().getName());
        if (tree.getStorageFactor() == null)
        {
            values.put(TREE_STORAGE_FACTOR_KEY, NULL_VALUE);
        }
        else
        {
            values.put(TREE_STORAGE_FACTOR_KEY, tree.getStorageFactor().toString());
        }
        if (tree.getMaterialType() == null)
        {
            values.put(TREE_MATERIAL_TYPE_KEY, NULL_VALUE);
        }
        else
        {
            values.put(TREE_MATERIAL_TYPE_KEY, tree.getMaterialType().toString());
        }
        values.put(TREE_QUADRAT_ID_KEY, quadratId);

        database.insert(TREE_TABLE_NAME, null, values);
        database.close();
    }

}
