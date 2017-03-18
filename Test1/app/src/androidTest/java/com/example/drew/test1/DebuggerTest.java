package com.example.drew.test1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.fail;

import static org.junit.Assert.*;

/**
 * Created by Mathieu Belzile-Ha on 15/03/2017.
 */

@RunWith(AndroidJUnit4.class)
public class DebuggerTest {

    @Test
    public void testQuadratDebugger(){
        Coordinate testCoord = new Coordinate(1.3,8.41);
        Context appContext = InstrumentationRegistry.getTargetContext();

        QuadratTableDebugger debugger = new QuadratTableDebugger(appContext);

        debugger.reset();
        assertTrue(debugger.getIds().size() == 0);

        debugger.addQuadrat(testCoord);
        assertTrue(debugger.getIds().size() == 1);    //test add

        debugger.reset();
        assertTrue(debugger.getIds().size() == 0);    //test reset

        debugger.addQuadrat(testCoord);
        debugger.addQuadrat(testCoord);                 //test add post-reset
        assertTrue(debugger.getIds().size() == 2);
        assertTrue(debugger.getIds().get(0) == 1);    //test ids
        assertTrue(debugger.getIds().get(1) == 2);
    }

    @Test
    public void testTreeDebugger(){
        Coordinate testCoord1 = new Coordinate(1.3,8.41);
        Coordinate testCoord2 = new Coordinate(67.8,800.13);
        Context appContext = InstrumentationRegistry.getTargetContext();

        QuadratTableDebugger quadratDebugger = new QuadratTableDebugger(appContext);
        TreeTableDebugger treeDebugger = new TreeTableDebugger(appContext);

        treeDebugger.reset();
        quadratDebugger.reset();
        assertTrue(treeDebugger.getTrees().size() == 0);

        TreeImage treeImage = new TreeImage(2.0, Species.EASTERN_LARCH,
                StorageFactor.STORAGE_FACTOR_ONE,MaterialType.ACCEPTABLE_PULP_MATERIAL);
        treeDebugger.addTree(treeImage,15);
        assertTrue(treeDebugger.getTrees().size() == 0);    //test add tree to invalid quadrat

        quadratDebugger.addQuadrat(testCoord1);
        treeImage = new TreeImage(2.0, Species.AMERICAN_BEECH,
                StorageFactor.STORAGE_FACTOR_ONE,MaterialType.ACCEPTABLE_PULP_MATERIAL);
        treeDebugger.addTree(treeImage,1);
        assertTrue(treeDebugger.getTrees().size() == 1);    //test add tree to valid quadrat

        treeDebugger.reset();
        assertTrue(treeDebugger.getTrees().size() == 0);    //test reset

        treeImage = new TreeImage(2.0, Species.EASTERN_LARCH,
                StorageFactor.STORAGE_FACTOR_ONE,MaterialType.ACCEPTABLE_PULP_MATERIAL);
        treeDebugger.addTree(treeImage, 5);
        assertTrue(treeDebugger.getTrees().size() == 0);    //test add tree to invalid quadrat post-reset

        quadratDebugger.addQuadrat(testCoord1);
        treeImage = new TreeImage(7.8, Species.YELLOW_BIRCH,
                StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL); //Test with normal TreeImage constructor for adding to db
        treeDebugger.addTree(treeImage, 1);

        quadratDebugger.addQuadrat(testCoord2);
        treeImage = new TreeImage(3.14, Species.BLACK_CHERRY,
                StorageFactor.STORAGE_FACTOR_TWO,MaterialType.ACCEPTABLE_SAW_MATERIAL, 6, 2);   //Test to make sure the db constructor cannot be used to override id
        treeDebugger.addTree(treeImage, 2);
        assertTrue(treeDebugger.getTrees().size() == 2);    //test add tree to valid quadrat post-reset

        TreeImage tree1 = treeDebugger.getTrees().get(0);
        TreeImage tree2 = treeDebugger.getTrees().get(1);

        assertTrue(tree1.getDbh() == 7.8); //test data conservation
        assertTrue(tree1.getSpecies().equals(Species.YELLOW_BIRCH));
        assertTrue(tree1.getStorageFactor().equals(StorageFactor.STORAGE_FACTOR_THREE));
        assertTrue(tree1.getMaterialType().equals(MaterialType.UNACCEPTABLE_PULP_MATERIAL));
        assertTrue(tree1.getId() == 1);
        assertTrue(tree1.getParentId() == 1);

        assertTrue(tree2.getDbh() == 3.14); //test data conservation
        assertTrue(tree2.getSpecies().equals(Species.BLACK_CHERRY));
        assertTrue(tree2.getStorageFactor().equals(StorageFactor.STORAGE_FACTOR_TWO));
        assertTrue(tree2.getMaterialType().equals(MaterialType.ACCEPTABLE_SAW_MATERIAL));
        assertTrue(tree2.getId() == 2);
        assertTrue(tree2.getParentId() == 2);
    }
}
