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

public class Quadrat implements Observable<QuadratObserver>{

	private List<QuadratObserver> observers;
	
	private List<Tree> trees;
	private TreeTracker treeTracker;
	
	public Quadrat(){
		this.observers = new LinkedList<QuadratObserver>();
		this.trees = new ArrayList<Tree>();
		this.treeTracker = new TreeTracker();
	}
	
	@Override
	public void addObserver(QuadratObserver observer) {
		this.observers.add(observer);
		
	}

	@Override
	public void removeObserver(QuadratObserver observer) {
		this.observers.remove(observer);
		
	}
	
	public void addTree(Tree tree){
		tree.addObserver(this.treeTracker);
		this.trees.add(tree);
	}
	
	public void removeTree(Tree tree){
		this.trees.remove(tree);
		tree.removeObserver(this.treeTracker);
	}
	
	private class TreeTracker implements TreeObserver{

		@Override
		public void newTree(Tree tree) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dbhChanged(int diameter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void speciesChanged(Species species) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void storageFactorChanged(int storageFactor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void materialChanged(MaterialType materialType) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
