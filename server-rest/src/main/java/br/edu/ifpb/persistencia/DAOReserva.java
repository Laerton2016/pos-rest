/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;


import br.edu.ifpb.entidades.Reserva;
import java.util.List;
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
public class DAOReserva implements IDAO<Reserva>{

    
    @PersistenceContext(name = "POS_PU")
    private EntityManager em ;

    public DAOReserva() 
    {
        
    }
   
        
    @Override
    /***
     * Salva uma reserva
     */
    public void save(Reserva obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
           
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Reserva obj) {
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
    public void remove(Reserva obj) {
        try {
            Reserva r = em.find(Reserva.class, obj.getId());
            em.getTransaction().begin();
            em.remove(r);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public Reserva findById(int id) {
        return em.find(Reserva.class, id);
    }

    @Override
    
    /***
     * Busca uma reserva pelo título/nome do livro
     */
    public List<Reserva> findByNome(String termo) 
    {
        String SQL = "Select r from Reserva r left join Livro l on l.id = r.idLivro where l.nome like :nome";
        Query q = em.createQuery(SQL, Reserva.class);
        q.setParameter("nome", termo + "%");
        return q.getResultList();
    }
    
}
