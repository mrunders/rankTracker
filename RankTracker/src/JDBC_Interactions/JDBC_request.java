package JDBC_Interactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JDBC_request {
	
	
	public static boolean deleteLast(Connection c) {
		
		PreparedStatement stmt = null;
		
		try {
			return c.prepareStatement("Delete from Rank where date = (Select max(date) from Rank);").execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getPreviousRank(Connection c) {
		
		PreparedStatement stmt = null;
		
		try {
			return  c.prepareStatement("SELECT rank from Rank where date = (Select max(date) from Rank);").executeQuery().getInt(1);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
	public static boolean insertTab(Connection c, String date, int rank, int diff, String pmain) {
		
		PreparedStatement stmt;
		
		try {
			assert(!c.isClosed());
			stmt = c.prepareStatement("insert into Rank values ('"+date+"',"+rank+","+diff+",'"+pmain+"');");
			return stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static ResultSet allTab(Connection c){
			
		PreparedStatement stmt;
		ResultSet r = null;
		
		try {
			stmt = c.prepareStatement("SELECT * from Rank ORDER BY Date");
			r = stmt.executeQuery();
			
		} catch (SQLException e) {
			JDBC_Connection.buildTable(c);
			
			try {
				stmt = c.prepareStatement("SELECT * from Rank ORDER BY Date");
				r = stmt.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return r;

	}

}
