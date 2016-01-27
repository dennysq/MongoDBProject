/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author Gaming
 */
@ViewScoped
@ManagedBean
public class ReporteBean implements Serializable {
    private boolean reporteProductos;
    private boolean reporteClientes;
    private boolean reporteVentas;

    public boolean isReporteProductos() {
        return reporteProductos;
    }

    public void setReporteProductos(boolean reporteProductos) {
        this.reporteProductos = reporteProductos;
    }

    public boolean isReporteClientes() {
        return reporteClientes;
    }

    public void setReporteClientes(boolean reporteClientes) {
        this.reporteClientes = reporteClientes;
    }

    public boolean isReporteVentas() {
        return reporteVentas;
    }

    public void setReporteVentas(boolean reporteVentas) {
        this.reporteVentas = reporteVentas;
    }
    public void mostarReporteProductos()
    {
        this.reporteProductos=true
                ;
    }
    public void mostarReporteClientes()
    {
        this.reporteClientes=true
                ;
    }
    public void mostarReporteVentas()
    {
        this.reporteVentas=true
                ;
    }
   
}
