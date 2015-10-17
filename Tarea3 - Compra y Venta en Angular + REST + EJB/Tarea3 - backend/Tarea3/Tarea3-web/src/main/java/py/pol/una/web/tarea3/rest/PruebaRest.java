package py.pol.una.web.tarea3.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.web.tarea3.PruebaEjb;

@Path("prueba")
@RequestScoped
public class PruebaRest {

	public PruebaRest(){
		
		
	}
	
	@EJB
	private PruebaEjb pruebaEjb;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
		List<String> lista= pruebaEjb.traerNombres();
		return Response.ok(lista).build();   
    }
	
	@GET
	@Path("/elmejor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElMejor() {
		return Response.ok(" el mejor ").build();   
    }
	
	
	
}
