/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the list of woodlots screen. It is
 * called MainActivity because Android requires one file to have this name.
 * The MainActivity file is the one that begins when someone opens the app.
 *
 * This code adds the desired functionality to the widgets declared in the
 * XML file. It has a scrolling list of all the woodlots, and also a button
 * to create a new woodlot
 */
package com.example.drew.wccc;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity
{

    private boolean open = false;

    //button widget
    protected Button addButton;

    //ArrayList to store buttons
    protected ArrayList<Button> buttonList = new ArrayList<Button>();

    /**
     * Begins automatically anytime a user pulls up the stand data entry
     * screen. It creates the buttons and adds the functionality.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (!open){
            WCCCProgram.initialize(this.getApplication());
            this.open = true;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addWoodlot);

        List<WoodlotImage> woodlotImages = WCCCProgram.Root.getWoodlotImages();
        /*int numWoodlots = WCCCProgram.Root.getNumWoodlots();*/
        LinearLayout layout = (LinearLayout) findViewById(R.id.woodlotList);
        for(WoodlotImage woodlotImage: woodlotImages)
        {
            final Button currButton = new Button(this);
            /*final int index = i;*/

            String name = woodlotImage.getName();
            final int woodlotId = woodlotImage.getId();

            currButton.setText(name);
            if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                currButton.setTextSize(45);
            }
            else
            {
                currButton.setTextSize(25);
            }

            currButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WCCCProgram.moveToWoodlot(woodlotId);
                    Intent intent = new Intent(MainActivity.this, StandList.class);
                    startActivity(intent);
                }
            });
            layout.addView(currButton);
            buttonList.add(currButton);
        }
    }

    /**
     * Called when a user presses add woodlot. Starts the woodlot input screen.
     * @param view (for method requirement purposes)
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, WoodlotInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, false);
        startActivity(intent);
    }

}
