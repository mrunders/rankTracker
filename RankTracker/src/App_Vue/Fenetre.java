package App_Vue;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Componements.Componement_JTabbledPane;

public class Fenetre extends JFrame implements Themable {
	
	private JPanel globalPan;
	private Module[] modules;
	
	private Componement_JTabbledPane jtp;
	
	public Fenetre(Module[] modules) {
		super();
		this.setTitle("Rank tracker");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(10, 10);
		this.globalPan = new JPanel();
		this.modules = modules;
		 
		for(int i=0; i<modules.length;i++) {
			modules[i].initialiseTable();
		}

		this.jtp = new Componement_JTabbledPane(modules);
		this.globalPan.add(this.jtp);

		this.setContentPane(this.globalPan);
		this.revalidate();
		this.repaint();
		
		this.pack();
		
	}

	@Override
	public void adapte() {
		this.globalPan.setBackground(ColorGUI.GLOBAL_BACKGROUND_COLOR);
		for (Module m : this.modules) {
			m.adapte();
		}
	}
}
