/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;


import br.edu.ifpb.entidades.Autor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author laerton
 */
@Singleton
@Named
public class DAOAutor implements IDAO<Autor>{

    @PersistenceContext(name = "POS_PU")
    private EntityManager em;

    public DAOAutor() 
    {
        
    }

    @Override
    public void save(Autor obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Autor obj) {
        try {
            em.getTransaction().begin();
            obj = em.merge(obj);
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void remove(Autor obj) {
        try {
            Autor a = findById(obj.getId());
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public Autor findById(int id) {
        return em.find(Autor.class, id);
    }

    @Override
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
