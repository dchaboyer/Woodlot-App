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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.text.TextWatcher;
import android.text.Editable;
import java.util.List;
public class StandInput extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener
{

    //widget objects
    protected Spinner spinner;
    protected EditText ageEdit;
    protected EditText heightEdit;
    protected EditText quadratEdit;
    protected Button cancelButton;
    protected Button acceptButton;

    //variables to store the inputed values
    protected int currAge;
    protected int currNumQuadrats;
    protected double currHeight;
    protected String currSpecies;

    boolean isEdit;

    /**
     * Begins automatically anytime a user pulls up the stand data entry
     * screen. It specifies the starting text for all widgets.
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stand_input);
        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra(QuadratScreen.EXTRA_ISEDIT, false);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add(Species.AMERICAN_BEECH.getName());
        categories.add(Species.BALSAM_FIR.getName());
        categories.add(Species.BLACK_CHERRY.getName());
        categories.add(Species.BLACK_SPRUCE.getName());
        categories.add(Species.EASTERN_HEMLOCK.getName());
        categories.add(Species.EASTERN_LARCH.getName());
        categories.add(Species.EASTERN_WHITE_CEDAR.getName());
        categories.add(Species.GENERIC_HARD_WOOD.getName());
        categories.add(Species.GENERIC_SOFT_WOOD.getName());
        categories.add(Species.GREY_BIRCH.getName());
        categories.add(Species.IRON_WOOD.getName());
        categories.add(Species.JACK_PINE.getName());
        categories.add(Species.LARGE_TOOTHED_ASPEN.getName());
        categories.add(Species.RED_MAPLE.getName());
        categories.add(Species.RED_OAK.getName());
        categories.add(Species.RED_PINE.getName());
        categories.add(Species.RED_SPRUCE.getName());
        categories.add(Species.SUGAR_MAPLE.getName());
        categories.add(Species.TREMBLING_ASPEN.getName());
        categories.add(Species.WHITE_ASH.getName());
        categories.add(Species.WHITE_BIRCH.getName());
        categories.add(Species.WHITE_PINE.getName());
        categories.add(Species.WHITE_SPRUCE.getName());
        categories.add(Species.YELLOW_BIRCH.getName());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);

        ageEdit = (EditText) findViewById(R.id.editAge);
        heightEdit = (EditText) findViewById(R.id.editHeight);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        acceptButton = (Button) findViewById(R.id.acceptButton);
        quadratEdit = (EditText) findViewById(R.id.numQuadratsEdit);

        Stand currStand = WCCCProgram.getCurrStand();
        if(isEdit) {
            Integer oldAge = currStand.getAge();
            ageEdit.setText(oldAge.toString());
            Double oldHeight = currStand.getHeight();
            heightEdit.setText(oldHeight.toString());
            Integer numQuadrats = currStand.getNumQuadrats();
            quadratEdit.setText(numQuadrats.toString());
        }
        addListeners();
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
     * Adds listeners to the age and height widgets. Since these fields are required,
     * when they are blank the program disables the "accept" button.
     */
    public void addListeners()
    {
        ageEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable arg0) {
                boolean isEmpty = ageEdit.getText().toString().isEmpty();
                acceptButton.setEnabled(!isEmpty);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        heightEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {
                boolean isEmpty = heightEdit.getText().toString().isEmpty();
                acceptButton.setEnabled(!isEmpty);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        quadratEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {
                acceptButton.setEnabled(checkNumber());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /**
     * Called when a user presses accept. Starts the stand summary screen,
     * and saves the inputed values.
     * @param view (for method requirement purposes)
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, StandOverview.class);

        String heightString = heightEdit.getText().toString();
        currHeight = Double.parseDouble(heightString);

        String ageString = ageEdit.getText().toString();
        currAge = Integer.parseInt(ageString);

        Species tempSpecies = InputParser.parseSpecies(currSpecies);

        String numQuadratString = quadratEdit.getText().toString();
        currNumQuadrats = Integer.parseInt(numQuadratString);

        Stand currStand = WCCCProgram.getCurrStand();
        currStand.setAge(currAge);
        currStand.setHeight(currHeight);
        currStand.setSpecies(tempSpecies,1);
        currStand.setNumQuadrats(currNumQuadrats);
        startActivity(intent);
    }

    /**
     * Called when a user presses cancel. Starts the stand summary
     * screen and saves the old values
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

    private boolean checkNumber() {
        boolean isEmpty = quadratEdit.getText().toString().isEmpty();
        boolean isPositive = false;
        if(!isEmpty)
            isPositive = Integer.parseInt(quadratEdit.getText().toString()) >= 1;
        return !isEmpty && isPositive;
    }
}
