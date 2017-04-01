package com.example.drew.test1;

/**
 * Created by Jen on 15/03/2017.
 *
 * TREE INFO
 *
 * READ-ONLY CLASS HOLDING TREE DATA
 *
 */

public class TreeImage {
    private double dbh;
    private Species species;
    private StorageFactor storageFactor;
    private MaterialType materialType;
    private Integer id;
    private Integer parentId;

    //CONSTRUCTORS// -------------------------------------------------------------------------------

    public TreeImage(double dbh, Species species, StorageFactor storageFactor,   //DataBase output constructor
                     MaterialType materialType, int id, int parentId){
        this.dbh = dbh;
        this.species = species;
        this.storageFactor = storageFactor;
        this.materialType = materialType;
        this.id = id;
        this.parentId = parentId;
    }

    public TreeImage(double dbh, Species species, StorageFactor storageFactor, //OpenHelper input constructor
                     MaterialType materialType){
        this.dbh = dbh;
        this.species = species;
        this.storageFactor = storageFactor;
        this.materialType = materialType;
        this.parentId = null;
        this.id = null;
    }

    //ACCESSORS// ----------------------------------------------------------------------------------

    public double getDbh() {
        return dbh;
    }

    public Species getSpecies() {
        return species;
    }

    public StorageFactor getStorageFactor() {
        return storageFactor;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }
}
