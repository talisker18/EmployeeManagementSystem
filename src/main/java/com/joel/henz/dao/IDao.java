package com.joel.henz.dao;

import java.util.List;

//by default, methods of interfaces are public
public interface IDao<T> {
	
	T getById(int id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(Integer id);
    
}
