package py.pol.una.web.tarea3;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.pol.una.web.tarea3.modelos.SolicitudCompra;

@Stateless
@LocalBean
public class SolicitudCompraEjb {

	public SolicitudCompraEjb(){
		
	}
	
	@PersistenceContext(unitName= "Tarea3Ejb")
    private EntityManager em;
	
	@Resource
    private SessionContext context;
	
	public List<SolicitudCompra> listar() throws Exception{
		try{
			Query q= em.createNativeQuery("SELECT * FROM SOLICITUD_COMPRAS", SolicitudCompra.class);
			return q.getResultList();
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
}
