package com.example.drew.test1;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mbelzileha
 *
 */

public class Woodlot {


    private ArrayList<Stand> stands;

    public Woodlot(){
        this.stands = new ArrayList<Stand>();
    }

    //STAND//------------------------------------------

    public void addStand(Stand stand){
        this.stands.add(stand);
    }

    public void removeStand(Stand stand){
        this.stands.remove(stand);
    }

    //GET//--------------------------------------------

    public List<Stand> getStands(){
        ArrayList<Stand> output = new ArrayList<Stand>();

        for (Stand stand: this.stands){
            output.add(stand);
        }

        return output;
    }

    public Stand getStand(int index){
        return this.stands.get(index);
    }

}
