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
public class AgregacionVentas {
    @Id
    private java.util.Date dia;
    private Float total;

    public java.util.Date getDia() {
        return dia;
    }

    public void setDia(java.util.Date dia) {
        this.dia = dia;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "AgregacionVentas{" + "dia=" + dia + ", total=" + total + '}';
    }
}
