package com.example.drew.test1;

/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the java code for the stand summary screen. It provides
 * lists all the information for the stand, and has buttons to allow
 * the user to edit the info, or to proceed to the quadrat list screen.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;


public class StandSummary extends AppCompatActivity
{

    public static final double CI_INTERVAL = 1.96;
    public static final double ERROR_THRESHOLD = 0.10;

    // Widget objects
    protected TextView ageWidget;
    protected TextView heightWidget;
    protected TextView speciesWidget;
    protected TextView dwmWidget;
    protected TextView sizeWidget;
    protected TextView rangeWidget;
    protected TextView errorWidget;
    protected TextView statusWidget;
    protected EditText notesWidget;

    // variables to store current information
    protected String age;
    protected String height;
    protected String species;
    protected String notes;
    protected String size;

    /**
     * Begins automatically when this screen is opened. It adds the
     * functionality to the widgets declared in the xml code. It loads
     * the stand's current values to display them.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stand_summary);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ageWidget = (TextView) findViewById(R.id.age);
        heightWidget = (TextView) findViewById(R.id.height);
        speciesWidget = (TextView) findViewById(R.id.species);
        notesWidget = (EditText) findViewById(R.id.editNotes);
        dwmWidget = (TextView) findViewById(R.id.dwmView);
        sizeWidget = (TextView) findViewById(R.id.standSummarySize);
        rangeWidget = (TextView) findViewById(R.id.dwmRange);
        errorWidget = (TextView) findViewById(R.id.errorEstimate);
        statusWidget = (TextView) findViewById(R.id.errorStatus);
        final Stand currStand = WCCCProgram.getCurrStand();

        Integer currAge = currStand.getAge();
        age = currAge.toString();
        Double currHeight = currStand.getHeight();
        height = currHeight.toString();
        Species currSpecies = currStand.getSpecies();
        species = currSpecies.getName();
        Double currSize = currStand.getArea();
        size = currSize.toString();

        Double dwm = DwmCalculator.calculateDwmStand(currStand);
        String rangeDisplay, errorDisplay, statusDisplay;
        Double[] dwmSamples = ErrorAnalysis.getInfo(currStand);
        if(dwmSamples.length > 1) {
            double stdev = ErrorAnalysis.calculateStandardDeviation(dwmSamples);
            double errorEstimate = ErrorAnalysis.calculateError(dwmSamples);
            errorEstimate = ErrorAnalysis.round3Places(errorEstimate);
            double lowerBound = dwm - (stdev * CI_INTERVAL);
            double upperBound = dwm + (stdev * CI_INTERVAL);
            String lowerString = Long.toString(Math.round(lowerBound));
            String upperString = Long.toString(Math.round(upperBound));
            boolean acceptableError = ErrorAnalysis.errorIsBelowThreshhold(errorEstimate, ERROR_THRESHOLD);
            rangeDisplay = "Range: ".concat(lowerString).concat(" - ").concat(upperString);
            errorDisplay = "Error: ".concat(Double.toString(errorEstimate));
            if(acceptableError)
                statusDisplay = "Status: Error is acceptable";
            else
                statusDisplay = "Status: Error is unacceptable";
        }
        else {
            rangeDisplay = "Range: N/A";
            statusDisplay = "Status: Additional Quadrats Required";
            errorDisplay = "Error: N/A";

        }
        String ageDisplay = "Age: ".concat(age);
        String heightDisplay = "Height: ".concat(height);
        String speciesDisplay = "Species: ".concat(species);
        String dwmString = Long.toString(Math.round(dwm));
        String dwmDisplay = "DWM Estimate: ".concat(dwmString);

        String sizeDisplay = "Size: ".concat(size);

        ageWidget.setText(ageDisplay);
        heightWidget.setText(heightDisplay);
        speciesWidget.setText(speciesDisplay);
        dwmWidget.setText(dwmDisplay);
        rangeWidget.setText(rangeDisplay);
        sizeWidget.setText(sizeDisplay);
        errorWidget.setText(errorDisplay);
        statusWidget.setText(statusDisplay);
        notes = currStand.getNotes();

        if(notes != null)
        {
            notesWidget.setText(currStand.getNotes());
        }

    }

    /**
     * Called when the user hits the edit info button. Starts the stand input screen.
     * @param view (only to satisfy method requirements)
     */
    public void sendEdit(View view)
    {
        saveNotes();
        Intent intent = new Intent(this, StandInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, true);
        startActivity(intent);
    }

    /**
     * Called when the user hits the quadrat list button. Starts the quadrat list screen.
     * @param view (only to satisfy method requirements)
     */
    public void sendList(View view)
    {
        saveNotes();
        Intent intent = new Intent(this, StandOverview.class);
        startActivity(intent);
    }
    /**
     * Saves the notes to the application.
     */
    private void saveNotes()
    {
        Stand currStand = WCCCProgram.getCurrStand();
        currStand.setNotes(notesWidget.getText().toString());
    }

}
