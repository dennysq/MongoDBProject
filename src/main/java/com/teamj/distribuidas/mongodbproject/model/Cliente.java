/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.model;

import com.teamj.distribuidas.mongodbproject.persistence.BaseEntity;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author Freddy
 */
@Entity(noClassnameStored = true)
public class Cliente extends BaseEntity{

    private String identificacion;
    private String nombre;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}
