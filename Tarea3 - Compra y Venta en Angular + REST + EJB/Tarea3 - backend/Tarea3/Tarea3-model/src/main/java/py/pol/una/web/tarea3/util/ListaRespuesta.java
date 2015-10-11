package py.pol.una.web.tarea3.util;

import java.util.List;


public class ListaRespuesta<T> {
    
    private List<T> data;
    private Paginador meta;
    
    public ListaRespuesta(){
        
    }
    
    public ListaRespuesta(List<T> data, Integer total,
			Integer totalPages, Integer page){
    	this.data= data;
		this.meta= new Paginador(total, totalPages, page);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> ventas) {
        this.data = ventas;
    }

    public Paginador getMeta() {
        return meta;
    }

    public void setMeta(Paginador meta) {
        this.meta = meta;
    }
    
}
