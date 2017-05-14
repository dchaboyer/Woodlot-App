package com.example.drew.wccc;

/**
 * Created by Jen on 30/03/2017.
 */

public class StandInsertFailureException extends RuntimeException {
    public StandInsertFailureException(){
        super();
    }

    public StandInsertFailureException(String message){
        super(message);
    }
}
