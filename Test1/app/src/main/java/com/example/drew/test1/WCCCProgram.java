package com.example.drew.test1;

import android.content.Context;

import java.util.List;

/**
 *
 * Main program functions
 *
 * Created by Jen on 10/03/2017.
 */

public class WCCCProgram {

    private static final int UNDEFINED_ID = -1;

    private static int woodlotId = UNDEFINED_ID;
    private static int standId = UNDEFINED_ID;
    private static int quadratId = UNDEFINED_ID;
    private static int treeId = UNDEFINED_ID;

    private static int standIndex = UNDEFINED_ID;
    private static int quadratIndex = UNDEFINED_ID;
    private static int treeIndex = UNDEFINED_ID;

    private static DataBaseOpenHelper openHelper;

    public static void moveToWoodlot(int index){
        woodlotId = index;
        standId = UNDEFINED_ID;
        quadratId = UNDEFINED_ID;
        treeId = UNDEFINED_ID;

        standIndex = UNDEFINED_ID;
        quadratIndex = UNDEFINED_ID;
        treeIndex = UNDEFINED_ID;
    }

    public static void moveToStand(int index){
        standId = openHelper.getStandIdFromWoodlot(index, woodlotId);
        quadratId = UNDEFINED_ID;
        treeId = UNDEFINED_ID;

        standIndex = index;
        quadratIndex = UNDEFINED_ID;
        treeIndex = UNDEFINED_ID;
    }

    public static void moveToQuadrat(int index){
        quadratId = openHelper.getQuadratIdFromStand(index, standId);
        treeId = UNDEFINED_ID;

        quadratIndex = index;
        treeIndex = UNDEFINED_ID;
    }

    public static void moveToTree(int index){
        treeId = openHelper.getTreeIdFromQuadrat(index, quadratId);

        treeIndex = index;
    }

    public static class Root{
        public static int getNumWoodlots(){
            return openHelper.getNumWoodlotsInDataBase();
        }

        public static List<WoodlotImage> getWoodlotImages(){
            return openHelper.getWoodlotImagesFromDataBase();
        }

        public static void addWoodlot(WoodlotImage woodlotImage){
            openHelper.addWoodlotToDataBase(woodlotImage);
        }
    }

    public static class CurrWoodlot{
        public static WoodlotImage getImage(){
            return openHelper.getWoodlotImageFromDataBase(woodlotId);
        }

        public static String getName(){
            return openHelper.getWoodlotName(woodlotId);
        }

        public static int getNumStands(){
            return openHelper.getNumStandsInWoodlot(woodlotId);
        }

        public static void setName(String name){
            openHelper.setWoodlotName(name, woodlotId);
        }

        public static List<StandImage> getStandImages(){
            return openHelper.getStandImagesFromWoodlot(woodlotId);
        }

        public static StandImage getStandImage(int index){
            return openHelper.getStandImageFromWoodlot(index, woodlotId);
        }

        public static void addStand(StandImage standImage){
            openHelper.addStandToWoodlot(standImage, woodlotId);
        }

        public static void removeStand(int index){
            openHelper.removeStandFromWoodlot(index, woodlotId);
        }
    }

    public static class CurrStand{
        public static StandImage getImage(){
            return openHelper.getStandImageFromWoodlot(standIndex, woodlotId);
        }

        public static double getArea(){
            return openHelper.getStandArea(standId);
        }

        public static int getAge(){
            return openHelper.getStandAge(standId);
        }

        public static double getHeight(){
            return openHelper.getStandHeight(standId);
        }

        public static String getNotes(){
            return openHelper.getStandNotes(standId);
        }

        public static List<Species> getCommonSpecies(){
            return openHelper.getStandCommonSpecies(standId);
        }

        public static void setArea(double area){
            openHelper.setStandArea(area, standId);
        }

        public static void setAge(int age){
            openHelper.setStandAge(age, standId);
        }

        public static void setHeight(double height){
            openHelper.setStandHeight(height, standId);
        }

        public static void setNotes(String notes){
            openHelper.setStandNotes(notes, standId);
        }

        public static void setSpecie(Species specie, int rank){
            openHelper.setStandSpecies(specie, rank, standId);
        }

        public static int getNumQuadrats(){
            return openHelper.getNumQuadratsInStand(standId);
        }

        public static List<QuadratImage> getQuadratImages(){
            return openHelper.getQuadratImagesFromStand(standId);
        }

        public static void addQuadrat(QuadratImage quadratImage){
            openHelper.addQuadratToStand(quadratImage, standId);
        }
    }

    public static class CurrQuadrat{
        public static QuadratImage getImage(){
            return openHelper.getQuadratImageFromStand(quadratIndex, standId);
        }

        public static Coordinate getCoordinates(){
            return openHelper.getQuadratCoordinates(quadratId);
        }

        public static boolean isComplete(){
            return openHelper.getQuadratCompletionStatus(quadratId);
        }

        public static void setCoordinates(Coordinate coordinates){
            openHelper.setQuadratCoordinates(coordinates, quadratId);
        }

        public static void setComplete(){
            openHelper.setQuadratCompletionStatus(true, quadratId);
        }

        public static void setIncomplete(){
            openHelper.setQuadratCompletionStatus(false, quadratId);
        }

        public static int getNumTrees(){
            return openHelper.getNumTreesInQuadrat(quadratId);
        }

        public static List<TreeImage> getTreeImages(){
            return openHelper.getTreeImagesFromQuadrat(quadratId);
        }

        public static TreeImage getTreeImage(int index){
            return openHelper.getTreeImageFromQuadrat(index, quadratId);
        }

        public static void addTree(TreeImage treeImage){
            openHelper.addTreeToQuadrat(treeImage, quadratId);
        }

        public static void removeTree(int index){
            openHelper.removeTreeFromQuadrat(index, quadratId);
        }
    }

    public static class CurrTree{
        public static TreeImage getImage(){
            return openHelper.getTreeImageFromQuadrat(treeIndex, quadratId);
        }
    }

    /**
     * Initializes openHelper required for SQLite data accessing
     *
     * @param context application the openhelper is being used in
     */
    public static void initialize(Context context){
        openHelper = new DataBaseOpenHelper(context);
    }

}
