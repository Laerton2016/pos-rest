/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;


import br.edu.ifpb.entidades.Livro;
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
public class DAOLivro {

    
    @PersistenceContext(unitName = "POS_PU")
    private EntityManager em;

    public DAOLivro() 
    {
        
    }

    
    public void save(Livro obj) {
            em.persist(obj);
        
    }

    
    public void update(Livro obj) {
            
            obj = em.merge(obj);
        
    }

    
    public void remove(Livro obj) {
            Livro l = findById(obj.getId());
            em.remove(l);
     
    }

    
    public Livro findById(int id) {
        return em.find(Livro.class, id);
    }

    
    public List<Livro> findByNome(String termo) {
        String Sql = "Select l from Livro l where l.titulo like :titulo order by l.titulo";
        Query q = em.createQuery(Sql, Livro.class);
        q.setParameter("titulo", termo +"%");
        return q.getResultList();
    }
    
    public List<Livro> livros (){
        
        return em.createQuery("Select l from Livro l order by l.titulo", Livro.class).getResultList();
    }
    
}
