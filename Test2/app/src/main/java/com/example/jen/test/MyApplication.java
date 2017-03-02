package com.example.jen.test;

import android.app.Application;

/**
 * Created by Jen on 24/02/2017.
 */

public class MyApplication extends Application {

    private int num;

    public void set(int num) {
        this.num = num;
    }

    public int getNum(){
        return this.num;
    }
}
