package App_Vue;

import java.awt.Color;

public class ColorGUI {
	
	public enum ThemeTab { BLACK, WHITE }
	
	public static Color
		FONT_COLOR_TEXT = Color.BLACK,
		GLOBAL_BACKGROUND_COLOR = new Color(225,230,245),
		GLOBAL_MODULE_BACKGROUND_COLOR = Color.WHITE,
		GLOBAL_TABLE_BACKGROUND_COLOR = Color.WHITE,
		GLOBAL_TABLE_BORDER_COLOR = new Color(240,240,240);
		
	public static void changeGUI(ThemeTab color) {
		
		switch (color) {
		
		case BLACK:
			FONT_COLOR_TEXT = new Color(220,221,222);
			GLOBAL_BACKGROUND_COLOR = new Color(30,33,36);
			GLOBAL_MODULE_BACKGROUND_COLOR = new Color(47,49,54);
			GLOBAL_TABLE_BACKGROUND_COLOR = new Color(54,57,62);
			GLOBAL_TABLE_BORDER_COLOR = new Color(62,65,70);
			break;
			
		default:
			FONT_COLOR_TEXT = Color.BLACK;
			GLOBAL_BACKGROUND_COLOR = new Color(225,230,245);
			GLOBAL_MODULE_BACKGROUND_COLOR = Color.WHITE;
			GLOBAL_TABLE_BACKGROUND_COLOR = Color.WHITE;
			GLOBAL_TABLE_BORDER_COLOR = new Color(240,240,240);
		}
	}
	
	public static void changeGUI(String color) {
		
		switch (color) {
		
		case "BLACK":
			FONT_COLOR_TEXT = new Color(220,221,222);
			GLOBAL_BACKGROUND_COLOR = new Color(30,33,36);
			GLOBAL_MODULE_BACKGROUND_COLOR = new Color(47,49,54);
			GLOBAL_TABLE_BACKGROUND_COLOR = new Color(54,57,62);
			GLOBAL_TABLE_BORDER_COLOR = new Color(62,65,70);
			break;
			
		default:
			FONT_COLOR_TEXT = Color.BLACK;
			GLOBAL_BACKGROUND_COLOR = new Color(225,230,245);
			GLOBAL_MODULE_BACKGROUND_COLOR = Color.WHITE;
			GLOBAL_TABLE_BACKGROUND_COLOR = Color.WHITE;
			GLOBAL_TABLE_BORDER_COLOR = new Color(240,240,240);
		}
	}

}
