/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.model;

import com.teamj.distribuidas.mongodbproject.persistence.BaseEntity;
import java.math.BigDecimal;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author Freddy
 */
@Entity(noClassnameStored = true)
public class Producto extends BaseEntity {

    private String codigo;
    private String nombre;
    private Float precio;
    private Integer stock;

    public Producto() {
    }

    public Producto(String codigo, String nombre, Float precio, Integer stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + '}';
    }

}
