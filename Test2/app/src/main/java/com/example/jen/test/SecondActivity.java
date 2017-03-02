package com.example.jen.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static TextView prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        prompt = (TextView) findViewById(R.id.prompt2);
        prompt.setText(getIntent().getExtras().getString("data") + ((MyApplication) this.getApplication()).getNum());
    }
}
