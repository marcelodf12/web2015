package py.pol.una.web.tarea3.dto;

import java.util.List;

import py.pol.una.web.tarea3.modelos.Proveedor;

public class ProveedorDTO {
	
	private String ruc;
	private Boolean activo;
	private String direccion;
	private String nombre;
	
	public ProveedorDTO(){
		
	}
	
	public ProveedorDTO(Proveedor p){
		this.ruc= p.getRuc();
		this.activo= p.getActivo();
		this.direccion= p.getDireccion();
		this.nombre= p.getNombre();
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
