/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author laerton
 */
@Entity
@XmlRootElement
public class Livro implements Serializable {
   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String edicao;
   private String descricao;
   private String titulo;
   @OneToMany(cascade = CascadeType.PERSIST)
   private List<Autor> autores = new LinkedList<>();
   @OneToMany(cascade = CascadeType.ALL)   
   private List<Reserva> reservas = new LinkedList<>();

    public Livro() 
    {
        
    }

    public Livro(String edicao, String descricao, String titulo) {
        this.edicao = edicao;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public void addAutor(Autor autor){
        autores.add(autor);
    }
    
    public void remAutor(Autor autor){
        autores.remove(autor);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
   
   
   
}
