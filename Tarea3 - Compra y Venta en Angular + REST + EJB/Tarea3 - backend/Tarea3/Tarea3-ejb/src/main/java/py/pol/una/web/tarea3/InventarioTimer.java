package py.pol.una.web.tarea3;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.pol.una.web.tarea3.modelos.Producto;
import py.pol.una.web.tarea3.modelos.SolicitudCompra;

@Singleton
public class InventarioTimer {

	@Resource
	TimerService timerService;
	
	@EJB
	private ProductoEjb productoEjb;
	
	@Schedule(minute="*/3", hour="*")
    public void inventarioAutomatico() throws Exception {
        System.out.println("REALIZANDO INVENTARIO DE PRODUCTOS...");
        productoEjb.inventario();
    }
	
	
	
}
