/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author laerton
 */
public class FactoryEntiteManeger {
    
    private static EntityManager em = Persistence.createEntityManagerFactory("POS_PU").createEntityManager();
    static EntityManager FactoryEM() {
        return em;
    }
    
}
