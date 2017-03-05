package com.example.drew.test1;

/**
 *
 * ALLOMETRIC BIOMASS PREDICTION EQUATION for MISC SPECIES
 *
 * @author Mathieu
 * Created by Jen on 25/02/2017.
 *
 */
public class MiscAbpe implements AbpEquation{

    private double a;
    private double b;

    public MiscAbpe(double a, double b){
        this.a = a;
        this.b = b;
    }

    /**
     * returns ln(W) where w = dry weight in kg
     */
    @Override
    public double calculate(double dbh) {
        return Math.exp(a + b*Math.log(dbh));
    }

}
