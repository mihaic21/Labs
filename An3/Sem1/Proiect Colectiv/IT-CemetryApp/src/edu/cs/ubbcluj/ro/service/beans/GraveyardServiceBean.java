package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Graveyard;
import edu.cs.ubbcluj.ro.repository.GraveyardRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.GraveyardService;

@Stateless(name = "GraveyardService", mappedName = "GraveyardService")
@DependsOn({ "GraveyardRepository" })
@Local
public class GraveyardServiceBean implements GraveyardService {

	@EJB(name = "GraveyardRepository")
	GraveyardRepository repo;

	@Override
	public Graveyard insertGraveyard(Graveyard graveyard) {
		try {
			return repo.save(graveyard);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return graveyard;
	}

	@Override
	public Graveyard updateGraveyard(Graveyard graveyard) {
		try {
			return this.repo.merge(graveyard);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteGraveyard(Graveyard graveyard) {
		try {
			this.repo.delete(graveyard);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Graveyard> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public Graveyard getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
