package com.sample.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		System.out.println("this.getClass().getGenericSuperclass()>>"+
	                                         this.getClass().getGenericSuperclass());
		
		Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
		
		System.out.println("types Array>>"+types.toString());
		
		this.persistentClass = (Class<T> )types[1];
		System.out.println(" ..persistence class >>"+persistentClass);
	}

	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
 
	@SuppressWarnings("unchecked")
	public T getByKey(PK key){
		return (T) getSession().get(persistentClass,key);
	}
	
	 public void persist(T entity) {
	        getSession().persist(entity);
	    }
	 
	    public void delete(T entity) {
	        getSession().delete(entity);
	    }
	     
	    protected Criteria createEntityCriteria(){
	        return getSession().createCriteria(persistentClass);
	    }
	
}
