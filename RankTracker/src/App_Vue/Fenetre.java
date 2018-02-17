package App_Vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import AutoCompletionPanel.ChaineConstructorPanel;
import Componements.Componement_JTabbledPane;

public class Fenetre extends JFrame implements Themable {
	
	private JPanel globalPan;
	private Module[] modules;
	
	private Componement_JTabbledPane jtp;
	private ChaineConstructorPanel ccp = new ChaineConstructorPanel(this);
	
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
		
		this.globalPan.add(this.jtp, BorderLayout.NORTH);
		this.globalPan.add(this.ccp, BorderLayout.SOUTH);
		
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
		
		this.ccp.adapte();
	}
	
	public void ecraserTextField(String c){
		
		this.modules[this.jtp.getSelectedIndex()].forceModifierCmd(c);
	}
}
