/*
package com.example.drew.test1;

import java.util.List;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

*/
/**
 * Created by Mathieu Belzile-Ha on 15/03/2017.
 *//*


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

        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(appContext);
    }

    @Test
    public void getCoordinatesTest(){
        Coordinate testCoord = new Coordinate(1.3,8.41);

        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger editor = new QuadratTableDebugger(appContext);

        editor.reset();
        editor.addQuadrat(testCoord);

        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(appContext);
        Coordinate coord = dataBaseOpenHelper.getQuadratCoordinates(1);
        assertTrue(coord.equals(testCoord));
    }

    @Test
    public void setCoordinatesTest(){
        Coordinate origCoord = new Coordinate(0.3,7.34);
        Coordinate newCoord = new Coordinate(37.1,2.0008);

        Context appContext = InstrumentationRegistry.getTargetContext();
        QuadratTableDebugger editor = new QuadratTableDebugger(appContext);

        editor.reset();
        editor.addQuadrat(origCoord);

        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(appContext);
        dataBaseOpenHelper.setQuadratCoordinates(newCoord,1);
        assertTrue(dataBaseOpenHelper.getQuadratCoordinates(1).equals(newCoord));
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

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        DataBaseOpenHelper quadratOpenHelper = new DataBaseOpenHelper(appContext);

        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(1).size() == 0);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(2).size() == 0);
        treeDebugger.addTree(treeImage2_a, 2);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(1).size() == 0);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(2).size() == 1);
        treeDebugger.addTree(treeImage1_a, 1);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(1).size() == 1);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(2).size() == 1);
        treeDebugger.addTree(treeImage1_b, 1);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(1).size() == 2);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(2).size() == 1);
        treeDebugger.addTree(treeImage2_b, 2);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(1).size() == 2);
        assertTrue(quadratOpenHelper.getTreeImagesFromQuadrat(2).size() == 2);

        List<TreeImage> quadrat1Trees = quadratOpenHelper.getTreeImagesFromQuadrat(1);
        List<TreeImage> quadrat2Trees = quadratOpenHelper.getTreeImagesFromQuadrat(2);

        assertTrue(treeImageDebugger.same(quadrat1Trees.get(0), treeImage1_a, 2, 1));
        assertTrue(treeImageDebugger.same(quadrat1Trees.get(1), treeImage1_b, 3, 1));
        assertTrue(treeImageDebugger.same(quadrat2Trees.get(0), treeImage2_a, 1, 2));
        assertTrue(treeImageDebugger.same(quadrat2Trees.get(1), treeImage2_b, 4, 2));
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

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(appContext);

        treeDebugger.addTree(treeImage2_a, 2);
        treeDebugger.addTree(treeImage1_a, 1);
        treeDebugger.addTree(treeImage1_b, 1);
        treeDebugger.addTree(treeImage2_b, 2);

        List<TreeImage> quadrat1Trees = dataBaseOpenHelper.getTreeImagesFromQuadrat(1);
        List<TreeImage> quadrat2Trees = dataBaseOpenHelper.getTreeImagesFromQuadrat(2);

        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(0,1), quadrat1Trees.get(0)));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(1,1), quadrat1Trees.get(1)));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(0,2), quadrat2Trees.get(0)));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(1,2), quadrat2Trees.get(1)));
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

        quadratDebugger.addQuadrat(testCoord1);
        quadratDebugger.addQuadrat(testCoord2);

        DataBaseOpenHelper dataBaseOpenHelper = new DataBaseOpenHelper(appContext);

        assertTrue(treeDebugger.getTrees().size() == 0);

        dataBaseOpenHelper.addTreeToQuadrat(treeImage1_a,1);
        assertTrue(treeDebugger.getTrees().size() == 1
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(1).size() == 1
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(2).size() == 0);

        dataBaseOpenHelper.addTreeToQuadrat(treeImage2_a,2);
        assertTrue(treeDebugger.getTrees().size() == 2
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(1).size() == 1
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(2).size() == 1);

        dataBaseOpenHelper.addTreeToQuadrat(treeImage1_b, 1);
        assertTrue(treeDebugger.getTrees().size() == 3
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(1).size() == 2
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(2).size() == 1);

        dataBaseOpenHelper.addTreeToQuadrat(treeImage2_b, 2);
        assertTrue(treeDebugger.getTrees().size() == 4
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(1).size() == 2
                && dataBaseOpenHelper.getTreeImagesFromQuadrat(2).size() == 2);

        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(0,1), treeImage1_a, 1, 1));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(1,1), treeImage1_b, 3, 1));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(0,2), treeImage2_a, 2, 2));
        assertTrue(treeImageDebugger.same(dataBaseOpenHelper.getTreeImageFromQuadrat(1,2), treeImage2_b, 4, 2));
    }
}
*/
