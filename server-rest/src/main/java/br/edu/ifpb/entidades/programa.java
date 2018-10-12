/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.entidades;

import br.edu.ifpb.clientrest.ClientRest;

import java.io.IOException;
import java.util.List;



/**
 *
 * @author laerton
 */
public class programa {
    public static void main(String[] args) throws IOException 
    {
        List<Livro> livros = ClientRest.allLivros();
        Livro l = livros.get(0);
        l.setTitulo("Mudei o titulo");
        ClientRest.aualizarLivro(l);
    }
}
