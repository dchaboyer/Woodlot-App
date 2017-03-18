package com.example.drew.test1;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Mathieu Belzile-Ha on 14/03/2017.
 *
 * WOODLOT OPEN HELPER
 *
 * CLASS WITH PURPOSE TO FACILITATE NAVIGATION OF WOODLOT TABLE.
 *
 */

public class WoodlotOpenHelper extends SQLiteOpenHelper{

    //CONSTANTS// -------------------------------------------------
    private static final String NULL_VALUE = "\0";
    private static final String KEY_PRIMARY = "ID";
    private static final String DATABASE_NAME = "wcc.db";
    private static final int DATABASE_VERSION = 1;
    private static final String WOODLOT_TABLE_NAME = "WOODLOTS";
    private static final String WOODLOT_NAME_KEY = "NAME";
    private static final String WOODLOT_TABLE_CREATE =
            "CREATE TABLE " + WOODLOT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " SERIAL PRIMARY KEY, " +
                    WOODLOT_NAME_KEY + " TEXT);";

    //CONSTRUCTOR// ------------------------------------------------
    WoodlotOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(WOODLOT_TABLE_CREATE);
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
        database.execSQL("DROP TABLE IF EXISTS " + WOODLOT_TABLE_NAME);
        onCreate(database);
    }

}
