package model;

import java.io.Serializable;
import java.time.LocalDate;



public class Temperatura  implements Serializable {
	

	Integer id = 0;
	double temperatura;
	String local;
	LocalDate data;

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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Temperatura [id=" + id + ", temperatura=" + temperatura + ", local=" + local + ", data=" + data + "]";
	}
	
	

}
