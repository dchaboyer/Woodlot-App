package com.example.drew.test1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

public class StandImage {

    private final String EMPTY_STRING = "";
    private final int NUM_SPECIES = 5;

    private Integer id;
    private double area;
    private Integer age;
    private double height;
    private Species[] commonSpecies;
    private Integer parentId;
    private String notes;

    private List<QuadratImage> quadratImages;

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

    public StandImage(Integer id, double area, Integer age, double height, Integer parentId) {
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
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

    public List<QuadratImage> getQuadratImages(){
        LinkedList<QuadratImage> myQuadratImages = new LinkedList<QuadratImage>();

        for (QuadratImage quadratImage : this.quadratImages){
            myQuadratImages.add(quadratImage);
        }

        return myQuadratImages;
    }

}
