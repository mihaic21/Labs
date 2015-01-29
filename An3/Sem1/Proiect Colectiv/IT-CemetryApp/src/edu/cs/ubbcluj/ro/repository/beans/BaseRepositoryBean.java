package edu.cs.ubbcluj.ro.repository.beans;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.cs.ubbcluj.ro.repository.BaseRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Local
public abstract class BaseRepositoryBean<T, I> implements
		BaseRepository<T, I> {

	final Class<T> clazz;
	@PersistenceContext(unitName = "LoginSample")
	private EntityManager entityManager;

	public BaseRepositoryBean(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> getAll() throws RepositoryException {
		try {
			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			final CriteriaQuery<T> criteriaQuery = cb.createQuery(clazz);
			final Root<T> rootEntry = criteriaQuery.from(clazz);
			final CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
			final TypedQuery<T> allQuery = entityManager.createQuery(all);
			return allQuery.getResultList();
		} catch (IllegalArgumentException | PersistenceException e) {
			e.printStackTrace();
			throw new RepositoryException("Stuff");
		}
	}

	@Override
	public T save(T item) throws RepositoryException {
		try {
			entityManager.persist(item);
			entityManager.flush();
			return item;
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new RepositoryException("Error in save");
		}
	}

	@Override
	public T merge(T item) throws RepositoryException {
		try {
			item = entityManager.merge(item);
			entityManager.flush();
			return item;
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new RepositoryException("Error in save");
		}
	}

	@Override
	public void delete(T item) throws RepositoryException {
		try {
			item = entityManager.merge(item);
			entityManager.remove(item);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new RepositoryException("Error in delete " + e.toString());
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}