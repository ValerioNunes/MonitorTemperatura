package servico;



import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.TemperaturaDAO;
import model.Periodo;
import model.Temperatura;


@Path("/ServicoMonitorTemp")
public class ServicoMonitorTemp{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServicoMonitorTemp()  {
		super();
		
		// TODO Auto-generated constructor stub
	}
	@GET
	@Path("getUltimaTemperaturasLocal/{local}")
	@Produces("application/json")
	public Temperatura getUltimaTemperaturasLocal( @PathParam("local") String local)  {
		// TODO Auto-generated method stub

		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		return tempDAO.getItemUltimo(local,"local");
	}
	@GET
	@Path("getTest")
	@Produces("application/json")
	public Temperatura getTest()  {
		// TODO Auto-generated method stub

		Temperatura temp =  new Temperatura();
		 temp.setLocal("minha casa");
		 temp.setData(LocalDateTime.now().toString());
		 temp.setTemperatura(12);
		 

		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		temp =  tempDAO.Salvar(temp);
		tempDAO.close();
		return temp;
	}
	
	
	@GET
	@Path("getTodasTemperaturasLocal/{local}")
	@Produces("application/json")
	public List<Temperatura> getTodasTemperaturasLocal(@PathParam("local") String local) {
		// TODO Auto-generated method stub
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		List<Temperatura> obj =  tempDAO.getItens(local, "local");
		 
		tempDAO.close();
		
		return obj;
	}
	
	@POST
	@Path("getTodasTemperaturasLocalPorData")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> getTodasTemperaturasLocalPorData(Periodo periodo) {
		// TODO Auto-generated method stub
		System.out.println(" == == = == = = = ++ " + periodo);
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		List<Temperatura>  obj= tempDAO.getItens(periodo.getLocal(),"local",periodo.getInicio(),periodo.getFim());
		tempDAO.close();
		
		return obj;
	}
	@POST
	@Path("salvarTemperaturas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Temperatura salvarTemperaturas(Temperatura temperatura)  {
		
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		temperatura.setId(null);
		temperatura.setData(LocalDateTime.now().toString());
		Temperatura obj =  tempDAO.Salvar(temperatura);
		tempDAO.close();
		
		return obj;
	}
	@GET
	@Path("getLocaisCadastrado")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getLocaisCadastrador() {
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		Object obj = tempDAO.getLocaiCadastrados();
		tempDAO.close();
		
		return obj;
	}
  @DELETE
  @Path("deleteTemp/{id}")
  public boolean deleteTemp(@PathParam("id") int id) {
	  TemperaturaDAO tempDAO =  new TemperaturaDAO();
	  
	  boolean r = tempDAO.Delete(id);
	  
	  tempDAO.close();
	  
	  return r;
	  
  }
  
  @PUT
  @Path("updateTemp/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void update(@PathParam("id") int id, Temperatura temperatura) {
   
	  TemperaturaDAO tempDAO =  new TemperaturaDAO();
	  
	  tempDAO.Update(temperatura);
	  
	  tempDAO.close();
  }



}
