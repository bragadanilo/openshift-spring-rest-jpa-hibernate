package com.dbraga.springrest.app.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	public Class<T> getTypeClass();

	public List<T> all();

	public T searchById(ID id);

	public List<T> listByQuery(String query);
	
	public T save(T entity) throws Exception;

	public T update(T entity) throws Exception;

	public void delete(T entity) throws Exception;

	public void flush() throws Exception;
	
}
