package py.pol.una.web.tarea3;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

@Singleton
public class InventarioTimer {

	@Resource
	TimerService timerService;
	
	@Schedule(minute="*/3", hour="*")
    public void inventarioAutomatico() {
        System.out.println("CONSULTAR BASE DE DATOS DE PRODUCTOS");
    }
	
}
