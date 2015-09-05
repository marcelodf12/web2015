/**
 * 
 */
package com.ha.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ha.model.Venta;
import com.ha.util.RespuestaVenta;

/**
 * @author marcelo
 *
 */
@Path("/rest/ventas")
public class VentaRESTService {
	
	@Inject
	private EntityManager em;

	/**
	 * @param venta
	 * @return
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	public RespuestaVenta create(final Venta venta) {
		RespuestaVenta r;
		try{
			em.persist(venta);
			r = new RespuestaVenta(venta);
			r.setEstado("OK");
		}catch(Exception e){
			r = new RespuestaVenta(venta);
			r.setEstado("ERROR: "+ e.getMessage());
		}
		return r;
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces({ "application/xml", "application/json" })
	public List<Venta> findById(@PathParam("id") Integer id) {
		return (List<Venta>) em.createQuery("SELECT * FROM ventas WHERE :id")
				.setParameter("id", "numero="+Integer.toString(id))
				.getResultList();
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Venta> listAll(
			@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<Venta> ventas;
		ventas = (List<Venta>) em.createQuery("SELECT * FROM ventas").setMaxResults(maxResult).setFirstResult(startPosition).getResultList();
		return ventas;
	}

	/**
	 * @param id
	 * @param venta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Venta> listar(Integer inicio ,Integer cantidad, String orderBy, String orderDir, String campoBusqueda, String busqueda){
        String consulta = "SELECT NUMERO, MONTO_TOTAL, NOMBRE_CLIENTE, RUC_CLIENTE, FECHA FROM VENTAS ";
        if (campoBusqueda != null){
            consulta += "WHERE ";
            if (campoBusqueda.compareTo("by_all_atributes") == 0){
                consulta += "NUMERO LIKE " + busqueda + "% OR ";
                consulta += "MONTO_TOTAL LIKE " + busqueda + "% OR ";
                consulta += "NOMBRE_CLIENTE LIKE " + busqueda + "% OR ";
                consulta += "RUC_CLIENTE LIKE " + busqueda + "% OR ";
                consulta += "FECHA LIKE " + busqueda + "% ";
            }else{
                consulta += campoBusqueda + " LIKE " + busqueda + "% ";  
            }
            if (orderBy != null){
                consulta += orderBy + " " + orderDir + " ";
            }
        }
        Query q= em.createQuery(consulta);
        q.setFirstResult(inicio);
        q.setMaxResults(cantidad);
        return (List<Venta>) q.getResultList();
    }
    
    public Integer total(Integer inicio ,Integer cantidad, String orderBy, String orderDir, String campoBusqueda, String busqueda){
        String consulta = "SELECT NUMERO, MONTO_TOTAL, NOMBRE_CLIENTE, RUC_CLIENTE, FECHA FROM VENTAS ";
        if (campoBusqueda != null){
            consulta += "WHERE ";
            if (campoBusqueda.compareTo("by_all_atributes") == 0){
                consulta += "NUMERO LIKE " + busqueda + "% OR ";
                consulta += "MONTO_TOTAL LIKE " + busqueda + "% OR ";
                consulta += "NOMBRE_CLIENTE LIKE " + busqueda + "% OR ";
                consulta += "RUC_CLIENTE LIKE " + busqueda + "% OR ";
                consulta += "FECHA LIKE " + busqueda + "% ";
            }else{
                consulta += campoBusqueda + " LIKE " + busqueda + "% ";  
            }
            if (orderBy != null){
                consulta += orderBy + " " + orderDir + " ";
            }
        }
        Query q= em.createQuery(consulta);
        return q.getMaxResults();
    }

}
