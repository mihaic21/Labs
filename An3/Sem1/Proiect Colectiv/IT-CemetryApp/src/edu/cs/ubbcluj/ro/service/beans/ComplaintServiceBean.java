package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Complaint;
import edu.cs.ubbcluj.ro.repository.ComplaintRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.ComplaintService;

@Stateless(name = "ComplaintService", mappedName = "ComplaintService")
@DependsOn({ "ComplaintRepository" })
@Local
public class ComplaintServiceBean implements ComplaintService {

	@EJB(name = "ComplaintRepository")
	ComplaintRepository repo;

	@Override
	public Complaint insertComplaint(Complaint complaint) {
		try {
			return repo.save(complaint);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return complaint;
	}

	@Override
	public Complaint updateComplaint(Complaint complaint) {
		try {
			return this.repo.merge(complaint);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteComplaint(Complaint complaint) {
		try {
			this.repo.delete(complaint);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Complaint> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Complaint getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

}
