/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.controladores;

import br.edu.ifpb.clientrest.ClientRest;
import br.edu.ifpb.entidades.Autor;
import br.edu.ifpb.entidades.Livro;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author laerton
 */
@Named
@SessionScoped
public class ControladorBibliotec  implements Serializable
{
    private Livro livro = new Livro();
    private Autor autor = new Autor();
    
    public List<Livro> livros (){
        return ClientRest.allLivros();
    }
    public String addAutor(){
        this.livro.addAutor(this.autor);
        autor = new Autor();
        return null;
    }
    
    public String editarAutor(Autor autor){
        this.autor = autor;
        return null;
    }
    
    public String removeAutor(Autor autor){
        this.livro.getAutores().remove(autor);
        return null;
    }
    
    public String vincula(Autor autor){
        this.livro.addAutor(autor);
        return null;
    }
    
    public List<Autor> todosAutores(){
        try {
            return ClientRest.allAltores();
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }
    
    public String removeLivro(Livro livro){
        try {
            ClientRest.deleteLivro(livro.getId());
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String atualizaAutor(){
        List<Autor> autores = livro.getAutores();
        Autor a = new Autor() ;
        for (Autor autore : autores) {
            if (autore.getId() == autor.getId()){
                a = autore;
            }
        }
        autores.remove(a);
        autores.add(autor);
        livro.setAutores(autores);
        autor = new Autor();
        return null;
    }
    
    public String salvarLivro(){
        try {
            ClientRest.salvarlivro(this.livro);
            livro = new Livro();
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public String AtualizarLivro(){
        try {
            ClientRest.aualizarLivro(this.livro);
            livro = new Livro();
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public List<Autor> autores (){
        try {
            return ClientRest.allAltores();
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }
    
    public String editarLivro(Livro livro){
        this.livro = livro;
        return null;
    }
    public List<Autor> autoresByLivro(){
        try {
            return  ClientRest.findAutorByIdLivro(livro.getId());
        } catch (IOException ex) {
            Logger.getLogger(ControladorBibliotec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
