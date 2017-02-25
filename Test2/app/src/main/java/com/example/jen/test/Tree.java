package com.example.jen.test;
import java.util.List;

import java.util.LinkedList;
/**
 * TREE
 *
 * @author mbelzileha
 *
 *NOTES:
 *
 *	TERMINOLOGY
 *
 *	DBH = Diameter at Breast Height  (!)API
 */

public class Tree {

    private int dbh;

    public Tree(int dbh){

        this.dbh = dbh;
    }

    //SET//--------------------------------------------

    public void setDbh(int dbh){
        this.dbh = dbh;
    }

    //GET//--------------------------------------------

    public int getDbh(){
        return this.dbh;
    }

}
