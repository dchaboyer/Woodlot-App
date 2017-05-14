package com.example.drew.wccc;

/**
 * Created by Mathieu Belzile-Ha on 01/03/2017.
 */

public enum StorageFactor {
    STORAGE_FACTOR_ONE("1"), STORAGE_FACTOR_TWO("2"), STORAGE_FACTOR_THREE("3");

    private String name;

    private StorageFactor(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
