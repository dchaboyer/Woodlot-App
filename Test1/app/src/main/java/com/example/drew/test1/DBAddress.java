package com.example.drew.test1;

/**
 * Created by Jen on 15/03/2017.
 */

public class DBAddress {

    private int woodlotIndex;
    private int standIndex;
    private int quadratIndex;
    private int treeIndex;

    public int getWoodlotIndex() {
        return woodlotIndex;
    }

    public int getStandIndex() {
        return standIndex;
    }

    public int getQuadratIndex() {
        return quadratIndex;
    }

    public int getTreeIndex() {
        return treeIndex;
    }

    public DBAddress(int woodlotIndex, int standIndex, int quadratIndex, int treeIndex){
        this.woodlotIndex = woodlotIndex;
        this.standIndex = standIndex;
        this.quadratIndex = quadratIndex;
        this.treeIndex = treeIndex;
    }


}
