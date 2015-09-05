/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.util;


import java.util.List;

import com.ha.model.Venta;

public class ListaRespuesta {
    
    private List<Venta> ventas;
    private Paginador meta;
    
    public ListaRespuesta(){
        
    }
    
    public ListaRespuesta(List<Venta> ventas, Integer total, Integer pageSize, Integer totalPages){
        this.ventas= ventas;
        this.meta= new Paginador(total, pageSize, totalPages);
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Paginador getMeta() {
        return meta;
    }

    public void setMeta(Paginador meta) {
        this.meta = meta;
    }
    
    
}
