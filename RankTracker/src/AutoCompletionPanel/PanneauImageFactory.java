package AutoCompletionPanel;

public class PanneauImageFactory {
	
	public PanneauImageFactory(){
		
	}
	
	public static APanneauImage[] pourHero(String[] collection){
		
		APanneauImage[] pan = new PanneauImageHero[collection.length];
		
		int i = 0;
		for (String nom : collection)
			pan[i++] = new PanneauImageHero(nom);
		
		return pan;
	}
	
	public static APanneauImage[] pourMap(String[] collection){
		
		APanneauImage[] pan = new PanneauImageMap[collection.length];
		
		int i = 0;
		for (String nom : collection)
			pan[i++] = new PanneauImageMap(nom);
		
		return pan;
	}

}
