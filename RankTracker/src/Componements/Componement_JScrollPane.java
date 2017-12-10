package Componements;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_JScrollPane extends JScrollPane implements Themable {

	public Componement_JScrollPane(Componement_JTable t) {
		super(t);
		
		
	}

	@Override
	public void adapte() {
		
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		//this.getVerticalScrollBar().setUI(new Componement_ScrollUI());
	}

}
