package br.com.newsmanagerApp.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

public interface IGenericDAO <T, PK extends Serializable>{
	public void persist(T entity);
	public void marge(T entity);
	public void removeById(PK id);
	public T getByID(PK id);
	public List<T> findAll();
	public Query createQuery(String query, Object... parameters);
}
