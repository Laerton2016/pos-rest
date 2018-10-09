/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.entidades;

import br.edu.ifpb.clientrest.ClientRest;
import br.edu.ifpb.persistencia.DAOLivro;
import br.edu.ifpb.persistencia.FactoryEntiteManeger;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.eclipse.persistence.jpa.config.Entity;



/**
 *
 * @author laerton
 */
public class programa {
    public static void main(String[] args) throws IOException {
        Livro l = new Livro("1", "Teste", "t");
        Livro l2 = ClientRest.vinculaAutor(1, 1);
        System.out.println(l2.getAutores().get(0).getNome());
 
        
        
    }
}
