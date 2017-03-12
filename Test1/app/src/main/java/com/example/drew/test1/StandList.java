package com.example.drew.test1;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;

public class StandList extends AppCompatActivity
{


    //list of all the quadrat buttons
    protected ArrayList<Button> standButtons = new ArrayList<Button>();

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
        setContentView(R.layout.stand_list);

        final Woodlot currWoodlot =  WCCCProgram.getCurrWoodlot();

        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.standList);
        int numStands = currWoodlot.getNumStands();

        for(int i = 1; i <= numStands; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;
            currButton.setText("Stand " + i);
            currButton.setTextSize(40);
            currButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    WCCCProgram.setCurrStand(index-1);
                    if(currWoodlot.getStand(index-1).getSpecies() == null) {
                        Intent intent = new Intent(StandList.this, StandInput.class);
                        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, false);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(StandList.this, StandOverview.class);
                        startActivity(intent);
                    }

                }
            });
            standButtons.add(currButton);
            layout.addView(currButton);
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the stand input screen,
     * passing along the current values.
     * @param view (only to satisfy method requirements)
     */
    public void sendEdit(View view)
    {
        Intent intent = new Intent(this, WoodlotInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, true);
        startActivity(intent);
    }

    /**
     * Saves the notes to the application.
     */
    public void sendBack(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
