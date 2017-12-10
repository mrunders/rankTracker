package Componements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import App_Controler.AControler;
import App_Observer.Observer;
import App_Vue.ColorGUI;
import App_Vue.Themable;

public class Componement_BotPannel extends JPanel implements Observer, Themable {
	
	private Componement_EntryField entry = new Componement_EntryField();
	private Componement_Button b = new Componement_Button("execute");
	private AControler ctrl;
	
	public Componement_BotPannel(AControler obs) {
		
		this.setLayout(new GridLayout(1,2,2,2));
		this.add(this.entry); this.add(this.b);
		this.ctrl = obs;
		this.b.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Componement_BotPannel.this.ctrl.control(Componement_BotPannel.this.entry.getText());
				Componement_BotPannel.this.initialiseTable();
			}
		});
	}
	
	public void initialiseTable() {
		
		this.ctrl.importStats();
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 5, 2));
		
		this.entry.adapte();
		this.b.adapte();
	}

	@Override
	public void update(String[][] tab, HashMap<String, Integer> stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[][] getNewTableData() {
		// TODO Auto-generated method stub
		return null;
	}

}
