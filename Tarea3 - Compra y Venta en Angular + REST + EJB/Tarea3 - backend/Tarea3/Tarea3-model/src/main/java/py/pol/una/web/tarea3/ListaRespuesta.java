package py.pol.una.web.tarea3;

import java.util.List;

public class ListaRespuesta {
    
    private List<Venta> ventas;
    private Paginador meta;
    
    public ListaRespuesta(){
        
    }
    
    public ListaRespuesta(List<Venta> ventas, Integer total, Integer pageSize, Integer totalPages){
        this.ventas= ventas;
        this.meta= new Paginador(total, pageSize, totalPages);
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Paginador getMeta() {
        return meta;
    }

    public void setMeta(Paginador meta) {
        this.meta = meta;
    }
    
    
}
