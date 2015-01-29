package edu.cs.ubbcluj.ro.repository.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.cs.ubbcluj.ro.model.User;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.UserRepository;

@Stateless(name = "UserRepository", mappedName = "UserRepository")
public class UserRepositoryBean extends BaseRepositoryBean<User, Integer>
		implements UserRepository {

	public UserRepositoryBean() {
		super(User.class);
	}

	@Override
	public User getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(User.class, id);

	}

	@Override
	public User getByUsername(String username) throws RepositoryException {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> User = query.from(User.class);
		query.where(cb.equal(User.get("username"), username));
		List<User> result = this.getEntityManager().createQuery(query)
				.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public void deleteUser(User username) throws RepositoryException {
		this.getEntityManager().remove(username);

	}
}
