package com.example.drew.test1;

/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * Class that takes specific strings, and returns the corresponding object.
 * Intended to be used with dropdown menus. These menus require strings,
 * however the database uses specific classes. InputParser provides
 * the link between the two.
 */

public class InputParser {

    /**
     * @param storageFactor
     * @return a StorageFactor object with the appropriate type.
     */
    public static StorageFactor parseStorageFactor(String storageFactor) {
        switch(storageFactor) {
            case "1":
                return StorageFactor.STORAGE_FACTOR_ONE;
            case "2":
                return StorageFactor.STORAGE_FACTOR_TWO;
            case "3":
                return StorageFactor.STORAGE_FACTOR_THREE;
            default:
                return null;
        }
    }

    /**
     * @param materialType
     * @return a MaterialType object with the appropriate type.
     */
    public static MaterialType parseMaterialType(String materialType) {
        switch(materialType) {
            case "Unacceptable Saw Material":
                return MaterialType.UNACCEPTABLE_SAW_MATERIAL;
            case "Acceptable Saw Material":
                return MaterialType.ACCEPTABLE_SAW_MATERIAL;
            case "Unacceptable Pulp Material":
                return MaterialType.UNACCEPTABLE_PULP_MATERIAL;
            case "Acceptable Pulp Material":
                return MaterialType.ACCEPTABLE_PULP_MATERIAL;
            default:
                return null;
        }
    }

    /**
     * @param species
     * @return a Species object with the appropriate type.
     */
    public static Species parseSpecies(String species) {
        switch(species) {
            case "American Beech":
                return Species.AMERICAN_BEECH;
            case "Balsam Fir":
                return Species.BALSAM_FIR;
            case "Black Cherry":
                return Species.BLACK_CHERRY;
            case "Black Spruce":
                return Species.BLACK_SPRUCE;
            case "Eastern Hemlock":
                return Species.EASTERN_HEMLOCK;
            case "Eastern Larch":
                return Species.EASTERN_LARCH;
            case "Eastern White Cedar":
                return Species.EASTERN_WHITE_CEDAR;
            case "Generic Hard Wood":
                return Species.GENERIC_HARD_WOOD;
            case "Generic Soft Wood":
                return Species.GENERIC_SOFT_WOOD;
            case "Grey Birch":
                return Species.GREY_BIRCH;
            case "Iron Wood":
                return Species.IRON_WOOD;
            case "Jack Pine":
                return Species.JACK_PINE;
            case "Large Toothed Aspen":
                return Species.LARGE_TOOTHED_ASPEN;
            case "Red Maple":
                return Species.RED_MAPLE;
            case "Red Oak":
                return Species.RED_OAK;
            case "Red Pine":
                return Species.RED_PINE;
            case "Red Spruce":
                return Species.RED_SPRUCE;
            case "Sugar Maple":
                return Species.SUGAR_MAPLE;
            case "Trembling Aspen":
                return Species.TREMBLING_ASPEN;
            case "White Ash":
                return Species.WHITE_ASH;
            case "White Birch":
                return Species.WHITE_BIRCH;
            case "White Pine":
                return Species.WHITE_PINE;
            case "White Spruce":
                return Species.WHITE_SPRUCE;
            default:
                return Species.YELLOW_BIRCH;
        }
    }
}
