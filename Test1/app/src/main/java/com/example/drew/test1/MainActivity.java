package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    EditText ageEdit, heightEdit;
    Button cancelButton, acceptButton;
    int currAge, currHeight;
    String currSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);

        ageEdit = (EditText) findViewById(R.id.editAge);
        heightEdit = (EditText) findViewById(R.id.editHeight);

        //might be unnecessary in final build
        ageEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageString = ageEdit.getText().toString();
                if(!ageString.equals(""))
                    currAge = Integer.parseInt(ageString);
            }
        });

        //might be unnecessary in final build
        heightEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightString = heightEdit.getText().toString();
                if(!heightString.equals(""))
                    currHeight = Integer.parseInt(heightString);
            }
        });

        cancelButton = (Button) findViewById(R.id.cancelButton);
        acceptButton = (Button) findViewById(R.id.acceptButton);

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Do
            }
        });

        acceptButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //To Do
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        currSpecies = item;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
