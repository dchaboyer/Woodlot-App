package com.example.drew.test1;
import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * QUADRAT
 *
 * Interface by which to interact with a given quadrat from the database.
 * 
 * @author mbelzileha
 *
 */

//TODO: modify the exceptions to send a DATACORRUPT exception chained with exceptions like invalidTableFormat and stuff

public class Quadrat{
/*
	private boolean open; //Indicates if the quadrat open helper has been initialized yet or not.
	private int id; //ID in database table
	private Context context; //Android app context
	private QuadratOpenHelper quadratOpenHelper; //Tool by which to access Quadrat data (Open for testing)

	public Quadrat(){
		this.id = id;
		this.context = context;
		this.setOpen(false);
	}

	//MUTATORS// -----------------------------------------------------------------------------------

	public void setQuadratCoordinates(Coordinate coordinates) throws QuadratNotFoundException{
		onAction();
		try {
			this.quadratOpenHelper.setQuadratCoordinates(coordinates, this.id);
		} catch(QuadratNotFoundException e){
			e.printStackTrace();
			throw e;
		}
	}

	public void addTreeToQuadrat(TreeImage treeImage) throws QuadratNotFoundException{
		onAction();
		try {
			this.quadratOpenHelper.addTreeToQuadrat(treeImage, this.id);
		} catch(QuadratNotFoundException e){
			e.printStackTrace();
			throw e;
		}
	}

	//ACCESSORS// ----------------------------------------------------------------------------------

	public Coordinate getQuadratCoordinates() throws QuadratNotFoundException{
		onAction();
		try {
			return this.quadratOpenHelper.getQuadratCoordinates(this.id); //TODO: check if possible to throw somethign unexpected
		} catch(QuadratNotFoundException e){
			e.printStackTrace();
			throw e;
		}
	}

	public List<TreeImage> getTreeImagesFromQuadrat() throws QuadratNotFoundException{ //TODO: make so that just gets Trees
		onAction();
		try {
			return this.quadratOpenHelper.getTreeImagesFromQuadrat(this.id);
		} catch(QuadratNotFoundException e){
			e.printStackTrace();
			throw e;
		}
	}

	public TreeImage getTreeImageFromQuadrat(int index) throws QuadratNotFoundException, TableIndexOutOfBoundsException{ //TODO: have a non-sql exception that chains with the out of bounds one
		onAction();
		try {
			return this.quadratOpenHelper.getTree(index, this.id);
		} catch(QuadratNotFoundException e){
			e.printStackTrace();
			throw e;
		} catch(TableIndexOutOfBoundsException e){
			e.printStackTrace();
			throw e;
		}
	}

	//HELPERS// ------------------------------------------------------------------------------------

	private void onAction(){
		if(!this.isOpen()){
			this.quadratOpenHelper = new QuadratOpenHelper(context);
			this.setOpen(true);
		}
	}

	private void setOpen(boolean val){
		this.open = val;
	}

	private boolean isOpen(){
		return this.open;
	}
*/
	////////////....../////////////////////ARCHIVE//////////////////////////////////////////////////

	private boolean complete;
	private Coordinate coordinate;
	private ArrayList<Tree> trees;

	public Quadrat(){
		this.complete = false;
		this.coordinate = null;
		this.trees = new ArrayList<Tree>();
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
		ArrayList<Tree> output = new ArrayList<Tree>();

		for (Tree tree : this.trees){
			output.add(tree);
		}

		return output;
	}

	public Tree getTree(int index){
		return this.trees.get(index);
	}

	//TREE//-------------------------------------------
	public void addTree(Tree tree){
		this.trees.add(tree);
	}

	public void removeTree(Tree tree){
		this.trees.remove(tree);
	}

}
