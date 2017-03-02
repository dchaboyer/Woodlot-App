/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 12/2/17
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
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener
{
    public final static String EXTRA_AGE = "com.example.drew.test1.AGE";
    public final static String EXTRA_HEIGHT = "com.example.drew.test1.HEIGHT";
    public final static String EXTRA_SPECIES = "com.example.drew.test1.SPECIES";
    public final static String EXTRA_NOTES = "com.example.drew.test1.NOTES";

    protected Spinner spinner;
    protected EditText ageEdit;
    protected EditText heightEdit;
    protected Button cancelButton;
    protected Button acceptButton;
    protected String currAge;
    protected String currHeight;
    protected String currSpecies;
    protected String oldAge = null;
    protected String oldHeight = null;
    protected String oldSpecies = null;
    protected String notes = null;

    /**
     * Begins automatically anytime a user pulls of the stand data entry
     * screen. It saves the old values for the stand, in case the user chooses to
     * cancel any changes.
     *
     * The rest of the method specifies the widgets.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if(intent != null)
        {
            oldAge = intent.getStringExtra(EXTRA_AGE);
            oldHeight = intent.getStringExtra(EXTRA_HEIGHT);
            oldSpecies = intent.getStringExtra(EXTRA_SPECIES);
            notes = intent.getStringExtra(EXTRA_NOTES);
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("American Beech");
        categories.add("Balsam Fur");
        categories.add("Black Cherry");
        categories.add("Black Spruce");
        categories.add("Eastern Hemlock");
        categories.add("Eastern Larch");
        categories.add("Eastern White Cedar");
        categories.add("Generic Hard Wood");
        categories.add("Generic Soft Wood");
        categories.add("Grey Birch");
        categories.add("Iron Wood");
        categories.add("Jack Pine");
        categories.add("Large Toothed Aspen");
        categories.add("Red Maple");
        categories.add("Red Oak");
        categories.add("Red Pine");
        categories.add("Red Spruce");
        categories.add("Sugar Maple");
        categories.add("Trembling Aspen");
        categories.add("White Ash");
        categories.add("White Birch");
        categories.add("White Pine");
        categories.add("White Spruce");
        categories.add("Yellow Birch");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                                                R.layout.spinner_layout, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);

        ageEdit = (EditText) findViewById(R.id.editAge);
        heightEdit = (EditText) findViewById(R.id.editHeight);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        acceptButton = (Button) findViewById(R.id.acceptButton);
    }

    /**
     * Called when a user selects an option from the drop down menu. Stores the selected
     * option in the variable currSpecies.
     * value
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
{
    String item = parent.getItemAtPosition(position).toString();
    currSpecies = item;
}

    /**
     * Required method for interface purposes. when a user selects nothing from
     * the drop down menu, nothing happens.
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?> arg0)
    {
    }

    /**
     * Called when a user presses accept. Starts the stand summary screen,
     * and saves the inputed values.
     * @param view
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, StandOverview.class);
        String ageString = ageEdit.getText().toString();
        if(!ageString.equals(""))
        {
            currAge = ageString;
        }
        String heightString = heightEdit.getText().toString();
        if(!heightString.equals(""))
        {
            currHeight = heightString;
        }
        intent.putExtra(EXTRA_AGE, currAge);
        intent.putExtra(EXTRA_HEIGHT, currHeight);
        intent.putExtra(EXTRA_SPECIES, currSpecies);
        intent.putExtra(EXTRA_NOTES, notes);
        startActivity(intent);
    }

    /**
     * Called when a user presses cancel. Starts the stand summary
     * screen and saves the old values
     * @param view
     */
    public void sendOldValues(View view)
    {
        Intent intent = new Intent(this, StandOverview.class);
        intent.putExtra(EXTRA_AGE, oldAge);
        intent.putExtra(EXTRA_HEIGHT, oldHeight);
        intent.putExtra(EXTRA_SPECIES, oldSpecies);
        intent.putExtra(EXTRA_NOTES, notes);
        startActivity(intent);
    }
}
