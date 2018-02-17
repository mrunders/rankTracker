package App_Vue;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import App_Model.LISTE_HERO;

public class RESSOURCES {
	
	public static final String[] heroListe = {"Ana","Bastion","Doomfist","Dva","Genji","Hanzo","Junkrat","Lucio","Mccree","Mei",
											  "Mercy","Moira","Orisa","Pharah","Reaper","Reinhardt","Roadhog","Soldier","Sombra",
											  "Symmetra","Torbjorn","Tracer","Widowmaker","Winston","Zarya","Zenyatta"},
								 mapListe  = {"Anubis","Dorado","einchenwald","Gibraltar","Hanamoura","Hollywood","Ilios",
										      "KingsRow","lijiang","LunarColonie","Nepal","Numbani","oasis","route66",
										      "volskaya","JunkerTown"};
	
	public static final String HERO_ICON_REP = "assets/img/heros/HeroIcon_",
							   MAPS_ICON_REP = "assets/img/maps/";
			
	
	public static final HashMap<String,Image> HeroIconMap = new HashMap<>(),
						 					  MapsIconMap = new HashMap<>();
	
	public static void charger(){
		
		for ( String hero : heroListe )
			HeroIconMap.put(hero, loadImage(HERO_ICON_REP + hero + ".png"));
		
		for ( String map : mapListe )
			MapsIconMap.put(map, loadImage(MAPS_ICON_REP + map + ".png"));
	}
	
	public static Image loadImage(String s){
		
		return new LoadRessource().loadImage(s);
	}
	
	public static ImageIcon loadImageIcon(String s){
		
		return new LoadRessource().loadIcon(s);
	}

	public static InputStreamReader loadText(String s){
		
		return new LoadRessource().loadText(s);
	}
}

class LoadRessource {
	
	public Image loadImage(String path){
		
		System.out.println(path);
		return loadIcon(path).getImage();
	}
	
	public ImageIcon loadIcon(String path){
		
		return new ImageIcon(getClass().getClassLoader().getResource(path));
	}

	public InputStreamReader loadText(String path){
		
		return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path));
	}
}
