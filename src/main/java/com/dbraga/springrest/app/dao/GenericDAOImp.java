package com.dbraga.springrest.app.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDAOImp<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAOImp() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) throws Exception {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) throws Exception {
		entity = getEntityManager().merge(entity);
		getEntityManager().flush();
		return entity;
	}

	@Override
	public void delete(T entity) throws Exception {
		entity = getEntityManager().merge(entity);
		getEntityManager().remove(entity);
		getEntityManager().flush();
	}

	@Override
	public List<T> all() {
		String query = "FROM " + getTypeClass().getSimpleName();
		return entityManager.createQuery(query, clazz).getResultList();
	}
	

	@Override
	public T searchById(ID id) {
		return getEntityManager().find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listByQuery(String query) {
		Query q = getEntityManager().createQuery(query);
		return q.getResultList();
	}

	@Override
	public void flush() throws Exception {
		getEntityManager().flush();
	}
	
	@Override
	public Class<T> getTypeClass() {
		return this.clazz;
	}
	
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {		
		return entityManager;
	}
	
	

}
