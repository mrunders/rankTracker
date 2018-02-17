package AutoCompletionPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import App_Vue.ColorGUI;
import App_Vue.Themable;

public class ListeImages extends JPanel implements RecuperationDeDonne, Themable {
	
	public static final String INSTANCE_MAP = "maps",
							   INSTANCE_HERO = "hero";
	
	private APanneauImage[] pan;
	
	public ListeImages(String type, String[] collection) throws Exception{
		super();
		
		switch (type){
		case(ListeImages.INSTANCE_HERO):
			this.pan = PanneauImageFactory.pourHero(collection);
			this.setLayout(new GridLayout(5,6));
			break;
		
		case(ListeImages.INSTANCE_MAP):
			this.pan = PanneauImageFactory.pourMap(collection);
			this.setLayout(new GridLayout(3,6));
			break;
			
		default:
			throw new Exception("");
		}
		
		
		for (APanneauImage p : this.pan)
			this.add(p);
			
	}
	
	@Override
	public String recuperationSelectionner(){
		
		StringBuilder str = new StringBuilder();
		
		for (APanneauImage p : this.pan){
			if ( p.estSelectionne() ){
				str.append(',');
				str.append(p.nom);
			}
		}
		
		if ( !(str.length() == 0)) str.deleteCharAt(0);
		
		return str.toString();
	}
	
	public void toutDeselectionner(){
		
		for (APanneauImage p : this.pan)
			p.deselectionne();
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
	}

}
