package servico;


import java.util.Calendar;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.TemperaturaDAO;
import model.Periodo;
import model.Temperatura;


@Path("/ServicoMonitorTemp")
public class ServicoMonitorTemp {

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
		 temp.setLocal("ifma");
		 temp.setTemperatura(12);
		return temp;
	}
	
	
	@GET
	@Path("getTodasTemperaturasLocal/{local}")
	@Produces("application/json")
	public List<Temperatura> getTodasTemperaturasLocal(@PathParam("local") String local) {
		// TODO Auto-generated method stub
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		return  tempDAO.getItens(local, "local");
	}
	
	@POST
	@Path("getTodasTemperaturasLocalPorData")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> getTodasTemperaturasLocalPorData(Periodo periodo) {
		// TODO Auto-generated method stub
		System.out.println(" == == = == = = = ++ " + periodo);
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		
		return   tempDAO.getItens(periodo.getLocal(),"local",periodo.getInicio(),periodo.getFim());
	}
	@POST
	@Path("salvarTemperaturas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Temperatura salvarTemperaturas(Temperatura temperatura)  {
		
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		java.util.Date data = Calendar.getInstance().getTime();
		temperatura.setData(data);
		temperatura.setId(null);
		return tempDAO.Salvar(temperatura);
		
	}
	@GET
	@Path("getLocaisCadastrado")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getLocaisCadastrador() {
		TemperaturaDAO tempDAO =  new TemperaturaDAO();
		return tempDAO.getLocaiCadastrados();
	}
  @DELETE
  @Path("deleteTemp/{id}")
  public boolean deleteTemp(@PathParam("id") int id) {
	  TemperaturaDAO tempDAO =  new TemperaturaDAO();

	  tempDAO.Delete(tempDAO.getItens(id));
	  
	  return true;
	  
  }

}
