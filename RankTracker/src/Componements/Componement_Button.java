package Componements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_Button extends JButton implements MouseListener, Themable {
	
	private static final Color CLICKED = Color.GRAY,
			                   FOCUSED = Color.LIGHT_GRAY,
			                   OTHER   = Color.WHITE;
	
	public Componement_Button(String name) {
		super(name);

	}

	public Componement_Button() {
		this("");
	}
	
	public Componement_Button(String name, Dimension d){
		
		this(name);
		this.setPreferredSize(d);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.setBackground(CLICKED);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setBackground(FOCUSED);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setBackground(OTHER);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.setBackground(CLICKED);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.setBackground(FOCUSED);
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		this.setForeground(ColorGUI.FONT_COLOR_TEXT);
		this.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR));
	}

}
