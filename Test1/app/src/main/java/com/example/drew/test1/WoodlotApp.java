package com.example.drew.test1;

import android.app.Application;

/**
 * Created by Jen on 25/02/2017.
 */

public class WoodlotApp extends Application{

    private DataBase database;

    @Override
    public void onCreate(){
        super.onCreate();
        database = new DataBase();
    }

    /**
     * Provides acces to main application database.
     * @return
     */
    public DataBase dataBase(){
        return this.database;
    }
}
