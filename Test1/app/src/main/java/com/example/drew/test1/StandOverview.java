/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the java code for the stand summary screen. It displays the values
 * inputed on the input screen. It also provides an area to record notes, a button
 * to return to the input screen (for editing purposes), and a scroll menu
 * for buttons that will link to all the quadrats in a particular stand.
 */
package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import android.view.WindowManager;

public class StandOverview extends AppCompatActivity
{

    // Widget objects
    protected TextView ageWidget;
    protected TextView heightWidget;
    protected TextView speciesWidget;
    protected TextView dwmWidget;
    protected EditText notesWidget;

    //list of all the quadrat buttons
    protected ArrayList<IndexedButton> quadratButtons = new ArrayList<IndexedButton>();

    // variables to store current information
    protected String age;
    protected String height;
    protected String species;
    protected String notes;
    protected int numQuadrats;

    /**
     * Begins automatically when this screen is opened. First saves the values
     * passed to it by the input screen. It then adds the functionality to
     * the widgets declared in the xml code.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_overview);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ageWidget = (TextView) findViewById(R.id.age);
        heightWidget = (TextView) findViewById(R.id.height);
        speciesWidget = (TextView) findViewById(R.id.species);
        notesWidget = (EditText) findViewById(R.id.editNotes);
        dwmWidget = (TextView) findViewById(R.id.dwmView);

        final DataBase database = ((WCCCApp) this.getApplication()).getDataBase();
        int currWoodlot = database.getCurrWoodlot();
        int standIndex = database.getCurrStand();
        final Stand currStand = database.getWoodlot(currWoodlot).getStand(standIndex);

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

        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        numQuadrats = currStand.getQuadrats().size();

        for(int i = 1; i <= numQuadrats; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;
            currButton.setText("Quadrat " + i);
            currButton.setTextSize(40);
            final IndexedButton currIndexedButton = new IndexedButton(currButton, index);
            currButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    saveNotes();
                    database.setCurrQuadrat(index-1);
                    Intent intent = new Intent(StandOverview.this, QuadratScreen.class);
                    startActivity(intent);
                }
            });
            quadratButtons.add(currIndexedButton);
            layout.addView(currButton);
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the stand input screen,
     * passing along the current values.
     * @param view (only to satisfy method requirements)
     */
    public void sendMessage(View view)
    {
        saveNotes();
        Intent intent = new Intent(this, StandInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, true);
        startActivity(intent);
    }

    public void sendBack(View view)
    {
        Intent intent = new Intent(this, StandList.class);
        startActivity(intent);
    }

    /**
     * Saves the notes to the application.
     */
    private void saveNotes()
    {
        DataBase database = ((WCCCApp) this.getApplication()).getDataBase();
        int currWoodlot = database.getCurrWoodlot();
        int standIndex = database.getCurrStand();
        Stand currStand = ((WCCCApp) this.getApplication()).getDataBase().getWoodlot(currWoodlot).getStand(standIndex);
        currStand.setNotes(notesWidget.getText().toString());
    }

}
