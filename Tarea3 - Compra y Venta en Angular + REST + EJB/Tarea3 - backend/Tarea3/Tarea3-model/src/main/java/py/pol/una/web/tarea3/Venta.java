package py.pol.una.web.tarea3;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="VENTA")
public class Venta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="monto_total")
	private Integer montoTotal;
	
	@Column(name="nombre_cliente")
	@Size(min = 0, max = 50)
	private String nombreCliente;
	
	@Column(name="ruc_cliente")
	@Size(min = 0, max = 30)
	private String rucCliente;
	
	@Column(name="fecha")
	private Date fecha;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Integer montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getRucCliente() {
		return rucCliente;
	}

	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
