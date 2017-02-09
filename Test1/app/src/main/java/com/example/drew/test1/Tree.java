import java.util.List;

import javax.management.InvalidAttributeValueException;

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

public class Tree implements Observable<TreeObserver>{
	
	private List<TreeObserver> observers;
	
	private int dbh;
	private Species species;
	private Integer storageFactor;
	private MaterialType materialType;
	
	public Tree(int dbh, Species species){
		this.observers = new LinkedList<TreeObserver>();
		
		this.dbh = dbh;
		this.species = species;
		this.storageFactor = null;
		this.materialType = null;
	}
	
	//OBSERVERS//--------------------------------------
	
	@Override
	public void addObserver(TreeObserver observer) {
		this.observers.add(observer);
		this.notifyAssignment(observer);
	}

	@Override
	public void removeObserver(TreeObserver observer) {
		this.observers.remove(observer);
	}
	
		// helpers
	
		/**
		 * Tell an added observer it was just assigned to this Tree.
		 * @param newObserver
		 */
		public void notifyAssignment(TreeObserver newObserver){
			newObserver.newTree(this);
		}
		
		public void notifyDbhChange(){
			for (TreeObserver observer : this.observers){
				observer.dbhChanged(this.dbh);
			}
		}
		
		//*TODO: remaining
	
	//SET//--------------------------------------------
	
	public void setDbh(int dbh){
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
	
	public int getDbh(){
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
