package com.example.drew.test1;

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
    public static double calculateQuadratCarbon(Quadrat quadrat){
        double totalCarbon = 0.0;
        double aboveABP, rootsABP, totalABP, currCarbon;
        for (Tree tree: quadrat.getTrees()){
            aboveABP = calculateAbp(tree);
            if(tree.getSpecies().isHardwood())
            {
                rootsABP = 1.576 * (Math.pow(aboveABP, 0.615));
            }
            else
            {
                rootsABP = aboveABP * 0.22;
            }
            totalABP = aboveABP + rootsABP;
            currCarbon = totalABP * 0.47;
            totalCarbon += currCarbon;
        }

        return totalCarbon;
    }

    public static double calculateCarbonStand(Stand stand) {
        double carbon = 0.0;
        for (Quadrat quadrat: stand.getQuadrats()) {
            carbon += calculateQuadratCarbon(quadrat);
        }
        int quadratsCompleted = stand.getCompletedQuadrats();
        double sampledSize = quadratsCompleted * QUADRAT_AREA;
        double standSize = stand.getArea() * METERS_IN_HECTARE;
        carbon = carbon * (standSize/sampledSize);
        return carbon;
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