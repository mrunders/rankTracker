package AutoCompletionPanel;

import App_Vue.RESSOURCES;

public class PanneauImageHero extends APanneauImage {
	
	public PanneauImageHero(String nom){
		
		super(nom, RESSOURCES.HeroIconMap.get(nom));
	}


}
