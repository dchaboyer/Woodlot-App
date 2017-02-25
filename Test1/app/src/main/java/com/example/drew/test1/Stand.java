package com.example.drew.test1;
import java.util.List;
import java.util.LinkedList;

/**
 * STAND
 *
 * Holds all data from a Stand.
 *
 * @author mbelzileha
 *
 */

public class Stand {

    private List<Quadrat> quadrats;

    private Species species;
    private int age;
    private double height;

    public Stand(Species species, int age, double height){
        this.quadrats = new LinkedList<Quadrat>();

        this.species = species;
        this.age = age;
        this.height = height;
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
        LinkedList<Quadrat> output = new LinkedList<Quadrat>();

        for (Quadrat quadrat: this.quadrats){
            output.add(quadrat);
        }

        return output;
    }
}

