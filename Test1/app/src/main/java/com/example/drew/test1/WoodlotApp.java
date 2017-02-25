package com.example.drew.test1;

import android.app.Application;

/**
 * WOODLOT APPLICATION
 *
 * Holds core data of the application.
 *
 * @author mbelzileha
 * Created by Jen on 25/02/2017.
 */

public class WoodlotApp extends Application{

    private DataBase database;

    //EVENT//------------------------------------------

    @Override
    public void onCreate(){
        super.onCreate();
        DataBaseBuilder dataBaseBuilder = new DataBaseBuilder();

        /*DEBUG*/ dataBaseBuilder.buildDebug();   /*DEBUG*/

        this.database = dataBaseBuilder.getResult();
    }

    //GET//--------------------------------------------

    /**
     * Gets main application database.
     * @return
     */
    public DataBase getDataBase(){
        return this.database;
    }
}
