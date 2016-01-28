/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import com.teamj.distribuidas.mongodbproject.dao.ProductoDAO;
import com.teamj.distribuidas.mongodbproject.model.Factura;
import com.teamj.distribuidas.mongodbproject.model.Producto;
import com.teamj.distribuidas.mongodbproject.persistence.PersistenceManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;

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
    private List<Producto> productos;
    private final ProductoDAO productoDAO;
    private List<AgregacionProducto> productosSumarizados;
    private List<AgregacionVentas> ventasSumarizados;
    private List<AgregacionClientes> clientesSumarizados;
    private Producto productoSeleccionado;

    public ReporteBean() {
        this.productoDAO = new ProductoDAO(Producto.class, PersistenceManager.instance().datastore());
    }

    @PostConstruct
    public void init() {
        Iterator<AgregacionClientes> aggregateClientes = PersistenceManager.instance().datastore().createAggregation(Factura.class).unwind("detalles").group("nombre", Group.grouping("total", new Accumulator("$sum", "detalles.subtotal"))).aggregate(AgregacionClientes.class);
        this.clientesSumarizados = new ArrayList<>();
        this.productosSumarizados = new ArrayList<>();
        this.ventasSumarizados = new ArrayList<>();
        while (aggregateClientes.hasNext()) {
            this.clientesSumarizados.add(aggregateClientes.next());

        }
        Iterator<AgregacionProducto> aggregateProductos = PersistenceManager.instance().datastore().createAggregation(Factura.class).unwind("detalles").group("detalles.nombre", Group.grouping("total", new Accumulator("$sum", "detalles.subtotal"))).aggregate(AgregacionProducto.class);
        while (aggregateProductos.hasNext()) {
            this.productosSumarizados.add(aggregateProductos.next());

        }
        Iterator<AgregacionVentas> aggregateVentas = PersistenceManager.instance().datastore().createAggregation(Factura.class).unwind("detalles").group("fechaEmision", Group.grouping("total", new Accumulator("$sum", "detalles.subtotal"))).aggregate(AgregacionVentas.class);
        while (aggregateVentas.hasNext()) {
            this.ventasSumarizados.add(aggregateVentas.next());

        }
    }

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<AgregacionProducto> getProductosSumarizados() {
        return productosSumarizados;
    }

    public void setProductosSumarizados(List<AgregacionProducto> productosSumarizados) {
        this.productosSumarizados = productosSumarizados;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<AgregacionVentas> getVentasSumarizados() {
        return ventasSumarizados;
    }

    public void setVentasSumarizados(List<AgregacionVentas> ventasSumarizados) {
        this.ventasSumarizados = ventasSumarizados;
    }

    public List<AgregacionClientes> getClientesSumarizados() {
        return clientesSumarizados;
    }

    public void setClientesSumarizados(List<AgregacionClientes> clientesSumarizados) {
        this.clientesSumarizados = clientesSumarizados;
    }

}
