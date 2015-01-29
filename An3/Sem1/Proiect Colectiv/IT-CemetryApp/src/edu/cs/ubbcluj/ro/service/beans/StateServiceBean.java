package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.State;
import edu.cs.ubbcluj.ro.repository.StateRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.StateService;

@Stateless(name = "StateService", mappedName = "StateService")
@DependsOn({ "StateRepository" })
@Local
public class StateServiceBean implements StateService {

	@EJB(name = "StateRepository")
	StateRepository repo;

	@Override
	public State insertState(State State) {
		try {
			return repo.save(State);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return State;
	}

	@Override
	public State updateState(State State) {
		try {
			return this.repo.merge(State);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteState(State State) {
		try {
			this.repo.delete(State);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<State> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public State getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
