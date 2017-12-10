package App_Controler;

import java.util.Scanner;

import App_Model.AModel;
import JDBC_Interactions.JDBC_request;

public class Controler extends AControler {

	public Controler(AModel cal) {
		super(cal);
	}
	
	private String format(String s) { //add <new rank> <perso>
		
		boolean space = false;
		String ss = ""; char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == ' ' && !space) {
				space = true;
				ss += "\n";
			} 
			else {
				space = false;
				ss += c;
			}
		}
		
		return ss;
	}

	@Override
	public void control(String s) {
		
		Scanner sc = new Scanner(format(s));
		
		if (!sc.hasNextLine()) return;
		
		switch (sc.nextLine()) {
		case("add"):
			
		this.calc.insert(Integer.parseInt(sc.nextLine()), sc.nextLine());
		break;
			
		case("del"):
			
			switch(sc.nextLine()) {
			case("last"):  this.calc.delete("last"); break;
			default:  System.out.println("Delete ERROR");
			}
			break;
			
		case("filter"):
			
			String s1 = sc.nextLine();
			switch(s1) {
			case("only"): this.calc.filter(sc.nextLine(), "only"); break;
			default: this.calc.filter(s1);
			}
			
			break;
			
		case("save"):
			
			switch (sc.nextLine()) {
			case("db"): this.calc.saveIntoDB(); break;
			default: System.out.println("ERROR SAVE");
			}
			break;
			
		default: System.out.println("ERROR CTRL");
		}
	}

	@Override
	public void importStats() {
		
		this.calc.importStats();
	}

}
