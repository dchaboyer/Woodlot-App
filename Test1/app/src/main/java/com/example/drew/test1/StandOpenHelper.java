package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Mathieu Belzile-Ha on 23/03/2017.
 *
 * STAND OPEN HELPER
 *
 * CLASS WITH PURPOSE TO FACILITATE NAVIGATION OF STAND TABLE.
 *
 */

public class StandOpenHelper extends DataBaseOpenHelper {

    private int id;

    //CONSTRUCTOR// -------------------------------------------------------
    StandOpenHelper(Context context, int id){
        super(context);
        this.id = id;
    }

    //MUTATORS// -----------------------------------------------------

    public void addQuadrat(QuadratImage quadratImage){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Coordinate coordinate = quadratImage.getCoordinate();

        values.put(SQLiteConstants.QUADRAT_X_COORDINATE_KEY, coordinate.getX());
        values.put(SQLiteConstants.QUADRAT_Y_COORDINATE_KEY, coordinate.getY());
        values.put(SQLiteConstants.QUADRAT_STAND_ID_KEY, this.id);

        database.insert(SQLiteConstants.QUADRAT_TABLE_NAME, null, values);
        database.close();
    }

    public void setSpecies(Species species, int rank){
        String updateCommand = "UPDATE "+ SQLiteConstants.STAND_TABLE_NAME+ " SET " +
                standSpeciesKey(rank) + "=" + species.getName() + ", " +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    public void setStandAge(int age){
        String updateCommand = "UPDATE "+ SQLiteConstants.STAND_TABLE_NAME+ " SET " +
                SQLiteConstants.STAND_AGE_KEY + "=" + age + ", " +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    public void setStandHeight(int height){
        String updateCommand = "UPDATE "+ SQLiteConstants.STAND_TABLE_NAME+ " SET " +
                SQLiteConstants.STAND_HEIGHT_KEY + "=" + height + ", " +
                " WHERE " + SQLiteConstants.KEY_PRIMARY + "=" + this.id + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    //ACCESSORS// ------------------------------------------------------

    //HELPERS// -------------------------------------------------------

    /**
     * Returns correct key to acces SQLite database given the common species rank
     *
     * @param rank - the position in the list of common species we are trying to get at
     * @return correct SQLite Key for the corresponding species data.
     */
    public String standSpeciesKey(int rank){
        switch (rank){
            case 1:
                return SQLiteConstants.STAND_SPECIES_1_KEY;
            case 2:
                return SQLiteConstants.STAND_SPECIES_2_KEY;
            case 3:
                return SQLiteConstants.STAND_SPECIES_3_KEY;
            case 4:
                return SQLiteConstants.STAND_SPECIES_4_KEY;
            case 5:
                return SQLiteConstants.STAND_SPECIES_5_KEY;
            default:
                throw new InvalidSpeciesRankException();
        }
    }
}
