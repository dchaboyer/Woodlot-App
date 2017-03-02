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


public class TreeLayout extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener
{
    protected Spinner speciesSpinner;
    protected EditText dbhEdit;
    protected Spinner factorSpinner;
    protected Spinner aspmSpinner;

    protected String currSpecies;
    protected String currFactor;
    protected String currAspm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treelayout);

        speciesSpinner = (Spinner) findViewById(R.id.speciesSpinner2);
        dbhEdit = (EditText) findViewById(R.id.dbhEdit);
        factorSpinner = (Spinner) findViewById(R.id.factorSpinner);
        aspmSpinner = (Spinner) findViewById(R.id.aspmSpinner);

        speciesSpinner.setOnItemSelectedListener(this);
        factorSpinner.setOnItemSelectedListener(this);
        aspmSpinner.setOnItemSelectedListener(this);

        List<String> speciesCategories = new ArrayList<String>();
        speciesCategories.add("American Beech");
        speciesCategories.add("Balsam Fur");
        speciesCategories.add("Black Cherry");
        speciesCategories.add("Black Spruce");
        speciesCategories.add("Eastern Hemlock");
        speciesCategories.add("Eastern Larch");
        speciesCategories.add("Eastern White Cedar");
        speciesCategories.add("Generic Hard Wood");
        speciesCategories.add("Generic Soft Wood");
        speciesCategories.add("Grey Birch");
        speciesCategories.add("Iron Wood");
        speciesCategories.add("Jack Pine");
        speciesCategories.add("Large Toothed Aspen");
        speciesCategories.add("Red Maple");
        speciesCategories.add("Red Oak");
        speciesCategories.add("Red Pine");
        speciesCategories.add("Red Spruce");
        speciesCategories.add("Sugar Maple");
        speciesCategories.add("Trembling Aspen");
        speciesCategories.add("White Ash");
        speciesCategories.add("White Birch");
        speciesCategories.add("White Pine");
        speciesCategories.add("White Spruce");
        speciesCategories.add("Yellow Birch");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, speciesCategories);
        dataAdapter1.setDropDownViewResource(R.layout.spinner_layout);
        speciesSpinner.setAdapter(dataAdapter1);

        List<String> storageCategories = new ArrayList<String>();
        storageCategories.add("");
        storageCategories.add("1");
        storageCategories.add("2");
        storageCategories.add("3");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, storageCategories);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_layout);
        factorSpinner.setAdapter(dataAdapter2);

        List<String> aspmCategories = new ArrayList<String>();
        aspmCategories.add("");
        aspmCategories.add("Unacceptable Saw Material");
        aspmCategories.add("Acceptable Saw Material");
        aspmCategories.add("Unacceptable Pulp Material");
        aspmCategories.add("Acceptable Pulp Material");

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, aspmCategories);
        dataAdapter3.setDropDownViewResource(R.layout.spinner_layout);
        aspmSpinner.setAdapter(dataAdapter3);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item;
        switch(parent.getId()) {
            case(R.id.speciesSpinner2):
                item = parent.getItemAtPosition(position).toString();
                currSpecies = item;
                break;
            case(R.id.factorSpinner):
                item = parent.getItemAtPosition(position).toString();
                currFactor = item;
                break;
            case(R.id.aspmSpinner):
                item = parent.getItemAtPosition(position).toString();
                currAspm = item;
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

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, QuadratScreen.class);
        startActivity(intent);
    }

    public void sendMessage2(View view)
    {
        Intent intent = new Intent(this, QuadratScreen.class);
        startActivity(intent);
    }
}
