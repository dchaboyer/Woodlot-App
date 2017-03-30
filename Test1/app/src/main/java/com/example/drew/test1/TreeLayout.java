package com.example.drew.test1;

/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the data entry screen for trees.
 * This code adds the desired functionality to the widgets, allowing someone
 * to specify a tree's species, height, storage factor, and aspm height.
 * From here when a user clicks either the Cancel or the Done button they return
 * to the quadrat overview screen.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    //widget objects
    protected Spinner speciesSpinner;
    protected EditText dbhEdit;
    protected Spinner factorSpinner;
    protected Spinner aspmSpinner;
    protected Button doneButton;

    //variables to hold the inputed values
    protected String currSpecies;
    protected String currFactor;
    protected String currAspm;

    //the number of the tree's quadrat
    protected int quadratNum;

    //specifies whether this is editing or adding a tree
    protected boolean isEdit;

    /**
     * Begins automatically anytime a user pulls up the tree input screen.
     * It loads the proper values for the class member variables, then specifies
     * the default text for all the widgets.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treelayout);

        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra(QuadratScreen.EXTRA_ISEDIT, false);

        speciesSpinner = (Spinner) findViewById(R.id.speciesSpinner2);
        dbhEdit = (EditText) findViewById(R.id.dbhEdit);
        factorSpinner = (Spinner) findViewById(R.id.factorSpinner);
        aspmSpinner = (Spinner) findViewById(R.id.aspmSpinner);
        doneButton = (Button) findViewById(R.id.doneButton);

        speciesSpinner.setOnItemSelectedListener(this);
        factorSpinner.setOnItemSelectedListener(this);
        aspmSpinner.setOnItemSelectedListener(this);

        List<String> speciesCategories = new ArrayList<String>();
        speciesCategories.add(Species.AMERICAN_BEECH.getName());
        speciesCategories.add(Species.BALSAM_FIR.getName());
        speciesCategories.add(Species.BLACK_CHERRY.getName());
        speciesCategories.add(Species.BLACK_SPRUCE.getName());
        speciesCategories.add(Species.EASTERN_HEMLOCK.getName());
        speciesCategories.add(Species.EASTERN_LARCH.getName());
        speciesCategories.add(Species.EASTERN_WHITE_CEDAR.getName());
        speciesCategories.add(Species.GENERIC_HARD_WOOD.getName());
        speciesCategories.add(Species.GENERIC_SOFT_WOOD.getName());
        speciesCategories.add(Species.GREY_BIRCH.getName());
        speciesCategories.add(Species.IRON_WOOD.getName());
        speciesCategories.add(Species.JACK_PINE.getName());
        speciesCategories.add(Species.LARGE_TOOTHED_ASPEN.getName());
        speciesCategories.add(Species.RED_MAPLE.getName());
        speciesCategories.add(Species.RED_OAK.getName());
        speciesCategories.add(Species.RED_PINE.getName());
        speciesCategories.add(Species.RED_SPRUCE.getName());
        speciesCategories.add(Species.SUGAR_MAPLE.getName());
        speciesCategories.add(Species.TREMBLING_ASPEN.getName());
        speciesCategories.add(Species.WHITE_ASH.getName());
        speciesCategories.add(Species.WHITE_BIRCH.getName());
        speciesCategories.add(Species.WHITE_PINE.getName());
        speciesCategories.add(Species.WHITE_SPRUCE.getName());
        speciesCategories.add(Species.YELLOW_BIRCH.getName());

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

        dbhEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable arg0) {
                boolean isEmpty = dbhEdit.getText().toString().isEmpty();
                doneButton.setEnabled(!isEmpty);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
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

    /**
     * Called when a user hits the Done button. If the user was editing the tree,
     * the method overwrites the old values in the database. Otherwise it adds
     * a new tree to the database.
     * @param view
     */
    public void sendMessage(View view)
    {
        Tree tree;
        Species tempSpecies = InputParser.parseSpecies(currSpecies);
        StorageFactor tempFactor = InputParser.parseStorageFactor(currFactor);
        MaterialType tempMaterial = InputParser.parseMaterialType(currAspm);

        String heightString = dbhEdit.getText().toString();
        double tempHeight = Double.parseDouble(heightString);

        Quadrat currQuadrat = WCCCProgram.getCurrQuadrat();

        if(isEdit)
        {
            tree = WCCCProgram.getCurrTree();
            tree.setSpecies(tempSpecies);
            tree.setDbh(tempHeight);
            tree.setStorageFactor(tempFactor);
            tree.setMaterialType(tempMaterial);
        }
        else
        {
            tree = new Tree(tempHeight, tempSpecies, tempFactor, tempMaterial);
            currQuadrat.addTree(tree);

            XDatabaseOpenHelper db = new XDatabaseOpenHelper(this); //DEBUG
            //db.addTreeToQuadrat(tree);
        }

        Intent intent = new Intent(this, QuadratScreen.class);
        startActivity(intent);
    }

    /**
     * Called when a user presses the Cancel button.
     * @param view
     */
    public void sendMessage2(View view)
    {
        Intent intent = new Intent(this, QuadratScreen.class);
        startActivity(intent);
    }
}
