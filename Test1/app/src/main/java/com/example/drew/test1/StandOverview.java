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
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;

public class StandOverview extends AppCompatActivity
{

    //list of all the quadrat buttons
    protected ArrayList<IndexedButton> quadratButtons = new ArrayList<IndexedButton>();

    // variables to store current information
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

        final Stand currStand = WCCCProgram.getCurrStand();

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
                    WCCCProgram.setCurrQuadrat(index-1);
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
    public void sendSummary(View view)
    {
        Intent intent = new Intent(this, StandSummary.class);
        startActivity(intent);
    }

    public void sendList(View view)
    {
        Intent intent = new Intent(this, StandList.class);
        startActivity(intent);
    }


}
