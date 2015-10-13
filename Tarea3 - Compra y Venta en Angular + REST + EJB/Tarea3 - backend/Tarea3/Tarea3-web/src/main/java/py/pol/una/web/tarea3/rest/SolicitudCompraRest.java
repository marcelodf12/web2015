
package py.pol.una.web.tarea3.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.web.tarea3.SolicitudCompraEjb;
import py.pol.una.web.tarea3.modelos.SolicitudCompra;

@Path("solicituddecompras")
@RequestScoped
public class SolicitudCompraRest {

	@EJB
	private SolicitudCompraEjb solicitudCompraEjb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() throws Exception{
		List<SolicitudCompra> resultado= solicitudCompraEjb.listar();
		return Response.ok(resultado).header("Access-Control-Allow-Origin", "*").build();
	}
	
}
