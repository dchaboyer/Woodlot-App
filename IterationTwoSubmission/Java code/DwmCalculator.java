package com.example.drew.test1;
import java.util.List;

/**
 * DRY WEIGHT MASS CALCULATOR
 * Created by Mathieu on 01/03/2017.
 *
 * Class with purpose to evaluate the dry weight mass for a given set of trees.
 */

public class DwmCalculator {

    //CALCULATIONS//--------------------------------------

    /**
     * Calculate DWM for a given quadrat
     * @param quadrat
     * @return
     */
    public static double calculateDwm(Quadrat quadrat){
        double dwm = 0.0;

        for (Tree tree: quadrat.getTrees()){
            dwm += calculateAbp(tree);
        }

        return dwm;
    }

    public static double calculateDwmStand(Stand stand) {
        double dwm = 0.0;

        for (Quadrat quadrat: stand.getQuadrats()) {
            dwm += calculateDwm(quadrat);
        }
        return dwm;
    }

    //HELPERS//-------------------------------------------

    /**
     * Calculates the Allometric Biomass Prediction for a given tree
     * @param tree
     * @return abp
     */
    private static double calculateAbp(Tree tree) {
        AbpEquation abpEquation = tree.getSpecies().getAbpEquation();
        double dbh = tree.getDbh();

        return abpEquation.calculate(dbh);
    }
}