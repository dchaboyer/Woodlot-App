package com.example.drew.test1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jen on 30/03/2017.
 */

public class QuadratImage {

    private Integer id;
    private Coordinate coordinate;
    private List<TreeImage> trees;
    private Integer parentId;

    public QuadratImage(Coordinate coordinate) {
        this.id = null;
        this.coordinate = coordinate;
        this.trees = new LinkedList<TreeImage>();
        this.parentId = null;
    }

    public QuadratImage(int id, Coordinate coordinate, int parentId) {
        this.id = id;
        this.coordinate = coordinate;
        this.trees = new LinkedList<TreeImage>();
        this.parentId = parentId;
    }

    public Integer getId() {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
