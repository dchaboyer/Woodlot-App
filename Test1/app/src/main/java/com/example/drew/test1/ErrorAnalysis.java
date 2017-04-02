package com.example.drew.test1;
/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code that calculates both standard deviations
 * and the relative standard error.
 */

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ErrorAnalysis {

    //check if relative standard error is below threshhold
    public static boolean errorIsBelowThreshhold(double relStdErr, double threshhold)
    {
        if(relStdErr<threshhold)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Double[] getInfo(Stand stand) {
        ArrayList<Double> dwmList = new ArrayList<Double>();
        for(Quadrat quadrat : stand.getQuadrats())
        {
            if(quadrat.getCompletionStatus())
            {
                Double dwm = DwmCalculator.calculateQuadratCarbon(quadrat);
                dwmList.add(dwm);
            }
        }
        Double[] dwmSamples = new Double[dwmList.size()];
        for(int i = 0; i < dwmSamples.length; i++)
        {
            dwmSamples[i] = dwmList.get(i);
        }
        return dwmSamples;
    }

    //mass calculation for stdError
    public static double calculateError(Double[] dwmSamples)
    {
        int sampleNum = dwmSamples.length;
        double sampleMean = calculateSampleMean(dwmSamples);
        double stdDev = calculateStandardDeviation(dwmSamples);
        double stdErr = calculateStandardError(stdDev, sampleNum);
        double relStdErr = calculateRelativeStandardError(stdErr, sampleMean);

        return relStdErr;
    }

    public static double calculateSampleMean(Double[] dwms)
    {
        double totalDwm = 0;
        //iterates through and sums up the dwms
        for(int i = 0; i < dwms.length; i++)
        {
            totalDwm += dwms[i];
        }

        //calculates the mean DWM
        double sampleMean = totalDwm/dwms.length;

        return sampleMean;

    }

    //calculate the standard deviation
    //stdDev = squareroot((summation(xi-x)^2)/(numSamples-1)) where xi = sample value, and x = sample mean
    public static double calculateStandardDeviation(Double[] dwms)
    {
        double sampleMean = calculateSampleMean(dwms);
        int numSamples = dwms.length;

        //placeholder value for future calculations
        double placeHolderValue = 0;
        double difference;

        //iterates through the dwms to calculate (xi-x)^2, where xi is current sample, and x is sample mean
        for(int i = 0; i < dwms.length; i++)
        {
            difference = dwms[i] - sampleMean;
            difference = difference * difference;
            placeHolderValue += difference;
        }


        //divides that value by number of samples - 1
        placeHolderValue = placeHolderValue/(numSamples-1);

        //squareroots the current value to get the standard deviation
        double stdDev = Math.sqrt(placeHolderValue);

        return stdDev;

    }

    //calculate the standard error
    //stdErr = stdDev/(squareroot of numSamples)
    private static double calculateStandardError(double stdDev, int numSamples)
    {

        double stdErr = stdDev/(Math.sqrt(numSamples));

        return stdErr;
    }

    //calculate the relative standard error
    //relStdErr = stdErr/sampleMean
    private static double calculateRelativeStandardError(double stdErr, double sampleMean)
    {
        double relStdErr = stdErr/sampleMean;

        return relStdErr;
    }

    public static double round3Places(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
