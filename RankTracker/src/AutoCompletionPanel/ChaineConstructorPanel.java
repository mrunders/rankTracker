package AutoCompletionPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import App_Vue.ColorGUI;
import App_Vue.Fenetre;
import App_Vue.RESSOURCES;
import App_Vue.Themable;
import Componements.Componement_Button;

public class ChaineConstructorPanel extends JPanel implements Themable, ActionListener {
	
	private Fenetre fen = null;
	private ListeImages hero = null;
	private ListeImages maps = null;
	private ElementCote cote = null;
	
	private Componement_Button valider = new Componement_Button("valider");
	
	public ChaineConstructorPanel(){
		
		this.setLayout(new BorderLayout());
		
		this.valider.addActionListener(this);
		this.cote = new ElementCote(3400);
		
		try {
			this.hero = new ListeImages(ListeImages.INSTANCE_HERO, RESSOURCES.heroListe);
			this.maps = new ListeImages(ListeImages.INSTANCE_MAP,  RESSOURCES.mapListe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.add(this.maps,BorderLayout.NORTH);
		this.add(this.hero, BorderLayout.CENTER);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(this.cote,BorderLayout.NORTH);
		p.add(this.valider,BorderLayout.SOUTH);
		this.add(p, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(360,540));
		this.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR, 2));
		
	}

	public ChaineConstructorPanel(Fenetre fenetre) {
		
		this();
		this.fen = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		StringBuilder str = new StringBuilder();
		str.append("add ");
		str.append(this.cote.recuperationSelectionner());
		str.append(" ");
		str.append(this.hero.recuperationSelectionner());
		str.append(":");
		str.append(this.maps.recuperationSelectionner());
		System.out.println(str);
		this.fen.ecraserTextField(str.toString());
		this.hero.toutDeselectionner();
		this.maps.toutDeselectionner();
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_BACKGROUND_COLOR);
		this.valider.adapte();
		this.hero.adapte();
		this.maps.adapte();
		this.cote.adapte();
		
	}

}
