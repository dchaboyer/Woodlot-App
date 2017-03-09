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
    private Double area;
    private Integer age;
    private Double height;
    private String notes;

    public Stand(Species species, Double area, int age, double height, int numQuadrats){
        this.quadrats = new ArrayList<Quadrat>();
        this.species = species;
        this.age = age;
        this.area = area;
        this.height = height;
        this.notes = null;
        for(int i = 0; i < numQuadrats; i++) {
            quadrats.add(new Quadrat());
        }
    }

    public Stand()
    {
        this.quadrats = new ArrayList<Quadrat>();
        this.species = null;
        this.age = null;
        this.height = null;
        this.notes = null;
        this.area = null;
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

    public int setNumQuadrats(int numQuadrats)
    {
        if(numQuadrats < 1)
            return -1;
        if(numQuadrats > quadrats.size()) {
            for(int i = quadrats.size(); i < numQuadrats; i++) {
                quadrats.add(new Quadrat());
            }
        }
        else if(numQuadrats < quadrats.size())
        {
            while (numQuadrats < quadrats.size()) {
                quadrats.remove(quadrats.size() - 1);
            }
        }
        return 1;
    }

    public int getNumQuadrats() {
        return quadrats.size();
    }

}

