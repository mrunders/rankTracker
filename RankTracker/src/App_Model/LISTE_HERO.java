package App_Model;

public enum LISTE_HERO {

	ANA ("ana"),
	MOIRA ("moira");
	
	private String nom;
	
	LISTE_HERO(String nom) {
		this.nom = nom;
	}
	
	public String toString() {
		return this.nom;
	}
}
