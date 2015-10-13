package py.pol.una.web.tarea3;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.pol.una.web.tarea3.dto.ProductoDTO;
import py.pol.una.web.tarea3.modelos.Producto;
import py.pol.una.web.tarea3.modelos.SolicitudCompra;

@Stateless
@LocalBean
public class ProductoEjb {

	public ProductoEjb(){
		
	}
	
	@PersistenceContext(unitName= "Tarea3Ejb")
    private EntityManager em;
	
	@Resource
    private SessionContext context;
	
	public ProductoDTO get(Integer id) throws Exception{
		try{
			Producto existe= em.find(Producto.class, id);
			if (existe!= null) return new ProductoDTO(existe);
			return null;
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void insert(Producto producto) throws Exception{
		try{
			em.persist(producto);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void update(Producto producto) throws Exception{
		try{
			Producto existe= em.find(Producto.class, producto.getId());
			if (existe== null) throw new Exception("No existe producto a modificar");
			em.merge(producto);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public void delete(Integer id) throws Exception{
		try{
			Producto existe= em.find(Producto.class, id);
			if (existe== null) throw new Exception("No existe producto a eliminar");
			em.remove(existe);
		}catch(Exception e){
			context.setRollbackOnly();
			throw e;
		}
	}
	
	public List<ProductoDTO> listar(Integer inicio ,Integer cantidad, String orderBy, String orderDir, Producto buscar) throws Exception{
        try{
        	boolean existeWhere= false;
        	String consulta = "SELECT * FROM PRODUCTOS ";
            if (buscar != null){
                consulta += "WHERE ";  
                if (buscar.getId()!= null){
                	consulta += "CAST (ID AS TEXT) LIKE '" + buscar.getId().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getNombre()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "NOMBRE LIKE '" + buscar.getNombre() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getPrecio()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (PRECIO AS TEXT) LIKE '" + buscar.getPrecio().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getStock()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (STOCK AS TEXT) LIKE '" + buscar.getStock().toString() + "%' ";
                	existeWhere= true;
                }
            }
            if (orderBy != null){
                consulta += "ORDER BY " + orderBy + " " + orderDir + " ";
            }
            Query q= em.createNativeQuery(consulta, Producto.class);
            q.setFirstResult(inicio);
            q.setMaxResults(cantidad);
            List<Producto> lista= q.getResultList();
            List<ProductoDTO> ret= new ArrayList<ProductoDTO>();
            for (Producto p: lista){
            	ret.add(new ProductoDTO(p));
            }
            return ret;
        }catch(Exception e){
        	context.setRollbackOnly();
        	throw e;
        }
		
    }
    
    public Integer total(Producto buscar) throws Exception{
        try{
        	boolean existeWhere= false;
        	String consulta = "SELECT * FROM PRODUCTOS ";
            if (buscar != null){
                consulta += "WHERE ";  
                if (buscar.getId()!= null){
                	consulta += "CAST (ID AS TEXT) LIKE '" + buscar.getId().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getNombre()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "NOMBRE LIKE '" + buscar.getNombre() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getPrecio()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (PRECIO AS TEXT) LIKE '" + buscar.getPrecio().toString() + "%' ";
                	existeWhere= true;
                }
                if (buscar.getStock()!= null){
                	if (existeWhere) consulta+= "OR ";
                	consulta += "CAST (STOCK AS TEXT) LIKE '" + buscar.getStock().toString() + "%' ";
                	existeWhere= true;
                }
            }
            Query q= em.createNativeQuery(consulta);
            List<Producto> aux = q.getResultList();
            return aux.size();
        }catch(Exception e){
        	context.setRollbackOnly();
        	throw new Exception(e.getMessage());
        }
    }
    
    public void exportacion(Producto producto, String orderBy, String orderDir, String metodo) throws Exception{
    	try{
    		String nombreArchivo= "C:/PRODUCTOS." + metodo;
        	File fichero= new File(nombreArchivo);
        	if (fichero.exists()) fichero.delete();
        	Integer inicio= 0;
        	Integer cantidad= 3;//Cuantos elementos va a cargar en memoria
        	FileWriter fw = new FileWriter(fichero);
        	if (metodo.compareTo("CVS")==0){
        		fw.write("ID, NOMBRE, PRECIO, STOCK");
        	}else{
        		fw.write("[");
        	}
        	List<ProductoDTO> lista= listar(inicio, cantidad, orderBy, orderDir, producto);
        	boolean yaExisteJson= false; //Control para las comas en JSON
        	while (lista.size() != 0){
        		for (ProductoDTO p: lista){
        			if (p != null){
        				if (metodo.compareTo("CVS")==0){
                			fw.write("\n" + p.getId() + ", ");
                			fw.write("\"" + p.getNombre() + "\", ");
                			fw.write(p.getPrecio() + ", ");
                			fw.write(p.getStock() );;
                		}else{
                			if (yaExisteJson) fw.write(",");
                			fw.write("{\"id\":" + p.getId() + ",");
                			fw.write("\"nombre\":" + "\"" + p.getNombre() + "\",");
                			fw.write("\"precio\":" +  p.getPrecio() + ",");
                			fw.write("\"stock\":"  + p.getStock() + "}");
                			yaExisteJson= true;
                		}
        			}
        		}
        		inicio+=cantidad;
        		lista= listar(inicio, cantidad, orderBy, orderDir, producto);
        	}  	
        	if (metodo.compareTo("JSON")==0) fw.write("]");
        	fw.flush();
        	fw.close();
    	}catch(Exception e){
    		context.setRollbackOnly();
    		throw e;
    	}
    }
    
    public void inventario() throws Exception{
    	try{
    		Query q= em.createNativeQuery("SELECT * FROM PRODUCTOS WHERE STOCK<10", Producto.class);
            List<Producto> lista= q.getResultList();
            for (Producto p: lista){
            	SolicitudCompra s= new SolicitudCompra();
            	s.setNombre(p.getNombre());
            	s.setFecha(new Date());	
            	if (em.find(SolicitudCompra.class, s.getNombre())== null){
            		em.persist(s);
            	}else{
            		em.merge(s);
            	}
            }
    	}catch(Exception e){
    		context.setRollbackOnly();
    		throw e;
    	}
    }
	
}
