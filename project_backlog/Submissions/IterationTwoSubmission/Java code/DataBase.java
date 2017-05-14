package com.example.drew.test1;
import java.util.ArrayList;

/**
 * DATABASE
 * @author mbelzileha
 *
 */

public class DataBase {

    private ArrayList<Woodlot> woodlots;

    public DataBase(){
        this.woodlots = new ArrayList<Woodlot>();
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
}