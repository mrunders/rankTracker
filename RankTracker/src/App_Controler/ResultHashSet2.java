package App_Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class ResultHashSet2 {
	
	private HashMap<String, Integer> numberSet = new HashMap<>();
	private String[][] data;
	
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
		n.put(HASH_CONSTANTES.TOTAL_PLAYED, totalOfDraw+totalOfLose+totalOfWin);

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
	
	
	public void WorkAndFill(ResultSet r) throws SQLException{
		
		if (r == null || r.isClosed()) return;
		
		ArrayList<String[]> listePreFaite = new ArrayList<>();
		String dateCourante = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
		String[] insererDetailJour = {"","","",""};
		String[] str = new String[4];
		
		int coteParser;
		int totalOfWin=0, totalOfLose=0, totalOfDraw=0, totalPtsWin=0, totalPtsLose=0;
		int ttofWin=0, ttOfLose=0, ttOfDaw=0, ttpw=0, ttpl=0;
		
		for (; r.next() ; ) {
			
			str[0] = r.getString(1);
			str[1] = r.getString(2);
			str[2] = r.getString(3);
			str[3] = r.getString(4);
			
			coteParser = Integer.parseInt(str[2]);
			
			if (coteParser > 0){ 
				str[2] = "+" + str[2]; totalOfWin++; totalPtsWin += coteParser;
				ttofWin++; ttpw += coteParser;
			} else if (coteParser < 0) { 
				totalOfLose++; totalPtsLose += coteParser;
				ttOfLose++; ttpl += coteParser;
				}
			else {
				totalOfDraw++; ttOfDaw++;
			}
			
			if ( !str[0].substring(0, 11).contentEquals(dateCourante) ) {
				insererDetailJour[1] = "TOTALWIN: " + (ttpw - ttpl);
				insererDetailJour[2] = "GAMES: " + ttofWin + "/" + ttOfLose + "/" + ttOfDaw;
				ttofWin = 0; ttOfLose = 0; ttOfDaw = 0; ttpw = 0; ttpl = 0;
				dateCourante = str[0].substring(0, 11);
				
				System.out.format("[%s,%s,%s,%s]\n", insererDetailJour[0], insererDetailJour[1], insererDetailJour[2], insererDetailJour[3]);
				listePreFaite.add(insererDetailJour.clone());
				
			}
			
			System.out.format("[%s,%s,%s,%s]\n", str[0], str[1], str[2], str[3]);
			listePreFaite.add(str.clone());
		}
		
		totalOfWin--;
		if (!listePreFaite.isEmpty()) {
			
			this.data = new String[listePreFaite.size()][listePreFaite.get(0).length];
			
			int j=this.data.length-1;
			for (int i=0;i<this.data.length;++i) {
				this.data[j--] = listePreFaite.get(i);
			}
		} else this.data = null;
		
		if (!listePreFaite.isEmpty())
			this.numberSet = fillHashMap(this.numberSet, data.length, totalOfWin, totalOfDraw, 
			totalOfLose, totalPtsWin/totalOfWin, totalPtsLose/totalOfLose, 
			Integer.parseInt(this.data[0][1]));
		
	}
	
	public HashMap<String, Integer> getHashMap(){
		return this.numberSet;
	}
	
	public String[][] getDataTable() {
		return this.data;
	}

}
