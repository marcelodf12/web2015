package py.pol.una.web.tarea3.rest;

import java.util.List;







import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import py.pol.una.web.tarea3.VentaEjb;
import py.pol.una.web.tarea3.dto.VentaDTO;
import py.pol.una.web.tarea3.modelos.Venta;
import py.pol.una.web.tarea3.modelos.VentaDetalle;
import py.pol.una.web.tarea3.util.ListaRespuesta;

@Path("ventas")
@RequestScoped
public class VentaRest {
	
	@EJB
	private VentaEjb ventaEjb;
	
	public VentaRest(){
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response get(@PathParam("id") Integer id) throws Exception{
		VentaDTO resultado = ventaEjb.get(id);
		return Response.ok(resultado).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Venta venta, List<VentaDetalle> detalles)throws Exception{
		ventaEjb.insert(venta, detalles);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Venta venta)throws Exception{
		ventaEjb.update(venta);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) throws Exception{
		ventaEjb.delete(id);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar(
			@QueryParam("data") String data,
			@QueryParam("page") @DefaultValue("1") Integer page,
			@QueryParam("cantidad") @DefaultValue("3") Integer cantidad,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("orderDir") String orderDir
			) throws Exception{
		Venta buscar= null;
		List<VentaDTO> lista = null;
        Integer total= null;
        Integer totalPages= null;
        ListaRespuesta respuesta= null;
        Integer inicio= (page-1)*cantidad;
        if (data!= null) {
			try {
				Gson gson = new Gson();
				buscar = gson.fromJson(data, Venta.class);
			} catch (Exception e) {
				throw new Exception(
						"Argumento Venta mal formado.");
			}
		}
        lista= ventaEjb.listar(inicio, cantidad, orderBy, orderDir, buscar);
        total= ventaEjb.total(buscar);
        if (total%cantidad > 0){
            totalPages = total/cantidad + 1;
        }else{
            totalPages = total/cantidad;
        }
        respuesta= new ListaRespuesta(lista, total, totalPages, page);
        return Response.ok(respuesta).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/exportar")
	public Response exportar(
			Venta venta,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("orderDir") String orderDir,
			@QueryParam("metodo") @DefaultValue("json") String metodo
			) throws Exception{
		ventaEjb.exportacion(venta, orderBy, orderDir, metodo);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
}
