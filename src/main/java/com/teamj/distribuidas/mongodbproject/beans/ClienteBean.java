/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.beans;

import com.teamj.distribuidas.mongodbproject.dao.ClienteDAO;
import com.teamj.distribuidas.mongodbproject.model.Cliente;
import com.teamj.distribuidas.mongodbproject.persistence.PersistenceManager;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Freddy
 */
@ManagedBean
@ViewScoped
public class ClienteBean {

    ClienteDAO clienteDAO;
    Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ClienteBean() {
        clienteDAO = new ClienteDAO(Cliente.class, PersistenceManager.instance().datastore());
        cliente = new Cliente();

    }

    public void agregarCliente() {

        try {
            Cliente temp = this.clienteDAO.findOne("identificacion", this.cliente.getIdentificacion());
            if (temp == null) {
                this.clienteDAO.save(cliente);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El  nuevo cliente ha sido registrado"));
                this.cliente = new Cliente();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Un cliente ya ha sido registrado con esa identificacion"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El  nuevo cliente no se pudo registrar"));
            System.out.println("" + e);
        }

    }

}
