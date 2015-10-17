package py.pol.una.web.tarea3.modelos;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the compra_detalle database table.
 * 
 */
@Entity
@Table(name="compra_detalle")
@NamedQuery(name="CompraDetalle.findAll", query="SELECT c FROM CompraDetalle c")
public class CompraDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_detalle_seq_gen")
	@SequenceGenerator(name = "compra_detalle_seq_gen", sequenceName = "seq_compra_detalle")
	private Integer id;

	private Integer cantidad;

	private Integer precio;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="id_compra")
	private Compra compra;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public CompraDetalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}