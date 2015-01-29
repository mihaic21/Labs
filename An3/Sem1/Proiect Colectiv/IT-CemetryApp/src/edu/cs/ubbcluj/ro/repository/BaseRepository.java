package edu.cs.ubbcluj.ro.repository;

import java.util.List;

import javax.ejb.Local;

@Local
public interface BaseRepository<T, I> {
	List<T> getAll() throws RepositoryException;

	T getById(I id) throws RepositoryException;

	T save(T item) throws RepositoryException;

	T merge(T item) throws RepositoryException;

	void delete(T item) throws RepositoryException;

}
