package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Dead;
import edu.cs.ubbcluj.ro.repository.DeadRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.DeadService;

@Stateless(name = "DeadService", mappedName = "DeadService")
@DependsOn({ "DeadRepository" })
@Local
public class DeadServiceBean implements DeadService {

	@EJB(name = "DeadRepository")
	DeadRepository repo;

	@Override
	public Dead insertDead(Dead Dead) {
		try {
			return repo.save(Dead);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Dead;
	}

	@Override
	public Dead updateDead(Dead Dead) {
		try {
			return this.repo.merge(Dead);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteDead(Dead Dead) {
		try {
			this.repo.delete(Dead);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Dead> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Dead getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
