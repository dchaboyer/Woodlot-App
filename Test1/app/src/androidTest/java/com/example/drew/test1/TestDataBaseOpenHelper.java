package com.example.drew.test1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

//TODO: add remove functionality
    //TODO: make WCCC command that gets images for things and handles updating stuff via images

@RunWith(AndroidJUnit4.class)
public class TestDataBaseOpenHelper {

    //TEST DATA//-----------------------------------------------------------------------------------

        //Data Elements
            private final static Context appContext = InstrumentationRegistry.getTargetContext();
            private static DataBaseOpenHelper openHelper;

            private final static String woodlot1Name = "woodlot1Name";
            private final static String woodlot2Name = "woodlot2Name";

            private final static WoodlotImage woodlot1Image = new WoodlotImage(woodlot1Name);
            private final static WoodlotImage woodlot2Image = new WoodlotImage(woodlot2Name);

            private static WoodlotImage populatedWoodlot1Image;
            private static WoodlotImage populatedWoodlot2Image;

            private final static int woodlot1Id = 1; // while not explicitly put in woodlots, tests
                                                     // ought to be designed so that these are the
                                                     // corresponding woodlot ids.
            private final static int woodlot2Id = 2; // while not explicitly put in woodlots, tests
                                                     // ought to be designed so that these are the
                                                     // corresponding woodlot ids.

            private final static int stand1Id = 1;   // Just like with woodlots, test populators
            private final static int stand2Id = 2;   // ought to make sure that stand ids
            private final static int stand3Id = 3;   // correspond.
            private final static int stand4Id = 4;
            private final static int stand5Id = 5;

            private final static int quadrat1Id = 1;   // Just like with woodlots, test populators
            private final static int quadrat2Id = 2;   // ought to make sure that quadrat ids
            private final static int quadrat3Id = 3;   // correspond.
            private final static int quadrat4Id = 4;
            private final static int quadrat5Id = 5;

            private final static double stand1Area = 2.35;
            private final static double stand2Area = 4.72123;
            private final static double stand3Area = 6.842;
            private final static double stand4Area = 7;
            private final static double stand5Area = 10000.323;

            private final static int stand1Age = 921;
            private final static int stand2Age = 3;
            private final static int stand3Age = 56;
            private final static int stand4Age = 77777;
            private final static int stand5Age = 5111;

            private final static double stand1Height = 45.6211321;
            private final static double stand2Height = 89;
            private final static double stand3Height = 45324234.1;
            private final static double stand4Height = 0.543534;
            private final static double stand5Height = 123213.08912;

            private final static StandImage stand1Image = new StandImage(stand1Area, stand1Age, stand1Height);
            private final static StandImage stand2Image = new StandImage(stand2Area, stand2Age, stand2Height);
            private final static StandImage stand3Image = new StandImage(stand3Area, stand3Age, stand3Height);
            private final static StandImage stand4Image = new StandImage(stand4Area, stand4Age, stand4Height);
            private final static StandImage stand5Image = new StandImage(stand5Area, stand5Age, stand5Height);

            private static StandImage populatedStand1Image;
            private static StandImage populatedStand2Image;

            private static StandImage altStand1Image;
            private static StandImage altStand2Image;
            private static StandImage altStand3Image;
            private static StandImage altStand4Image;
            private static StandImage altStand5Image;

            private final static Coordinate coord1 = new Coordinate(1.7, 31123);
            private final static Coordinate coord2 = new Coordinate(866.0222, 55.4);
            private final static Coordinate coord3 = new Coordinate(654, 1);
            private final static Coordinate coord4 = new Coordinate(123120.1, 1.011);
            private final static Coordinate coord5 = new Coordinate(83.432, 3.7);

            private static QuadratImage quadrat1Image = new QuadratImage(coord1);
            private static QuadratImage quadrat2Image = new QuadratImage(coord2);
            private static QuadratImage quadrat3Image = new QuadratImage(coord3);
            private static QuadratImage quadrat4Image = new QuadratImage(coord4);
            private static QuadratImage quadrat5Image = new QuadratImage(coord5);

            private static QuadratImage populatedQuadrat1Image;
            private static QuadratImage populatedQuadrat2Image;

            private final static TreeImage treeImage1_a = new TreeImage(7.8, Species.YELLOW_BIRCH,
                    StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

            private final static TreeImage treeImage1_b = new TreeImage(2.52, Species.EASTERN_LARCH,
                    StorageFactor.STORAGE_FACTOR_ONE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

            private final static TreeImage treeImage2_a = new TreeImage(3.14, Species.BLACK_CHERRY,
                    StorageFactor.STORAGE_FACTOR_TWO,MaterialType.UNACCEPTABLE_SAW_MATERIAL);

            private final static TreeImage treeImage2_b = new TreeImage(8.01, Species.GENERIC_HARD_WOOD,
                    StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    //DATA BUILD//----------------------------------------------------------------------------------

    @Before
    public void setup(){
        populatedQuadrat1Image = new QuadratImage(quadrat1Image.getCoordinate());
        populatedQuadrat2Image = new QuadratImage(quadrat2Image.getCoordinate());

        populatedQuadrat1Image.addTree(treeImage1_a);
        populatedQuadrat1Image.addTree(treeImage1_b);

        populatedQuadrat2Image.addTree(treeImage2_a);
        populatedQuadrat2Image.addTree(treeImage2_b);

        populatedStand1Image = new StandImage(stand1Image.getArea(), stand1Image.getAge(),
                stand1Image.getHeight());
        populatedStand2Image = new StandImage(stand2Image.getArea(), stand2Image.getAge(),
                stand2Image.getHeight());

        populatedStand1Image.addQuadratImage(populatedQuadrat1Image);
        populatedStand1Image.addQuadratImage(quadrat3Image);

        populatedStand2Image.addQuadratImage(populatedQuadrat2Image);

        populatedWoodlot1Image = new WoodlotImage(woodlot1Image.getName());
        populatedWoodlot2Image = new WoodlotImage(woodlot2Image.getName());

        populatedWoodlot1Image.addStandImage(populatedStand1Image);
        populatedWoodlot1Image.addStandImage(stand3Image);

        populatedWoodlot2Image.addStandImage(populatedStand2Image);

        openHelper = new DataBaseOpenHelper(appContext);
    }

    //HELPERS//------------------------------------------------------------------------------
    private boolean imageMatch(WoodlotImage woodlotImage1, WoodlotImage woodlotImage2){
        return woodlotImage1.getName().equals(woodlotImage2.getName())
                && standsImageMatch(woodlotImage1.getStandImages(), woodlotImage2.getStandImages());
    }

    private boolean fullMatch(WoodlotImage woodlotImage1, WoodlotImage woodlotImage2){
        return imageMatch(woodlotImage1, woodlotImage2)
                && woodlotImage1.getId() == woodlotImage2.getId()
                && standsFullMatch(woodlotImage1.getStandImages(), woodlotImage2.getStandImages());
    }

    private boolean imageMatch(StandImage standImage1, StandImage standImage2){
        return standImage1.getAge() == standImage2.getAge()
                && standImage1.getArea() == standImage2.getArea()
                && standImage1.getHeight() == standImage2.getHeight()
                && standImage1.getCommonSpecies(1) == standImage2.getCommonSpecies(1)
                && standImage1.getCommonSpecies(2) == standImage2.getCommonSpecies(2)
                && standImage1.getCommonSpecies(3) == standImage2.getCommonSpecies(3)
                && standImage1.getCommonSpecies(4) == standImage2.getCommonSpecies(4)
                && standImage1.getCommonSpecies(5) == standImage2.getCommonSpecies(5)
                && standImage1.getNotes().equals(standImage2.getNotes())
                && quadratsImageMatch(standImage1.getQuadratImages(), standImage2.getQuadratImages());
    }

    private boolean fullMatch(StandImage standImage1, StandImage standImage2){
        return imageMatch(standImage1, standImage2)
                && standImage1.getId() == standImage2.getId()
                && standImage1.getParentId() == standImage2.getParentId()
                && quadratsFullMatch(standImage1.getQuadratImages(), standImage2.getQuadratImages());
    }

    private boolean imageMatch(QuadratImage quadratImage1, QuadratImage quadratImage2){
        return quadratImage1.getCoordinate().equals(quadratImage2.getCoordinate())
                && treesImageMatch(quadratImage1.getTrees(), quadratImage2.getTrees());
    }

    private boolean fullMatch(QuadratImage quadratImage1, QuadratImage quadratImage2){
        return imageMatch(quadratImage1, quadratImage2)
                && quadratImage1.getId() == quadratImage2.getId()
                && quadratImage1.getParentId() == quadratImage2.getParentId()
                && treesFullMatch(quadratImage1.getTrees(), quadratImage2.getTrees()); //TODO: fix unecessary duplication of tasks
    }

    private boolean fullMatch(TreeImage treeImage1, TreeImage treeImage2){
        return imageMatch(treeImage1, treeImage2)
                && treeImage1.getId() == treeImage2.getId()
                && treeImage1.getParentId() == treeImage2.getParentId();
    }

    private boolean imageMatch(TreeImage treeImage1, TreeImage treeImage2){
        return treeImage1.getDbh() == treeImage2.getDbh()
                && treeImage1.getMaterialType() == treeImage2.getMaterialType()
                && treeImage1.getSpecies() == treeImage2.getSpecies()
                && treeImage1.getStorageFactor() == treeImage2.getStorageFactor();
    }

    private boolean match (List<Species> species1, List<Species> species2){
        Iterator<Species> species1Iterator = species1.iterator();
        Iterator<Species> species2Iterator = species2.iterator();

        while (species1Iterator.hasNext() && species2Iterator.hasNext()){
            if (species1Iterator.next() != species2Iterator.next()){
                return false;
            }
        }

        return true;
    }

    private boolean treesImageMatch (List<TreeImage> treeImages1, List<TreeImage> treeImages2){
        if (treeImages1.size() != treeImages2.size()){
            return false;
        }

        Iterator<TreeImage> treeImages1Iterator = treeImages1.iterator();
        Iterator<TreeImage> treeImages2Iterator = treeImages2.iterator();

        while (treeImages1Iterator.hasNext() && treeImages2Iterator.hasNext()){
            if (!imageMatch(treeImages1Iterator.next(), treeImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean standsImageMatch(List<StandImage> standImages1, List<StandImage> standImages2){
        if (standImages1.size() != standImages2.size()){
            return false;
        }

        Iterator<StandImage> standImages1Iterator = standImages1.iterator();
        Iterator<StandImage> standImages2Iterator = standImages2.iterator();

        while (standImages1Iterator.hasNext() && standImages2Iterator.hasNext()){
            if (!imageMatch(standImages1Iterator.next(), standImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean quadratsImageMatch(List<QuadratImage> quadratImages1, List<QuadratImage> quadratImages2){
        if (quadratImages1.size() != quadratImages2.size()){
            return false;
        }

        Iterator<QuadratImage> quadratImages1Iterator = quadratImages1.iterator();
        Iterator<QuadratImage> quadratImages2Iterator = quadratImages2.iterator();

        while (quadratImages1Iterator.hasNext() && quadratImages2Iterator.hasNext()){
            if (!imageMatch(quadratImages1Iterator.next(), quadratImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean standsFullMatch(List<StandImage> standImages1, List<StandImage> standImages2){
        if (standImages1.size() != standImages2.size()){
            return false;
        }

        Iterator<StandImage> standImages1Iterator = standImages1.iterator();
        Iterator<StandImage> standImages2Iterator = standImages2.iterator();

        while (standImages1Iterator.hasNext() && standImages2Iterator.hasNext()){
            if (!fullMatch(standImages1Iterator.next(), standImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean quadratsFullMatch(List<QuadratImage> quadratImages1, List<QuadratImage> quadratImages2){
        if (quadratImages1.size() != quadratImages2.size()){
            return false;
        }

        Iterator<QuadratImage> quadratImages1Iterator = quadratImages1.iterator();
        Iterator<QuadratImage> quadratImages2Iterator = quadratImages2.iterator();

        while (quadratImages1Iterator.hasNext() && quadratImages2Iterator.hasNext()){
            if (!fullMatch(quadratImages1Iterator.next(), quadratImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean treesFullMatch (List<TreeImage> treeImages1, List<TreeImage> treeImages2){
        if (treeImages1.size() != treeImages2.size()){
            return false;
        }

        Iterator<TreeImage> treeImages1Iterator = treeImages1.iterator();
        Iterator<TreeImage> treeImages2Iterator = treeImages2.iterator();

        while (treeImages1Iterator.hasNext() && treeImages2Iterator.hasNext()){
            if (!fullMatch(treeImages1Iterator.next(), treeImages2Iterator.next())){
                return false;
            }
        }
        return true;
    }

    private StandImage addDataToStandImage(StandImage standImage, String notes, Species species1, Species species2,
                                           Species species3,Species species4,Species species5){

        StandImage newStandImage = new StandImage(standImage.getId(), standImage.getArea(),
                                                    standImage.getAge(), standImage.getHeight(),
                                                    standImage.getParentId());

        newStandImage.setNotes(notes);
        newStandImage.setCommonSpecies(species1, 1);
        newStandImage.setCommonSpecies(species2, 2);
        newStandImage.setCommonSpecies(species3, 3);
        newStandImage.setCommonSpecies(species4, 4);
        newStandImage.setCommonSpecies(species5, 5);

        for(QuadratImage quadratImage: standImage.getQuadratImages()){
            newStandImage.addQuadratImage(quadratImage);
        }

        return newStandImage;
    }

    private Species randomSpecies(){
        Random random = new Random();
        int numSpecies = Species.values().length;

        int index = random.nextInt(numSpecies + 1);
        if (index == numSpecies){
            return null;
        }

        return Species.values()[index];
    }
    private void populateDataForQuadratAddTesting(){
        openHelper.addWoodlotToDataBase(woodlot1Image); // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image); // add second to correspond to woodlot2Id

        String stand1ImageNote = "stand 1 note";
        String stand2ImageNote = "stand 2 note";
        String stand3ImageNote = "stand 3 note";
        String stand4ImageNote = "stand 4 note";
        String stand5ImageNote = "stand 5 note";

        altStand1Image = addDataToStandImage(stand1Image, stand1ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand2Image = addDataToStandImage(stand2Image, stand2ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand3Image = addDataToStandImage(stand3Image, stand3ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand4Image = addDataToStandImage(stand4Image, stand4ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand5Image = addDataToStandImage(stand5Image, stand5ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());

        openHelper.addStandToWoodlot(altStand1Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand2Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand3Image, woodlot2Id);
        openHelper.addStandToWoodlot(altStand4Image, woodlot2Id);
        openHelper.addStandToWoodlot(altStand5Image, woodlot2Id);
    }

    private void populateDataForStandTesting(){
        openHelper.addWoodlotToDataBase(woodlot1Image); // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image); // add second to correspond to woodlot2Id

        String stand1ImageNote = "stand 1 note";
        String stand2ImageNote = "stand 2 note";
        String stand3ImageNote = "stand 3 note";
        String stand4ImageNote = "stand 4 note";
        String stand5ImageNote = "stand 5 note";

        altStand1Image = addDataToStandImage(populatedStand1Image, stand1ImageNote, randomSpecies(),
                                                randomSpecies(), randomSpecies(), randomSpecies(),
                                                randomSpecies());
        altStand2Image = addDataToStandImage(populatedStand2Image, stand2ImageNote, randomSpecies(),
                                                randomSpecies(), randomSpecies(), randomSpecies(),
                                                randomSpecies());
        altStand3Image = addDataToStandImage(stand3Image, stand3ImageNote, randomSpecies(),
                                                randomSpecies(), randomSpecies(), randomSpecies(),
                                                randomSpecies());
        altStand4Image = addDataToStandImage(stand4Image, stand4ImageNote, randomSpecies(),
                                                randomSpecies(), randomSpecies(), randomSpecies(),
                                                randomSpecies());
        altStand5Image = addDataToStandImage(stand5Image, stand5ImageNote, randomSpecies(),
                                                randomSpecies(), randomSpecies(), randomSpecies(),
                                                randomSpecies());

        openHelper.addStandToWoodlot(altStand1Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand2Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand3Image, woodlot2Id);
        openHelper.addStandToWoodlot(altStand4Image, woodlot2Id);
        openHelper.addStandToWoodlot(altStand5Image, woodlot2Id);
    }

    private void populateDataForQuadratTesting(){
        openHelper.addWoodlotToDataBase(woodlot1Image); // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image); // add second to correspond to woodlot2Id

        String stand1ImageNote = "stand 1 note";
        String stand2ImageNote = "stand 2 note";

        altStand1Image = addDataToStandImage(stand1Image, stand1ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand2Image = addDataToStandImage(stand2Image, stand2ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());

        openHelper.addStandToWoodlot(altStand1Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand2Image, woodlot1Id);

        openHelper.addQuadratToStand(populatedQuadrat1Image, stand1Id);
        openHelper.addQuadratToStand(populatedQuadrat2Image, stand1Id);
        openHelper.addQuadratToStand(quadrat3Image, stand2Id);
        openHelper.addQuadratToStand(quadrat4Image, stand2Id);
        openHelper.addQuadratToStand(quadrat5Image, stand2Id);
    }

    private void populateDataForTreeTesting(){
        openHelper.addWoodlotToDataBase(woodlot1Image); // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image); // add second to correspond to woodlot2Id

        String stand1ImageNote = "stand 1 note";
        String stand2ImageNote = "stand 2 note";
        String stand3ImageNote = "stand 3 note";

        altStand1Image = addDataToStandImage(stand1Image, stand1ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand2Image = addDataToStandImage(stand2Image, stand2ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand3Image = addDataToStandImage(stand3Image, stand3ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());

        openHelper.addStandToWoodlot(altStand1Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand2Image, woodlot1Id);

        openHelper.addStandToWoodlot(altStand3Image, woodlot2Id);

        openHelper.addQuadratToStand(populatedQuadrat1Image, stand1Id);
        openHelper.addQuadratToStand(populatedQuadrat2Image, stand2Id);
    }

    private void populateDataForTreeAddTesting(){
        openHelper.addWoodlotToDataBase(woodlot1Image); // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image); // add second to correspond to woodlot2Id

        String stand1ImageNote = "stand 1 note";
        String stand2ImageNote = "stand 2 note";

        altStand1Image = addDataToStandImage(stand1Image, stand1ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());
        altStand2Image = addDataToStandImage(stand2Image, stand2ImageNote, randomSpecies(),
                randomSpecies(), randomSpecies(), randomSpecies(),
                randomSpecies());

        openHelper.addStandToWoodlot(altStand1Image, woodlot1Id);
        openHelper.addStandToWoodlot(altStand2Image, woodlot1Id);

        openHelper.addQuadratToStand(quadrat1Image, stand1Id);
        openHelper.addQuadratToStand(quadrat2Image, stand2Id);
    }

    //TEST WOODLOT//--------------------------------------------------------------------------------

    @Test
    public void testAddGetWoodlots(){
        openHelper.reset();
        assertTrue(openHelper.getWoodlotImagesFromDataBase().isEmpty());

        openHelper.addWoodlotToDataBase(populatedWoodlot1Image);
        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 1);
        assertTrue(imageMatch(openHelper.getWoodlotImagesFromDataBase().get(0), populatedWoodlot1Image));

        openHelper.addWoodlotToDataBase(populatedWoodlot2Image);
        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 2);
        assertTrue(imageMatch(openHelper.getWoodlotImagesFromDataBase().get(0), populatedWoodlot1Image));
        assertTrue(imageMatch(openHelper.getWoodlotImagesFromDataBase().get(1), populatedWoodlot2Image));

        assertTrue(fullMatch(openHelper.getWoodlotImagesFromDataBase().get(0), openHelper.getWoodlotImageFromDataBase(1)));
        assertTrue(fullMatch(openHelper.getWoodlotImagesFromDataBase().get(1), openHelper.getWoodlotImageFromDataBase(2)));

        openHelper.addWoodlotToDataBase(openHelper.getWoodlotImagesFromDataBase().get(1));
        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 2);
        assertTrue("DSADAS " + openHelper.getWoodlotImagesFromDataBase().get(0).getName() + " : \n" +
                        populatedWoodlot1Image.getName(),
                imageMatch(openHelper.getWoodlotImagesFromDataBase().get(0), populatedWoodlot1Image));
        assertTrue(imageMatch(openHelper.getWoodlotImagesFromDataBase().get(1), populatedWoodlot2Image));

        assertTrue(fullMatch(openHelper.getWoodlotImagesFromDataBase().get(0), openHelper.getWoodlotImageFromDataBase(1)));
        assertTrue(fullMatch(openHelper.getWoodlotImagesFromDataBase().get(1), openHelper.getWoodlotImageFromDataBase(2)));
    }

    @Test
    public void testAddStands(){
        openHelper.reset();
        assertTrue(openHelper.getWoodlotImagesFromDataBase().isEmpty());
        openHelper.addWoodlotToDataBase(woodlot1Image);  // add first to correspond to woodlot1Id
        openHelper.addWoodlotToDataBase(woodlot2Image);  // add second to correspond to woodlot2Id

        openHelper.addStandToWoodlot(populatedStand1Image, woodlot1Id);
        assertTrue(openHelper.dumpStandTable().size() == 1);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);

        openHelper.addStandToWoodlot(populatedStand2Image, woodlot2Id);
        assertTrue(openHelper.dumpStandTable().size() == 2);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(1), populatedStand2Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(1).getParentId() == woodlot2Id);

        openHelper.addStandToWoodlot(stand3Image, woodlot1Id);
        assertTrue(openHelper.dumpStandTable().size() == 3);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(1), populatedStand2Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(2), stand3Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(1).getParentId() == woodlot2Id);
        assertTrue(openHelper.dumpStandTable().get(2).getParentId() == woodlot1Id);

        openHelper.addStandToWoodlot(stand4Image, woodlot2Id);
        assertTrue(openHelper.dumpStandTable().size() == 4);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(1), populatedStand2Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(2), stand3Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(3), stand4Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(1).getParentId() == woodlot2Id);
        assertTrue(openHelper.dumpStandTable().get(2).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(3).getParentId() == woodlot2Id);

        openHelper.addStandToWoodlot(openHelper.dumpStandTable().get(3), woodlot2Id);
        assertTrue(openHelper.dumpStandTable().size() == 4);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(1), populatedStand2Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(2), stand3Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(3), stand4Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(1).getParentId() == woodlot2Id);
        assertTrue(openHelper.dumpStandTable().get(2).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(3).getParentId() == woodlot2Id);

        openHelper.addStandToWoodlot(openHelper.dumpStandTable().get(3), woodlot1Id);
        assertTrue(openHelper.dumpStandTable().size() == 5);
        assertTrue(imageMatch(openHelper.dumpStandTable().get(0), populatedStand1Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(1), populatedStand2Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(2), stand3Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(3), stand4Image));
        assertTrue(imageMatch(openHelper.dumpStandTable().get(4), stand4Image));
        assertTrue(openHelper.dumpStandTable().get(0).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(1).getParentId() == woodlot2Id);
        assertTrue(openHelper.dumpStandTable().get(2).getParentId() == woodlot1Id);
        assertTrue(openHelper.dumpStandTable().get(3).getParentId() == woodlot2Id);
        assertTrue(openHelper.dumpStandTable().get(4).getParentId() == woodlot1Id);
    }

    @Test
    public void testGetStands(){
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot1Id).size() == 2);
        assertTrue("\nFROM DB: species = " + openHelper.getStandImagesFromWoodlot(woodlot1Id).get(0).getCommonSpecies() +
                "\nOriginal: species = " + altStand1Image.getCommonSpecies(), imageMatch(openHelper.getStandImagesFromWoodlot(woodlot1Id).get(0), altStand1Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImagesFromWoodlot(woodlot1Id).get(1).getCommonSpecies() +
                "\nOriginal: species = " + altStand2Image.getCommonSpecies(), imageMatch(openHelper.getStandImagesFromWoodlot(woodlot1Id).get(1), altStand2Image));

        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot2Id).size() == 3);
        assertTrue(imageMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(0), altStand3Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImagesFromWoodlot(woodlot2Id).get(1).getCommonSpecies() +
                "\nOriginal: species = " + altStand4Image.getCommonSpecies(), imageMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(1), altStand4Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImagesFromWoodlot(woodlot2Id).get(2).getCommonSpecies() +
                "\nOriginal: species = " + altStand5Image.getCommonSpecies(),imageMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(2), altStand5Image));

        assertTrue(fullMatch(openHelper.getStandImagesFromWoodlot(woodlot1Id).get(0),
                openHelper.dumpStandTable().get(0)));
        assertTrue(fullMatch(openHelper.getStandImagesFromWoodlot(woodlot1Id).get(1),
                openHelper.dumpStandTable().get(1)));
        assertTrue(fullMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(0),
                openHelper.dumpStandTable().get(2)));
        assertTrue(fullMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(1),
                openHelper.dumpStandTable().get(3)));
        assertTrue(fullMatch(openHelper.getStandImagesFromWoodlot(woodlot2Id).get(2),
                openHelper.dumpStandTable().get(4)));
    }

    @Test
    public void testGetStand(){
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage debugImage = openHelper.getStandImageFromWoodlot(0,woodlot1Id);
        assertTrue("\nFROM DB: species = " + debugImage.getCommonSpecies() +
                "\nOriginal: species = " + altStand1Image.getCommonSpecies(),
                imageMatch(openHelper.getStandImageFromWoodlot(0,woodlot1Id), altStand1Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImageFromWoodlot(1,woodlot1Id).getCommonSpecies() +
                "\nOriginal: species = " + altStand2Image.getCommonSpecies(), imageMatch(openHelper.getStandImageFromWoodlot(1,woodlot1Id), altStand2Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImageFromWoodlot(0,woodlot2Id).getCommonSpecies() +
                "\nOriginal: species = " + altStand3Image.getCommonSpecies(), imageMatch(openHelper.getStandImageFromWoodlot(0,woodlot2Id), altStand3Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImageFromWoodlot(1,woodlot2Id).getCommonSpecies() +
                "\nOriginal: species = " + altStand4Image.getCommonSpecies(), imageMatch(openHelper.getStandImageFromWoodlot(1,woodlot2Id), altStand4Image));
        assertTrue("\nFROM DB: species = " + openHelper.getStandImageFromWoodlot(2,woodlot2Id).getCommonSpecies() +
                "\nOriginal: species = " + altStand5Image.getCommonSpecies(), imageMatch(openHelper.getStandImageFromWoodlot(2,woodlot2Id), altStand5Image));

        assertTrue(fullMatch(openHelper.getStandImageFromWoodlot(0,woodlot1Id),
                openHelper.dumpStandTable().get(0)));
        assertTrue(fullMatch(openHelper.getStandImageFromWoodlot(1,woodlot1Id),
                openHelper.dumpStandTable().get(1)));
        assertTrue(fullMatch(openHelper.getStandImageFromWoodlot(0,woodlot2Id),
                openHelper.dumpStandTable().get(2)));
        assertTrue(fullMatch(openHelper.getStandImageFromWoodlot(1,woodlot2Id),
                openHelper.dumpStandTable().get(3)));
        assertTrue(fullMatch(openHelper.getStandImageFromWoodlot(2,woodlot2Id),
                openHelper.dumpStandTable().get(4)));
    }

    /**
     * Uncomment for stress testing: Last stress test was successful.
     */
    /*@Test
    public void addGetStandsStressTest(){
        Random random = new Random();
        for (int i = 0; i < 100; i ++){
            if (random.nextInt(2) == 1){
                testGetStand();
                testGetStands();
            } else {
                testGetStands();
                testGetStand();
            }
        }
    }*/


    //TEST STAND//----------------------------------------------------------------------------------

    @Test
    public void testGetStandArea() {
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage[] controlStandImages = {altStand1Image, altStand2Image, altStand3Image,
                                            altStand4Image, altStand5Image};

        for (int i = 0; i < controlStandImages.length; i++){
            assertTrue("Failed at iteration: " + i, openHelper.getStandArea(i+1) == controlStandImages[i].getArea());
        }
    }

    @Test
    public void testGetStandAge() {
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage[] controlStandImages = {altStand1Image, altStand2Image, altStand3Image,
                altStand4Image, altStand5Image};

        for (int i = 0; i < controlStandImages.length; i++){
            assertTrue("Failed at iteration: " + i, openHelper.getStandAge(i+1) == controlStandImages[i].getAge());
        }
    }

    @Test
    public void testGetStandHeight() {
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage[] controlStandImages = {altStand1Image, altStand2Image, altStand3Image,
                altStand4Image, altStand5Image};

        for (int i = 0; i < controlStandImages.length; i++){
            assertTrue("Failed at iteration: " + i, openHelper.getStandHeight(i+1) == controlStandImages[i].getHeight());
        }
    }

    @Test
    public void testGetStandNotes() {
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage[] controlStandImages = {altStand1Image, altStand2Image, altStand3Image,
                altStand4Image, altStand5Image};

        for (int i = 0; i < controlStandImages.length; i++){
            assertTrue("Failed at iteration: " + i, openHelper.getStandNotes(i+1).equals(controlStandImages[i].getNotes()));
        }
    }

    @Test
    public void testGetStandCommonSpecies() {
        openHelper.reset();
        populateDataForStandTesting();  // Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id

        StandImage[] controlStandImages = {altStand1Image, altStand2Image, altStand3Image,
                altStand4Image, altStand5Image};

        for (int i = 0; i < controlStandImages.length; i++){
            assertTrue("Failed at iteration: " + i, match(openHelper.getStandCommonSpecies(i+1),
                            controlStandImages[i].getCommonSpecies()));
        }
    }

    @Test
    public void testAddQuadrat() {
        openHelper.reset();
        populateDataForQuadratAddTesting();// Note: in populateDataForStandTesting():
                                           // woodlot1 is added first to correspond to woodlot1Id
                                           // and woodlot2 is added second to correspond to woodlot2Id
                                           // Same with Stands.

        openHelper.addQuadratToStand(populatedQuadrat1Image, stand1Id);
        assertTrue(openHelper.dumpQuadratTable().size() == 1);
        assertTrue(openHelper.dumpQuadratTable().get(0).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(0), populatedQuadrat1Image));

        openHelper.addQuadratToStand(populatedQuadrat2Image, stand1Id);
        assertTrue(openHelper.dumpQuadratTable().size() == 2);
        assertTrue(openHelper.dumpQuadratTable().get(0).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(0), populatedQuadrat1Image));
        assertTrue(openHelper.dumpQuadratTable().get(1).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(1), populatedQuadrat2Image));

        openHelper.addQuadratToStand(quadrat3Image, stand2Id);
        assertTrue(openHelper.dumpQuadratTable().size() == 3);
        assertTrue(openHelper.dumpQuadratTable().get(0).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(0), populatedQuadrat1Image));
        assertTrue(openHelper.dumpQuadratTable().get(1).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(1), populatedQuadrat2Image));
        assertTrue(openHelper.dumpQuadratTable().get(2).getParentId() == stand2Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(2), quadrat3Image));

        openHelper.addQuadratToStand(openHelper.dumpQuadratTable().get(0), stand1Id);
        assertTrue(openHelper.dumpQuadratTable().size() == 3);
        assertTrue(openHelper.dumpQuadratTable().get(0).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(0), populatedQuadrat1Image));
        assertTrue(openHelper.dumpQuadratTable().get(1).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(1), populatedQuadrat2Image));
        assertTrue(openHelper.dumpQuadratTable().get(2).getParentId() == stand2Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(2), quadrat3Image));

        openHelper.addQuadratToStand(openHelper.dumpQuadratTable().get(0), stand2Id);
        assertTrue(openHelper.dumpQuadratTable().size() == 4);
        assertTrue(openHelper.dumpQuadratTable().get(0).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(0), populatedQuadrat1Image));
        assertTrue(openHelper.dumpQuadratTable().get(1).getParentId() == stand1Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(1), populatedQuadrat2Image));
        assertTrue(openHelper.dumpQuadratTable().get(2).getParentId() == stand2Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(2), quadrat3Image));
        assertTrue(openHelper.dumpQuadratTable().get(3).getParentId() == stand2Id);
        assertTrue(imageMatch(openHelper.dumpQuadratTable().get(3), populatedQuadrat1Image));
    }

    @Test
    public void testGetQuadrat(){
        openHelper.reset();
        populateDataForQuadratTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.

        assertTrue(imageMatch(openHelper.getQuadratImageFromStand(0, stand1Id), populatedQuadrat1Image));
        assertTrue(fullMatch(openHelper.getQuadratImageFromStand(0, stand1Id),
                openHelper.dumpQuadratTable().get(0)));
        assertTrue(imageMatch(openHelper.getQuadratImageFromStand(0, stand2Id), quadrat3Image));
        assertTrue(fullMatch(openHelper.getQuadratImageFromStand(0, stand2Id),
                openHelper.dumpQuadratTable().get(2)));
    }

    @Test
    public void testGetQuadrats(){
        openHelper.reset();
        populateDataForQuadratTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.

        assertTrue(openHelper.getQuadratImagesFromStand(stand1Id).size() == 2);
        assertTrue(imageMatch(openHelper.getQuadratImagesFromStand(stand1Id).get(0), populatedQuadrat1Image));
        assertTrue(fullMatch(openHelper.getQuadratImagesFromStand(stand1Id).get(0),
                openHelper.dumpQuadratTable().get(0)));

        assertTrue(openHelper.getQuadratImagesFromStand(stand2Id).size() == 3);
        assertTrue(imageMatch(openHelper.getQuadratImagesFromStand(stand2Id).get(1), quadrat4Image));
        assertTrue(fullMatch(openHelper.getQuadratImagesFromStand(stand2Id).get(1),
                openHelper.dumpQuadratTable().get(3)));
    }

    /**
     * Uncomment for stress testing: Last stress test was successful.
     */
    /*@Test
    public void standAttributesStressTest(){
        for (int i = 0; i < 100; i ++){
            testGetStandArea();
            testGetStandAge();
            testGetStandHeight();
            testGetStandNotes();
            testGetStandCommonSpecies();
            testAddQuadrat();
            testGetQuadrat();
            testGetQuadrats();
        }
    }*/

    //TEST QUADRAT//--------------------------------------------------------------------------------

    @Test
    public void testGetQuadratCoordinates(){
        openHelper.reset();
        populateDataForQuadratTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.

        assertTrue(openHelper.getQuadratCoordinates(1).equals(coord1));
        assertTrue(openHelper.getQuadratCoordinates(2).equals(coord2));
        assertTrue(openHelper.getQuadratCoordinates(3).equals(coord3));
        assertTrue(openHelper.getQuadratCoordinates(4).equals(coord4));
        assertTrue(openHelper.getQuadratCoordinates(5).equals(coord5));
    }

    @Test
    public void testSetQuadratCoordinates(){
        openHelper.reset();
        populateDataForQuadratTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.

        openHelper.setQuadratCoordinates(coord5, 1);
        openHelper.setQuadratCoordinates(coord4, 2);
        openHelper.setQuadratCoordinates(coord3, 3);
        openHelper.setQuadratCoordinates(coord2, 4);
        openHelper.setQuadratCoordinates(coord1, 5);

        assertTrue(openHelper.dumpQuadratTable().get(0).getCoordinate().equals(coord5));
        assertTrue(openHelper.dumpQuadratTable().get(1).getCoordinate().equals(coord4));
        assertTrue(openHelper.dumpQuadratTable().get(2).getCoordinate().equals(coord3));
        assertTrue(openHelper.dumpQuadratTable().get(3).getCoordinate().equals(coord2));
        assertTrue(openHelper.dumpQuadratTable().get(4).getCoordinate().equals(coord1));
    }

    @Test
    public void testAddTree(){
        openHelper.reset();
        populateDataForTreeAddTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.
                                        // Same with quadrats

        assertTrue(openHelper.dumpTreeTable().isEmpty());
        openHelper.addTreeToQuadrat(treeImage1_a, quadrat1Id);
        assertTrue(openHelper.dumpTreeTable().size() == 1);
        assertTrue(openHelper.dumpTreeTable().get(0).getParentId() == quadrat1Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(0), treeImage1_a));

        openHelper.addTreeToQuadrat(treeImage2_a, quadrat2Id);
        assertTrue(openHelper.dumpTreeTable().size() == 2);
        assertTrue(openHelper.dumpTreeTable().get(1).getParentId() == quadrat2Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(1), treeImage2_a));

        //Test inserting TreeImage with an ID already present in the table

        openHelper.addTreeToQuadrat(openHelper.dumpTreeTable().get(1), quadrat2Id);
        assertTrue(openHelper.dumpTreeTable().size() == 2);
        assertTrue(openHelper.dumpTreeTable().get(0).getParentId() == quadrat1Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(0), treeImage1_a));
        assertTrue(openHelper.dumpTreeTable().get(1).getParentId() == quadrat2Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(1), treeImage2_a));

        openHelper.addTreeToQuadrat(openHelper.dumpTreeTable().get(0), quadrat2Id);
        assertTrue(openHelper.dumpTreeTable().size() == 3);
        assertTrue(openHelper.dumpTreeTable().get(0).getParentId() == quadrat1Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(0), treeImage1_a));
        assertTrue(openHelper.dumpTreeTable().get(1).getParentId() == quadrat2Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(1), treeImage2_a));
        assertTrue(openHelper.dumpTreeTable().get(2).getParentId() == quadrat2Id);
        assertTrue(imageMatch(openHelper.dumpTreeTable().get(2), treeImage1_a));
    }

    @Test
    public void testGetTree() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
                                        // woodlot1 is added first to correspond to woodlot1Id
                                        // and woodlot2 is added second to correspond to woodlot2Id
                                        // Same with stand1 and stand2.
                                        // Same with quadrats

        assertTrue(imageMatch(openHelper.getTreeImageFromQuadrat(0,quadrat1Id),treeImage1_a));
        assertTrue(fullMatch(openHelper.getTreeImageFromQuadrat(0,quadrat1Id),
                                openHelper.dumpTreeTable().get(0)));

        assertTrue(imageMatch(openHelper.getTreeImageFromQuadrat(1,quadrat1Id),treeImage1_b));
        assertTrue(fullMatch(openHelper.getTreeImageFromQuadrat(1,quadrat1Id),
                                openHelper.dumpTreeTable().get(1)));

        assertTrue(imageMatch(openHelper.getTreeImageFromQuadrat(0,quadrat2Id),treeImage2_a));
        assertTrue(fullMatch(openHelper.getTreeImageFromQuadrat(0,quadrat2Id),
                                openHelper.dumpTreeTable().get(2)));

        assertTrue(imageMatch(openHelper.getTreeImageFromQuadrat(1,quadrat2Id),treeImage2_b));
        assertTrue(fullMatch(openHelper.getTreeImageFromQuadrat(1,quadrat2Id),
                                openHelper.dumpTreeTable().get(3)));
    }

    @Test
    public void testGetTrees() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
                                     // woodlot1 is added first to correspond to woodlot1Id
                                     // and woodlot2 is added second to correspond to woodlot2Id
                                     // Same with stand1 and stand2.
                                     // Same with quadrats

        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 2);
        assertTrue(imageMatch(openHelper.getTreeImagesFromQuadrat(quadrat1Id).get(0),treeImage1_a));
        assertTrue(fullMatch(openHelper.getTreeImagesFromQuadrat(quadrat1Id).get(0),
                openHelper.dumpTreeTable().get(0)));

        assertTrue(imageMatch(openHelper.getTreeImagesFromQuadrat(quadrat1Id).get(1),treeImage1_b));
        assertTrue(fullMatch(openHelper.getTreeImagesFromQuadrat(quadrat1Id).get(1),
                openHelper.dumpTreeTable().get(1)));

        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat2Id).size() == 2);
        assertTrue(imageMatch(openHelper.getTreeImagesFromQuadrat(quadrat2Id).get(0),treeImage2_a));
        assertTrue(fullMatch(openHelper.getTreeImagesFromQuadrat(quadrat2Id).get(0),
                openHelper.dumpTreeTable().get(2)));

        assertTrue(imageMatch(openHelper.getTreeImagesFromQuadrat(quadrat2Id).get(1),treeImage2_b));
        assertTrue(fullMatch(openHelper.getTreeImagesFromQuadrat(quadrat2Id).get(1),
                openHelper.dumpTreeTable().get(3)));
    }

    @Test
    public void testRemoveTree() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats

        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 2);
        openHelper.removeTreeFromQuadrat(1, quadrat1Id);
        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 1);
        assertTrue(imageMatch(openHelper.getTreeImagesFromQuadrat(quadrat1Id).get(0),
                                treeImage1_a));
    }

    @Test
    public void testGetNumTrees() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats

        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 2);
        assertTrue("Indicated: " + openHelper.getNumTreesInQuadrat(quadrat1Id),
                openHelper.getNumTreesInQuadrat(quadrat1Id) == 2);
        openHelper.removeTreeFromQuadrat(1, quadrat1Id);
        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 1);
        assertTrue("Indicated: " + openHelper.getNumTreesInQuadrat(quadrat1Id),
                openHelper.getNumTreesInQuadrat(quadrat1Id) == 1);
    }

    @Test
    public void testRemoveQuadrat() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getQuadratImagesFromStand(stand1Id).size() == 1);
        openHelper.removeQuadratFromStand(0, stand1Id);
        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 0);
        assertTrue(!openHelper.dumpTreeTable().isEmpty());
    }

    @Test
    public void testGetNumQuadrats() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getQuadratImagesFromStand(stand1Id).size() == 1);
        assertTrue(openHelper.getNumQuadratsInStand(stand1Id) == 1);
        openHelper.removeQuadratFromStand(0, stand1Id);
        assertTrue(openHelper.getTreeImagesFromQuadrat(quadrat1Id).size() == 0);
        assertTrue(openHelper.getNumQuadratsInStand(stand1Id) == 0);
    }

    @Test
    public void testRemoveStand() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot1Id).size() == 2);
        openHelper.removeStandFromWoodlot(1, woodlot1Id);
        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot1Id).size() == 1);
        assertTrue(!openHelper.dumpQuadratTable().isEmpty());
    }

    @Test
    public void testGetNumStands() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot1Id).size() == 2);
        assertTrue(openHelper.getNumStandsInWoodlot(woodlot1Id) == 2);
        openHelper.removeStandFromWoodlot(1, woodlot1Id);
        assertTrue(openHelper.getStandImagesFromWoodlot(woodlot1Id).size() == 1);
        assertTrue(openHelper.getNumStandsInWoodlot(woodlot1Id) == 1);
    }

    @Test
    public void testRemoveWoodlot() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 2);
        openHelper.removeWoodlotFromDataBase(1);
        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 1);
        assertTrue(!openHelper.dumpStandTable().isEmpty());
    }

    @Test
    public void testGetNumWoodlots() {
        openHelper.reset();
        populateDataForTreeTesting();// Note: in populateDataForStandTesting():
        // woodlot1 is added first to correspond to woodlot1Id
        // and woodlot2 is added second to correspond to woodlot2Id
        // Same with stand1 and stand2.
        // Same with quadrats
        // used this populator because it allows for cascade testing since there is data at
        // every level from Woodlot to Tree

        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 2);
        assertTrue(openHelper.getNumWoodlotsInDataBase(1) == 2);
        openHelper.removeWoodlotFromDataBase(1);
        assertTrue(openHelper.getWoodlotImagesFromDataBase().size() == 1);
        assertTrue(openHelper.getNumWoodlotsInDataBase(1) == 1);
    }

        //TEST DEBUG//----------------------------------------------------------------------------------

    @Test
    public void testDebugReset(){
        openHelper.addWoodlotToDataBase(woodlot1Image);
        openHelper.addStandToWoodlot(stand1Image,1);
        openHelper.addQuadratToStand(populatedQuadrat1Image, stand1Id);
        openHelper.addTreeToQuadrat(treeImage1_a, quadrat1Id);
        assertTrue(!openHelper.getWoodlotImagesFromDataBase().isEmpty());
        assertTrue(!openHelper.dumpStandTable().isEmpty());
        assertTrue(!openHelper.dumpQuadratTable().isEmpty());
        assertTrue(!openHelper.dumpTreeTable().isEmpty());
        openHelper.reset();
        assertTrue(openHelper.getWoodlotImagesFromDataBase().isEmpty());
        assertTrue(openHelper.dumpStandTable().isEmpty());
        assertTrue(openHelper.dumpQuadratTable().isEmpty());
        assertTrue(openHelper.dumpTreeTable().isEmpty());
    }
}
