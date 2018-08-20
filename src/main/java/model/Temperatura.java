package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Temperatura  implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	double temperatura;
	String local;
	
	@Temporal(TemporalType.DATE)
	java.util.Date data;

	private static final long serialVersionUID = 1L;
		
	public Temperatura() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



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
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	
	

}
