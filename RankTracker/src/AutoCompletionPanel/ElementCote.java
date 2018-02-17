package AutoCompletionPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import App_Vue.ColorGUI;
import App_Vue.Themable;
import Componements.Componement_Button;

public class ElementCote extends JPanel implements RecuperationDeDonne, ActionListener, Themable {
	
	private static final Dimension d = new Dimension(60, 30);
	
	private JTextField champ = new JTextField();
	private Componement_Button rangIcon = new Componement_Button(),
					moin  = new Componement_Button("-1", d),
					plu   = new Componement_Button("+1", d),
					moins = new Componement_Button("-25", d),
					plus  = new Componement_Button("+25", d);
	
	private JPanel sousPan = new JPanel(new BorderLayout());

	public ElementCote(int cote) {
		
		this.setLayout(new FlowLayout());
		this.champ.setText(cote + "");
		this.rangIcon.setEnabled(false);
		
		this.plus.addActionListener(this);
		this.moins.addActionListener(this);
		this.plu.addActionListener(this);
		this.moin.addActionListener(this);
		
		this.add(this.moins);
		this.add(this.moin);
		this.add(this.sousPan);
		this.add(this.plu);
		this.add(this.plus);
		
		this.champ.setPreferredSize(d);
		
		this.sousPan.add(this.rangIcon, BorderLayout.WEST);
		this.sousPan.add(this.champ, BorderLayout.EAST);
	}
	
	@Override
	public String recuperationSelectionner() {
		
		return this.champ.getText();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton b = (JButton)(arg0.getSource());
		
		switch (b.getText()){
		case ("+25"):
			this.champ.setText( (Integer.parseInt(this.champ.getText()) + 25) + "");
			break;
		case ("-25"):
			this.champ.setText( (Integer.parseInt(this.champ.getText()) + -25) + "");
			break;
		case ("+1"):
			this.champ.setText( (Integer.parseInt(this.champ.getText()) + 1) + "");
			break;
		case ("-1"):
			this.champ.setText( (Integer.parseInt(this.champ.getText()) + -1) + "");
			break;
		}
	}

	@Override
	public void adapte() {
		
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		this.moin.adapte(); this.plus.adapte();
		this.moins.adapte(); this.plu.adapte();
		this.rangIcon.adapte();
		
		this.champ.setBackground(ColorGUI.GLOBAL_TABLE_BACKGROUND_COLOR);
		this.champ.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR, 1));
		this.champ.setForeground(ColorGUI.FONT_COLOR_TEXT);
	}

}
