package com.example.drew.test1;
import java.util.ArrayList;

/**
 * DATABASE
 * @author mbelzileha
 *
 */

public class DataBase {

    private ArrayList<Woodlot> woodlots;
    Integer currWoodlot;
    Integer currStand;
    Integer currQuadrat;
    Integer currTree;

    public DataBase(){
        this.woodlots = new ArrayList<Woodlot>();
        currWoodlot = null;
        currStand = null;
        currQuadrat = null;
        currTree = null;
    }

    public void addWoodlot(Woodlot woodlot){
        this.woodlots.add(woodlot);
    }

    public void removeWoodlot(Woodlot woodlot){
        this.woodlots.remove(woodlot);
    }

    public Woodlot getWoodlot(int index){
        return woodlots.get(index);
    }

    public int getNumWoodlots() {
        return woodlots.size();
    }

    public int getCurrWoodlot() {
        return currWoodlot;
    }

    public void setCurrWoodlot(int index) {
        currWoodlot = index;
    }

    public int getCurrStand() {
        return currStand;
    }

    public void setCurrStand(int newStand) {
        this.currStand = newStand;
    }

    public int getCurrQuadrat() {
        return currQuadrat;
    }

    public void setCurrQuadrat(int newQuadrat) {
        currQuadrat = newQuadrat;
    }

    public int getCurrTree() {
        return currTree;
    }

    public void setCurrTree(int newTree) {
        currTree = newTree;
    }

}