package App_Observer;

import java.util.ArrayList;
import java.util.HashMap;

public interface Observer { 

	public void update(String[][] tab, HashMap<String, Integer> stats);
	
	public String[][] getNewTableData();

} 