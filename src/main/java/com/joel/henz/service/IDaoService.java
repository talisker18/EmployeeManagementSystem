package com.joel.henz.service;

import java.util.List;

public interface IDaoService<T> {
	void add(T t);
	List<T> getAll();
	void delete(Integer id);
	T get(int id);
	void update(T t);
}
