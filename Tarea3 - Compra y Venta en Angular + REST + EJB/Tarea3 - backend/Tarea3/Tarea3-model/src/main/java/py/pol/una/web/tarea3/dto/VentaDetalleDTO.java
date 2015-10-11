package py.pol.una.web.tarea3.dto;
import py.pol.una.web.tarea3.modelos.VentaDetalle;

public class VentaDetalleDTO {
	
	private Integer id;
	private Integer cantidad;
	private Integer idVenta;
	private Integer precio;
	private ProductoDTO producto;
	private VentaDTO venta;
	
	public VentaDetalleDTO(){
		
	}
	
	public VentaDetalleDTO(VentaDetalle v){
		this.id= v.getId();
		this.cantidad= v.getCantidad();
		this.id= v.getIdVenta();
		this.precio= v.getPrecio();
		this.producto= new ProductoDTO(v.getProducto());
		if (v.getVenta()!=null) this.venta= new VentaDTO(v.getVenta());
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

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public VentaDTO getVenta() {
		return venta;
	}

	public void setVenta(VentaDTO venta) {
		this.venta = venta;
	}
}
