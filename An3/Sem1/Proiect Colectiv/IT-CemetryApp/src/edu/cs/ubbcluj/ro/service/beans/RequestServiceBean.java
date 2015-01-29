package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Request;
import edu.cs.ubbcluj.ro.repository.RequestRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.RequestService;

@Stateless(name = "RequestService", mappedName = "RequestService")
@DependsOn({ "RequestRepository" })
@Local
public class RequestServiceBean implements RequestService {

	@EJB(name = "RequestRepository")
	RequestRepository repo;

	@Override
	public Request insertRequest(Request Request) {
		try {
			return repo.save(Request);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Request;
	}

	@Override
	public Request updateRequest(Request Request) {
		try {
			return this.repo.merge(Request);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteRequest(Request Request) {
		try {
			this.repo.delete(Request);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Request> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Request getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
