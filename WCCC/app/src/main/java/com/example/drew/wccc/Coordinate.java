package com.example.drew.wccc;

public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if (!o.getClass().isAssignableFrom(Coordinate.class)){
            return false;
        }
        Coordinate coord = (Coordinate) o;

        return this.x == coord.getX() && this.y == coord.getY();
    }
}
