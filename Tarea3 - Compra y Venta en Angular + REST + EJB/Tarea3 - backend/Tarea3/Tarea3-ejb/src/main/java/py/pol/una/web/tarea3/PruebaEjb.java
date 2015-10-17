package py.pol.una.web.tarea3;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class PruebaEjb
 */
@Stateless
@LocalBean
public class PruebaEjb implements PruebaEjbLocal {

    /**
     * Default constructor. 
     */
    public PruebaEjb() {
        // TODO Auto-generated constructor stub
    }
    
    public List<String> traerNombres(){
    	List<String> lista= new ArrayList<String>();
    	lista.add("Miguel");
    	lista.add("WEB");
    	return lista;
    }

}
