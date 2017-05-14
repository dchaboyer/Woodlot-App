/**
 * @author Jonathan Whitaker, Mathieu Belzile-Ha, Drew Chaboyer
 * COMP 4721
 * 5/3/17
 *
 * This is the Java code for the data entry screen for stands.
 *
 * This code adds the desired functionality to the widgets, allowing someone
 * to specify a stand's average age, height, and number of quadrats, and size.
 * From here when a userclicks either the cancel or accept button they are taken to
 * the list of quadrats for that same stand.
 */
package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.text.TextWatcher;
import android.text.Editable;

public class StandInput extends AppCompatActivity
{

    //widget objects
    protected EditText ageEdit;
    protected EditText heightEdit;
    protected EditText quadratEdit;
    protected EditText sizeEdit;
    protected Button cancelButton;
    protected Button acceptButton;

    //variables to store the inputed values
    protected int currAge;
    protected int currNumQuadrats;
    protected double currHeight;
    protected double currSize;

    //true if editting information, false if creating new stand
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

        ageEdit = (EditText) findViewById(R.id.editAge);
        heightEdit = (EditText) findViewById(R.id.editHeight);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        acceptButton = (Button) findViewById(R.id.acceptButton);
        quadratEdit = (EditText) findViewById(R.id.numQuadratsEdit);
        sizeEdit = (EditText) findViewById(R.id.standSizeEdit);

        //Stand currStand = WCCCProgram.getCurrStand();
        if(isEdit) {
            Integer oldAge = WCCCProgram.CurrStand.getAge();
            ageEdit.setText(oldAge.toString());
            Double oldHeight = WCCCProgram.CurrStand.getHeight();
            heightEdit.setText(oldHeight.toString());
            Integer numQuadrats = WCCCProgram.CurrStand.getNumQuadrats();
            quadratEdit.setText(numQuadrats.toString());
            Double oldSize = WCCCProgram.CurrStand.getArea();
            sizeEdit.setText(oldSize.toString());
        }
        else
            acceptButton.setEnabled(false);
        addListeners();
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
                setAcceptStatus();
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
                setAcceptStatus();
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
                setAcceptStatus();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        sizeEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {
                setAcceptStatus();
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

        String numQuadratString = quadratEdit.getText().toString();
        currNumQuadrats = Integer.parseInt(numQuadratString);

        String sizeString = sizeEdit.getText().toString();
        currSize = Double.parseDouble(sizeString);

        WCCCProgram.CurrStand.setAge(currAge);
        WCCCProgram.CurrStand.setHeight(currHeight);
        /*WCCCProgram.CurrStand.setNumQuadrats(currNumQuadrats);*/
        WCCCProgram.CurrStand.setArea(currSize);

        StandImage standImage = WCCCProgram.CurrStand.getImage();
        standImage.setNumQuadrats(currNumQuadrats);
        WCCCProgram.CurrWoodlot.addStand(standImage);
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

    /**
     * Checks that the inputed number is both not null, and positive
     * @param textField
     * @return
     */
    private boolean checkNumber(EditText textField) {
        boolean isEmpty = textField.getText().toString().isEmpty();
        boolean isPositive = false;
        if(!isEmpty)
            isPositive = Double.parseDouble(textField.getText().toString()) > 0;
        return !isEmpty && isPositive;
    }

    private void setAcceptStatus() {
        if(checkNumber(ageEdit) && checkNumber(heightEdit) && checkNumber(sizeEdit) && checkNumber(quadratEdit))
            acceptButton.setEnabled(true);
        else
            acceptButton.setEnabled(false);

    }
}
