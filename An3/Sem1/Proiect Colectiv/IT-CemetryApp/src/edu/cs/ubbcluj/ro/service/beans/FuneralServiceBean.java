package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Funeral;
import edu.cs.ubbcluj.ro.repository.FuneralRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.FuneralService;

@Stateless(name = "FuneralService", mappedName = "FuneralService")
@DependsOn({ "FuneralRepository" })
@Local
public class FuneralServiceBean implements FuneralService {

	@EJB(name = "FuneralRepository")
	FuneralRepository repo;

	@Override
	public Funeral insertFuneral(Funeral Funeral) {
		try {
			return repo.save(Funeral);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Funeral;
	}

	@Override
	public Funeral updateFuneral(Funeral Funeral) {
		try {
			return this.repo.merge(Funeral);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteFuneral(Funeral Funeral) {
		try {
			this.repo.delete(Funeral);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Funeral> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Funeral getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
}
