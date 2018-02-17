package App_Model;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import App_Controler.TableauStorage;
import JDBC_Interactions.JDBC_Connection;
import JDBC_Interactions.JDBC_request;

public class Model extends AModel {
	
	private String account;
	private TableauStorage tableauStorage;
	
	public Model(String acc) {
		this.account = acc;
		
		Connection c = JDBC_Connection.connect(this.account);
		this.tableauStorage = new TableauStorage(JDBC_request.allTab(c));
		JDBC_Connection.Close(c);
	}

	@Override
	public void importStats() {
		
		Connection c;
		
		/*if (!new File(this.account+".db").exists()) {
			System.out.println("FILE NOT FOUND!");
			c = JDBC_Connection.connect(this.account);
			JDBC_Connection.buildTable(c);
			
		} else*/ c = JDBC_Connection.connect(this.account);
		
		this.notifyObserver(this.tableauStorage.getTableau(), this.tableauStorage.getStatus());
		JDBC_Connection.Close(c);
		
	}

	@Override
	public void insert(int parseInt, String next) {
		
		String date; int diff;
		Connection c = JDBC_Connection.connect(this.account);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		date = dtf.format(now).toString();
		diff = -(JDBC_request.getPreviousRank(c)-parseInt);
		
		JDBC_request.insertTab(c, date, parseInt, diff, next);
		JDBC_Connection.Close(c);
		
		this.tableauStorage.ajouterUneLigne(date, parseInt, diff, next);
		
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
		/*JFrame f = new JFrame();
		
		f.setVisible(true);
		f.setSize(400, 200);
		f.setTitle(this.account + ":" + nextLine);
		f.setContentPane(new Componement_StatsPanel(this.rhs.calculeHashMap(this.rhs.extractWithPick(nextLine, 
		                                     modificateur)), this.account + ":" + nextLine));	
		*/
	}

	@Override
	public void saveIntoDB() {
		
		Connection c = JDBC_Connection.connect(this.account + "_BK");
		JDBC_Connection.buildTable(c, super.obs.getNewTableData());
		JDBC_Connection.Close(c);
		
	}

}
