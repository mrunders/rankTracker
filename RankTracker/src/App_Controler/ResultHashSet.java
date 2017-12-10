package App_Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ResultHashSet {
	
	private HashMap<String, Integer> numberSet = new HashMap<>();
	private String[][] data;
	
	public ResultHashSet() {
		
	}
	
	private boolean pickInContained(String s, String pick) {
		
		return s.contains(pick);
	}
	
	private HashMap<String, Integer> fillHashMap(HashMap<String, Integer> m, int dataLenght, int totalOfWin, int totalOfDraw, int totalOfLose, int avgWin, int avgLose, int currentRank){
		
		HashMap<String, Integer> n = new HashMap<>();
		int intParsed; float f;
		
		n.put(HASH_CONSTANTES.AVERANGE_WIN, avgWin);
		n.put(HASH_CONSTANTES.AVERANGE_LOSE, avgLose);
		n.put(HASH_CONSTANTES.AVERANGE_STAT, ( avgWin < -avgLose )? avgLose + avgWin : avgWin + avgLose);
		n.put(HASH_CONSTANTES.TOTAL_WIN, totalOfWin);
		n.put(HASH_CONSTANTES.TOTAL_LOSE, totalOfLose);
		n.put(HASH_CONSTANTES.TOTAL_DRAW, totalOfDraw);
		n.put(HASH_CONSTANTES.TOTAL_COMP_POINT, totalOfWin*15 + totalOfDraw*5);
		n.put(HASH_CONSTANTES.TOTAL_PLAYED, (data != null)? dataLenght-2 : 0);

		f = totalOfWin/(float)(dataLenght-2);
		intParsed = (int)(f*100);
		n.put(HASH_CONSTANTES.WINRATE, (data != null)? intParsed : -1);
		
		if (intParsed < 50 && totalOfWin < totalOfLose) {
			
			intParsed = currentRank + (totalOfLose - totalOfWin) * avgWin;
			n.put(HASH_CONSTANTES.PREV_WR, intParsed);
		}
		
		return n;
	}
	
	public String[][] extractWithPick(String pick){
		
		return extractWithPick(this.data, pick, "");
	}
	
	public String[][] extractWithPick(String pick, String modificateur){
		
		return extractWithPick(this.data, pick, modificateur);
	}
	
	public String[][] extractWithPick(String[][] r, String pick, String modificateur) {
		
		ArrayList<String[]> ars = new ArrayList<>();
		
		if (modificateur == "") {
			for (String[] rr : r) {
				if (pickInContained(rr[3], pick)) ars.add(rr);
			}
		} else if (modificateur == "only") {
			for (String[] rr : r) {
				if (rr[3].equals(pick)) ars.add(rr);
			}
		}
			
		String[][] rr = new String[ars.size()][r[0].length]; int j=0;
		for (Iterator<String[]> i = ars.iterator() ; i.hasNext() ; ) {
			rr[j++] = i.next();
		}
		
		return rr;
	}
	
	public HashMap<String, Integer> calculeHashMap(String[][] rr){
		
		HashMap<String,Integer> map = new HashMap<>();
		int totalOfWin = 0, totalOfDraw = 0, totalOfLose = 0, avgWin = 0, avgLose = 0, intParsed;
		for (String[] r : rr ) {
			if (r[0].contains("TOTAL")) continue;
			
			intParsed = Integer.parseInt(r[2]);
			if (0 < intParsed) {
				totalOfWin++; 
				if (intParsed < 1000) avgWin += intParsed;
			} else if (intParsed < 0) {
				totalOfLose++; avgLose += intParsed;
			} else totalOfDraw++;
			
			
			avgWin /= totalOfWin;
			avgLose /=totalOfLose;
		}
		
		return fillHashMap(map, rr.length, totalOfWin, totalOfDraw, totalOfLose, avgWin, avgLose, Integer.parseInt(rr[0][1]));
	}
	
	
	public void WorkAndFill(ResultSet r){
		
		if (r == null) return;
		
		ArrayList<String[]> c = new ArrayList<>();
		String[] str = new String[4], td = {"TODAY","","",""};
		String currentDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()).toString().substring(6, 11);
		int lastDay = 0; int indTD = -1, totalOfWin = 0, totalOfDraw = 0, totalOfLose = 0, avgWin = 0, avgLose = 0, intParsed;
		boolean b = true;
		
		try {
			if (r != null && !r.isClosed()) {
				
				// tank que r != null, on lit les donnés de la bd
				while ( r.next() ) {
					str[0] = r.getString(1);
					str[1] = r.getInt(2) + "";
					str[2] = r.getInt(3) + "";
					str[3] = r.getString(4);
					
					intParsed = Integer.parseInt(str[2]);
					// inserer un '+' si le nombre trouvé est positif
					if (intParsed > 0) { 
						str[2] = "+" + str[2]; totalOfWin++; 
						if (intParsed < 1000) avgWin += intParsed;
					} else if (intParsed < 0) {
						totalOfLose++; 
						avgLose += intParsed;
					} else totalOfDraw++;
					
					// if b = vrai -> on import les donnés des jours antérieur à la date.now
					if (b) {
						
						// si on tombe sur ajd on insert une ligne qui délimite date.now
						if (str[0].substring(6, 11).contentEquals(currentDate)) {
							b = false;
							
							try {
							lastDay = Integer.parseInt(c.get(c.size()-1)[1]);
							} catch (java.lang.ArrayIndexOutOfBoundsException e) {
								lastDay = 0;
							}
							c.add(td);
						} indTD++;
						
					}
					
					c.add(str.clone());
					
				}
				
				// si b est encore vrai, c'est qu'on n'a rien fait ajd
				if (b) c.add(td);
				
				// c sale çà
				try {
					c.get(indTD)[1] = "TOTAL WIN: "+ (Integer.parseInt(c.get(c.size()-1)[1]) - lastDay);
				} catch (java.lang.NumberFormatException e) {
					c.get(++indTD)[1] = "TOTAL WIN: 0";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!c.isEmpty()) {
			this.data = new String[c.size()][c.get(0).length];
			
			int j = this.data.length-1;
			for (int i = 0; i < this.data.length; i++) {
				this.data[j--] = c.get(i);
			}
			
			if (totalOfWin != 0) avgWin /= totalOfWin; else avgWin = 0;
			if (totalOfLose!= 0) avgLose /=totalOfLose; else avgLose = 0;
			
		} else this.data = null;
		
		
		totalOfWin--;
		if (!c.isEmpty())
			this.numberSet = fillHashMap(this.numberSet, data.length, totalOfWin, totalOfDraw, totalOfLose, avgWin, avgLose, Integer.parseInt(c.get(0)[1]));
		
	}
	
	public HashMap<String, Integer> getHashMap(){
		return this.numberSet;
	}
	
	public String[][] getDataTable() {
		return this.data;
	}

}
