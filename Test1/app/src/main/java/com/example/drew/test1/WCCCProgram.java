package com.example.drew.test1;

/**
 * Created by Jen on 10/03/2017.
 */

public class WCCCProgram {

    private static final DataBase DATA_BASE = new DataBase();
    private static final int INDEX_UNDEFINED = -1;

    private static int woodlotIndex = INDEX_UNDEFINED;
    private static int standIndex = INDEX_UNDEFINED;
    private static int quadratIndex = INDEX_UNDEFINED;
    private static int treeIndex = INDEX_UNDEFINED;

    public static void setCurrWoodlot(int index){
        woodlotIndex = index;
    }

    public static void setCurrStand(int index){
        standIndex = index;
    }

    public static void setCurrQuadrat(int index){
        quadratIndex = index;
    }

    public static void setCurrTree(int index){
        treeIndex = index;
    }

    public static DataBase getRoot(){
        return DATA_BASE;
    }

    public static Woodlot getCurrWoodlot(){
        return DATA_BASE.getWoodlot(woodlotIndex);
    }

    public static Stand getCurrStand(){
        return DATA_BASE.getWoodlot(woodlotIndex).getStand(standIndex);
    }

    public static Quadrat getCurrQuadrat(){
        return DATA_BASE.getWoodlot(woodlotIndex).getStand(standIndex).getQuadrat(quadratIndex);
    }

    public static Tree getCurrTree(){
        return DATA_BASE.getWoodlot(woodlotIndex).getStand(standIndex).getQuadrat(quadratIndex).getTree(treeIndex);
    }
}
