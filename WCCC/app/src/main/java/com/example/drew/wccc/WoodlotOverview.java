/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the java code for the woodlot overview screen.
 * It provides a scrolling list of all the stands in the woodlot,
 * a button to edit each stand a button to edit the woodlot info,
 * and a button to return to the list of woodlots.
 */

package com.example.drew.wccc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class WoodlotOverview extends AppCompatActivity
{

    //list of all the stand buttons
    protected ArrayList<Button> standButtons = new ArrayList<Button>();

    /**
     * Begins automatically when this screen is opened. It adds the functionality to
     * the widgets declared in the xml code.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stand_list);

        //This next block creates the stand buttons.
        List<StandImage> standImages = WCCCProgram.CurrWoodlot.getStandImages();
        LinearLayout layout = (LinearLayout) findViewById(R.id.standList);
        int numStands = standImages.size();

        for(int i = 0; i < numStands; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;

            currButton.setText("Stand " + (index + 1));
            currButton.setTextSize(40);
            currButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    WCCCProgram.moveToStand(index);
                    Double area = WCCCProgram.CurrWoodlot.getStandImage(index).getArea();
                    if(area == null) {
                        Intent intent = new Intent(WoodlotOverview.this, StandSpecies.class);
                        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, false);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(WoodlotOverview.this, StandOverview.class);
                        startActivity(intent);
                    }

                }
            });
            standButtons.add(currButton);
            layout.addView(currButton);
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the woodlot input screen,
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
     * Called when the user hits the Back button, returns the user to the
     * list of woodlots.
     */
    public void sendBack(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
