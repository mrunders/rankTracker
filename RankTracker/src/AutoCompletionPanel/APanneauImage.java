package AutoCompletionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import App_Vue.ColorGUI;
import App_Vue.Themable;

abstract class APanneauImage extends JPanel implements Themable, MouseListener {
	
	public static final Dimension dimentionImage = new Dimension(60,60);
	
	protected String nom = null;
	protected Image icon = null;
	protected boolean selectionne = false;
	
	public APanneauImage(String nom, Image img){
		
		if ( nom.contains("HeroIcon_") ) nom = nom.substring(9);

		this.nom = nom.substring(0, nom.length()-4);
		this.icon = img;
		this.addMouseListener(this);
		this.setPreferredSize(dimentionImage);
	}
	
	public boolean estSelectionne(){
		
		return this.selectionne;
	}
	
	public void deselectionne() {
		this.selectionne = false;
		repaint();
		
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {

		this.selectionne = !this.selectionne;
		repaint();
		
	}

	
	@Override
	public void paintComponent(Graphics g){
		
		if (this.selectionne)
			g.setColor(Color.GREEN);
		else 
			g.setColor(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		
		g.fillRect(0, 0, dimentionImage.width, dimentionImage.height);
		
		g.drawImage(this.icon, 0, 0, dimentionImage.width, dimentionImage.height ,this);
		
	}
	
	public void adapte(){
		
		this.setBackground(ColorGUI.GLOBAL_TABLE_BACKGROUND_COLOR);
	}
	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {}

	

}
