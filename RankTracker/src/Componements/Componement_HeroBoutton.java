package Componements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import App_Vue.ColorGUI;
import App_Vue.RESSOURCES;
import App_Vue.Themable;

public class Componement_HeroBoutton extends JButton implements ActionListener, Themable {
	
	private String heroName;
	private Image heroIcon;
	private boolean estSelectionner = false;
	private static Dimension s = new Dimension(50,50);
	
	public Componement_HeroBoutton(String heroName) {
		 
		this.heroName = heroName;
		System.out.println(" format:" + this.heroName);
		this.heroIcon = RESSOURCES.HeroIconMap.get(heroName);
		this.setPreferredSize(s);
		
	}
	
	public void paintcomponent(Graphics g) {
		
		super.paintComponent(g);
		
		System.out.println("dd");
		
		if (this.estSelectionner) g.setColor(Color.GREEN);
		else g.setColor(Color.WHITE);
		
		g.fillRect(0, 0, s.width, s.height);
		g.drawImage(this.heroIcon, 4, 4, this);
	}
	
	public boolean estSelectionner() {
		
		return this.estSelectionner;
	}
	
	public String obtenirNomHero() {
		
		return this.heroName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.estSelectionner = !this.estSelectionner;
		this.repaint();
		this.revalidate();
		
	}

	@Override
	public void adapte() {
	
		this.repaint();
		this.revalidate();
		
	}
	

}
