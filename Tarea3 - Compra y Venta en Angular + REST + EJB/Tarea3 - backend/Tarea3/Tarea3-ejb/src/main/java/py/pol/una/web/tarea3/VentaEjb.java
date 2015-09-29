package py.pol.una.web.tarea3;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class VentaEjb
 */
@Stateless
@LocalBean
public class VentaEjb {

    /**
     * Default constructor. 
     */
    public VentaEjb() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName= "Tarea3Ejb")
    private EntityManager em;
	
	@Resource
    private SessionContext context;
	
	public List<Venta> listar(Integer inicio ,Integer cantidad, String orderBy, String orderDir, String campoBusqueda, String busqueda) throws Exception{
        try{
        	String consulta = "SELECT NUMERO, MONTO_TOTAL, NOMBRE_CLIENTE, RUC_CLIENTE, FECHA FROM VENTAS ";
            if (campoBusqueda != null){
                consulta += "WHERE ";
                if (campoBusqueda.compareTo("by_all_attributes") == 0){
                    consulta += "CAST (NUMERO AS TEXT) LIKE '"  + busqueda + "%' OR ";
                    consulta += "CAST (MONTO_TOTAL AS TEXT) LIKE '" + busqueda + "%' OR ";
                    consulta += "NOMBRE_CLIENTE LIKE '" + busqueda + "%' OR ";
                    consulta += "RUC_CLIENTE LIKE '" + busqueda + "%' OR ";
                    consulta += "CAST (FECHA AS TEXT) LIKE '" + busqueda + "%' ";
                }else{
                    consulta += "CAST (" + campoBusqueda  + " AS TEXT) LIKE '" + busqueda + "%' ";  
                }
            }
            if (orderBy != null){
                consulta += " ORDER BY " + orderBy + " " + orderDir + " ";
            }
            Query q= em.createNativeQuery(consulta);
            q.setFirstResult(inicio);
            q.setMaxResults(cantidad);
            List<Venta> ret= (List<Venta>) q.getResultList();
            return ret;
        }catch(Exception e){
        	context.setRollbackOnly();
        	throw new Exception(e.getMessage());
        }
		
    }
    
    public Integer total(String campoBusqueda, String busqueda) throws Exception{
        try{
        	String consulta = "SELECT * FROM VENTAS ";
            if (campoBusqueda != null){
                consulta += "WHERE ";
                if (campoBusqueda.compareTo("by_all_attributes") == 0){
                    consulta += "CAST (NUMERO AS TEXT) LIKE '"  + busqueda + "%' OR ";
                    consulta += "CAST (MONTO_TOTAL AS TEXT) LIKE '" + busqueda + "%' OR ";
                    consulta += "NOMBRE_CLIENTE LIKE '" + busqueda + "%' OR ";
                    consulta += "RUC_CLIENTE LIKE '" + busqueda + "%' OR ";
                    consulta += "CAST (FECHA AS TEXT) LIKE '" + busqueda + "%' ";
                }else{
                    consulta += "CAST (" + campoBusqueda  + " AS TEXT) LIKE '" + busqueda + "%' ";  
                }
            }
            Query q= em.createNativeQuery(consulta);
            List<Venta> aux = q.getResultList();
            return aux.size();
        }catch(Exception e){
        	context.setRollbackOnly();
        	throw new Exception(e.getMessage());
        }
    }

}
