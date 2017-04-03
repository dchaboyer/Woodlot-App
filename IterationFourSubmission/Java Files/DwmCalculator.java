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
     * @param quadratImage
     * @return
     */
    public static double calculateQuadratCarbon(QuadratImage quadratImage){
        double totalCarbon = 0.0;
        double aboveABP, rootsABP, totalABP, currCarbon;
        for (TreeImage treeImage: quadratImage.getTrees()){
            aboveABP = calculateAbp(treeImage);
            if(treeImage.getSpecies().isHardwood())
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

    public static double calculateCarbonStand(StandImage stand) {
        double carbon = 0.0;
        for (QuadratImage quadrat: stand.getQuadratImages()) {
            carbon += calculateQuadratCarbon(quadrat);
        }
        int quadratsCompleted = getNumberOfCompletedQuadrats(stand);
        double sampledSize = quadratsCompleted * QUADRAT_AREA;
        double standSize = stand.getArea() * METERS_IN_HECTARE;
        carbon = carbon * (standSize/sampledSize);
        return carbon;
    }

    //HELPERS//-------------------------------------------

    /**
     * Calculates the Allometric Biomass Prediction for a given tree
     * @param treeImage
     * @return abp
     */
    private static double calculateAbp(TreeImage treeImage) {
        AbpEquation abpEquation = treeImage.getSpecies().getAbpEquation();
        double dbh = treeImage.getDbh();

        return abpEquation.calculate(dbh);
    }

    /**
     * Returns number of compelted quadrats
     * @return numCompleted
     */
    private static int getNumberOfCompletedQuadrats(StandImage standImage){
        int count = 0;
        for (QuadratImage quadratImage : standImage.getQuadratImages()){
            if (quadratImage.isComplete()){
                count ++;
            }
        }

        return count;
    }


}