package App_Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class TableauStorage {
	
	private	HashMap<String, Integer> status = new HashMap<>();
	private ArrayList<String[]> tableau;
	
	private int totalOfWin = 0,
			totalOfDraw = 0,
			totalOfLose = 0,
			avgWin = 0,
			avgLose = 0,
			currentRank = 0;
	
	public TableauStorage(ResultSet rs){
		
		int intParsed;
		boolean b = true;
		String currentDate;
		String[] str = new String[4], td = {"TODAY","","",""};
		
		this.tableau = new ArrayList<>();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		currentDate = dtf.format(now).toString();
		
		try {
			while ( rs.next() ){
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				
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
						
						this.tableau.add(td);
					}
					
				}
				
				this.tableau.add(str.clone());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		remplirHashMap();
	}
	
	public void ajouterUneLigne(String date, int nouvcote, int diff, String hero){
		
		String t[] = new String[4];
		
		t[0] = date; 
		t[1] = nouvcote + ""; 
		t[2] = diff + ""; 
		t[3] = hero;
		
		this.tableau.add(t);
		
		if (diff < 0){ totalOfLose++; avgLose += diff;}
		else if (diff > 0){ totalOfWin++; avgWin += diff;}
		else totalOfDraw++;
		
		remplirHashMap();
	}
	
	private void remplirHashMap(){
		
		int intParsed; float f;
		int aw = (totalOfWin!=0)? avgWin/totalOfWin : 0;
		int al = (totalOfLose!=0)? avgLose/totalOfLose : 0;
		
		this.status.clear();
		this.status.put(HASH_CONSTANTES.AVERANGE_WIN, aw);
		this.status.put(HASH_CONSTANTES.AVERANGE_LOSE, al);
		this.status.put(HASH_CONSTANTES.AVERANGE_STAT, ( aw < -al )? al + aw : aw + al);
		this.status.put(HASH_CONSTANTES.TOTAL_WIN, totalOfWin);
		this.status.put(HASH_CONSTANTES.TOTAL_LOSE, totalOfLose);
		this.status.put(HASH_CONSTANTES.TOTAL_DRAW, totalOfDraw);
		this.status.put(HASH_CONSTANTES.TOTAL_COMP_POINT, totalOfWin*15 + totalOfDraw*5);
		this.status.put(HASH_CONSTANTES.TOTAL_PLAYED, totalOfDraw+totalOfLose+totalOfWin);

		f = totalOfWin/(float)(this.status.get(HASH_CONSTANTES.TOTAL_PLAYED)-1);
		intParsed = (int)(f*100);
		this.status.put(HASH_CONSTANTES.WINRATE, (!this.tableau.isEmpty())? intParsed : -1);
		
		if (intParsed < 50 && totalOfWin < totalOfLose) {
			
			intParsed = currentRank + (totalOfLose - totalOfWin) * avgWin;
			this.status.put(HASH_CONSTANTES.PREV_WR, intParsed);
		}
		
	}
	
	public void supprimerDernier(){
		
		int diff = Integer.parseInt(this.tableau.get(this.tableau.size()-1)[3]);
		this.tableau.remove(this.tableau.size()-1);
		
		if (diff < 0){ totalOfLose--; avgLose -= diff;}
		else if (diff > 0){ totalOfWin--; avgWin -= diff;}
		else totalOfDraw--;
		
		remplirHashMap();
	}

	public HashMap<String, Integer> getStatus(){

		if (this.status.isEmpty()) remplirHashMap();
		return this.status;
	}
	
	

	public String[][] getTableau(){
		
		if (this.tableau.isEmpty()) return null;
		
		String[][] ret = new String[this.tableau.size()][this.tableau.get(0).length];
		
		int j = this.tableau.size()-1;
		for (int i = 0; i < this.tableau.size(); i++) {
			ret[j--] = this.tableau.get(i);
		}
		
		return ret;
	}
}
