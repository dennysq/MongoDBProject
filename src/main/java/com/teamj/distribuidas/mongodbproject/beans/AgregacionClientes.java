/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author Gaming
 */
@Entity
public class AgregacionClientes {

    @Id
    private String nombre;
    private Float total;

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "AgregacionProducto{" + "nombre=" + nombre + ", total=" + total + '}';
    }
}
