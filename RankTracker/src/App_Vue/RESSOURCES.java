package App_Vue;

import java.awt.Image;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class RESSOURCES {
	
	public static final String HERO_ICON_REP = "src/assets/img/HeroIcon_",
			HERO_ICON_ANA = HERO_ICON_REP + "Ana.png",
			HERO_ICON_Moira = HERO_ICON_REP + "Moira.png",
			HERO_ICON_Junkrat = HERO_ICON_REP + "Junkrat.png",
			HERO_ICON_Zenyatta = HERO_ICON_REP + "Zenyatta.png";
	
	public static final HashMap<String,ImageIcon> HeroIconMap = new HashMap<>();
	
	{
		HeroIconMap.put("ana", loadImageIcon(HERO_ICON_ANA));
		HeroIconMap.put("moira", loadImageIcon(HERO_ICON_Moira));
		HeroIconMap.put("junk", loadImageIcon(HERO_ICON_Junkrat));
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
		
		return new ImageIcon(getClass().getClassLoader().getResource(path)).getImage();
	}
	
	public ImageIcon loadIcon(String path){
		
		return new ImageIcon(getClass().getClassLoader().getResource(path));
	}

	public InputStreamReader loadText(String path){
		
		return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path));
	}
}
