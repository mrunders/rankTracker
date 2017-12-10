package App_Controler;

import App_Model.AModel;

public abstract class AControler {
	
	protected AModel calc; 
	
	public AControler(AModel cal){ 
		this.calc = cal;
	}
	
	public void setXXX(String s){
		
	}
	
	public abstract void control(String string);

	public abstract void importStats();

}
