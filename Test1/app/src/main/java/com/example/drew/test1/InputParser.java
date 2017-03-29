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
        for(Species currSpecies : Species.values())
            if(currSpecies.getName().equals(species))
                return currSpecies;
        return null;
    }
}
