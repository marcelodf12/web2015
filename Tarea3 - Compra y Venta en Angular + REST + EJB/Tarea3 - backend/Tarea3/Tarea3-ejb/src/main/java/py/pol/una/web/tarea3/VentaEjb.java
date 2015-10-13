package py.pol.una.web.tarea3;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.pol.una.web.tarea3.dto.VentaDTO;
import py.pol.una.web.tarea3.modelos.Cliente;
import py.pol.una.web.tarea3.modelos.Venta;

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
	
	public VentaDTO get(Integer id) throws Exception{
		try{
			Venta existe= em.find(Venta.class, id);
			if (existe!= null) return new VentaDTO(existe);
			return null;
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void insert(Venta venta) throws Exception{
		try{
			Cliente c= em.find(Cliente.class, venta.getCliente().getRuc());
			if (c== null) throw new Exception("No existe el cliente al que se realiza la Venta"); 
			em.persist(venta);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void update(Venta venta) throws Exception{
		try{
			Venta existe= em.find(Venta.class, venta.getNumero());
			if (existe== null) throw new Exception("No existe venta a modificar");
			em.merge(venta);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void delete(Integer id) throws Exception{
		try{
			Venta existe= em.find(Venta.class, id);
			if (existe== null) throw new Exception("No existe venta a eliminar");
			em.remove(existe);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public List<VentaDTO> listar(Integer inicio ,Integer cantidad, String orderBy, String orderDir, Venta buscar) throws Exception{
        try{
        	boolean existeWhere= false;
        	String consulta = "SELECT * FROM VENTAS ";
            if (buscar != null){
                consulta += "WHERE ";  
                if (buscar.getNumero()!= null){
                	consulta += "CAST (NUMERO AS TEXT) LIKE '" + buscar.getNumero().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getNombreCliente()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "NOMBRE_CLIENTE LIKE '" + buscar.getNombreCliente() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getMontoTotal()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (MONTO_TOTAL AS TEXT) LIKE '" + buscar.getMontoTotal().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getFecha()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (FECHA AS TEXT) LIKE '" + buscar.getFecha().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getCliente()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "RUC_CLIENTE LIKE '" + buscar.getCliente().getRuc() + "%' ";
                }
            }
            if (orderBy != null){
                consulta += "ORDER BY " + orderBy + " " + orderDir + " ";
            }
            Query q= em.createNativeQuery(consulta, Venta.class);
            q.setFirstResult(inicio);
            q.setMaxResults(cantidad);
            List<Venta> lista= q.getResultList();
            List<VentaDTO> ret= new ArrayList<VentaDTO>();
            for (Venta p: lista){
            	ret.add(new VentaDTO(p));
            }
            return ret;
        }catch(Exception e){
        	context.setRollbackOnly();
        	throw e;
        }
		
    }
    
    public Integer total(Venta buscar) throws Exception{
        try{
        	boolean existeWhere= false;
        	String consulta = "SELECT * FROM VENTAS ";
            if (buscar != null){
                consulta += "WHERE ";  
                if (buscar.getNumero()!= null){
                	consulta += "CAST (NUMERO AS TEXT) LIKE '" + buscar.getNumero().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getNombreCliente()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "NOMBRE_CLIENTE LIKE '" + buscar.getNombreCliente() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getMontoTotal()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (MONTO_TOTAL AS TEXT) LIKE '" + buscar.getMontoTotal().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getFecha()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (FECHA AS TEXT) LIKE '" + buscar.getFecha().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getCliente()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "RUC_CLIENTE LIKE '" + buscar.getCliente().getRuc() + "%' ";
                	existeWhere= true;
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
    
    public void exportacion(Venta venta, String orderBy, String orderDir, String metodo) throws Exception{
    	try{
    		String nombreArchivo= "C:/ventas." + metodo;
        	File fichero= new File(nombreArchivo);
        	if (fichero.exists()) fichero.delete();
        	Integer inicio= 0;
        	Integer cantidad= 3;//Cuantos elementos va a cargar en memoria
        	FileWriter fw = new FileWriter(fichero);
        	if (metodo.compareTo("cvs")==0){
        		fw.write("NUMERO, NOMBRE_CLIENTE, MONTO_TOTAL, FECHA, RUC_CLIENTE");
        	}else{
        		fw.write("[");
        	}
        	List<VentaDTO> lista= listar(inicio, cantidad, orderBy, orderDir, venta);
        	boolean yaExisteJson= false; //Control para las comas en JSON
        	while (lista.size() != 0){
        		for (VentaDTO p: lista){
        			if (p != null){
        				if (metodo.compareTo("cvs")==0){
                			fw.write("\n" + p.getNumero() + ", ");
                			fw.write("\"" + p.getNombreCliente() + "\", ");
                			fw.write(p.getMontoTotal() + ", ");
                			fw.write(p.getFecha() + ", " );
                			fw.write(p.getCliente().getRuc());
                		}else{
                			if (yaExisteJson) fw.write(",");
                			fw.write("{\"numero\":" + p.getNumero() + ",");
                			fw.write("\"nombre_cliente\":" + "\"" + p.getNombreCliente() + "\",");
                			fw.write("\"monto_total\":" +  p.getMontoTotal() + ",");
                			fw.write("\"fecha\":" + "\"" + p.getFecha() + "\"" +  ",");
                				fw.write("\"cliente\":{\"ruc\":" + "\"" + p.getCliente().getRuc() + "\"" + ",");
                				fw.write("\"nombre\":" + "\"" + p.getCliente().getNombre() + "\"" + "," );
                				fw.write("\"direccion\":" + "\"" + p.getCliente().getDireccion() + "\"" + "," );
                				fw.write("\"activo\":" + p.getCliente().getActivo() + "}" );
                			fw.write("}");
                			yaExisteJson= true;
                		}
        			}
        		}
        		inicio+=cantidad;
        		lista= listar(inicio, cantidad, orderBy, orderDir, venta);
        	}  	
        	if (metodo.compareTo("json")==0) fw.write("]");
        	fw.flush();
        	fw.close();
    	}catch(Exception e){
    		context.setRollbackOnly();
    		throw e;
    	}
    }

}
