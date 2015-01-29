package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Monument;
import edu.cs.ubbcluj.ro.repository.MonumentRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "MonumentRepository", mappedName = "MonumentRepository")
public class MonumentRepositoryBean extends BaseRepositoryBean<Monument, Integer>
		implements MonumentRepository {

	public MonumentRepositoryBean() {
		super(Monument.class);
	}
	@Override
	public Monument getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Monument.class, id);

	}

}
