package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface RepositoryInterface<T> {
    T create(T object);
    String delete(String id);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id,T updated);
}
