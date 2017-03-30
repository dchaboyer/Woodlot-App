package com.example.drew.test1;

import java.util.List;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StandOpenHelperTest {
    private static Context appContext = InstrumentationRegistry.getTargetContext();

    private static TreeImage treeImage1_a = new TreeImage(7.8, Species.YELLOW_BIRCH,
            StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    private static TreeImage treeImage1_b = new TreeImage(2.52, Species.EASTERN_LARCH,
            StorageFactor.STORAGE_FACTOR_ONE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    private static TreeImage treeImage2_a = new TreeImage(3.14, Species.BLACK_CHERRY,
            StorageFactor.STORAGE_FACTOR_TWO,MaterialType.UNACCEPTABLE_SAW_MATERIAL);

    private static TreeImage treeImage2_b = new TreeImage(8.01, Species.GENERIC_HARD_WOOD,
            StorageFactor.STORAGE_FACTOR_THREE,MaterialType.UNACCEPTABLE_PULP_MATERIAL);

    private static Coordinate coord1 = new Coordinate(1.7, 9.31);

    private static QuadratImage quadratImage1 = new QuadratImage(1,coord1,1);

    @Before
    public void build(){
        quadratImage1.addTree(treeImage1_a);
        quadratImage1.addTree(treeImage1_b);
    }

    @After
    public void tearDown(){
        quadratImage1 = new QuadratImage(1,coord1,1);
    }
}
