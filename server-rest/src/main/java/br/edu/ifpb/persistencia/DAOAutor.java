/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;


import br.edu.ifpb.entidades.Autor;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author laerton
 */
@Singleton
//@Named
public class DAOAutor {

    @PersistenceContext
    private EntityManager em;

    public DAOAutor() 
    {
        
    }

    
    public void save(Autor obj) {
            em.persist(obj);
    }

    
    public void update(Autor obj) {
            obj = em.merge(obj);
            
    }

    
    public void remove(Autor obj) {
            Autor a = findById(obj.getId());
            em.remove(a);
    }

    
    public Autor findById(int id) {
        return em.find(Autor.class, id);
    }

    
    public List<Autor> findByNome(String termo) {
        String SQL = "Select a from Autor a where a.nome like :nome order by a.nome";
        Query q = em.createQuery(SQL, Autor.class);
        q.setParameter("nome", termo + "%");
        return q.getResultList();
    }
    
    public List<Autor> autores (){
       return em.createQuery("Select a from Autor a order by a.nome", Autor.class).getResultList();
    }

    public List<Autor> findAutoresByIdLivro(int id) {
        Query q = em.createQuery("Select a from Autor a , Livro l WHERE a MEMBER OF l.autores AND l.id = :idlivro", Autor.class);
        q.setParameter("idlivro", id);
        return q.getResultList();
    }

    
}
