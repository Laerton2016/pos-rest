/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.persistencia;

import java.util.List;

/**
 *
 * @author laerton
 */
public interface IDAO<T> {
    void save(T obj);
    void update (T obj);
    void remove (T obj);
    T findById (int id);
    List<T> findByNome (String termo);
}
