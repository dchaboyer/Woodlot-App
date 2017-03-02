package com.example.drew.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;

public class QuadratScreen extends AppCompatActivity
{
    int currTreeNum = 0;
    protected ArrayList<Button> buttonList = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quadratscreen);

        //This next block dynamically creates the quadrat buttons.
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout2);
        for(int i = 1; i <= currTreeNum; i++)
        {
            final Button currButton = new Button(this);
            currButton.setText("Tree " + i + " (Functionality coming soon)");
            currButton.setTextSize(40);
            layout.addView(currButton);
            buttonList.add(currButton);
        }
    }

    /**
     * Called when the user hits the edit info button. Starts the stand input screen,
     * passing along the current values.
     * @param view
     */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, TreeLayout.class);
        startActivity(intent);
    }
}
