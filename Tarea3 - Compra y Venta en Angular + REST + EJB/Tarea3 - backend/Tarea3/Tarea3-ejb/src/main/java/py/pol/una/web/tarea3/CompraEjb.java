package py.pol.una.web.tarea3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import py.pol.una.web.tarea3.modelos.Compra;
import py.pol.una.web.tarea3.modelos.CompraDetalle;
import py.pol.una.web.tarea3.modelos.Producto;
import py.pol.una.web.tarea3.modelos.Proveedor;

/**
 * Session Bean implementation class VentaEjb
 */
@Stateless
@LocalBean
public class CompraEjb {

	/**
	 * Default constructor.
	 */
	public CompraEjb() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "Tarea3Ejb")
	private EntityManager em;

	@Resource
	private SessionContext context;

	public ArrayList<Integer> cargaMasiva(String file) throws IOException {
		Boolean fallo = false;
		ArrayList<Integer> errores = new ArrayList<Integer>();
		BufferedReader br = null;
		String line = "";
		String splitBy = ";";
		Boolean formato;
		Integer numeroDeLinea = 0;
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				numeroDeLinea++;
				String[] element = line.split(splitBy);
				Compra CompraNuevo = new Compra();

				try {
					formato = true;
					if (element[0].compareTo("") != 0 && element[1].compareTo("") != 0
							&& element[2].compareTo("") != 0) {
						Proveedor p;
						try {
							p = (Proveedor) em.find(Proveedor.class, element[0]);
						} catch (Exception e) {
							formato = false;
							p = null;
						}
						CompraNuevo.setProveedor(p);
						CompraNuevo.setMontoTotal(Integer.valueOf(element[1]));
						CompraNuevo.setFecha(Date.valueOf(element[2]));

						Integer pos = 3;
						ArrayList<CompraDetalle> listaDetalle = new ArrayList<CompraDetalle>();
						if ((element.length - 3) % 3 == 0) {
							while (pos < element.length) {
								CompraDetalle nuevoDetalle = new CompraDetalle();
								Producto pro;
								try {
									pro = (Producto) em.find(Producto.class, element[pos]);
									nuevoDetalle.setProducto(pro);
									nuevoDetalle.setCantidad(Integer.valueOf(element[++pos]));
									nuevoDetalle.setPrecio(Integer.valueOf(element[++pos]));
									nuevoDetalle.setCompra(CompraNuevo);
									pro.setStock(pro.getStock()+nuevoDetalle.getCantidad());
									listaDetalle.add(nuevoDetalle);
								} catch (Exception e) {
									pro = null;
									formato = false;
								}
							}

						} else {
							formato = false;
						}

					} else {
						System.out.println("Fallo formato");
						formato = false;
					}
				} catch (Exception e) {
					System.out.println("Salto excepcion por campos vacios");
					formato = false;
				}
				if (formato) {
					System.out.println("Intento Persistir");
					if (!this.persistir(CompraNuevo)) {
						fallo = true;
						errores.add(numeroDeLinea);
					}
				} else {
					fallo = true;
					errores.add(numeroDeLinea);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (fallo) {
			context.setRollbackOnly();
		}
		return errores;
	}

	// TODO ver cual es la anotación para que no muera la transacción
	// @Trasactional(noRollback=Exception)
	private Boolean persistir(Compra c) {
		try {
			// en ves de persist
			em.merge(c);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
