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
	
	private double dbh;
	private Species species;
	private Integer storageFactor;
	private MaterialType materialType;
	
	public Tree(double dbh, Species species){
		
		this.dbh = dbh;
		this.species = species;
		this.storageFactor = null;
		this.materialType = null;
	}
	//SET//--------------------------------------------
	
	public void setDbh(double dbh){
		this.dbh = dbh;
	}
	
	public void setSpecies(Species species){
		this.species = species;
	}
	
	public void setStorageFactor(Integer storageFactor) throws InvalidAttributeValueException{
		if (storageFactor < 1 || storageFactor > 3)
			throw new InvalidAttributeValueException("Storage factor " + storageFactor + " is invalid. Valid storage factors are 1, 2, 3 only.");
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
	
	public Integer getStorageFactor(){
		return this.storageFactor;
	}
	
	public MaterialType getMaterialType(){
		return this.materialType;
	}

}
