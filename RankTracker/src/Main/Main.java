package Main;

import App_Controler.AControler;
import App_Controler.Controler;
import App_Model.AModel;
import App_Model.Model;
import App_Observer.Observer;
import App_Vue.ColorGUI;
import App_Vue.ColorGUI.ThemeTab;
import App_Vue.Fenetre;
import App_Vue.Module;
import App_Vue.RESSOURCES;

public class Main {
	
	public static void main(String[] args)  {
		
		RESSOURCES.charger();
		
		AModel model_main = new Model("main"); 
		AControler controle_main = new Controler(model_main); 
		Observer vue_main = new Module(controle_main, "MAIN ACCOUNT !"); 
		model_main.addObserver(vue_main);
		
		AModel model_smurf = new Model("smurf"); 
		AControler controle_smurf = new Controler(model_smurf); 
		Observer vue_smurf = new Module(controle_smurf, "SMURF ACCOUNT"); 
		model_smurf.addObserver(vue_smurf);
	
		AModel model_tryhard = new Model("tryhard"); 
		AControler controle_tryhard = new Controler(model_tryhard); 
		Observer vue_tryhard = new Module(controle_tryhard, "TRYHARD ACCOUNT"); 
		model_tryhard.addObserver(vue_tryhard);
		
		Module[] g = {(Module) vue_main, (Module) vue_smurf, (Module) vue_tryhard};
		
		Fenetre f = new Fenetre(g);
		ColorGUI.changeGUI(ThemeTab.BLACK);
		f.adapte();

	}

}
