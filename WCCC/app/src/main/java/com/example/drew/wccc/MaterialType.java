package com.example.drew.wccc;

/**
 * Created by Mathieu Belzile-Ha on 01/03/2017.
 */

public enum MaterialType {
	ACCEPTABLE_SAW_MATERIAL("Acceptable Saw Material"),
	UNACCEPTABLE_SAW_MATERIAL("Unacceptable Saw Material"),
	ACCEPTABLE_PULP_MATERIAL("Acceptable Pulp Material"),
	UNACCEPTABLE_PULP_MATERIAL("Unacceptable Pulp Material");

	private String name;

	private MaterialType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
}