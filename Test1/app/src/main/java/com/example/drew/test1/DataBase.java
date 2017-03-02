package com.example.drew.test1;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mbelzileha
 *
 */

public class DataBase {

    private List<Woodlot> woodlots;

    public DataBase(){
        this.woodlots = new LinkedList<Woodlot>();
    }

    //WOODLOT//-------------------------------------------

    public void addWoodlot(Woodlot woodlot){
        this.woodlots.add(woodlot);
    }

    public void removeWoodlot(Woodlot woodlot){
        this.woodlots.remove(woodlot);
    }
}