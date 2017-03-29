package com.example.drew.test1;
import java.util.List;

/**
 * DRY WEIGHT MASS CALCULATOR
 * Created by Mathieu on 01/03/2017.
 *
 * Class with purpose to evaluate the dry weight mass for a given set of trees.
 */

public class DwmCalculator {

    public static final double QUADRAT_AREA = 400;
    public static final int METERS_IN_HECTARE = 10000;

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
        int quadratsCompleted = stand.getCompletedQuadrats();
        double sampledSize = quadratsCompleted * QUADRAT_AREA;
        double standSize = stand.getArea() * METERS_IN_HECTARE;
        dwm = dwm * (standSize/sampledSize);
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