package com.sample.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		System.out.println("this.getClass().getGenericSuperclass()>>" + this.getClass().getGenericSuperclass());

		Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();

		System.out.println("types Array>>" + types.toString());

		this.persistentClass = (Class<T>) types[1];
		System.out.println(" ..persistence class >>" + persistentClass);
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) entityManager.find(persistentClass, key);
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

}
