package com.example.drew.wccc;

/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the data quadrat overview.
 * The code displays a list of all currently entered trees that are buttons so
 * that the user can return and edit the information. It also allows the
 * user to add a tree.
 */


import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.CheckBox;
import java.util.ArrayList;

public class QuadratScreen extends AppCompatActivity
{
    //constant that is used when passing information to different screens
    public final static String EXTRA_ISEDIT = "com.example.drew.wccc.IS_EDIT";

    CheckBox checkBox;

    //list of the tree buttons
    protected ArrayList<Button> buttonList = new ArrayList<Button>();

    /**
     * Begins automatically anytime a user pulls up the quadrat overview screen
     * It displays a list of trees, with some basic information about them.
     *
     * The method loads the proper values for the member variables, then creates
     * the tree buttons.
     * @param savedInstanceState (a class that is part of the android library)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quadratscreen);
        int numTrees = WCCCProgram.CurrQuadrat.getNumTrees();

        checkBox = (CheckBox) findViewById(R.id.checkbox_completed);
        checkBox.setChecked(WCCCProgram.CurrQuadrat.isComplete());

        //This next block dynamically creates the tree buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout2);

        LayoutParams param = new LayoutParams(
                LayoutParams.MATCH_PARENT, 0, 0.5f
        );

        for(int i = 0; i < numTrees; i++)
        {
            TreeImage treeImage = WCCCProgram.CurrQuadrat.getTreeImage(i);
            double height = treeImage.getDbh();
            String species = treeImage.getSpecies().getName();
            final Button currButton = new Button(this);
            final int index = i;

            currButton.setText("Tree " + (index + 1) + "   DBH: " + height + "   Species: " + species);
            if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                currButton.setTextSize(30);
            }
            else
            {
                currButton.setTextSize(15);
            }
            currButton.setLayoutParams(param);

            currButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WCCCProgram.moveToTree(index);
                    Intent intent = new Intent(QuadratScreen.this, TreeLayout.class);
                    intent.putExtra(EXTRA_ISEDIT, true);
                    startActivity(intent);
                }
            });
            layout.addView(currButton);
            buttonList.add(currButton);
        }
    }

    /**
     * Method called whenever the user checks or unchecks the checkbox.
     * @param view
     */

    public void onCheckboxClicked(View view) {
        if(checkBox.isChecked())
        {
            WCCCProgram.CurrQuadrat.setComplete();
        }
        else
        {
            WCCCProgram.CurrQuadrat.setIncomplete();
        }
    }

    /**
     * Called when the user hits the Back button. Starts the stand overview screen.
     * @param view (for method requirement purposes)
     */

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, TreeLayout.class);
        startActivity(intent);
    }

    /**
     * Called when the user hits the Add Tree button. Starts the tree entry screen.
     * @param view (for method requirement purposes)
     */

    public void sendMessage2(View view)
    {
        Intent intent;
        if(WCCCProgram.CurrQuadrat.isComplete()) {
            intent = new Intent(this, StandSummary.class);
        }
        else {
            intent = new Intent(this, StandOverview.class);
        }
        startActivity(intent);
    }
}
