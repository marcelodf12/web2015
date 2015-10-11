package py.pol.una.web.tarea3.dto;

import py.pol.una.web.tarea3.modelos.CompraDetalle;


public class CompraDetalleDTO {

	private Integer id;
	private Integer cantidad;
	private Integer precio;
	private CompraDTO compra;
	private ProductoDTO producto;
	
	public CompraDetalleDTO(){
		
	}
	
	public CompraDetalleDTO(CompraDetalle c){
		this.id= c.getId();
		this.cantidad= c.getCantidad();
		this.precio= c.getPrecio();
		if (c.getCompra()!=null) this.compra= new CompraDTO(c.getCompra());
		if (c.getProducto()!=null) this.producto= new ProductoDTO(c.getProducto());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public CompraDTO getCompra() {
		return compra;
	}

	public void setCompra(CompraDTO compra) {
		this.compra = compra;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
	
	
}
