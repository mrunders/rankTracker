package Componements;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_EntryField extends JTextField implements Themable {
	
	public Componement_EntryField() {
		
		this.setFont(new Font("Courriel new", 2, 15));
		this.setText("Enter cmd here");
	}

	public String getText() {
		return super.getText();
	}

	@Override
	public void adapte() {

		this.setBackground(ColorGUI.GLOBAL_TABLE_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR));
		this.setForeground(ColorGUI.FONT_COLOR_TEXT);
	}

}
