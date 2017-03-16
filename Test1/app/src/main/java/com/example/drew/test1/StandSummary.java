package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;


public class StandSummary extends AppCompatActivity
{

    // Widget objects
    protected TextView ageWidget;
    protected TextView heightWidget;
    protected TextView speciesWidget;
    protected TextView dwmWidget;
    protected EditText notesWidget;

    // variables to store current information
    protected String age;
    protected String height;
    protected String species;
    protected String notes;
    protected int numQuadrats;

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

        final Stand currStand = WCCCProgram.getCurrStand();

        Integer currAge = currStand.getAge();
        age = currAge.toString();
        Double currHeight = currStand.getHeight();
        height = currHeight.toString();
        Species currSpecies = currStand.getSpecies();
        species = currSpecies.getName();

        double dwm = DwmCalculator.calculateDwmStand(currStand);

        String ageDisplay = "Age: ".concat(age);
        String heightDisplay = "Height: ".concat(height);
        String speciesDisplay = "Species: ".concat(species);
        String dwmDisplay = "DWM: ".concat(Double.toString(dwm));

        ageWidget.setText(ageDisplay);
        heightWidget.setText(heightDisplay);
        speciesWidget.setText(speciesDisplay);
        dwmWidget.setText(dwmDisplay);
        notes = currStand.getNotes();

        if(notes != null)
        {
            notesWidget.setText(currStand.getNotes());
        }

    }

    public void sendEdit(View view) {
        saveNotes();
        Intent intent = new Intent(this, StandInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, true);
        startActivity(intent);
    }

    public void sendList(View view) {
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
