/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the data entry screen for stands. It is
 * called MainActivity because Android requires one file to have this name.
 * The MainActivity file is the one that begins when someone opens the app.
 *
 * This code adds the desired functionality to the widgets, allowing someone
 * to specify a stand's average age, height, and species. From here when a user
 * clicks either the cancel or accept button they are taken to the summary screen
 * for that same stand.
 */
package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import java.util.ArrayList;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity
{

    //widget objects

    protected Button addButton;

    protected ArrayList<Button> buttonList = new ArrayList<Button>();

    /**
     * Begins automatically anytime a user pulls up the stand data entry
     * screen. It specifies the starting text for all widgets.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addWoodlot);

        final DataBase database = ((WCCCApp) this.getApplication()).getDataBase();
        int numWoodlots = database.getNumWoodlots();
        LinearLayout layout = (LinearLayout) findViewById(R.id.woodlotList);
        for(int i = 0; i < numWoodlots; i++)
        {
            final Button currButton = new Button(this);
            final int index = i;

            String name = database.getWoodlot(i).getName();

            currButton.setText(name);
            currButton.setTextSize(40);

            currButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.setCurrWoodlot(index);
                    Intent intent = new Intent(MainActivity.this, StandList.class);
                    startActivity(intent);
                }
            });
            layout.addView(currButton);
            buttonList.add(currButton);
        }
    }

    /**
     * Called when a user presses accept. Starts the stand summary screen,
     * and saves the inputed values.
     * @param view (for method requirement purposes)
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, WoodlotInput.class);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, false);
        startActivity(intent);
    }

}
