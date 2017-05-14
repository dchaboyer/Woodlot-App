package com.example.drew.wccc;

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
    public static final String NOT_APPLICABLE = "N/A";

    // Widget objects
    protected TextView ageWidget;
    protected TextView heightWidget;
    protected TextView speciesWidget1, speciesWidget2, speciesWidget3, speciesWidget4, speciesWidget5;
    protected TextView carbonWidget;
    protected TextView sizeWidget;
    protected TextView rangeWidget;
    protected TextView errorWidget;
    protected TextView statusWidget;
    protected EditText notesWidget;

    // variables to store current information
    protected String age;
    protected String height;
    protected String species1, species2, species3, species4, species5;
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
        notesWidget = (EditText) findViewById(R.id.editNotes);
        carbonWidget = (TextView) findViewById(R.id.dwmView);
        sizeWidget = (TextView) findViewById(R.id.standSummarySize);
        rangeWidget = (TextView) findViewById(R.id.dwmRange);
        errorWidget = (TextView) findViewById(R.id.errorEstimate);
        statusWidget = (TextView) findViewById(R.id.errorStatus);
        speciesWidget1 = (TextView) findViewById(R.id.summarySpecies1);
        speciesWidget2 = (TextView) findViewById(R.id.summarySpecies2);
        speciesWidget3 = (TextView) findViewById(R.id.summarySpecies3);
        speciesWidget4 = (TextView) findViewById(R.id.summarySpecies4);
        speciesWidget5 = (TextView) findViewById(R.id.summarySpecies5);
        final StandImage currStand = WCCCProgram.CurrStand.getImage();

        Integer currAge = currStand.getAge();
        age = currAge.toString();
        Double currHeight = currStand.getHeight();
        height = currHeight.toString();
        Double currSize = currStand.getArea();
        size = currSize.toString();
        Species currSpecies = currStand.getCommonSpecies(1);
        species1 = currSpecies.getName();
        currSpecies = currStand.getCommonSpecies(2);
        if(currSpecies != null)
            species2 = currSpecies.getName();
        else
            species2 = NOT_APPLICABLE;
        currSpecies = currStand.getCommonSpecies(3);
        if(currSpecies != null)
            species3 = currSpecies.getName();
        else
            species3 = NOT_APPLICABLE;
        currSpecies = currStand.getCommonSpecies(4);
        if(currSpecies != null)
            species4 = currSpecies.getName();
        else
            species4 = NOT_APPLICABLE;
        currSpecies = currStand.getCommonSpecies(5);
        if(currSpecies != null)
            species5 = currSpecies.getName();
        else
            species5 = NOT_APPLICABLE;

        Double carbon = DwmCalculator.calculateCarbonStand(currStand);
        String rangeDisplay, errorDisplay, statusDisplay;
        Double[] carbonSamples = ErrorAnalysis.getInfo(currStand);
        if(currStand.getQuadratImages().size() == carbonSamples.length) {
            rangeDisplay = "Range: Estimate is Exact";
            statusDisplay = "Status: Stand Complete";
            errorDisplay = "Error: No Error";
        }
        else if(carbonSamples.length > 1) {
            double stdev = ErrorAnalysis.calculateStandardDeviation(carbonSamples);
            double errorEstimate = ErrorAnalysis.calculateError(carbonSamples);
            errorEstimate = ErrorAnalysis.round3Places(errorEstimate);
            double lowerBound = carbon - (stdev * CI_INTERVAL);
            double upperBound = carbon + (stdev * CI_INTERVAL);
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
        String carbonString = Long.toString(Math.round(carbon));
        String carbonDisplay = "Carbon Estimate (KG): ".concat(carbonString);
        String sizeDisplay = "Size: ".concat(size);
        String speciesDisplay1 = "Species 1: ".concat(species1);
        String speciesDisplay2 = "Species 2: ".concat(species2);
        String speciesDisplay3 = "Species 3: ".concat(species3);
        String speciesDisplay4 = "Species 4: ".concat(species4);
        String speciesDisplay5 = "Species 5: ".concat(species5);

        ageWidget.setText(ageDisplay);
        heightWidget.setText(heightDisplay);
        carbonWidget.setText(carbonDisplay);
        rangeWidget.setText(rangeDisplay);
        sizeWidget.setText(sizeDisplay);
        errorWidget.setText(errorDisplay);
        statusWidget.setText(statusDisplay);
        speciesWidget1.setText(speciesDisplay1);
        speciesWidget2.setText(speciesDisplay2);
        speciesWidget3.setText(speciesDisplay3);
        speciesWidget4.setText(speciesDisplay4);
        speciesWidget5.setText(speciesDisplay5);
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
        Intent intent = new Intent(this, StandSpecies.class);
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
        WCCCProgram.CurrStand.setNotes("\"" + notesWidget.getText().toString() + "\"");
    }

}