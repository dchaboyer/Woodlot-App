package com.example.drew.test1;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Mathieu Belzile-Ha on 15/03/2017.
 */

public abstract class DBPointer {

    private XDatabaseOpenHelper dbHelper;
    private DBAddress address;

    public DBPointer(Context context, DBAddress address){
        this.dbHelper = new XDatabaseOpenHelper(context);
        this.address = address;
    }

    protected XDatabaseOpenHelper getDbHelper(){
        return this.dbHelper;
    }

    protected DBAddress getAddress(){
        return this.address;
    }

    protected abstract Cursor cursor();
}
