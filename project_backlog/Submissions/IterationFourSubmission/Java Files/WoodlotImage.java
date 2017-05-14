package com.example.drew.test1;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathieu Belzile-Ha on 30/03/2017.
 */

public class WoodlotImage {

    private String name;
    private Integer id;
    private List<StandImage> standImages;

    public WoodlotImage(String name, int id){
        this.name = name;
        this.id = id;
        this.standImages = new LinkedList<StandImage>();
    }
    public WoodlotImage(String name){
        this.name = name;
        this.id = null;
        this.standImages = new LinkedList<StandImage>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addStandImage(StandImage standImage){
        this.standImages.add(standImage);
    }

    public void removeStandImage(int index){
        this.standImages.remove(index);
    }

    public List<StandImage> getStandImages(){
        LinkedList<StandImage> myStandImages = new LinkedList<StandImage>();

        for (StandImage standImage : this.standImages){
            myStandImages.add(standImage);
        }

        return myStandImages;
    }

    public int setNumStands(int numStands) {
        if(numStands < 1)
            throw new InvalidParameterValueException();

        if(numStands > this.standImages.size()) {
            for(int i = this.standImages.size(); i < numStands; i++) {
                this.addStandImage(new StandImage());
            }
        }
        else if(numStands < this.standImages.size()) {
            while(numStands < this.standImages.size()) {
                this.removeStandImage(this.standImages.size() - 1);
            }
        }
        return 1;
    }
}
