package com.example.drew.test1;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * QUADRAT
 * 
 * Observable by QuadratObservers
 * Holds data for a Quadrat.
 * 
 * @author mbelzileha
 *
 */

public class Quadrat{

	private List<Tree> trees;
	private boolean complete;

	private Coordinate coordinate;

	public Quadrat(){
		this.complete = false;
		this.trees = new ArrayList<Tree>();
		this.coordinate = null;
	}

	//SET//--------------------------------------------

	public void setComplete(){
		this.complete = true;
	}

	public void setIncomplete(){
		this.complete = false;
	}

	public void setCoordinate(Coordinate newCoordinate){
		this.coordinate = newCoordinate;
	}

	//GET//--------------------------------------------

	public boolean getCompletionStatus(){
		return this.complete;
	}

	public Coordinate getCoordinate(){
		return this.coordinate;
	}

	public List<Tree> getTrees(){
		LinkedList<Tree> output = new LinkedList<Tree>();

		for (Tree tree : this.trees){
			output.add(tree);
		}

		return output;
	}

	//TREE//-------------------------------------------

	public void addTree(Tree tree){
		this.trees.add(tree);
	}

	public void removeTree(Tree tree){
		this.trees.remove(tree);
	}
}
