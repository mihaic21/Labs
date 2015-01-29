package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Owner;
import edu.cs.ubbcluj.ro.repository.OwnerRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "OwnerRepository", mappedName = "OwnerRepository")
public class OwnerRepositoryBean extends BaseRepositoryBean<Owner, Integer>
		implements OwnerRepository {

	public OwnerRepositoryBean() {
		super(Owner.class);
	}

	@Override
	public Owner getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Owner.class, id);

	}

}
