package py.pol.una.web.tarea3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
		Compra CompraNuevo;
		CompraDetalle nuevoDetalle;
		Proveedor p;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				System.out.println("---- LINEA NUMERO " + Integer.valueOf(numeroDeLinea + 1) + "----");
				System.out.println(line);
				numeroDeLinea++;
				String[] element = line.split(splitBy);
				for (String s : element) {
					System.out.println(s);
				}
				CompraNuevo = new Compra();

				try {
					formato = true;
					if (element[0].compareTo("") != 0 && element[1].compareTo("") != 0
							&& element[2].compareTo("") != 0) {
						try {
							System.out.println(1);
							p = (Proveedor) em.find(Proveedor.class, element[0]);
						} catch (Exception e) {
							System.out.println("No se encontró el proveedor");
							formato = false;
							p = null;
						}
						System.out.println(2);
						CompraNuevo.setProveedor(p);
						System.out.println(3);
						CompraNuevo.setMontoTotal(Integer.valueOf(element[1]));
						System.out.println(4);
						CompraNuevo.setFecha(sdf.parse(element[2]));
						System.out.println(5);
						Integer pos = 3;
						System.out.println(6);
						ArrayList<CompraDetalle> listaDetalle = new ArrayList<CompraDetalle>();
						
						if ((element.length - 3) % 3 == 0) {
							while (pos < element.length) {
								System.out.print(" ## Detalles ## ");
								System.out.println(Integer.valueOf(pos) + " " + Integer.valueOf(pos + 1) + " "
										+ Integer.valueOf(pos + 2));
								System.out.println(element[pos]);
								System.out.println(element[pos + 1]);
								System.out.println(element[pos + 2]);
								nuevoDetalle = new CompraDetalle();
								Producto pro;
								try {
									pro = (Producto) em.find(Producto.class, Integer.valueOf(element[pos]));
									nuevoDetalle.setProducto(pro);
									nuevoDetalle.setCantidad(Integer.valueOf(element[pos+1]));
									nuevoDetalle.setPrecio(Integer.valueOf(element[pos+2]));
									nuevoDetalle.setCompra(CompraNuevo);
									pro.setStock(pro.getStock() + nuevoDetalle.getCantidad());
									listaDetalle.add(nuevoDetalle);
								} catch (Exception e) {
									System.out.println("No se encontro del producto");
									pro = null;
									formato = false;
								}
								pos+=3;
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
					System.out.println("Ex" +  e.getMessage());
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
