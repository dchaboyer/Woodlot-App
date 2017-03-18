package com.example.drew.test1;

/**
 * Created by Jen on 16/03/2017.
 */

public class TreeImageDebugger {

    //TODO: add null security

    public boolean same(TreeImage treeImage1, TreeImage treeImage2){
        return (treeImage1.getSpecies().equals(treeImage2.getSpecies())
                    && treeImage1.getParentId() == treeImage2.getParentId()
                    && treeImage1.getDbh() == treeImage2.getDbh()
                    && treeImage1.getId() == treeImage2.getId()
                    && treeImage1.getMaterialType().equals(treeImage2.getMaterialType())
                    && treeImage1.getStorageFactor().equals(treeImage2.getStorageFactor()));
    }

    public boolean same(TreeImage treeImage1, TreeImage treeImage2, int id, int parentId){
        return (treeImage1.getSpecies().equals(treeImage2.getSpecies())
                && treeImage1.getParentId() == parentId
                && treeImage1.getDbh() == treeImage2.getDbh()
                && treeImage1.getId() == id
                && treeImage1.getMaterialType().equals(treeImage2.getMaterialType())
                && treeImage1.getStorageFactor().equals(treeImage2.getStorageFactor()));
    }
}
