package com.example.drew.test1;

/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the data entry screen for woodlots.
 *
 * This code adds the desired functionality to the widgets, allowing someone
 * to specify a woodlot's name and number of quadrats.
 * From here when a user clicks either the cancel or accept button they are returned
 * to the list of woodlots.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.text.TextWatcher;
import android.text.Editable;

public class WoodlotInput extends AppCompatActivity
{

    //widget objects
    protected EditText nameEdit;
    protected EditText numStandsEdit;
    protected Button cancelButton;
    protected Button acceptButton;

    //true if editting information, false if creating new stand
    protected boolean isEdit;

    /**
     * Begins automatically anytime a user pulls up this screen.
     * It adds the desired functionality to the widgets
     * @param savedInstanceState (a class that is part of the android library)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.woodlot_input);

        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra(QuadratScreen.EXTRA_ISEDIT, false);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        nameEdit = (EditText) findViewById(R.id.woodlotNameEntry);
        numStandsEdit = (EditText) findViewById(R.id.woodlotStandInput);
        cancelButton = (Button) findViewById(R.id.woodlotCancel);
        acceptButton = (Button) findViewById(R.id.woodlotAccept);

        if(isEdit)
        {
            Woodlot currWoodlot = WCCCProgram.getCurrWoodlot();
            nameEdit.setText(currWoodlot.getName());
            Integer oldNumStands = currWoodlot.getStands().size();
            numStandsEdit.setText(oldNumStands.toString());
        }
        acceptButton.setEnabled(false);
        addListeners();
    }

    /**
     * Adds listeners to the name and stand number widgets. Since these fields are required,
     * when they are blank the program disables the "accept" button.
     */
    public void addListeners()
    {
        nameEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable arg0)
            {
                setAcceptEnabled();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        numStandsEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {
                setAcceptEnabled();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /**
     * Called when a user presses accept. Starts the woodlot list screen,
     * and saves the inputed values.
     * @param view (for method requirement purposes)
     */
    public void acceptEntry(View view)
    {

        String name = nameEdit.getText().toString();

        String numStandsString = numStandsEdit.getText().toString();

        int numStands = Integer.parseInt(numStandsString);

        if (isEdit) {
            Woodlot currWoodlot = WCCCProgram.getCurrWoodlot();
            currWoodlot.setName(name);
            currWoodlot.setNumStands(numStands);
        } else {
            DataBase database = WCCCProgram.getRoot();
            database.addWoodlot(new Woodlot(name, numStands));
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Called when a user presses cancel. Starts the woodlot list
     * screen, and does not save the values
     * @param view (for method requirement purposes)
     */
    public void cancelEntry(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setAcceptEnabled()
    {
        boolean isNameEmpty = nameEdit.getText().toString().isEmpty();
        boolean isNumEmpty = numStandsEdit.getText().toString().isEmpty();
        if(isNameEmpty || isNumEmpty)
            acceptButton.setEnabled(false);
        else
            acceptButton.setEnabled(true);
    }
}
