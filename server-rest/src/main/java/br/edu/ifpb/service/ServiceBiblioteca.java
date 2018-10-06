package br.edu.ifpb.service;

import br.edu.ifpb.entidades.Autor;
import br.edu.ifpb.entidades.Livro;
import br.edu.ifpb.persistencia.DAOAutor;
import br.edu.ifpb.persistencia.DAOLivro;
import br.edu.ifpb.persistencia.DAOReserva;
import br.edu.ifpb.persistencia.IDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laerton
 */
@Stateless
public class ServiceBiblioteca {
    @Inject
    private DAOAutor daoAutor ;
    @Inject
    private DAOLivro daoLivro ;
   // @Inject
   // private DAOReserva daoReserva ;
    
    /***
     * Salva um livro no Banco de dados
     * @param livro - Livro a ser salvo
     * @return Livro salvo
     */
    public Livro Salvar(Livro livro){
        daoLivro.save(livro);
        return livro;
    }
    
    /***
     * Filtra lista de livro pelo título
     * @param titulo - Titulo a ser filtrado
     * @return Lista de livros
     */
    public List<Livro> FilterByTitulo(String titulo){
        return daoLivro.findByNome(titulo);
    }
    
    /***
     * Lista todos os livros em ordem alfabetica
     * @return - Lista de livros
     */
    public List<Livro> AllLivros(){
        return ((DAOLivro)daoLivro).livros();
    }
    
    /***
     * Atualiza os dados de um livro
     * @param livro - Livro cujos dados serão atualizados
     * @return - Livro com os dados atualizados
     */
    public Livro Update (Livro livro){
        daoLivro.update(livro);
        return livro;
    }
    
    /***
     * Remove o livro do banco de dados
     * @param livro 
     */
    public void Remove (Livro livro){
        daoLivro.remove(livro);
    }
    
    /***
     * Busca por um livro apartir do Id repassador
     * @param id - Id do livro
     * @return - Livro localizado
     */
    public Livro FindById(int id){
        return (Livro) daoLivro.findById(id);
    }
    /***
     * Busca por todos os autores cadastrados
     * @return - Lista de autores cadastrados
     */
    public List<Autor> allAutores(){
        return  ((DAOAutor)daoAutor).autores();
    }
    /***
     * Busca por autores que contenha o termo repassado no nome
     * @param nome = Termo que deve conter no nome
     * @return Lista de autores com o termo do nome repassado
     */
    public List<Autor> findByNomeAutor(String nome){
        return daoAutor.findByNome(nome);
    }
    /***
     * Busca por um autor por meio do ID 
     * @param id - Id do autor a ser buscado
     * @return Autor localizado
     */
    public Autor findByAutorByid (int id){
        return (Autor) daoAutor.findById(id);
    }

    public List<Autor> findByAutorByidLivro(int id) {
        return ((DAOAutor)daoAutor).findAutoresByIdLivro(id);
    }
}
