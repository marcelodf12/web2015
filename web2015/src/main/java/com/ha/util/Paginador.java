/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.util;

public class Paginador {
    
    private Integer total;
    private Integer totalPages;
    private Integer pageSize;
    
    public Paginador(){
        
    }

    public Paginador(Integer total, Integer totalPages, Integer pageSize) {
        this.total = total;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
}
