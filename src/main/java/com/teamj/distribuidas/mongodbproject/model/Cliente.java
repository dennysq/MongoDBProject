/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.model;

import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author Freddy
 */
@Entity(noClassnameStored = true)
public class Cliente {

    private String ruc;
    private String cedula;
    private String nombre;
    private String apellido;

}
