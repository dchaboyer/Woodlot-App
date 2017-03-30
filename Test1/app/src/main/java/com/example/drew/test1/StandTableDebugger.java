package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

public class StandTableDebugger extends SQLiteOpenHelper {


    //CONSTRUCTOR// -------------------------------------------------------
    StandTableDebugger(Context context) {
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
        database.execSQL(SQLiteConstants.STAND_TABLE_CREATE);
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
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.STAND_TABLE_NAME);
        onCreate(database);
    }

    public void reset(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + SQLiteConstants.STAND_TABLE_NAME);
        onCreate(database);
    }

    //MUTATORS// -----------------------------------------------------
    public void addStand(double area, int age, double height){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SQLiteConstants.STAND_AREA_KEY, area);
        values.put(SQLiteConstants.STAND_AGE_KEY, age);
        values.put(SQLiteConstants.STAND_HEIGHT_KEY, height);

        database.insert(SQLiteConstants.STAND_TABLE_NAME, null, values);
        database.close();
    }

    //ACCESSORS// ------------------------------------------------------
    public List<StandImage> dumpTable(){
        List<StandImage> standImages = new LinkedList<StandImage>();
        String selectQuery = "SELECT * FROM " + SQLiteConstants.STAND_TABLE_NAME + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            double area = cursor.getDouble(1);
            int age = cursor.getInt(2);
            double height = cursor.getDouble(3);
            standImages.add(new StandImage(id, area, age, height));
        }

        cursor.close();
        return standImages;
    }
}
