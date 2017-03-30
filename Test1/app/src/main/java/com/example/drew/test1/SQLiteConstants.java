package com.example.drew.test1;

/**
 * Created by Mathieu Belzile-Ha on 16/03/2017.
 */

public class SQLiteConstants {

    //COMMANDS// -------------------------------------------------------------
    static final String ACTIVATE_FOREIGN_KEY_SUPPORT = "PRAGMA foreign_keys = ON;";

    //TYPE CODES// ------------------------------------------------------------
    static final int FIELD_TYPE_NULL = 0;
    static final int FIELD_TYPE_INTEGER = 1;
    static final int FIELD_TYPE_FLOAT = 2;
    static final int FIELD_TYPE_STRING = 3;
    static final int FIELD_TYPE_BLOB = 4;

    //DATABASE DATA NAMES// --------------------------------------------------
    static final String NULL_VALUE = "\0";
    static final String KEY_PRIMARY = "_id";
    static final String DATABASE_NAME = "wcc.db";
    static final int DATABASE_VERSION = 4;

    //WOODLOT DATA NAMES// ---------------------------------------------------
    static final String WOODLOT_TABLE_NAME = "WOODLOTS";
    static final String WOODLOT_NAME_KEY = "NAME";

    static final String WOODLOT_TABLE_CREATE =
            "CREATE TABLE " + WOODLOT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    WOODLOT_NAME_KEY + " STRING);";

    //STAND DATA NAMES// ---------------------------------------------------
    static final String STAND_TABLE_NAME = "QUADRATS";
    static final String STAND_SPECIES_1_KEY = "SPECIES_1";
    static final String STAND_SPECIES_2_KEY = "SPECIES_2";
    static final String STAND_SPECIES_3_KEY = "SPECIES_3";
    static final String STAND_SPECIES_4_KEY = "SPECIES_4";
    static final String STAND_SPECIES_5_KEY = "SPECIES_5";
    static final String STAND_AREA_KEY= "AREA";
    static final String STAND_AGE_KEY = "AGE";
    static final String STAND_HEIGHT_KEY = "KEY";
    static final String STAND_NOTES_KEY = "NOTES";
    static final String STAND_WOODLOT_ID_KEY = "WOODLOT_ID";

    static final String STAND_TABLE_CREATE =
            "CREATE TABLE " + STAND_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    STAND_SPECIES_1_KEY + " STRING, " +
                    STAND_SPECIES_2_KEY + " STRING, " +
                    STAND_SPECIES_3_KEY + " STRING, " +
                    STAND_SPECIES_4_KEY + " STRING, " +
                    STAND_SPECIES_5_KEY + " STRING, " +
                    STAND_AREA_KEY + " DOUBLE, " +
                    STAND_AGE_KEY + " INTEGER, " +
                    STAND_HEIGHT_KEY + " DOUBLE, " +
                    STAND_NOTES_KEY + " STRING, " +
                    STAND_WOODLOT_ID_KEY + " INTEGER, " +
                    "FOREIGN KEY(" + STAND_WOODLOT_ID_KEY + ") REFERENCES " +
                    WOODLOT_TABLE_NAME + "(" + KEY_PRIMARY + "));";

    static final int STAND_NUM_COLUMNS = 10;
    static final int STAND_PRIMARY_KEY_COLUMN = 0;
    static final int STAND_SPECIES_1_COLUMN = 1;
    static final int STAND_SPECIES_2_COLUMN = 2;
    static final int STAND_SPECIES_3_COLUMN = 3;
    static final int STAND_SPECIES_4_COLUMN = 4;
    static final int STAND_SPECIES_5_COLUMN = 5;
    static final int STAND_AREA_COLUMN = 6;
    static final int STAND_AGE_COLUMN = 7;
    static final int STAND_HEIGHT_COLUMN = 8;
    static final int STAND_NOTES_COLUMN = 9;

    static final int STAND_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    static final int STAND_SPECIES_1_TYPE = 1;
    static final int STAND_SPECIES_2_TYPE = 2;
    static final int STAND_SPECIES_3_TYPE = 3;
    static final int STAND_SPECIES_4_TYPE = 4;
    static final int STAND_SPECIES_5_TYPE = 5;
    static final int STAND_AREA_TYPE = 6;
    static final int STAND_AGE_TYPE = 7;
    static final int STAND_HEIGHT_TYPE = 8;
    static final int STAND_NOTES_TYPE = 9;

    //QUADRAT DATA NAMES// ---------------------------------------------------
    static final String QUADRAT_TABLE_NAME = "QUADRATS";
    static final String QUADRAT_X_COORDINATE_KEY= "X_COORDINATE";
    static final String QUADRAT_Y_COORDINATE_KEY= "Y_COORDINATE";
    static final String QUADRAT_STAND_ID_KEY = "STAND_ID";

    static final String QUADRAT_TABLE_CREATE =
            "CREATE TABLE " + QUADRAT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    QUADRAT_X_COORDINATE_KEY + " DOUBLE, " +
                    QUADRAT_Y_COORDINATE_KEY + " DOUBLE, " +
                    QUADRAT_STAND_ID_KEY + " INTEGER, " +
                    "FOREIGN KEY(" + QUADRAT_STAND_ID_KEY + ") REFERENCES " +
                    STAND_TABLE_NAME + "(" + KEY_PRIMARY + "));";

    static final int QUADRAT_NUM_COLUMNS = 4;
    static final int QUADRAT_PRIMARY_KEY_COLUMN = 0;
    static final int QUADRAT_X_COORDINATE_COLUMN = 1;
    static final int QUADRAT_Y_COORDINATE_COLUMN = 2;
    static final int QUADRAT_STAND_ID_COLUMN = 3;

    static final int QUADRAT_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    static final int QUADRAT_X_COORDINATE_TYPE = FIELD_TYPE_FLOAT;
    static final int QUADRAT_Y_COORDINATE_TYPE = FIELD_TYPE_FLOAT;

    //TREE DATA NAMES// ------------------------------------------------------
    static final String TREE_TABLE_NAME = "TREES";
    static final String TREE_DBH_KEY = "DBH";
    static final String TREE_SPECIES_KEY = "SPECIES";
    static final String TREE_STORAGE_FACTOR_KEY = "STORAGE_FACTOR";
    static final String TREE_MATERIAL_TYPE_KEY = "MATERIAL_TYPE";
    static final String TREE_QUADRAT_ID_KEY = "QUADRAT_ID";
    static final String TREE_TABLE_CREATE =
            "CREATE TABLE " + TREE_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    TREE_DBH_KEY + " DOUBLE PRECISION, " +
                    TREE_SPECIES_KEY + " TEXT, " +
                    TREE_STORAGE_FACTOR_KEY + " TEXT, " +
                    TREE_MATERIAL_TYPE_KEY + " TEXT," +
                    TREE_QUADRAT_ID_KEY + " INTEGER, " +
                    "FOREIGN KEY(" + TREE_QUADRAT_ID_KEY + ") REFERENCES " +
                    QUADRAT_TABLE_NAME + "(" + KEY_PRIMARY + "));";

    static final int TREE_NUM_COLUMNS = 6;
    static final int TREE_PRIMARY_KEY_COLUMN = 0;
    static final int TREE_DBH_COLUMN = 1;
    static final int TREE_SPECIES_COLUMN = 2;
    static final int TREE_STORAGE_FACTOR_COLUMN = 3;
    static final int TREE_MATERIAL_TYPE_COLUMN = 4;
    static final int TREE_QUADRAT_ID_COLUMN = 5;

    static final int TREE_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    static final int TREE_DBH_TYPE = FIELD_TYPE_FLOAT;
    static final int TREE_SPECIES_TYPE = FIELD_TYPE_STRING;
    static final int TREE_STORAGE_FACTOR_TYPE = FIELD_TYPE_STRING;
    static final int TREE_MATERIAL_TYPE_TYPE = FIELD_TYPE_STRING;
    static final int TREE_QUADRAT_ID_TYPE = FIELD_TYPE_INTEGER;

    //TODO: REFACTOR TO SPLIT INTO OTHER CLASSES OR CLEARER ON ACCESS
}
