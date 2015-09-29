package py.pol.una.web.tarea3;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Session Bean implementation class VentaEjb
 */
@Stateless
@LocalBean
public class CompraEjb {

    /**
     * Default constructor. 
     */
    public CompraEjb() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName= "Tarea2Ejb")
    private EntityManager em;
	
	@Resource
    private SessionContext context;
	

}
