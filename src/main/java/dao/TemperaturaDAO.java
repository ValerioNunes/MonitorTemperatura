package dao;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;



import model.Temperatura;

//http://localhost:8080/SampleApp-1.0/webservice/ServicoMonitorTemp/getTest

public class TemperaturaDAO {
	
	 static Connection conn = null;
	 public static void connect() {
	       
	        try {
	            // db parameters
	        	try {
	        	
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				       
					e.printStackTrace();
				}

	            String url = "jdbc:sqlite::resource:monitortemp.db";
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
	        } catch (SQLException e) {
	            System.out.println( e.getClass().getName() + ": " + e.getMessage());
	        }

	    }
	
	public static void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public TemperaturaDAO() {
		super();
		connect();
	
	}

	
	public Temperatura Salvar(Object object) {
		// TODO Auto-generated method stub
		Temperatura obj =  (Temperatura) object;
		
	        String sql = "INSERT INTO temperatura (local,data,temperatura) VALUES(?,?,?)";
	        
	        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	            pstmt.setString(1, obj.getLocal());
	            pstmt.setString(2, obj.getData().toString());
	            pstmt.setDouble(3, obj.getTemperatura());
	            
	            int affectedRows = pstmt.executeUpdate();

	            if (affectedRows == 0) {
	                throw new SQLException("Creating user failed, no rows affected.");
	            }

	            try (ResultSet generatedKeys =  pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    obj.setId(generatedKeys.getInt(1));
	                }
	                else {
	                    throw new SQLException("Creating user failed, no ID obtained.");
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	       
		return obj;
	}
	
	
	public void Update(Object object) {
		// TODO Auto-generated method stub
		
		Temperatura obj = (Temperatura) object;
		try{	
				if(obj.getId() !=  0) {					
			        String sql = "UPDATE temperatura SET local = ? ,data = ? ,temperatura = ? WHERE id = ?";
			        
			        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        
			            pstmt.setString(1, obj.getLocal());
			            pstmt.setString(2, obj.getData().toString());
			            pstmt.setDouble(3, obj.getTemperatura());
			            pstmt.setDouble(4, obj.getId());
			            pstmt.executeUpdate();
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
		        }
			}
	        
		}catch(Exception e){
			 
		}
		
	}

	
	public boolean Delete(int id) {
		
		try{
			
	        String sql = "DELETE FROM  temperatura WHERE id = ?";
	        
	        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
			return true; 
		}catch(Exception e){
						
			return false;
		}
	}

	public List<String>  getLocaiCadastrados() {
		
		ArrayList<String> results = new ArrayList();
		try{
			
			 String sql = "SELECT DISTINCT local from temperatura";   
			
				try (
				     Statement stmt  = conn.createStatement();
				     ResultSet rs    = stmt.executeQuery(sql)){
				    
				    // loop through the result set
				    while (rs.next()) {
				    	
				            results.add(rs.getString("local"));
				    }
				} catch (SQLException e) {
				    System.out.println(e.getMessage());
				}
				
				
		}catch(Exception e){
		}
		finally{
			
		}
		
		return results;
	}
	public List<Temperatura> getItens(){
		
		ArrayList<Temperatura> obj    =   new ArrayList<Temperatura>();
		
		try{
			
			 String sql = "SELECT id, local,data,temperatura  FROM temperatura";   
		     obj = cmdSQL(sql);
			
		}catch(Exception e){
		}
	
		return obj;
	}

	private ArrayList<Temperatura> cmdSQL( String sql) {
		ArrayList<Temperatura> obj =   new ArrayList<Temperatura>();
		try (
		     Statement stmt  = conn.createStatement();
		     ResultSet rs    = stmt.executeQuery(sql)){
		    
		    // loop through the result set
		    while (rs.next()) {
		    	
		        Temperatura temp = new  Temperatura();
		                temp.setId(rs.getInt("id"));
		                temp.setLocal(rs.getString("local"));
		                LocalDate data = LocalDate.parse(rs.getString("data"));
		                temp.setData(LocalDateTime.now().toString());
		                temp.setTemperatura(rs.getDouble("temperatura"));
		        obj.add(temp);
		    }
		} catch (SQLException e) {
		    System.out.println(e.getMessage());
		}
			return obj;
		

	}

	public List<Temperatura> getItens(String nome, String coluna){
		
		List<Temperatura> obj    = null;
		
		try{
			 String sql = "SELECT id, local,data,temperatura FROM temperatura where "+coluna+" LIKE '"+ nome +"' order by data desc";   
		     obj = cmdSQL(sql);	
			
		}
		catch(Exception e) {
			
		}
		
		return obj;
	}
	
	public Temperatura getItemUltimo(String nome, String coluna){
		
		Temperatura obj    = null;
		
		try{
			
			
		}
		finally{
				}
		
		return obj;
	}
	public List<Temperatura> getItens(String nome, String coluna,Date startDate, Date endDate ){
		List<Temperatura> obj = null;
		try{
			 String sql = "SELECT id, local,data,temperatura FROM temperatura where "+coluna+" LIKE '"+ nome +"' and date(data) > '"+startDate.toString()+"'  and  date(data) < '"+ endDate.toString()+"'";   
			 obj = cmdSQL(sql);	
		}
		finally{
		}
		
		return obj;
	}
	
	public Temperatura getItens(int id){
		
		
		List<Temperatura> obj    = null;
		
		try{
			 String sql = "SELECT id, local,data,temperatura FROM temperatura where id = " + id;   
		     obj = cmdSQL(sql);	
			
		}
		catch(Exception e) {
			
		}
		
		return obj.get(0);
	}
}
