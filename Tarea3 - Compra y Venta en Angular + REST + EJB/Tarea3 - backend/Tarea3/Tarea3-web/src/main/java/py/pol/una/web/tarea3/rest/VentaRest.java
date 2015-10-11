package py.pol.una.web.tarea3.rest;

import java.util.List;





import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.pol.una.web.tarea3.VentaEjb;
import py.pol.una.web.tarea3.dto.VentaDTO;
import py.pol.una.web.tarea3.modelos.Venta;
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
    public Response listar( @QueryParam("by_all_attributes") String byAllAttributes,
                            @QueryParam("by_numero") Integer byNumero,
                            @QueryParam("by_monto_total") Integer byMontoTotal,
                            @QueryParam("by_nombre_cliente") String byNombreCliente,
                            @QueryParam("by_ruc_cliente") String byRucCliente,
                            @QueryParam("by_fecha") String byFecha,
                            @QueryParam("numero") String numero,
                            @QueryParam("monto_total") String montoTotal,
                            @QueryParam("nombre_cliente") String nombreCliente,
                            @QueryParam("ruc_cliente") String rucCliente,
                            @QueryParam("fecha") String fecha,
                            @QueryParam("page") @DefaultValue("1") Integer page,
                            @QueryParam("cantidad") @DefaultValue("3") Integer cantidad) throws Exception {
        List<VentaDTO> lista = null;
        Integer total= null;
        Integer totalPages= null;
        ListaRespuesta respuesta= null;
        
        String campoBusqueda, busqueda, orderDir, orderBy;
        Integer inicio= (page-1)*cantidad;
        if (byAllAttributes != null){
            campoBusqueda= "by_all_attributes";
            busqueda= byAllAttributes;
        }else{
            if (byNumero != null){
                campoBusqueda= "NUMERO";
                busqueda= byNumero.toString();
            }else if(byMontoTotal != null){
                campoBusqueda= "MONTO_TOTAL";
                busqueda= byMontoTotal.toString();
            }else if(byNombreCliente != null){
                campoBusqueda= "NOMBRE_CLIENTE";
                busqueda= byNombreCliente;
            }else if(byRucCliente != null){
                campoBusqueda= "RUC_CLIENTE";
                busqueda= byRucCliente;
            }else if(byFecha != null){
                campoBusqueda= "FECHA";
                busqueda= byFecha.toString();
            }else{
            	campoBusqueda= null;
            	busqueda= null;
            }
        }
        if (numero != null){
                orderBy= "NUMERO";
                orderDir= numero;
            }else if(montoTotal != null){
                orderBy= "MONTO_TOTAL";
                orderDir= montoTotal;
            }else if(nombreCliente != null){
                orderBy= "NOMBRE_CLIENTE";
                orderDir= nombreCliente;
            }else if(rucCliente != null){
                orderBy= "RUC_CLIENTE";
                orderDir= rucCliente;
            }else if (fecha != null){
                orderBy= "FECHA";
                orderDir= fecha;
            }else{
            	orderBy= null;
            	orderDir= null;
            }
        
        lista= ventaEjb.listar(inicio, cantidad, orderBy, orderDir, campoBusqueda, busqueda);
        total= ventaEjb.total(campoBusqueda, busqueda);
        if (total%cantidad > 0){
            totalPages = total/cantidad + 1;
        }else{
            totalPages = total/cantidad;
        }
        
        respuesta= new ListaRespuesta(lista, total, totalPages, page);
        return Response.ok(respuesta).header("Access-Control-Allow-Origin", "*").build();
    }
	
}
