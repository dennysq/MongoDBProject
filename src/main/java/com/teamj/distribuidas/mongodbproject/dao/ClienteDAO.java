/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.mongodbproject.dao;

import com.teamj.distribuidas.mongodbproject.model.Cliente;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Dennys
 */
public class ClienteDAO extends BasicDAO<Cliente, ObjectId> {

    public ClienteDAO(Class<Cliente> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
    

}
