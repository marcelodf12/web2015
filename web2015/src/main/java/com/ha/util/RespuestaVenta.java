package com.ha.util;

import java.util.ArrayList;

import com.ha.model.Venta;

public class RespuestaVenta {
	
	private ArrayList<Venta> ventas;
	
	private String estado;

	public RespuestaVenta(Venta venta){
		super();
		ventas = new ArrayList<Venta>();
		ventas.add(venta);		
	}
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void addVenta(Venta venta){
		ventas.add(venta);
	}
	
	

}
