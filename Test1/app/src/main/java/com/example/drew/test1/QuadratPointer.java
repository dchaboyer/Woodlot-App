package com.example.drew.test1;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by Jen on 15/03/2017.
 */

public class QuadratPointer extends DBPointer {

    public QuadratPointer(Context context, DBAddress address){
        super(context, address);
    }

    public List<Tree> getTreeSummaries(){
        List<Tree> trees = new LinkedList<Tree>();
        Cursor cursor = this.cursor();

        while(cursor.moveToNext()){
            Tree tree = new Tree();
            tree.setDbh(cursor.getDouble(1));
            tree.setSpecies(InputParser.parseSpecies(cursor.getString(2)));
            tree.setStorageFactor(InputParser.parseStorageFactor(cursor.getString(3)));
            tree.setMaterialType(InputParser.parseMaterialType(cursor.getString(4)));

            trees.add(tree);
        }

        cursor.close();
        return trees;
    }

    @Override
    protected Cursor cursor() {
        String selectQuery = "SELECT * FROM trees";
        SQLiteDatabase database = this.getDbHelper().getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }
}
