package com.example.drew.test1;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Mathieu Belzile-Ha on 15/03/2017.
 */

public abstract class DBPointer {

    private DatabaseOpenHelper dbHelper;
    private DBAddress address;

    public DBPointer(Context context, DBAddress address){
        this.dbHelper = new DatabaseOpenHelper(context);
        this.address = address;
    }

    protected DatabaseOpenHelper getDbHelper(){
        return this.dbHelper;
    }

    protected DBAddress getAddress(){
        return this.address;
    }

    protected abstract Cursor cursor();
}
