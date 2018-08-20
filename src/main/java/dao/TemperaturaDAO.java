package dao;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import database.JPA;
import model.Temperatura;

public class TemperaturaDAO {
    EntityManager em;
	
	public TemperaturaDAO() {
		super();
		this.em = JPA.em();
	}
	@Transactional
	public Temperatura Salvar(Object object) {
		// TODO Auto-generated method stub
		Temperatura obj =  (Temperatura) object;
				try{	
							em.getTransaction().begin();
							em.persist(obj);
							em.getTransaction().commit();
				}
				finally{
					if(em != null){
						em.close();
						}
					}
				return obj;
	}

	
	public void Update(Object object) {
		// TODO Auto-generated method stub
		
		Temperatura o = (Temperatura) object;
		try{	
					em.getTransaction().begin();
					em.merge(o);
					em.getTransaction().commit();
					//JOptionPane.showMessageDialog(null, "\tAlterado com Sucesso !!!");
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null, "\tFalha  ao Alterar  " + e);
		}
		finally{
			if(em != null)
				em.close();
		}
	}

	
	public void Delete(Object object) {
		
		Temperatura o = (Temperatura) object;
		try{
					em.getTransaction().begin();
					o= em.merge(o);
					em.flush();
					em.remove(o);
					em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public List  getLocaiCadastrados() {
		List results = null;
		try{
			
			String hql = "SELECT distinct T.local FROM Temperatura T";
			Query query = em.createQuery(hql);
			results =  query.getResultList();
		
		}catch(Exception e){
		}
		finally{
			if(em != null)
				em.close();	
		}
		
		return results;
	}
	public List<Temperatura> getItens(){
		
		List<Temperatura> obj    = null;
		
		try{
			 obj = em.createQuery("FROM Temperatura", Temperatura.class).getResultList();
		
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null, "\tBuscar Objeto : " + e);
		}
		finally{
			if(em != null)
				em.close();	
		}
		
		return obj;
	}
	
	public List<Temperatura> getItens(String nome, String coluna){
		
		List<Temperatura> obj    = null;
		
		try{
			 obj = em.createQuery("FROM Temperatura where "+coluna+" like :nome", Temperatura.class).setParameter("nome","%"+ nome +"%").getResultList();
		
		}
		finally{
			if(em != null)
				em.close();	
		}
		
		return obj;
	}
	
	public Temperatura getItemUltimo(String nome, String coluna){
		
		Temperatura obj    = null;
		
		try{
			 obj = em.createQuery("FROM Temperatura where "+coluna+" like :nome  ORDER BY id DESC", Temperatura.class).setParameter("nome","%"+ nome +"%").setMaxResults(1).getSingleResult();
		
		}
		finally{
			if(em != null)
				em.close();	
		}
		
		return obj;
	}
	public List<Temperatura> getItens(String nome, String coluna,Date startDate, Date endDate ){
		
		List<Temperatura> obj    = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String frmDate = format.format(startDate);
		String enDate = format.format(endDate);
		System.out.println(frmDate+ " "+ enDate);
		try{
			 obj = em.createQuery("FROM Temperatura T where T."+coluna+" like :nome AND  T.data  BETWEEN :stDate AND :edDate", Temperatura.class)
					 .setParameter("stDate", startDate)
					 .setParameter("edDate", endDate)
					 .setParameter("nome","%"+ nome +"%")
					 .getResultList();
		
		}
		finally{
			if(em != null)
				em.close();	
		}
		
		return obj;
	}
	
	public Temperatura getItens(int id){
		
		Temperatura obj    = null;
		
		try{
			 obj = em.createQuery("FROM Temperatura where id = :id", Temperatura.class).setParameter("id",id).getSingleResult();
		
		}finally{
			
		}
		
		return obj;
	}
	
}
