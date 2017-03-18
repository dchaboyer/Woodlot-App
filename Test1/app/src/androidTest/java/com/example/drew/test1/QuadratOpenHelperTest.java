package com.example.drew.test1;

import java.util.List;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Mathieu Belzile-Ha on 15/03/2017.
 */

@RunWith(AndroidJUnit4.class)
public class QuadratOpenHelperTest {

    private static TreeImage treeImage1_a = new TreeImage(7.8, Species.YELLOW_BIRCH,
            StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    private static TreeImage treeImage1_b = new TreeImage(2.52, Species.EASTERN_LARCH,
            StorageFactor.STORAGE_FACTOR_ONE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    private static TreeImage treeImage2_a = new TreeImage(3.14, Species.BLACK_CHERRY,
            StorageFactor.STORAGE_FACTOR_TWO,MaterialType.UNACCEPTABLE_SAW_MATERIAL);

    private static TreeImage treeImage2_b = new TreeImage(8.01, Species.GENERIC_HARD_WOOD,
            StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    @Test
    public void initTest(){
        Coordinate testCoord = new Coordinate(0.3,7.34);
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger debugger = new QuadratTableDebugger(appContext);

        debugger.reset();
        debugger.addQuadrat(testCoord);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);

        assertTrue(quadratOpenHelper.exists(1));
        assertFalse(quadratOpenHelper.exists(2));
    }

    @Test
    public void getCoordinatesTest(){
        Coordinate testCoord = new Coordinate(1.3,8.41);

        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger editor = new QuadratTableDebugger(appContext);

        editor.reset();
        editor.addQuadrat(testCoord);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);
        Coordinate coord = quadratOpenHelper.getCoordinates(1);
        assertTrue(coord.equals(testCoord));

        try{
            quadratOpenHelper.getCoordinates(51);
        } catch(QuadratNotFoundException e){
            return;
        }
        fail();
    }

    @Test
    public void setCoordinatesTest(){
        Coordinate origCoord = new Coordinate(0.3,7.34);
        Coordinate newCoord = new Coordinate(37.1,2.0008);

        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger editor = new QuadratTableDebugger(appContext);

        editor.reset();
        editor.addQuadrat(origCoord);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);
        quadratOpenHelper.setCoordinates(newCoord,1);
        assertTrue(quadratOpenHelper.getCoordinates(1).equals(newCoord));

        try{
            quadratOpenHelper.setCoordinates(newCoord, 51);
        } catch(QuadratNotFoundException e){
            return;
        }
        fail();
    }

    @Test
    public void getTreesTest(){
        Coordinate testCoord1 = new Coordinate(1.3,8.41);
        Coordinate testCoord2 = new Coordinate(67.8,800.13);
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger quadratDebugger = new QuadratTableDebugger(appContext);
        TreeTableDebugger treeDebugger = new TreeTableDebugger(appContext);
        TreeImageDebugger treeImageDebugger = new TreeImageDebugger();

        quadratDebugger.reset();
        treeDebugger.reset();

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);

        assertTrue(quadratOpenHelper.getTrees(1).size() == 0);
        assertTrue(quadratOpenHelper.getTrees(2).size() == 0);
        treeDebugger.addTree(treeImage2_a, 2);
        assertTrue(quadratOpenHelper.getTrees(1).size() == 0);
        assertTrue(quadratOpenHelper.getTrees(2).size() == 1);
        treeDebugger.addTree(treeImage1_a, 1);
        assertTrue(quadratOpenHelper.getTrees(1).size() == 1);
        assertTrue(quadratOpenHelper.getTrees(2).size() == 1);
        treeDebugger.addTree(treeImage1_b, 1);
        assertTrue(quadratOpenHelper.getTrees(1).size() == 2);
        assertTrue(quadratOpenHelper.getTrees(2).size() == 1);
        treeDebugger.addTree(treeImage2_b, 2);
        assertTrue(quadratOpenHelper.getTrees(1).size() == 2);
        assertTrue(quadratOpenHelper.getTrees(2).size() == 2);

        List<TreeImage> quadrat1Trees = quadratOpenHelper.getTrees(1);
        List<TreeImage> quadrat2Trees = quadratOpenHelper.getTrees(2);

        assertTrue(treeImageDebugger.same(quadrat1Trees.get(0), treeImage1_a, 2, 1));
        assertTrue(treeImageDebugger.same(quadrat1Trees.get(1), treeImage1_b, 3, 1));
        assertTrue(treeImageDebugger.same(quadrat2Trees.get(0), treeImage2_a, 1, 2));
        assertTrue(treeImageDebugger.same(quadrat2Trees.get(1), treeImage2_b, 4, 2));

        try{
            quadratOpenHelper.getTrees(51);
        } catch(QuadratNotFoundException e){
            return;
        }
        fail();
    }

    @Test
    public void getTreeTest(){  //TODO: automated testing for table format exception throw
        Coordinate testCoord1 = new Coordinate(1.3,8.41);
        Coordinate testCoord2 = new Coordinate(67.8,800.13);
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger quadratDebugger = new QuadratTableDebugger(appContext);
        TreeTableDebugger treeDebugger = new TreeTableDebugger(appContext);
        TreeImageDebugger treeImageDebugger = new TreeImageDebugger();

        quadratDebugger.reset();
        treeDebugger.reset();

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);

        treeDebugger.addTree(treeImage2_a, 2);
        treeDebugger.addTree(treeImage1_a, 1);
        treeDebugger.addTree(treeImage1_b, 1);
        treeDebugger.addTree(treeImage2_b, 2);

        List<TreeImage> quadrat1Trees = quadratOpenHelper.getTrees(1);
        List<TreeImage> quadrat2Trees = quadratOpenHelper.getTrees(2);

        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(0,1), quadrat1Trees.get(0)));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(1,1), quadrat1Trees.get(1)));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(0,2), quadrat2Trees.get(0)));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(1,2), quadrat2Trees.get(1)));

        try {
            quadratOpenHelper.getTree(52, 2);
        } catch(TableIndexOutOfBoundsException e1){
            /*
            treeDebugger.alterTable();
            try {
                quadratOpenHelper.getTree(0,1);
            } catch(InvalidTableFormatException e2){
                return;
            }
            fail();*/ //TODO: automated testing for this exception throw
            try{
                quadratOpenHelper.getTree(0, 51);
            } catch(QuadratNotFoundException e){
                return;
            }
            fail();
        }
        fail();
    }

    @Test
    public void addTreeTest(){
        Coordinate testCoord1 = new Coordinate(1.3,8.41);
        Coordinate testCoord2 = new Coordinate(67.8,800.13);
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger quadratDebugger = new QuadratTableDebugger(appContext);
        TreeTableDebugger treeDebugger = new TreeTableDebugger(appContext);
        TreeImageDebugger treeImageDebugger = new TreeImageDebugger();

        quadratDebugger.reset();
        treeDebugger.reset();

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        QuadratOpenHelper quadratOpenHelper = new QuadratOpenHelper(appContext);

        assertTrue(treeDebugger.getTrees().size() == 0);

        quadratOpenHelper.addTree(treeImage1_a,1);
        assertTrue(treeDebugger.getTrees().size() == 1
                && quadratOpenHelper.getTrees(1).size() == 1
                && quadratOpenHelper.getTrees(2).size() == 0);

        quadratOpenHelper.addTree(treeImage2_a,2);
        assertTrue(treeDebugger.getTrees().size() == 2
                && quadratOpenHelper.getTrees(1).size() == 1
                && quadratOpenHelper.getTrees(2).size() == 1);

        quadratOpenHelper.addTree(treeImage1_b, 1);
        assertTrue(treeDebugger.getTrees().size() == 3
                && quadratOpenHelper.getTrees(1).size() == 2
                && quadratOpenHelper.getTrees(2).size() == 1);

        quadratOpenHelper.addTree(treeImage2_b, 2);
        assertTrue(treeDebugger.getTrees().size() == 4
                && quadratOpenHelper.getTrees(1).size() == 2
                && quadratOpenHelper.getTrees(2).size() == 2);

        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(0,1), treeImage1_a, 1, 1));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(1,1), treeImage1_b, 3, 1));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(0,2), treeImage2_a, 2, 2));
        assertTrue(treeImageDebugger.same(quadratOpenHelper.getTree(1,2), treeImage2_b, 4, 2));

        try{
            quadratOpenHelper.addTree(treeImage2_a, 51);
        } catch(QuadratNotFoundException e){
            return;
        }
        fail();
    }
}
