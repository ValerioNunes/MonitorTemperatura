package model;

import java.io.Serializable;

public class LocalView implements Serializable{
	
	double temperatura;
	String local;
	private static final long serialVersionUID = 1L;
	
	
	public LocalView() {}


	public double getTemperatura() {
		return temperatura;
	}


	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}



	

}
