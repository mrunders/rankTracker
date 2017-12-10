package Componements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Componement_ObjectiveBar extends JPanel {
	
	public Componement_ObjectiveBar(String barName, int min, int max, int prefered, int current) {
		
		assert(min < max);
		assert(min <= prefered && prefered <= max);
		assert(min <= current && current <= max);
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel(barName), BorderLayout.NORTH);
		
	}
	
	private class CouloredBar extends JPanel {
		
		private HashMap<Color, Integer> c = new HashMap<>();
		
		public void add(Color c, int i) {
			this.c.put(c, i);
		}
	
		public void paintComponement(Graphics g) {
			
			for (Color c : this.c.keySet()) {
				g.setColor(c);
				g.drawRect(0, -50, this.c.get(c), 50);
			}
		}
	
	}

}
