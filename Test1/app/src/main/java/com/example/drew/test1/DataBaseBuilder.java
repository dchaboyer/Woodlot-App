package com.example.drew.test1;

/**
 * DATA BASE BUILDER
 *
 * Class responsible for instantiating a DataBase.
 *
 * @author mbelzileha
 * Created by Jen on 25/02/2017.
 */

public class DataBaseBuilder {

    private DataBase database;

    //GET//--------------------------------------------

    public DataBase getResult(){
        return this.database;
    }

    //DEBUG//------------------------------------------

    private final Species DEBUG_STAND_DEFAULT_SPECIES = Species.AMERICAN_BEECH;
    private final int DEBUG_STAND_DEFAULT_AGE = 10;
    private final double DEBUG_STAND_DEFAULT_HEIGHT = 12.5;

    /**
     *
     * Constructs a DataBase for the sake of debuging until the UI is fully operational.
     */
    public void buildDebug(){
        this.database = new DataBase();
        Woodlot woodlot1 = new Woodlot();
        Stand stand1 = new Stand(DEBUG_STAND_DEFAULT_SPECIES,
                                    DEBUG_STAND_DEFAULT_AGE,
                                    DEBUG_STAND_DEFAULT_HEIGHT);
        Quadrat quadrat1 = new Quadrat();

        stand1.addQuadrat(quadrat1);
        woodlot1.addStand(stand1);
        this.database.addWoodlot(woodlot1);
    }
}
