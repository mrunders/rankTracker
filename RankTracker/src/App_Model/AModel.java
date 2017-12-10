package App_Model;

import java.util.HashMap;

import App_Observer.Observable;
import App_Observer.Observer;

public abstract class AModel implements Observable {
	
	protected Observer obs;

	@Override
	public void addObserver(Observer obs) {
		this.obs = obs;
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver(String[][] data, HashMap<String,Integer> stats) {
		this.obs.update(data, stats);
		
	}

	public abstract void importStats();

	public abstract void insert(int parseInt, String next);

	public abstract void delete(String string);

	public abstract void filter(String nextLine);

	public abstract void filter(String nextLine, String modificateur);

	public abstract void saveIntoDB();

}
