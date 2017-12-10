package JDBC_Interactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Connection {
	
	private static final String MAIN_ACCOUNT = "jdbc:sqlite:mainacc.db",
							   SMURF_ACCOUNT ="jdbc:sqlite:smurfacc.db",
							   TRYHARD_ACC   = "jdbc:sqlite:tryhard.db";

	public static Connection connect(String account){
		
		Connection c = null;
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			switch(account) {
			case("main"):  c = DriverManager.getConnection(MAIN_ACCOUNT); break;
			case("smurf"): c = DriverManager.getConnection(SMURF_ACCOUNT);break;
			case("tryhard"): c = DriverManager.getConnection(TRYHARD_ACC);break;
			default: c = DriverManager.getConnection("jdbc:sqlite:"+account+".db");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void Close(Connection c){
		
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean buildTable(Connection c) {
		
		PreparedStatement stmt;
		
		try {
			stmt = c.prepareStatement("create table Rank (date Date,rank int,paverange int, heroPlayed varchar(20));");
			return stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean buildTable(Connection c, String[][] data) {
		
		PreparedStatement stmt;
		buildTable(c);
		
		try {
			
			for (String[] r : data) {
				if (r[0].charAt(0) == 'T') continue;
				stmt = c.prepareStatement("insert into Rank values (?,?,?,?);");
				stmt.setString(1, r[0]);
				stmt.setInt(2, Integer.parseInt(r[1]));
				stmt.setInt(3, Integer.parseInt(r[2]));
				stmt.setString(4, r[3]);
				
				stmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
}
