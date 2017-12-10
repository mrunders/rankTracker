package Componements;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_SingleStatModule extends JPanel implements Themable {
	
	private JLabel lab = new JLabel();
	
	public Componement_SingleStatModule(String name, String value) {
		this(name, value, "");
	}
	
	public Componement_SingleStatModule(String name, int value){
		this(name, value+"");
	}
	
	public Componement_SingleStatModule(String name, int value, String after) {
		this(name, value+"", after);
	}
	
	public Componement_SingleStatModule(String name, String value, String after) {
		this.lab.setText(name + ": " + value + after);
		this.add(lab);
	}

	@Override
	public void adapte() {

		this.lab.setForeground(ColorGUI.FONT_COLOR_TEXT);
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
	}

}
