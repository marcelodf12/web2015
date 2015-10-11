package py.pol.una.web.tarea3.dto;

import java.util.List;

import javax.persistence.OneToMany;

import py.pol.una.web.tarea3.modelos.Producto;

public class ProductoDTO {

	private Integer id;
	private Boolean activo;
	private String nombre;
	private Integer precio;
	private Integer stock;
	
	public ProductoDTO(){
		
	}
	
	public ProductoDTO(Producto p){
		this.id= p.getId();
		this.activo= p.getActivo();
		this.nombre= p.getNombre();
		this.precio= p.getPrecio();
		this.stock= p.getStock();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
