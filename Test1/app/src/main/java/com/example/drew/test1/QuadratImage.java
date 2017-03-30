package com.example.drew.test1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jen on 30/03/2017.
 */

public class QuadratImage {

    private int id;
    private Coordinate coordinate;
    private List<TreeImage> trees;
    private int parentId;

    public QuadratImage(int id, Coordinate coordinate, int parentId) {
        this.id = id;
        this.coordinate = coordinate;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void addTree(TreeImage tree){
        this.trees.add(tree);
    }

    public List<TreeImage> getTrees() {
        LinkedList<TreeImage> treesOut = new LinkedList<TreeImage>();
        for (TreeImage tree : this.trees){
            treesOut.add(tree);
        }

        return treesOut;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
