package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Owner;
import edu.cs.ubbcluj.ro.repository.OwnerRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.OwnerService;

@Stateless(name = "OwnerService", mappedName = "OwnerService")
@DependsOn({ "OwnerRepository" })
@Local
public class OwnerServiceBean implements OwnerService {

	@EJB(name = "OwnerRepository")
	OwnerRepository repo;

	@Override
	public Owner insertOwner(Owner Owner) {
		try {
			return repo.save(Owner);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Owner;
	}

	@Override
	public Owner updateOwner(Owner Owner) {
		try {
			return this.repo.merge(Owner);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteOwner(Owner Owner) {
		try {
			this.repo.delete(Owner);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Owner> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Owner getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
