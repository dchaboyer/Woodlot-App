package com.example.drew.test1;

/**
 * Created by Drew on 3/6/2017.
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

    protected boolean isEdit;

    /**
     * Begins automatically anytime a user pulls up the stand data entry
     * screen. It specifies the starting text for all widgets.
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

        if(isEdit) {
            int woodlotNum = ((WCCCApp) this.getApplication()).getDataBase().getCurrWoodlot();
            Woodlot currWoodlot = ((WCCCApp) this.getApplication()).getDataBase().getWoodlot(woodlotNum);
            nameEdit.setText(currWoodlot.getName());
            Integer oldNumStands = currWoodlot.getStands().size();
            numStandsEdit.setText(oldNumStands.toString());
        }
        addListeners();
    }

    /**
     * Adds listeners to the age and height widgets. Since these fields are required,
     * when they are blank the program disables the "accept" button.
     */
    public void addListeners()
    {
        nameEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable arg0) {
                boolean isEmpty = nameEdit.getText().toString().isEmpty();
                acceptButton.setEnabled(!isEmpty);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        numStandsEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {
                acceptButton.setEnabled(checkNumber());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /**
     * Called when a user presses accept. Starts the stand summary screen,
     * and saves the inputed values.
     * @param view (for method requirement purposes)
     */
    public void acceptEntry(View view)
    {

        String name = nameEdit.getText().toString();

        String numStandsString = numStandsEdit.getText().toString();
        int numStands = Integer.parseInt(numStandsString);

        if(isEdit)
        {
            int woodlotNum = ((WCCCApp) this.getApplication()).getDataBase().getCurrWoodlot();
            Woodlot currWoodlot = ((WCCCApp) this.getApplication()).getDataBase().getWoodlot(woodlotNum);
            currWoodlot.setName(name);
            currWoodlot.setNumStands(numStands);
        }
        else
        {
            DataBase database = ((WCCCApp) this.getApplication()).getDataBase();
            database.addWoodlot(new Woodlot(name, numStands));
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Called when a user presses cancel. Starts the stand summary
     * screen and saves the old values
     * @param view (for method requirement purposes)
     */
    public void cancelEntry(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean checkNumber() {
        boolean isEmpty = numStandsEdit.getText().toString().isEmpty();
        boolean isPositive = false;
        if(!isEmpty)
            isPositive = Integer.parseInt(numStandsEdit.getText().toString()) >= 1;
        return !isEmpty && isPositive;
    }
}
