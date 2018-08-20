package model;

import java.io.Serializable;
import java.util.Date;

public class Periodo implements Serializable {

	String local;
	Date   inicio;
	Date   fim;
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	public Periodo() {
		// TODO Auto-generated constructor stub
	}
	public Periodo(String local, Date inicio, Date fim) {
		super();
		this.local = local;
		this.inicio = inicio;
		this.fim = fim;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	
	
	
}
