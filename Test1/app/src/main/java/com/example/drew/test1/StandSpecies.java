package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.view.View;
import android.content.Intent;
import java.util.List;

public class StandSpecies extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener
{
    protected boolean isEdit;

    ArrayAdapter<String> dataAdapter, dataAdapter2;

    protected Spinner species1, species2, species3, species4, species5;
    protected String speciesSelection1;
    protected String speciesSelection2 = null;
    protected String speciesSelection3 = null;
    protected String speciesSelection4 = null;
    protected String speciesSelection5 = null;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stand_species);
        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra(QuadratScreen.EXTRA_ISEDIT, false);

        species1 = (Spinner) findViewById(R.id.speciesInput1);
        species1.setOnItemSelectedListener(this);
        species2 = (Spinner) findViewById(R.id.speciesInput2);
        species2.setOnItemSelectedListener(this);
        species3 = (Spinner) findViewById(R.id.speciesInput3);
        species3.setOnItemSelectedListener(this);
        species4 = (Spinner) findViewById(R.id.speciesInput4);
        species4.setOnItemSelectedListener(this);
        species5 = (Spinner) findViewById(R.id.speciesInput5);
        species5.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        for(Species currSpecies : Species.values())
            categories.add(currSpecies.getName());

        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        species1.setAdapter(dataAdapter);

        List<String> categories2 = new ArrayList<String>();
        for(int i = 0; i < categories.size(); i++)
            categories2.add(categories.get(i));
        categories2.add(0, "");

        dataAdapter2 = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, categories2);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        species2.setAdapter(dataAdapter2);
        species3.setAdapter(dataAdapter2);
        species4.setAdapter(dataAdapter2);
        species5.setAdapter(dataAdapter2);
    }

/**
     * Called when a user selects an option from any of the drop down menus. First figures
     * out which drop down menu was used, then saves the value in the appropriate variable
     * value
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item;
        switch(parent.getId())
        {
            case(R.id.speciesInput1):
                item = parent.getItemAtPosition(position).toString();
                speciesSelection1 = item;
                break;
            case(R.id.speciesInput2):
                item = parent.getItemAtPosition(position).toString();
                speciesSelection2 = item;
                break;
            case(R.id.speciesInput3):
                item = parent.getItemAtPosition(position).toString();
                speciesSelection3 = item;
                break;
            case(R.id.speciesInput4):
                item = parent.getItemAtPosition(position).toString();
                speciesSelection4 = item;
                break;
            case(R.id.speciesInput5):
                item = parent.getItemAtPosition(position).toString();
                speciesSelection5 = item;
                break;
        }
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
     * @param view (for method requirement purposes)
     */

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, StandInput.class);
        Stand currStand = WCCCProgram.getCurrStand();

        Species tempSpecies1 = InputParser.parseSpecies(speciesSelection1);
        if(speciesSelection2 != null && !speciesSelection2.equals("")) {
            Species tempSpecies2 = InputParser.parseSpecies(speciesSelection2);
            currStand.setSpecies(tempSpecies2, 2);
        }
        if(speciesSelection3 != null && !speciesSelection3.equals("")) {
            Species tempSpecies3 = InputParser.parseSpecies(speciesSelection3);
            currStand.setSpecies(tempSpecies3, 3);
        }
        if(speciesSelection4 != null && !speciesSelection4.equals("")) {
            Species tempSpecies4 = InputParser.parseSpecies(speciesSelection4);
            currStand.setSpecies(tempSpecies4, 4);
        }
        if(speciesSelection5 != null && !speciesSelection5.equals("")) {
            Species tempSpecies5 = InputParser.parseSpecies(speciesSelection5);
            currStand.setSpecies(tempSpecies5, 5);
        }

        currStand.setSpecies(tempSpecies1, 1);
        intent.putExtra(QuadratScreen.EXTRA_ISEDIT, isEdit);
        startActivity(intent);
    }

    /**
     * Called when a user presses cancel. Starts the stand summary
     * screen and does not alter the old values
     * @param view (for method requirement purposes)
     */

    public void sendOldValues(View view)
    {
        Intent intent;
        if(isEdit)
            intent = new Intent(this, StandOverview.class);
        else
            intent = new Intent(this, StandList.class);
        startActivity(intent);
    }
}
