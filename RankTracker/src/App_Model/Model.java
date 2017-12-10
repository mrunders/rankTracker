package App_Model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;

import App_Controler.ResultHashSet;
import App_Controler.ResultHashSet2;
import Componements.Componement_StatsPanel;
import JDBC_Interactions.JDBC_Connection;
import JDBC_Interactions.JDBC_request;

public class Model extends AModel {
	
	private String account;
	private ResultHashSet rhs;
	private String[][] data;
	
	public Model(String acc) {
		this.account = acc;
	}

	@Override
	public void importStats() {
		
		ResultHashSet rhs = new ResultHashSet();
		Connection c;
		
		/*if (!new File(this.account+".db").exists()) {
			System.out.println("FILE NOT FOUND!");
			c = JDBC_Connection.connect(this.account);
			JDBC_Connection.buildTable(c);
			
		} else*/ c = JDBC_Connection.connect(this.account);
		
		rhs.WorkAndFill(JDBC_request.allTab(c));
		
		this.notifyObserver(rhs.getDataTable(), rhs.getHashMap());
		JDBC_Connection.Close(c);
		this.rhs = rhs; this.data = rhs.getDataTable();
		
	}

	@Override
	public void insert(int parseInt, String next) {
		
		Connection c = JDBC_Connection.connect(this.account);
		JDBC_request.insertTab(c, parseInt, next);
		JDBC_Connection.Close(c);
		
	}

	@Override
	public void delete(String string) {
		
		Connection c = JDBC_Connection.connect(this.account);
		JDBC_request.deleteLast(c);
		JDBC_Connection.Close(c);
		
	}

	@Override
	public void filter(String nextLine) {
		
		filter(nextLine, "");
		
	}

	@Override
	public void filter(String nextLine, String modificateur) {
		JFrame f = new JFrame();
		
		f.setVisible(true);
		f.setSize(400, 200);
		f.setTitle(this.account + ":" + nextLine);
		f.setContentPane(new Componement_StatsPanel(this.rhs.calculeHashMap(this.rhs.extractWithPick(nextLine, modificateur)), this.account + ":" + nextLine));	
	}

	@Override
	public void saveIntoDB() {
		
		Connection c = JDBC_Connection.connect(this.account + "_BK");
		JDBC_Connection.buildTable(c, super.obs.getNewTableData());
		JDBC_Connection.Close(c);
		
	}

}
