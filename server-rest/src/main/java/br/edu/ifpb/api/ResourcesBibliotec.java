package br.edu.ifpb.api;

import br.edu.ifpb.entidades.Autor;
import br.edu.ifpb.entidades.Livro;
import br.edu.ifpb.service.ServiceBiblioteca;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
@Path("biblioteca")
public class ResourcesBibliotec {
    
    @Inject
    private ServiceBiblioteca service;
    /***
     * Lista todos os livros cadastrados
     * @return - Lista de livros
     */
    @GET
    @Path("todososlivros")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response allLivros(){
        List<Livro> lista = this.service.AllLivros();
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(lista) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    
    /***
     * Salva os dados de um livro.
     * @param livro - Livro cujos dados serão persistidos
     * @param uriInfo
     * @return  - Mensagem de retorno
     */
    @POST
    @Path("salvarlivro")
    @Produces("application/json")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //@Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML})
    public Response salvarLivro(Livro livro,  @Context UriInfo uriInfo){
        Livro entity = this.service.Salvar(livro);
        String id = String.valueOf(entity.getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(location)
                .entity(entity)
                .build();
    }
    /***
     * Atualiza os dados de um livro.
     * @param livro - Livro cujos dados serão atualizados
     * @param uriInfo
     * @return - Menssagem de resposta
     */
    @POST
    @Path("atualizarlivro")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML})
    public Response update(Livro livro, @Context UriInfo uriInfo ){
        Livro entity = this.service.Update(livro);
        String id = String.valueOf(entity.getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(location)
                .entity(entity)
                .build();
    }
    /***
     * Lista todos os livros que contenham em seu título o termo repassado como paramentro
     * @param termo - Termo de filtragem do título do livro
     * @return  - Lista de livros localziados
     */
    @GET
    @Path("filtrarlivro/{termo}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response filtrarLivros(@PathParam("termo") String termo){
        List<Livro> lista = this.service.FilterByTitulo(termo);
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(lista) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    /***
     * Remove um livro pelo Id repassado
     * @param id - id do livro
     * @return - Messagem de confirmação
     */
    @DELETE
    @Path("removerlivro/{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removerLivro (@PathParam("id")  int id)
    {
        Livro l = service.FindById(id);
       
       
        service.Remove(l);
        return Response.ok()
                .build();
        
    }
    
    /***
     * Todos os autores cadastrados no banco de dados
     * @return - Lista de autores
     */
    @GET
    @Path("todososautores")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response todosAutores()
    {
        List<Autor> lista = this.service.allAutores();
        GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(lista) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
   
    }
    /***
     * Inclui no livro um altor apartir do Id do Livro e do Id do autor repassado
     * @param idLivro - Id do livro 
     * @param idAutor - Id do autor
     * @return - Livro com autor incluso
     */
    @GET
    @Path("vinculaAutor/{idautor}/{idlivro}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vinculaAutorALivro(@PathParam("idlivro") int idLivro, @PathParam("idautor") int idAutor)
    {
        Livro l = service.FindById(idLivro);
        Autor a = service.findByAutorByid(idAutor);
        l.addAutor(a);
        service.Update(l);
        GenericEntity<Livro> entity = new GenericEntity<Livro>(l) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    
    /***
     * Busca por tods os autores de um determiado livro
     * @param id - id do livro
     * @return - Lista de autores
     */
    @GET
    @Path("findAutorByIdLivro/{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAutorByIdLivro(@PathParam("id") int id){
        List<Autor> autores = service.findByAutorByidLivro(id);
        GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(autores) {
        };
        return Response.ok() 
                .entity(entity)
                .build();
    }
    
    
    
}
