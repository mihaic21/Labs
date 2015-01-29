package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Request;
import edu.cs.ubbcluj.ro.repository.RequestRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "RequestRepository", mappedName = "RequestRepository")
public class RequestRepositoryBean extends BaseRepositoryBean<Request, Integer>
		implements RequestRepository {

	public RequestRepositoryBean() {
		super(Request.class);
	}

	@Override
	public Request getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Request.class, id);

	}

}
