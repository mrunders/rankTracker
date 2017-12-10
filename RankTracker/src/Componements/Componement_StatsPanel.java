package Componements;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_StatsPanel extends JPanel implements Themable {
	
	public Componement_StatsPanel(HashMap<String, Integer> numbers, String name) {
		
		int i = numbers.size();
		i += (i%2 == 0)? 0 : 1;
		this.setLayout(new GridLayout(i>>1, 2));
		this.add(new Componement_SingleStatModule(name, ""));
		
		for (String s : numbers.keySet()) {
			this.add(new Componement_SingleStatModule(s, numbers.get(s)));
		}
		
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR,2));
		
		for (Component sm : this.getComponents()) {
			((Componement_SingleStatModule)sm).adapte();
		}
	}

}
