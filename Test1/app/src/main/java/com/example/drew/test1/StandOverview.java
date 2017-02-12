/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 12/2/17
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

public class StandOverview extends AppCompatActivity
{
    final int N = 11;
    protected TextView ageWidget;
    protected TextView heightWidget;
    protected TextView speciesWidget;
    protected Button[] quadratButtons = new Button[N];
    protected EditText notesWidget;
    protected String age;
    protected String height;
    protected String species;
    protected String notes;

    /**
     * Begins automatically when this screen is opened. First saves the values
     * passed to it by the input screen. It then adds the functionality to
     * the widgets declared in the xml code.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_overview);
        Intent intent = getIntent();
        age = intent.getStringExtra(MainActivity.EXTRA_AGE);
        height = intent.getStringExtra(MainActivity.EXTRA_HEIGHT);
        species = intent.getStringExtra(MainActivity.EXTRA_SPECIES);
        notes = intent.getStringExtra(MainActivity.EXTRA_NOTES);

        ageWidget = (TextView) findViewById(R.id.age);
        heightWidget = (TextView) findViewById(R.id.height);
        speciesWidget = (TextView) findViewById(R.id.species);
        notesWidget = (EditText) findViewById(R.id.editNotes);
        String ageDisplay = "Age: ".concat(age);
        String heightDisplay = "Height: ".concat(height);
        String speciesDisplay = "Species: ".concat(species);
        ageWidget.setText(ageDisplay);
        heightWidget.setText(heightDisplay);
        speciesWidget.setText(speciesDisplay);
        if(notes != null)
        {
            notesWidget.setText(notes);
        }

        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        for(int i = 1; i < N; i++)
        {
            final Button currButton = new Button(this);
            currButton.setText("Quadrat " + i + " (Functionality coming soon)");
            currButton.setTextSize(40);
            layout.addView(currButton);
            quadratButtons[i] = currButton;
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the stand input screen,
     * passing along the current values.
     * @param view
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_AGE, age);
        intent.putExtra(MainActivity.EXTRA_HEIGHT, height);
        intent.putExtra(MainActivity.EXTRA_SPECIES, species);
        String notesString = notesWidget.getText().toString();
        intent.putExtra(MainActivity.EXTRA_NOTES, notesString);
        startActivity(intent);
    }
}
