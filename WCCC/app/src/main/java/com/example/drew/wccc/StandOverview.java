/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the java code for the list of quadrats screen. It displays a button
 * to go to the stand summary, and a scroll menu for buttons that will
 * link to all the quadrats in a particular stand.
 */

package com.example.drew.wccc;

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

        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        numQuadrats = WCCCProgram.CurrStand.getNumQuadrats();

        for(int i = 0; i < numQuadrats; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;
            currButton.setText("Quadrat " + (index + 1));
            currButton.setTextSize(40);
            currButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    WCCCProgram.moveToQuadrat(index);
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
