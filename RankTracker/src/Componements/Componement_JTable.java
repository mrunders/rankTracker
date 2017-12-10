package Componements;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_JTable extends JTable implements Themable{
	
	public Componement_JTable() {
		init();
	}
	
	public Componement_JTable(String[][] tab, String[] index) {
		super(tab,index);
		init(index);
	}
	
	private void init(String[] index) {
		init();
		this.setTableHeader(new Componement_TableModelHeader(index));
	}
	
	private void init() {

		this.setRowHeight(24);
		this.setFont(new Font("Calisto MT", 2, 12));
		this.setRowSelectionAllowed(true);
		
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_TABLE_BACKGROUND_COLOR);
		this.setForeground(ColorGUI.FONT_COLOR_TEXT);
		this.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public DefaultTableModel getDefaultTableModel() {
		
		return (DefaultTableModel)this.getModel();
	}

}
