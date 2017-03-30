package com.example.drew.test1;

/**
 * Created by Jen on 30/03/2017.
 */

public class StandImage {

    private int id;
    private double area;
    private int age;
    private double height;

    public StandImage(int id, double area, int age, double height) {
        this.id = id;
        this.area = area;
        this.age = age;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
