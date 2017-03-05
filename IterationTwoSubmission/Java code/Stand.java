package com.example.drew.test1;
import java.util.List;
import java.util.ArrayList;

/**
 * STAND
 *
 * Holds all data from a Stand.
 *
 * @author mbelzileha
 *
 */

public class Stand {

    private ArrayList<Quadrat> quadrats;

    private Species species;
    private int age;
    private double height;
    private String notes;
    private Integer currQuadrat;

    public Stand(Species species, int age, double height){
        this.quadrats = new ArrayList<Quadrat>();

        this.species = species;
        this.age = age;
        this.height = height;
        this.notes = null;
        this.currQuadrat = null;
    }

    //QUADRAT//----------------------------------------

    public void addQuadrat(Quadrat quadrat){
        this.quadrats.add(quadrat);
    }

    public void removeQuadrat(Quadrat quadrat){
        this.quadrats.remove(quadrat);
    }

    //SET//--------------------------------------------

    public void setSpecies(Species species){
        this.species = species;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setHeight(double height){
        this.height = height;
    }

    //GET//--------------------------------------------

    public Species getSpecies(){
        return this.species;
    }

    public int getAge(){
        return this.age;
    }

    public double getHeight(){
        return this.height;
    }

    public List<Quadrat> getQuadrats(){
        ArrayList<Quadrat> output = new ArrayList<Quadrat>();

        for (Quadrat quadrat: this.quadrats){
            output.add(quadrat);
        }

        return output;
    }

    public Quadrat getQuadrat(int index){
        return this.quadrats.get(index);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String newNotes) {
        notes = newNotes;
    }

    public int getCurrQuadrat() {
        return currQuadrat;
    }

    public void setCurrQuadrat(int newQuadrat) {
        currQuadrat = newQuadrat;
    }
}

