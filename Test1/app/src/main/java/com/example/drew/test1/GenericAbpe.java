package com.example.drew.test1;

/**
 *
 * ALLOMETRIC BIOMASS PREDICTION EQUATION for GENERIC SPECIES
 *
 * @author Mathieu
 * Created by Jen on 25/02/2017.
 *
 */

public class GenericAbpe implements AbpEquation{

    private double a;
    private double b;

    public GenericAbpe(double a, double b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double evaluate(int dbh) {
        return Math.pow(10, a*Math.log10(dbh) - b);
    }
}
