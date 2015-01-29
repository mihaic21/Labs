package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.State;
import edu.cs.ubbcluj.ro.repository.StateRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "StateRepository", mappedName = "StateRepository")
public class StateRepositoryBean extends BaseRepositoryBean<State, Integer>
		implements StateRepository {

	public StateRepositoryBean() {
		super(State.class);
	}

	@Override
	public State getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(State.class, id);

	}

}
