package com.example.drew.test1;
import java.util.HashMap;

/**
 * ALLOMETRIC BIOMASS PREDICTION CALCULATOR
 *
 * Class calculates the ABP for a given tree.
 *
 * @author mbelzileha
 * Created by Jen on 25/02/2017.
 */

public class AbpCalculator {

    /**
     * returns the ABP for a given tree
     * @param tree
     * @return
     */
    public double calculateAbp(Tree tree){
        Species species = tree.getSpecies();
        AbpEquation equation = this.getEquationBySpecies(tree.getSpecies());
        return equation.evaluate(tree.getDbh());
    }

    //HELPERS//-------------------------------------------
    /**
     * Gets the appropriate equation for a given Species
     * @param species
     * @return
     */
    private AbpEquation getEquationBySpecies(Species species){

        AbpEquation equation = null;

        switch(species){
            case BALSAM_FIR:
                equation = new MiscAbpe(-2.2304,2.3263);
                break;
            case WHITE_SPRUCE:
                equation = new MiscAbpe(-1.8322,2.2413);
                break;
            case BLACK_SPRUCE:
                equation = new MiscAbpe(-1.3371,2.0707);
                break;
            case RED_SPRUCE:
                equation = new MiscAbpe(-1.7957,2.2417);
                break;
            case EASTERN_WHITE_CEDAR:
                equation = new MiscAbpe(-1.9615,2.1063);
                break;
            case EASTERN_LARCH:
                equation = new MiscAbpe(-2.3012,2.3853);
                break;
            case WHITE_PINE:
                equation = new MiscAbpe(-2.6177,2.4638);
                break;
            case RED_PINE:
                equation = new MiscAbpe(-2.6177,2.4638);
                break;
            case JACK_PINE:
                equation = new MiscAbpe(-2.6177,2.4638);
                break;
            case EASTERN_HEMLOCK:
                equation = new MiscAbpe(-2.3480,2.3876);
                break;
            case RED_MAPLE:
                equation = new MiscAbpe(-1.9702,2.3405);
                break;
            case SUGAR_MAPLE:
                equation = new MiscAbpe(-1.8760,2.3924);
                break;
            case YELLOW_BIRCH:
                equation = new MiscAbpe(-2.1306,2.4510);
                break;
            case WHITE_BIRCH:
                equation = new MiscAbpe(-2.0045,2.3634);
                break;
            case GREY_BIRCH:
                equation = new MiscAbpe(-2.2271,2.4513);
                break;
            case LARGE_TOOTHED_ASPEN:
                equation = new MiscAbpe(-2.3200,2.3773);
                break;
            case TREMBLING_ASPEN:
                equation = new MiscAbpe(-2.3778,2.4085);
                break;
            case IRON_WOOD:
                equation = new MiscAbpe(-2.2652,2.5349);
                break;
            case BLACK_CHERRY:
                equation = new MiscAbpe(-2.2118,2.4133);
                break;
            case AMERICAN_BEECH:
                equation = new MiscAbpe(-2.0705,2.4410);
                break;
            case RED_OAK:
                equation = new MiscAbpe(-2.0705,2.4410);
                break;
            case WHITE_ASH:
                equation = new MiscAbpe(-2.0314,2.3524);
                break;
            case GENERIC_HARD_WOOD:
                equation = new GenericAbpe(-2.46,1.01);
                break;
            case GENERIC_SOFT_WOOD:
                equation = new GenericAbpe(-2.41,1.01);
                break;
        }

        return equation;
    }
}
