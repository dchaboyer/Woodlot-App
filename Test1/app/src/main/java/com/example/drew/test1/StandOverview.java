/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the java code for the list of quadrats screen. It displays a button
 * to go to the stand summary, and a scroll menu for buttons that will
 * link to all the quadrats in a particular stand.
 */
package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;

public class StandOverview extends AppCompatActivity
{

    //list of all the quadrat buttons
    protected ArrayList<Button> quadratButtons = new ArrayList<Button>();

    // variables to store current information
    protected int numQuadrats;

    /**
     * Begins automatically when this screen is opened. It
     * adds the functionality to the widgets declared in the xml code.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_overview);

        final Stand currStand = WCCCProgram.getCurrStand();

<<<<<<< HEAD
        Integer currAge = currStand.getAge();
        age = currAge.toString();
        Double currHeight = currStand.getHeight();
        height = currHeight.toString();
        Species currSpecies = currStand.getSpecies(1);
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

=======
>>>>>>> refs/remotes/origin/master
        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        numQuadrats = currStand.getQuadrats().size();

        for(int i = 1; i <= numQuadrats; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;
            currButton.setText("Quadrat " + i);
            currButton.setTextSize(40);
            currButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    WCCCProgram.setCurrQuadrat(index-1);
                    Intent intent = new Intent(StandOverview.this, QuadratScreen.class);
                    startActivity(intent);
                }
            });
            quadratButtons.add(currButton);
            layout.addView(currButton);
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the stand summary screen.
     * @param view (only to satisfy method requirements)
     */
    public void sendSummary(View view)
    {
        Intent intent = new Intent(this, StandSummary.class);
        startActivity(intent);
    }

    /**
     * Called when the user hits the back button. Starts the stand list screen.
     * @param view (only to satisfy method requirements)
     */
    public void sendList(View view)
    {
        Intent intent = new Intent(this, StandList.class);
        startActivity(intent);
    }


}
