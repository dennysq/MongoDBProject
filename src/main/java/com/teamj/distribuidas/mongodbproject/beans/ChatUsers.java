/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dennys
 */
@ManagedBean
@ApplicationScoped
public class ChatUsers implements Serializable {

    private List<String> usuarios;

    public ChatUsers() {
        usuarios = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        usuarios = new ArrayList<>();
    }

    public List<String> getUsuarios() {
        return usuarios;
    }

    public void remove(String user) {
        this.usuarios.remove(user);
    }

    public void add(String user) {
        this.usuarios.add(user);
    }

    public boolean contains(String user) {
        return this.usuarios.contains(user);
    }
}
