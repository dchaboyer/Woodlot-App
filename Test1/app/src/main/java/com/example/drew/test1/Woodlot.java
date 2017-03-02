package com.example.drew.test1;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mbelzileha
 *
 */

public class Woodlot {


    private List<Stand> stands;

    public Woodlot(){
        this.stands = new LinkedList<Stand>();
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
        LinkedList<Stand> output = new LinkedList<Stand>();

        for (Stand stand: this.stands){
            output.add(stand);
        }

        return output;
    }

}
