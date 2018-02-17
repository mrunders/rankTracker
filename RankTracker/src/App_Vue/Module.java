package App_Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import App_Controler.AControler;
import App_Observer.Observer;
import Componements.Componement_BotPannel;
import Componements.Componement_JScrollPane;
import Componements.Componement_JTable;
import Componements.Componement_StatsPanel;

public class Module extends JPanel implements Observer, Themable {
	
	private String name;
	private AControler controleur;

	private Componement_BotPannel botPan;
	private Componement_JScrollPane jspan;
	private Componement_JTable table;
	private Componement_StatsPanel statsPanel;
	
	public Module(AControler controler, String name) {
		super();
		this.setLayout(new BorderLayout());
		this.name = name;
		this.controleur = controler;
		this.botPan = new Componement_BotPannel(controler);
	}

	@Override
	public void update(String[][] tab, HashMap<String, Integer> stats) {
		
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		if (tab != null) {
		
			String[] title = {"date", "rank", "decay", "pick"};

			this.table = new Componement_JTable(tab, title);
			jspan = new Componement_JScrollPane(this.table);
			
			this.add( (this.table.getRowCount() <= 14)? this.table : jspan, BorderLayout.CENTER);
		}
		
		
		this.statsPanel = new Componement_StatsPanel(stats, this.name);
		
		this.add(this.statsPanel, BorderLayout.NORTH);
		this.add(this.botPan, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(500,540));
		adapte();
	}

	@Override
	public void adapte() {
		
		if (this.table != null) {
			this.table.setGridColor(ColorGUI.GLOBAL_TABLE_BORDER_COLOR);
			this.table.setSelectionBackground(ColorGUI.GLOBAL_TABLE_BORDER_COLOR);
			this.table.adapte();
			this.jspan.adapte();
			}
		this.setBackground(ColorGUI.GLOBAL_MODULE_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(ColorGUI.GLOBAL_TABLE_BORDER_COLOR,2));
		
		this.statsPanel.adapte();
		this.botPan.adapte();

	}
	
	public void forceModifierCmd(String c){
		
		this.botPan.entry.setText(c);
	}
	
	public String getModuleName() {
		
		return this.name;
	}
	
	public void initialiseTable() {
		this.controleur.importStats();
	}
	
	public String[][] getNewTableData() {
		
		TableModel dtm = this.table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    String[][] tableData = new String[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = (String)dtm.getValueAt(i,j);
	    return tableData;
	}
}
