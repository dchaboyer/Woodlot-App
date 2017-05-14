package com.example.drew.wccc;
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

    private Species species1;
    private Species species2;
    private Species species3;
    private Species species4;
    private Species species5;
    private Double area;
    private Integer age;
    private Double height;
    private String notes;

    public Stand(Double area, int age, double height, int numQuadrats){
        this.quadrats = new ArrayList<Quadrat>();
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

    public void setSpecies(Species species, int rank){
        switch(rank){
            case 1:
                this.species1 = species;
                break;
            case 2:
                this.species2 = species;
                break;
            case 3:
                this.species3 = species;
                break;
            case 4:
                this.species4 = species;
                break;
            case 5:
                this.species5 = species;
                break;
        }
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setHeight(double height){
        this.height = height;
    }

    //GET//--------------------------------------------

    public Species getSpecies(int rank){
        switch(rank){
            case 1:
                return this.species1;
            case 2:
                return this.species2;
            case 3:
                return this.species3;
            case 4:
                return this.species4;
            case 5:
                return this.species5;
            default:
                return null;

        }
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

    public Double getArea() {
        return area;
    }

    public void setArea(double newArea) {
        area = newArea;
    }

    public int getCompletedQuadrats() {
        int numCompleted = 0;
        for(Quadrat quadrat: quadrats) {
            if(quadrat.getCompletionStatus()) {
                numCompleted++;
            }
        }
        return numCompleted;
    }
}

