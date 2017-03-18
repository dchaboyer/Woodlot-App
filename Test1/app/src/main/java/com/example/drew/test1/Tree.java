package com.example.drew.test1;
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

public class Tree{

	private final double DOUBLE_UNDEFINED = -1.0; //VALUE FOR UNDEFINED DOUBLE

	private double dbh;
	private Species species;
	private StorageFactor storageFactor;
	private MaterialType materialType;

	public Tree(){

		this.dbh = DOUBLE_UNDEFINED;
		this.species = null;
		this.storageFactor = null;
		this.materialType = null;
	}

	public Tree(double dbh, Species species){
		
		this.dbh = dbh;
		this.species = species;
		this.storageFactor = null;
		this.materialType = null;
	}

	public Tree(double dbh, Species species, StorageFactor storageFactor, MaterialType materialType){

		this.dbh = dbh;
		this.species = species;
		this.storageFactor = storageFactor;
		this.materialType = materialType;
	}
	//SET//--------------------------------------------
	
	public void setDbh(double dbh){
		this.dbh = dbh;
	}
	
	public void setSpecies(Species species){
		this.species = species;
	}
	
	public void setStorageFactor(StorageFactor storageFactor){
		this.storageFactor = storageFactor;
	}
	
	public void setMaterialType(MaterialType materialType){
		this.materialType = materialType;
	}
	
	//GET//--------------------------------------------
	
	public double getDbh(){
		return this.dbh;
	}
	
	public Species getSpecies(){
		return this.species;
	}
	
	public StorageFactor getStorageFactor(){
		return this.storageFactor;
	}
	
	public MaterialType getMaterialType(){
		return this.materialType;
	}

}
