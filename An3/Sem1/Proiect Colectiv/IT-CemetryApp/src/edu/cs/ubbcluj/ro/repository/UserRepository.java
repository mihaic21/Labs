package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.User;

@Local
public interface UserRepository extends BaseRepository<User, Integer> {

	public User getByUsername(String username) throws RepositoryException;

	public void deleteUser(User username) throws RepositoryException;

	User getById(Integer id) throws RepositoryException;
}
