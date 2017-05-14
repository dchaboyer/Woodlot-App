package com.example.drew.wccc;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

public class StandImage {
    private final String EMPTY_STRING = "";
    private final int NUM_SPECIES = 5;

    private Integer id;
    private Double area;
    private Integer age;
    private Double height;
    private Species[] commonSpecies;
    private Integer parentId;
    private String notes;

    private List<QuadratImage> quadratImages;

    public StandImage() {
        this.id = null;
        this.area = null;
        this.age = null;
        this.height = null;
        this.quadratImages = new LinkedList<QuadratImage>();
        this.commonSpecies = new Species[NUM_SPECIES];
        this.notes = EMPTY_STRING;
        this.parentId = null;
    }

    public StandImage(double area, Integer age, double height) {
        this.id = null;
        this.area = area;
        this.age = age;
        this.height = height;
        this.quadratImages = new LinkedList<QuadratImage>();
        this.commonSpecies = new Species[NUM_SPECIES];
        this.notes = EMPTY_STRING;
        this.parentId = null;
    }

    public StandImage(Integer id, Double area, Integer age, Double height, Integer parentId) {
        this.id = id;
        this.area = area;
        this.age = age;
        this.height = height;
        this.quadratImages = new LinkedList<QuadratImage>();
        this.commonSpecies = new Species[NUM_SPECIES];
        this.notes = EMPTY_STRING;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setCommonSpecies(Species species, int rank){
        if (rank > 0 && rank <= 5) {
            this.commonSpecies[rank - 1] = species;
        }
    }

    public void setCommonSpecies(Species[] inputSpecies){
        for (int i = 0; i < inputSpecies.length && i < NUM_SPECIES; i++){
            this.commonSpecies[i] = inputSpecies[i];
        }
    }

    public List<Species> getCommonSpecies(){
        LinkedList<Species> myCommonSpecies = new LinkedList<Species>();

        for (int i = 0; i < NUM_SPECIES; i ++){
            myCommonSpecies.add(this.commonSpecies[i]);
        }

        return myCommonSpecies;
    }

    public Species getCommonSpecies(int rank){
        if (rank < 1 || rank > 5){
            throw new InvalidSpeciesRankException();
        }

        return this.commonSpecies[rank -1];
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if (notes == null){
            throw new NullNoteException();
        }
        this.notes = notes;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void addQuadratImage(QuadratImage quadratImage){
        this.quadratImages.add(quadratImage);
    }

    public void removeQuadratImage(int index){
        this.quadratImages.remove(index);
    }

    public List<QuadratImage> getQuadratImages(){
        LinkedList<QuadratImage> myQuadratImages = new LinkedList<QuadratImage>();

        for (QuadratImage quadratImage : this.quadratImages){
            myQuadratImages.add(quadratImage);
        }

        return myQuadratImages;
    }

    public int setNumQuadrats(int numQuadrats)
    {
        if(numQuadrats < 1)
            throw new InvalidParameterValueException();
        if(numQuadrats > this.quadratImages.size()) {
            for(int i = this.quadratImages.size(); i < numQuadrats; i++) {
                this.addQuadratImage(new QuadratImage());
            }
        }
        else if(numQuadrats < this.quadratImages.size())
        {
            while (numQuadrats < this.quadratImages.size()) {
                this.removeQuadratImage(this.quadratImages.size() - 1);
            }
        }
        return 1;
    }

    @Override
    public String toString(){
        return "<" + this.id + ", " + this.area + ", " + this.age + ", " + this.height + ", " + this.commonSpecies + ">";
    }

}
