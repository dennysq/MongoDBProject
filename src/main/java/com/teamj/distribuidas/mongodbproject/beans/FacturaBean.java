/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import com.teamj.distribuidas.mongodbproject.dao.ClienteDAO;
import com.teamj.distribuidas.mongodbproject.dao.FacturaDAO;
import com.teamj.distribuidas.mongodbproject.dao.ProductoDAO;
import com.teamj.distribuidas.mongodbproject.model.Cliente;
import com.teamj.distribuidas.mongodbproject.model.DetalleFactura;
import com.teamj.distribuidas.mongodbproject.model.Factura;
import com.teamj.distribuidas.mongodbproject.model.Producto;
import com.teamj.distribuidas.mongodbproject.persistence.PersistenceManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ViewScoped
public class FacturaBean implements Serializable {

    private Factura factura;
    private List<Producto> productos;
    private final ProductoDAO productoDAO;
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private Cliente cliente;
    private Integer cantidad;
    private Producto productoSeleccionado;
    private String idProducto;
    private List<AgregacionProducto> productosSumarizados;

    public FacturaBean() {
        this.cliente = new Cliente();
        this.factura = new Factura();
        this.productoDAO = new ProductoDAO(Producto.class, PersistenceManager.instance().datastore());
        this.clienteDAO = new ClienteDAO(Cliente.class, PersistenceManager.instance().datastore());
        this.facturaDAO = new FacturaDAO(Factura.class, PersistenceManager.instance().datastore());
        this.cantidad = 1;
        this.productoSeleccionado = new Producto();
        this.productosSumarizados = new ArrayList<>();

    }

    public void setProductosSumarizados(List<AgregacionProducto> productosSumarizados) {
        this.productosSumarizados = productosSumarizados;
    }

    public List<AgregacionProducto> getProductosSumarizados() {
        return productosSumarizados;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @PostConstruct
    public void init() {
        productos = productoDAO.find().asList();
        if (productos != null && !productos.isEmpty()) {
            this.productoSeleccionado = productos.get(0);

        }

    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void guardarFactura() {
        try {
            this.factura.setCliente(this.cliente);
            this.factura.setNombre(this.cliente.getNombre());
            this.facturaDAO.save(factura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La Factura ha sido guardada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la factura" + e.getMessage()));

        }
    }

    public void buscarCliente() {
        Cliente temp = clienteDAO.findOne("identificacion", this.cliente.getIdentificacion());
        if (temp != null) {
            this.cliente = temp;
        } else {
            this.cliente.setNombre("");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha encontrado el cliente"));
        }
    }

    public void agregarDetalle() {

        boolean exists = false;
        for (DetalleFactura df : this.factura.getDetalles()) {
            if (this.productoSeleccionado.getCodigo().equals(df.getCodigo())) {
                df.setCantidad(df.getCantidad() + this.cantidad);
                df.setSubtotal(df.getPrecioUnitario() * df.getCantidad());
                exists = true;
                break;
            }
        }
        if (!exists) {
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setCantidad(cantidad);
            detalleFactura.setCodigo(this.productoSeleccionado.getCodigo());
            detalleFactura.setNombre(this.productoSeleccionado.getNombre());
            detalleFactura.setPrecioUnitario(this.productoSeleccionado.getPrecio());
            detalleFactura.setSubtotal(this.productoSeleccionado.getPrecio() * this.cantidad);
            this.factura.getDetalles().add(detalleFactura);
        }
    }

    public void cargarProductoSeleccionado() {
        System.out.println("hola");
        try {
            this.productoSeleccionado = this.productoDAO.findOne("codigo", this.idProducto);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el producto" + e.getMessage()));

        }
    }
}
