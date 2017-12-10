package Componements;

import javax.swing.JTabbedPane;

import App_Vue.ColorGUI;
import App_Vue.Module;
import App_Vue.RESSOURCES;

public class Componement_JTabbledPane extends JTabbedPane {
	
	public Componement_JTabbledPane(Module[] g) {
		
		super(JTabbedPane.LEFT);
		
		this.setBackground(ColorGUI.GLOBAL_BACKGROUND_COLOR);
		
		int i=0;
		for (Module gg : g) {
			this.addTab(gg.getModuleName(),RESSOURCES.HeroIconMap.get("ana"), gg);
			
		}
	}
	

}
