package com.example.drew.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu on 23/03/2017.
 *
 * WCCC OPEN HELPER
 *
 * Class with role to facilitate communication with the SQLite database.
 *
 */

//TODO: put items in order before returning them in a list (in methods like getTreeImagesInQuadrat)
//TODO: fix places where item info is not accessible by id, this will help clean rest of code
    //TODO: either add mutators/accessors for COMPLETE in Quadrat or remove all of them for other things

public class DataBaseOpenHelper extends SQLiteOpenHelper{

    private static final long INVALID_ID = -1; //Value to pass when an id is not valid
    private static final int TRUE_INTEGER_VALUE = 1;
    private static final int FALSE_INTEGER_VALUE = 0;

    //COMMANDS// -------------------------------------------------------------
    private static final String ACTIVATE_FOREIGN_KEY_SUPPORT = "PRAGMA foreign_keys = ON;";
    private static final String DISABLE_FOREIGN_KEY_SUPPORT = "PRAGMA foreign_keys = OFF;";

    //TYPE CODES// ------------------------------------------------------------
    private static final int FIELD_TYPE_NULL = 0;
    private static final int FIELD_TYPE_INTEGER = 1;
    private static final int FIELD_TYPE_FLOAT = 2;
    private static final int FIELD_TYPE_STRING = 3;
    private static final int FIELD_TYPE_BLOB = 4;

    //DATABASE DATA NAMES// --------------------------------------------------
    private static final String NULL_VALUE = "\0";
    private static final String KEY_PRIMARY = "_id";
    private static final String DATABASE_NAME = "wcc.db";
    private static final int DATABASE_VERSION = 8;

    //WOODLOT DATA NAMES// ---------------------------------------------------
    private static final String WOODLOT_TABLE_NAME = "WOODLOTS";
    private static final String WOODLOT_NAME_KEY = "NAME";
    private static final int WOODLOT_NUM_COLUMNS = 2;
    private static final int WOODLOT_PRIMARY_KEY_COLUMN = 0;
    private static final int WOODLOT_NAME_COLUMN = 1;
    //STAND DATA NAMES// ---------------------------------------------------
    private static final String STAND_TABLE_NAME = "STANDS";
    private static final String STAND_SPECIES_1_KEY = "SPECIES_1";
    private static final String STAND_SPECIES_2_KEY = "SPECIES_2";
    private static final String STAND_SPECIES_3_KEY = "SPECIES_3";
    private static final String STAND_SPECIES_4_KEY = "SPECIES_4";
    private static final String STAND_SPECIES_5_KEY = "SPECIES_5";
    private static final String STAND_AREA_KEY= "AREA";
    private static final String STAND_AGE_KEY = "AGE";
    private static final String STAND_HEIGHT_KEY = "KEY";
    private static final String STAND_NOTES_KEY = "NOTES";
    private static final String STAND_WOODLOT_ID_KEY = "WOODLOT_ID";

    private static final int STAND_NUM_COLUMNS = 11;
    private static final int STAND_PRIMARY_KEY_COLUMN = 0;
    private static final int STAND_SPECIES_1_COLUMN = 1;
    private static final int STAND_SPECIES_2_COLUMN = 2;
    private static final int STAND_SPECIES_3_COLUMN = 3;
    private static final int STAND_SPECIES_4_COLUMN = 4;
    private static final int STAND_SPECIES_5_COLUMN = 5;
    private static final int STAND_AREA_COLUMN = 6;
    private static final int STAND_AGE_COLUMN = 7;
    private static final int STAND_HEIGHT_COLUMN = 8;
    private static final int STAND_NOTES_COLUMN = 9;
    private static final int STAND_WOODLOT_ID_KEY_COLUMN = 10;

    private static final int STAND_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    private static final int STAND_SPECIES_1_TYPE = 1;
    private static final int STAND_SPECIES_2_TYPE = 2;
    private static final int STAND_SPECIES_3_TYPE = 3;
    private static final int STAND_SPECIES_4_TYPE = 4;
    private static final int STAND_SPECIES_5_TYPE = 5;
    private static final int STAND_AREA_TYPE = 6;
    private static final int STAND_AGE_TYPE = 7;
    private static final int STAND_HEIGHT_TYPE = 8;
    private static final int STAND_NOTES_TYPE = 9;

    //QUADRAT DATA NAMES// ---------------------------------------------------
    private static final String QUADRAT_TABLE_NAME = "QUADRATS";
    private static final String QUADRAT_X_COORDINATE_KEY= "X_COORDINATE";
    private static final String QUADRAT_Y_COORDINATE_KEY= "Y_COORDINATE";
    private static final String QUADRAT_COMPLETE_KEY = "COMPLETE";
    private static final String QUADRAT_STAND_ID_KEY = "STAND_ID";
    private static final int QUADRAT_NUM_COLUMNS = 5;
    private static final int QUADRAT_PRIMARY_KEY_COLUMN = 0;
    private static final int QUADRAT_X_COORDINATE_COLUMN = 1;
    private static final int QUADRAT_Y_COORDINATE_COLUMN = 2;
    private static final int QUADRAT_COMPLETE_COLUMN = 3;
    private static final int QUADRAT_STAND_ID_COLUMN = 4;

    private static final int QUADRAT_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    private static final int QUADRAT_X_COORDINATE_TYPE = FIELD_TYPE_FLOAT;
    private static final int QUADRAT_Y_COORDINATE_TYPE = FIELD_TYPE_FLOAT;
    private static final int QUADRAT_COMPLETE_COLUMN_TYPE = FIELD_TYPE_INTEGER;

    //TREE DATA NAMES// ------------------------------------------------------
    private static final String TREE_TABLE_NAME = "TREES";
    private static final String TREE_DBH_KEY = "DBH";
    private static final String TREE_SPECIES_KEY = "SPECIES";
    private static final String TREE_STORAGE_FACTOR_KEY = "STORAGE_FACTOR";
    private static final String TREE_MATERIAL_TYPE_KEY = "MATERIAL_TYPE";
    private static final String TREE_QUADRAT_ID_KEY = "QUADRAT_ID";

    private static final int TREE_NUM_COLUMNS = 6;
    private static final int TREE_PRIMARY_KEY_COLUMN = 0;
    private static final int TREE_DBH_COLUMN = 1;
    private static final int TREE_SPECIES_COLUMN = 2;
    private static final int TREE_STORAGE_FACTOR_COLUMN = 3;
    private static final int TREE_MATERIAL_TYPE_COLUMN = 4;
    private static final int TREE_QUADRAT_ID_COLUMN = 5;

    private static final int TREE_PRIMARY_KEY_TYPE = FIELD_TYPE_INTEGER;
    private static final int TREE_DBH_TYPE = FIELD_TYPE_FLOAT;
    private static final int TREE_SPECIES_TYPE = FIELD_TYPE_STRING;
    private static final int TREE_STORAGE_FACTOR_TYPE = FIELD_TYPE_STRING;
    private static final int TREE_MATERIAL_TYPE_TYPE = FIELD_TYPE_STRING;
    private static final int TREE_QUADRAT_ID_TYPE = FIELD_TYPE_INTEGER;

    private static final String WOODLOT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + WOODLOT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    WOODLOT_NAME_KEY + " STRING);";

    private static final String WOODLOT_ON_DELETE_TRIGGER =
            "CREATE TRIGGER delete_stands_with_woodlot BEFORE DELETE " +
                    "ON " + WOODLOT_TABLE_NAME + "\n" +
                    "BEGIN\n"+
                    "DELETE FROM " + STAND_TABLE_NAME +
                    " WHERE " + STAND_WOODLOT_ID_KEY + " = OLD._id;" +
                    "\nEND;";

    private static final String STAND_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + STAND_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    STAND_SPECIES_1_KEY + " STRING, " +
                    STAND_SPECIES_2_KEY + " STRING, " +
                    STAND_SPECIES_3_KEY + " STRING, " +
                    STAND_SPECIES_4_KEY + " STRING, " +
                    STAND_SPECIES_5_KEY + " STRING, " +
                    STAND_AREA_KEY + " DOUBLE, " +
                    STAND_AGE_KEY + " INTEGER, " +
                    STAND_HEIGHT_KEY + " DOUBLE, " +
                    STAND_NOTES_KEY + " STRING NOT NULL DEFAULT '', " +
                    STAND_WOODLOT_ID_KEY + " INTEGER, " +
                    "FOREIGN KEY(" + STAND_WOODLOT_ID_KEY + ") REFERENCES " +
                    WOODLOT_TABLE_NAME + "(" + KEY_PRIMARY + "));";

    private static final String STAND_ON_DELETE_TRIGGER =
            "CREATE TRIGGER delete_quadrats_with_stand BEFORE DELETE " +
                    "ON " + STAND_TABLE_NAME + "\n" +
                    "BEGIN\n"+
                    "DELETE FROM " + QUADRAT_TABLE_NAME +
                    " WHERE " + QUADRAT_STAND_ID_KEY + " = OLD._id;" +
                    "\nEND;";

    private static final String QUADRAT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + QUADRAT_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    QUADRAT_X_COORDINATE_KEY + " DOUBLE, " +
                    QUADRAT_Y_COORDINATE_KEY + " DOUBLE, " +
                    QUADRAT_COMPLETE_KEY + " INTEGER DEFAULT 0, " +
                    QUADRAT_STAND_ID_KEY + " INTEGER, " + //TODO: not tested either
                    "FOREIGN KEY(" + QUADRAT_STAND_ID_KEY + ") REFERENCES " +
                    STAND_TABLE_NAME + "(" + KEY_PRIMARY + "));";

    private static final String QUADRAT_ON_DELETE_TRIGGER =
                    "CREATE TRIGGER delete_trees_with_quadrat BEFORE DELETE " +
                    "ON " + QUADRAT_TABLE_NAME + "\n" +
                    "BEGIN\n"+
                    "DELETE FROM " + TREE_TABLE_NAME +
                    " WHERE " + TREE_QUADRAT_ID_KEY + " = OLD._id;" +
                    "\nEND;";

    private static final String TREE_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TREE_TABLE_NAME + " (" +
                    KEY_PRIMARY + " INTEGER PRIMARY KEY, " +
                    TREE_DBH_KEY + " DOUBLE PRECISION, " +
                    TREE_SPECIES_KEY + " TEXT, " +
                    TREE_STORAGE_FACTOR_KEY + " TEXT, " +
                    TREE_MATERIAL_TYPE_KEY + " TEXT," +
                    TREE_QUADRAT_ID_KEY + " INTEGER, " +
                    "FOREIGN KEY(" + TREE_QUADRAT_ID_KEY + ") REFERENCES " +
                    QUADRAT_TABLE_NAME + "(" + KEY_PRIMARY + "));";


    //CONSTRUCTOR// -------------------------------------------------------
    DataBaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //OPENHELPER// --------------------------------------------------------
    /**
     * Method called whenever the database is created.
     * Initializes the four important tables.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(WOODLOT_TABLE_CREATE);
        database.execSQL(STAND_TABLE_CREATE);
        database.execSQL(QUADRAT_TABLE_CREATE);
        database.execSQL(TREE_TABLE_CREATE);
        database.execSQL(QUADRAT_ON_DELETE_TRIGGER);
        database.execSQL(STAND_ON_DELETE_TRIGGER);
        database.execSQL(WOODLOT_ON_DELETE_TRIGGER);
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
        database.execSQL("DROP TABLE IF EXISTS " + TREE_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + QUADRAT_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + STAND_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + WOODLOT_TABLE_NAME);
        onCreate(database);
    }

    //MUTATORS// -----------------------------------------------------

    public void addWoodlotToDataBase(WoodlotImage woodlotImage){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(WOODLOT_NAME_KEY, woodlotImage.getName());

        long woodlotId = INVALID_ID;

        if (woodlotImage.getId() == null) {
            woodlotId = database.insert(WOODLOT_TABLE_NAME, null, values);
        } else {
            int affectedRows = database.update(WOODLOT_TABLE_NAME, values, KEY_PRIMARY + "= ?",
                    new String[]{Integer.toString(woodlotImage.getId())});
            if (affectedRows > 0){
                woodlotId = woodlotImage.getId();
            }
        }

        if (woodlotId < 0){
            throw new WoodlotInsertFailure();
        }

        for (StandImage standImage : woodlotImage.getStandImages()){
            addStandToWoodlot(standImage, woodlotId);
        }

        database.close();
    }

    public void addStandToWoodlot(StandImage standImage, long woodlotId){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(STAND_AREA_KEY, standImage.getArea());
        values.put(STAND_AGE_KEY, standImage.getAge());
        values.put(STAND_HEIGHT_KEY, standImage.getHeight());
        values.put(STAND_NOTES_KEY, standImage.getNotes());
        values.put(STAND_WOODLOT_ID_KEY, woodlotId);
        for (int i = 1; i <= 5; i++){
            if (standImage.getCommonSpecies(i) == null){
                values.putNull(standSpeciesKey(i));
            } else {
                values.put(standSpeciesKey(i), standImage.getCommonSpecies(i).getName());
            }
        }

        long standId = INVALID_ID;

        if (standImage.getId() == null) {
            standId = database.insert(STAND_TABLE_NAME, null, values);
        } else if (exists(STAND_TABLE_NAME, KEY_PRIMARY, Integer.toString(standImage.getId()),
                STAND_WOODLOT_ID_KEY, Long.toString(woodlotId))) {
            int affectedRows = database.update(STAND_TABLE_NAME, values, KEY_PRIMARY + "= ?",
                    new String[]{Integer.toString(standImage.getId())});
            if (affectedRows > 0){
                standId = standImage.getId();
            }
        } else {
            standId = database.insert(STAND_TABLE_NAME, null, values);
        }

        if (standId < 0){
            throw new StandInsertFailureException();
        }

        for (QuadratImage quadratImage : standImage.getQuadratImages()){
            addQuadratToStand(quadratImage, standId);
        }

        database.close();
    }

    public void addTreeToQuadrat(TreeImage tree, long quadratId){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TREE_DBH_KEY, tree.getDbh());
        values.put(TREE_SPECIES_KEY, tree.getSpecies().getName());

        if (tree.getStorageFactor() == null) {
            values.put(TREE_STORAGE_FACTOR_KEY, NULL_VALUE);
        } else {
            values.put(TREE_STORAGE_FACTOR_KEY, tree.getStorageFactor().getName());
        }

        if (tree.getMaterialType() == null) {
            values.put(TREE_MATERIAL_TYPE_KEY, NULL_VALUE);
        } else {
            values.put(TREE_MATERIAL_TYPE_KEY, tree.getMaterialType().getName());
        }

        values.put(TREE_QUADRAT_ID_KEY, quadratId);

        if (tree.getId() == null) {
            database.insert(TREE_TABLE_NAME, null, values);
            database.close();
            return;
        }

        if (exists(TREE_TABLE_NAME, KEY_PRIMARY, Integer.toString(tree.getId()),
                                    TREE_QUADRAT_ID_KEY, Long.toString(quadratId))) {
            database.update(TREE_TABLE_NAME, values, KEY_PRIMARY + "= ?",
                    new String[]{Integer.toString(tree.getId())});
        } else {
            database.insert(TREE_TABLE_NAME, null, values);
        }

        database.close();
    }

    public void setQuadratCoordinates(Coordinate coordinates, int quadratId){
        execUpdate(QUADRAT_TABLE_NAME, quadratId,
                QUADRAT_X_COORDINATE_KEY, coordinates.getX());
        execUpdate(QUADRAT_TABLE_NAME, quadratId,
                QUADRAT_Y_COORDINATE_KEY, coordinates.getY());
    }

    public void setQuadratCompletionStatus(boolean complete, int quadratId){
        if (complete){
            execUpdate(QUADRAT_TABLE_NAME, quadratId,
                    QUADRAT_COMPLETE_KEY, TRUE_INTEGER_VALUE);
        } else {
            execUpdate(QUADRAT_TABLE_NAME, quadratId,
                    QUADRAT_COMPLETE_KEY, FALSE_INTEGER_VALUE);
        }
    }

    public void addQuadratToStand(QuadratImage quadratImage, long standId){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Coordinate coordinate = quadratImage.getCoordinate();

        values.put(QUADRAT_X_COORDINATE_KEY, coordinate.getX());
        values.put(QUADRAT_Y_COORDINATE_KEY, coordinate.getY());

        if (quadratImage.isComplete()) {
            values.put(QUADRAT_COMPLETE_KEY, TRUE_INTEGER_VALUE);
        } else {
            values.put(QUADRAT_COMPLETE_KEY, FALSE_INTEGER_VALUE);
        }

        values.put(QUADRAT_STAND_ID_KEY, standId);

        long quadratId = INVALID_ID;

        if (quadratImage.getId() == null) {
            quadratId = database.insert(QUADRAT_TABLE_NAME, null, values);
        } else if (exists(QUADRAT_TABLE_NAME, KEY_PRIMARY, Integer.toString(quadratImage.getId()),
                QUADRAT_STAND_ID_KEY, Long.toString(standId))) {
            int affectedRows = database.update(QUADRAT_TABLE_NAME, values, KEY_PRIMARY + "= ?",
                    new String[]{Integer.toString(quadratImage.getId())});
            if (affectedRows > 0){
                quadratId = quadratImage.getId();
            }
        } else {
            quadratId = database.insert(QUADRAT_TABLE_NAME, null, values);
        }

        if (quadratId < 0){
            throw new QuadratInsertFailureException();
        }

        for (TreeImage treeImage : quadratImage.getTrees()){
            addTreeToQuadrat(treeImage, quadratId);
        }

        database.close();
    }

    public void setStandSpecies(Species species, int rank, int standId){
        execUpdate(STAND_TABLE_NAME, standId,
                standSpeciesKey(rank), species.getName());
    }

    public void setStandArea(double area, int standId){
        execUpdate(STAND_TABLE_NAME, standId,
                STAND_AREA_KEY, area);
    }

    public void setStandAge(int age, int standId){
        execUpdate(STAND_TABLE_NAME, standId,
                STAND_AGE_KEY, age);
    }

    public void setStandHeight(double height, int standId){
        execUpdate(STAND_TABLE_NAME, standId,
                STAND_HEIGHT_KEY, height);
    }

    public void setStandNotes(String notes, int standId){
        execUpdate(STAND_TABLE_NAME, standId,
                STAND_NOTES_KEY, notes);
    }

    //ACCESSORS// ------------------------------------------------------
    public List<WoodlotImage> getWoodlotImagesFromDataBase() {
        Cursor cursor = compileTableDumpCursor(WOODLOT_TABLE_NAME);

        List<WoodlotImage> woodlotImages = new LinkedList<WoodlotImage>();

        while (cursor.moveToNext()) {
            WoodlotImage woodlotImage = extractWoodlotImage(cursor);
            woodlotImages.add(woodlotImage);
        }

        cursor.close();
        return woodlotImages;
    }

    public WoodlotImage getWoodlotImageFromDataBase(int id) {
        Cursor cursor = compileIdSelectionCursor(WOODLOT_TABLE_NAME, id);

        if (!cursor.moveToFirst()) {
            throw new WoodlotNotFoundException();
        }

        WoodlotImage woodlotImage = extractWoodlotImage(cursor);

        cursor.close();
        return woodlotImage;
    }

    public void removeWoodlotFromDataBase(int id) {
        execDelete(WOODLOT_TABLE_NAME, id);
    }

    public int getNumWoodlotsInDataBase() {
        return execGetNumRows(WOODLOT_TABLE_NAME);
    }

    public List<StandImage> getStandImagesFromWoodlot(int woodlotId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                                                STAND_WOODLOT_ID_KEY, woodlotId);

        List<StandImage> standImages = new LinkedList<StandImage>();

        while(cursor.moveToNext()){
            StandImage standImage = extractStandImage(cursor);
            standImages.add(standImage);
        }

        cursor.close();
        return standImages;
    }

    public StandImage getStandImageFromWoodlot(int relativeID, int woodlotId) {
        Cursor cursor = compileRelativeSelectionCursor(STAND_TABLE_NAME,
                STAND_WOODLOT_ID_KEY,
                woodlotId, relativeID);

        cursor.moveToFirst();
        StandImage standImage = extractStandImage(cursor);

        cursor.close();
        return standImage;
    }

    public void removeStandFromWoodlot(int relativeID, int woodlotId) {
        execRelativeDelete(STAND_TABLE_NAME, STAND_WOODLOT_ID_KEY, woodlotId, relativeID);
    }

    public int getNumStandsInWoodlot(int woodlotId) {
        return execGetNumChildren(STAND_TABLE_NAME,STAND_WOODLOT_ID_KEY,woodlotId);
    }

    public List<TreeImage> getTreeImagesFromQuadrat(int quadratId){
        Cursor cursor = compileSelectionCursor(TREE_TABLE_NAME,
                                                TREE_QUADRAT_ID_KEY, quadratId);

        List<TreeImage> treeImages = new LinkedList<TreeImage>();

        while(cursor.moveToNext()){
            TreeImage treeImage = extractTreeImage(cursor);
            treeImages.add(treeImage);
        }

        cursor.close();
        return treeImages;
    }

    public TreeImage getTreeImageFromQuadrat(int relativeID, int quadratId){
        Cursor cursor = compileRelativeSelectionCursor(TREE_TABLE_NAME,
                                                        TREE_QUADRAT_ID_KEY,
                                                        quadratId, relativeID);

        cursor.moveToFirst();
        TreeImage treeImage = extractTreeImage(cursor);

        cursor.close();
        return treeImage;
    }

    public void removeQuadratFromStand(int relativeID, int standId) {
        execRelativeDelete(QUADRAT_TABLE_NAME, QUADRAT_STAND_ID_KEY, standId, relativeID);
    }

    public int getNumQuadratsInStand(int standId){
        return execGetNumChildren(QUADRAT_TABLE_NAME, QUADRAT_STAND_ID_KEY, standId);
    }

    public void removeTreeFromQuadrat(int relativeID, int quadratId){
        execRelativeDelete(TREE_TABLE_NAME, TREE_QUADRAT_ID_KEY, quadratId, relativeID);
    }

    public int getNumTreesInQuadrat(int quadratId){
        return execGetNumChildren(TREE_TABLE_NAME, TREE_QUADRAT_ID_KEY, quadratId);
    }

    public QuadratImage getQuadratImageFromStand(int relativeId, int standId) {
        Cursor cursor = compileRelativeSelectionCursor(QUADRAT_TABLE_NAME,
                QUADRAT_STAND_ID_KEY,
                standId, relativeId);

        cursor.moveToFirst();
        QuadratImage quadratImage = extractQuadratImage(cursor);

        cursor.close();

        return quadratImage;
    }

    public List<QuadratImage> getQuadratImagesFromStand(int standId) {
        Cursor cursor = compileSelectionCursor(QUADRAT_TABLE_NAME,
                QUADRAT_STAND_ID_KEY,
                standId);

        LinkedList<QuadratImage> quadratImages = new LinkedList<QuadratImage>();

        while(cursor.moveToNext()){
            QuadratImage quadratImage = extractQuadratImage(cursor);
            quadratImages.add(quadratImage);
        }

        cursor.close();
        return quadratImages;
    }

    public Coordinate getQuadratCoordinates(int quadratId){
        Cursor cursor = compileSelectionCursor(QUADRAT_TABLE_NAME,
                                                KEY_PRIMARY, quadratId);

        cursor.moveToFirst();
        double x = cursor.getDouble(QUADRAT_X_COORDINATE_COLUMN);
        double y = cursor.getDouble(QUADRAT_Y_COORDINATE_COLUMN);

        cursor.close();
        return new Coordinate(x,y);
    }

    public boolean getQuadratCompletionStatus(int quadratId){
        Cursor cursor = compileSelectionCursor(QUADRAT_TABLE_NAME,
                KEY_PRIMARY, quadratId);

        cursor.moveToFirst();
        int intVal = cursor.getInt(QUADRAT_COMPLETE_COLUMN);

        cursor.close();
        return intVal == 1;
    } //TODO: testing required

    public int getQuadratIdFromStand(int relativeId, int standId){
        Cursor cursor = compileRelativeSelectionCursor(QUADRAT_TABLE_NAME,
                QUADRAT_STAND_ID_KEY,
                standId, relativeId);

        cursor.moveToFirst();
        int id = cursor.getInt(QUADRAT_PRIMARY_KEY_COLUMN);

        cursor.close();
        return id;
    } //TODO: testing required

    public String getWoodlotName(int woodlotId){
        Cursor cursor = compileSelectionCursor(WOODLOT_TABLE_NAME,
                KEY_PRIMARY, woodlotId);

        cursor.moveToFirst();
        String name = cursor.getString(WOODLOT_NAME_COLUMN);

        cursor.close();
        return name;
    } //TODO: testing required

    public double getStandArea(int standId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                KEY_PRIMARY, standId);

        cursor.moveToFirst();
        double area = cursor.getDouble(STAND_AREA_COLUMN);

        cursor.close();
        return area;
    }

    public int getStandAge(int standId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                KEY_PRIMARY, standId);

        cursor.moveToFirst();
        int age = cursor.getInt(STAND_AGE_COLUMN);

        cursor.close();
        return age;
    }

    public double getStandHeight(int standId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                KEY_PRIMARY, standId);

        cursor.moveToFirst();
        double height = cursor.getDouble(STAND_HEIGHT_COLUMN);

        cursor.close();
        return height;
    }

    public int getStandIdFromWoodlot(int relativeId, int woodlotId){
        Cursor cursor = compileRelativeSelectionCursor(STAND_TABLE_NAME,
                STAND_WOODLOT_ID_KEY,
                woodlotId, relativeId);

        cursor.moveToFirst();
        int id = cursor.getInt(STAND_PRIMARY_KEY_COLUMN);

        cursor.close();
        return id;
    } //TODO: testing required

    public int getTreeIdFromQuadrat(int relativeId, int quadratId){
        Cursor cursor = compileRelativeSelectionCursor(TREE_TABLE_NAME,
                TREE_QUADRAT_ID_KEY,
                quadratId, relativeId);

        cursor.moveToFirst();
        int id = cursor.getInt(TREE_PRIMARY_KEY_COLUMN);

        cursor.close();
        return id;
    } //TODO: testing required

    public String getStandNotes(int standId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                KEY_PRIMARY, standId);

        cursor.moveToFirst();
        String notes = cursor.getString(STAND_NOTES_COLUMN);

        cursor.close();
        return notes;
    }

    public ArrayList<Species> getStandCommonSpecies(int standId){
        Cursor cursor = compileSelectionCursor(STAND_TABLE_NAME,
                KEY_PRIMARY, standId);

        cursor.moveToFirst();
        ArrayList<Species> commonSpecies = new ArrayList<Species>();

        commonSpecies.add(InputParser.parseSpecies(cursor.getString(STAND_SPECIES_1_COLUMN)));
        commonSpecies.add(InputParser.parseSpecies(cursor.getString(STAND_SPECIES_2_COLUMN)));
        commonSpecies.add(InputParser.parseSpecies(cursor.getString(STAND_SPECIES_3_COLUMN)));
        commonSpecies.add(InputParser.parseSpecies(cursor.getString(STAND_SPECIES_4_COLUMN)));
        commonSpecies.add(InputParser.parseSpecies(cursor.getString(STAND_SPECIES_5_COLUMN)));

        cursor.close();
        return commonSpecies;
    }

    //HELPERS// ------------------------------------------------------
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL(ACTIVATE_FOREIGN_KEY_SUPPORT);
        }
    }

    /**
     * Helper method that extracts WoodlotImage at current woodlot cursor position.
     * @param woodlotCursor cursor to extract image from
     * @return woodlotImage resultant woodlot image
     */
    private WoodlotImage extractWoodlotImage(Cursor woodlotCursor){
        int id = woodlotCursor.getInt(WOODLOT_PRIMARY_KEY_COLUMN);
        String name = woodlotCursor.getString(WOODLOT_NAME_COLUMN);

        WoodlotImage woodlotImage = new WoodlotImage(name, id);

        for (StandImage standImage: getStandImagesFromWoodlot(id)){
            woodlotImage.addStandImage(standImage);
        }

        return woodlotImage;
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param standCursor cursor to extract image from
     * @return treeImage resultant tree image
     */
    private StandImage extractStandImage(Cursor standCursor){
        int standId = standCursor.getInt(STAND_PRIMARY_KEY_COLUMN);
        double area = standCursor.getDouble(STAND_AREA_COLUMN);
        int age= standCursor.getInt(STAND_AGE_COLUMN);
        double height = standCursor.getDouble(STAND_HEIGHT_COLUMN);
        int parentId = standCursor.getInt(STAND_WOODLOT_ID_KEY_COLUMN);
        String notes = standCursor.getString(STAND_NOTES_COLUMN);

        Species commonSpecies[] = new Species[5];
        commonSpecies[0] = InputParser.parseSpecies(standCursor.getString(STAND_SPECIES_1_COLUMN));
        commonSpecies[1] = InputParser.parseSpecies(standCursor.getString(STAND_SPECIES_2_COLUMN));
        commonSpecies[2] = InputParser.parseSpecies(standCursor.getString(STAND_SPECIES_3_COLUMN));
        commonSpecies[3] = InputParser.parseSpecies(standCursor.getString(STAND_SPECIES_4_COLUMN));
        commonSpecies[4] = InputParser.parseSpecies(standCursor.getString(STAND_SPECIES_5_COLUMN));

        StandImage standImage = new StandImage(standId, area, age, height, parentId);
        standImage.setNotes(notes);
        standImage.setCommonSpecies(commonSpecies);

        for (QuadratImage quadratImage: getQuadratImagesFromStand(standImage.getId())){
            standImage.addQuadratImage(quadratImage);
        }

        return standImage;
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param treeCursor cursor to extract image from
     * @return treeImage resultant tree image
     */
    private TreeImage extractTreeImage(Cursor treeCursor){
        int id = treeCursor.getInt(TREE_PRIMARY_KEY_COLUMN); //TODO: refactoring so all tree parsing is handled by another class
        double dbh = treeCursor.getDouble(TREE_DBH_COLUMN);
        Species species = InputParser.parseSpecies(treeCursor.getString(TREE_SPECIES_COLUMN));
        StorageFactor storageFactor = InputParser.parseStorageFactor(treeCursor.getString(TREE_STORAGE_FACTOR_COLUMN));
        MaterialType materialType = InputParser.parseMaterialType(treeCursor.getString(TREE_MATERIAL_TYPE_COLUMN));
        int quadratId = treeCursor.getInt(TREE_QUADRAT_ID_COLUMN);

        return new TreeImage(dbh, species, storageFactor, materialType, id, quadratId);
    }

    /**
     * Helper method that extracts TreeImage at current tree cursor position.
     * @param quadratCursor cursor to extract image from
     * @return treeImage resultant tree image
     */
    private QuadratImage extractQuadratImage(Cursor quadratCursor){
        int quadratId = quadratCursor.getInt(QUADRAT_PRIMARY_KEY_COLUMN);
        double xCoord = quadratCursor.getDouble(QUADRAT_X_COORDINATE_COLUMN);
        double yCoord = quadratCursor.getDouble(QUADRAT_Y_COORDINATE_COLUMN);
        boolean complete = (quadratCursor.getInt(QUADRAT_COMPLETE_COLUMN) == 1);
        int standID = quadratCursor.getInt(QUADRAT_STAND_ID_COLUMN);

        QuadratImage quadratImage = new QuadratImage(quadratId, new Coordinate(xCoord, yCoord), standID);
        quadratImage.setComplete(complete); //TODO: fix potential code smell

        for (TreeImage treeImage: getTreeImagesFromQuadrat(quadratImage.getId())){
            quadratImage.addTree(treeImage);
        }

        return quadratImage;
    }

    /**
     * Returns correct key to acces SQLite database given the common species rank
     *
     * @param rank - the position in the list of common species we are trying to get at
     * @return correct SQLite Key for the corresponding species data.
     */
    private String standSpeciesKey(int rank){
        switch (rank){
            case 1:
                return STAND_SPECIES_1_KEY;
            case 2:
                return STAND_SPECIES_2_KEY;
            case 3:
                return STAND_SPECIES_3_KEY;
            case 4:
                return STAND_SPECIES_4_KEY;
            case 5:
                return STAND_SPECIES_5_KEY;
            default:
                throw new InvalidSpeciesRankException();
        }
    }

    private int standSpeciesColumn(int rank){
        switch (rank){
            case 1:
                return STAND_SPECIES_1_COLUMN;
            case 2:
                return STAND_SPECIES_2_COLUMN;
            case 3:
                return STAND_SPECIES_3_COLUMN;
            case 4:
                return STAND_SPECIES_4_COLUMN;
            case 5:
                return STAND_SPECIES_5_COLUMN;
            default:
                throw new InvalidSpeciesRankException();
        }
    }

    private Cursor compileTableDumpCursor(String tableName){
        String selectQuery = "SELECT * FROM " + tableName + " ORDER BY " + KEY_PRIMARY +" ;";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private Cursor compileSelectionCursor(String tableName, String fieldKey, int value){
        String selectQuery = "SELECT * FROM " + tableName +
                " WHERE " + fieldKey + "=" + value + ";";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private Cursor compileIdSelectionCursor(String tableName, int id){
        String selectQuery = "SELECT * FROM " + tableName +
                " WHERE " + KEY_PRIMARY + "=" + id + ";";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private Cursor compileRelativeSelectionCursor(String tableName, String parentIdKey, int parentId, int childIndex){
        String selectQuery = "SELECT * FROM " + tableName +
                " WHERE " + parentIdKey + "=" + parentId +
                " ORDER BY " + KEY_PRIMARY +
                " LIMIT 1 OFFSET " + childIndex + ";";

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(selectQuery, null);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, int value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, String value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    private void execUpdate(String tableName, int targetId, String fieldKey, double value){
        String updateCommand = "UPDATE "+ tableName + " SET " +
                fieldKey + "=" + value + " WHERE " +
                KEY_PRIMARY + "=" + targetId + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(updateCommand);
    }

    private boolean exists(String tableName, String attribute1Key, String attribute1Value,
                           String attribute2Key, String attribute2Value){
        String existsQuery = "SELECT EXISTS(SELECT 1 FROM " + tableName + " WHERE " +
                            attribute1Key + "=\"" + attribute1Value + "\" AND " +
                            attribute2Key + "=\"" + attribute2Value + "\" LIMIT 1);";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(existsQuery, null);

        cursor.moveToFirst();
        return cursor.getInt(0) == 1;
    }//TODO: try replacing readable by writeable (for clarity's sake)

    private void execRelativeDelete(String tableName, String parentIdKey, int parentId, int childIndex){
        String whereClause = KEY_PRIMARY + " IN (SELECT " + KEY_PRIMARY + " FROM " + tableName +
                " WHERE " + parentIdKey + " = ? ORDER BY " + KEY_PRIMARY +
                " LIMIT 1 OFFSET ?);";

        SQLiteDatabase database = this.getReadableDatabase();
        database.delete(tableName, whereClause, new String[]{Integer.toString(parentId),
                Integer.toString(childIndex)});
    }

    private void execDelete(String tableName, int id){
        String whereClause = KEY_PRIMARY + " = ?;";

        SQLiteDatabase database = this.getReadableDatabase();
        database.delete(tableName, whereClause, new String[]{Integer.toString(id)});
    }

    private int execGetNumChildren(String tableName,  String parentIdKey, int parentId){
        String countQuery = "SELECT Count(*) FROM (SELECT * FROM " + tableName +
                                " WHERE " + parentIdKey + "= " + parentId + ")";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(countQuery, null);

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private int execGetNumRows(String tableName){
        String countQuery = "SELECT Count(*) FROM " + tableName + ";";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(countQuery, null);

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //DEBUG//---------------------------------------------------------------------------------------

    protected void reset(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + TREE_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + QUADRAT_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + STAND_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + WOODLOT_TABLE_NAME);
        onCreate(database);
    }

    protected List<StandImage> dumpStandTable() {
        Cursor cursor = compileTableDumpCursor(STAND_TABLE_NAME);

        List<StandImage> standImages = new LinkedList<StandImage>();

        while (cursor.moveToNext()) {
            StandImage standImage = extractStandImage(cursor);
            standImages.add(standImage);
        }

        cursor.close();
        return standImages;
    }

    protected List<QuadratImage> dumpQuadratTable() {
        Cursor cursor = compileTableDumpCursor(QUADRAT_TABLE_NAME);

        List<QuadratImage> quadratImages = new LinkedList<QuadratImage>();

        while (cursor.moveToNext()) {
            QuadratImage quadratImage = extractQuadratImage(cursor);
            quadratImages.add(quadratImage);
        }

        cursor.close();
        return quadratImages;
    }

    protected List<TreeImage> dumpTreeTable() {
        Cursor cursor = compileTableDumpCursor(TREE_TABLE_NAME);

        List<TreeImage> treeImages = new LinkedList<TreeImage>();

        while (cursor.moveToNext()) {
            TreeImage treeImage = extractTreeImage(cursor);
            treeImages.add(treeImage);
        }

        cursor.close();
        return treeImages;
    }

/*    protected List<TreeImage> dumpTreeTable(){
        Cursor cursor = compileTableDumpCursor(TREE_TABLE_NAME);

        List<TreeImage> trees = new LinkedList<TreeImage>();

        while(cursor.moveToNext()){
            TreeImage tree = extractTreeImage(cursor);
            trees.add(tree);
        }

        cursor.close();
        return trees;
    }*/
}
