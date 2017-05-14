package com.example.drew.wccc;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mbelzileha
 *
 */

public class Woodlot {


    private ArrayList<Stand> stands;
    private String name;

    public Woodlot(String name, int numStands)
    {
        this.stands = new ArrayList<Stand>();
        for(int i = 0; i < numStands; i++) {
            stands.add(new Stand());
        }
        this.name = name;
    }

    //STAND//------------------------------------------

    public void addStand(Stand stand){
        this.stands.add(stand);
    }

    public void removeStand(Stand stand){
        this.stands.remove(stand);
    }

    //GET//--------------------------------------------

    public ArrayList<Stand> getStands(){
        return stands;
    }

    public Stand getStand(int index){
        return this.stands.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public int getNumStands() {
        return stands.size();
    }

    public int setNumStands(int numStands) {
        if(numStands < 1)
            return -1;
        if(numStands > stands.size()) {
            for(int i = stands.size(); i < numStands; i++) {
                stands.add(new Stand());
            }
        }
        else if(numStands < stands.size()) {
            while(numStands < stands.size()) {
                stands.remove(stands.size() - 1);
            }
        }
        return 1;
    }
}
