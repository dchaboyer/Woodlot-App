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
   /** public void buildDebug(){
        this.database = new DataBase();
        Woodlot woodlot1 = new Woodlot("Test", 5);
        Stand stand1 = new Stand(DEBUG_STAND_DEFAULT_SPECIES,
                                    DEBUG_STAND_DEFAULT_AGE,
                                    DEBUG_STAND_DEFAULT_HEIGHT);
        Quadrat quadrat1 = new Quadrat();
        Quadrat quadrat2 = new Quadrat();
        Quadrat quadrat3 = new Quadrat();
        Quadrat quadrat4 = new Quadrat();
        Quadrat quadrat5 = new Quadrat();
        Quadrat quadrat6 = new Quadrat();
        Quadrat quadrat7 = new Quadrat();
        Quadrat quadrat8 = new Quadrat();
        Quadrat quadrat9 = new Quadrat();
        Quadrat quadrat10 = new Quadrat();

        stand1.addQuadrat(quadrat1);
        stand1.addQuadrat(quadrat2);
        stand1.addQuadrat(quadrat3);
        stand1.addQuadrat(quadrat4);
        stand1.addQuadrat(quadrat5);
        stand1.addQuadrat(quadrat6);
        stand1.addQuadrat(quadrat7);
        stand1.addQuadrat(quadrat8);
        stand1.addQuadrat(quadrat9);
        stand1.addQuadrat(quadrat10);
        woodlot1.addStand(stand1);
        this.database.addWoodlot(woodlot1);
    } */
}
