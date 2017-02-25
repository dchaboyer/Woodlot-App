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
	
	public Quadrat(){
		this.trees = new ArrayList<Tree>();
	}
	
	public void addTree(Tree tree){
		this.trees.add(tree);
	}
	
	public void removeTree(Tree tree){
		this.trees.remove(tree);
	}

}
