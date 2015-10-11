package py.pol.una.web.tarea3.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import py.pol.una.web.tarea3.modelos.Compra;


public class CompraDTO {
	
	private Integer id;
	private Date fecha;
	private Integer montoTotal;
	private ProveedorDTO proveedor;
	
	public CompraDTO(){
		
	}
	
	public CompraDTO(Compra c){
		this.id= c.getId();
		this.fecha= c.getFecha();
		this.montoTotal= c.getMontoTotal();
		if (c.getProveedor()!=null) this.proveedor= new ProveedorDTO(c.getProveedor());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Integer montoTotal) {
		this.montoTotal = montoTotal;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	
}
