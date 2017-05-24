package br.com.newsmanagerApp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import br.com.newsmanagerApp.dao.interfaces.IGenericDAO;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, PK extends Serializable> implements IGenericDAO<T, PK> {

	@PersistenceContext(unitName = "managerNews")
	protected EntityManager entityManager;

	@Resource
	private UserTransaction ut;

	public void persist(T entity) {
		beginTransaction();
		entityManager.persist(entity);
		commitTransaction();

	}

	public void marge(T entity) {
		beginTransaction();
		entityManager.merge(entity);
		commitTransaction();

	}

	public void remove(T entity) {
		beginTransaction();
		entityManager.remove(entity);
		commitTransaction();
	}

	public void removeById(PK id) {
		beginTransaction();
		T entity = getByID(id);
		entityManager.remove(entity);
		commitTransaction();
	}

	public T getByID(PK id) {
		return (T) entityManager.find(getTypeClass(), id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("FROM " + getTypeClass().getName()).getResultList();
	}

	public Query createQuery(String query, Object... parameters) {
		Query q = entityManager.createQuery(query);

		for (int i = 1; i < parameters.length; i++) {
			q.setParameter(i, parameters[i]);
		}
		return q;
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}

	protected void beginTransaction() {
		try {
			if (ut.getStatus() != Status.STATUS_ACTIVE) {
				ut.begin();
			}
		} catch (Exception e) {
			rollbackTransaction();
			throw new RuntimeException(e);
		}
	}

	protected void commitTransaction() {
		try {
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.commit();
			}
		} catch (Exception e) {
			rollbackTransaction();
			throw new RuntimeException(e);
		}
	}

	protected void rollbackTransaction() {
		try {
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
