/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import com.teamj.distribuidas.mongodbproject.dao.ProductoDAO;
import com.teamj.distribuidas.mongodbproject.model.Producto;
import com.teamj.distribuidas.mongodbproject.persistence.PersistenceManager;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gaming
 */
@ManagedBean
@ViewScoped
public class ProductoBean implements Serializable {

    ProductoDAO productoDAO;
    Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoBean() {
        productoDAO = new ProductoDAO(Producto.class, PersistenceManager.instance().datastore());
        producto = new Producto();

    }

    public void agregarProducto() {

        try {
            Producto temp = this.productoDAO.findOne("codigo", this.producto.getCodigo());
            if (temp == null) {
                this.productoDAO.save(producto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El  nuevo producto ha sido registrado"));
                this.producto = new Producto();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Un producto ya ha sido registrado con esa identificacion"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El  nuevo producto no se pudo registrar"+e.getMessage()));
            System.out.println("" + e);
        }

    }
}
