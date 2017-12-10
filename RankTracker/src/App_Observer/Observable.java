package App_Observer;

import java.util.ArrayList;
import java.util.HashMap;

public interface Observable { 
	
	public void addObserver(Observer obs); 
	public void removeObserver(); 
	public void notifyObserver(String[][] data, HashMap<String, Integer> stats); 

} 