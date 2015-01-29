package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Receipt;
import edu.cs.ubbcluj.ro.repository.ReceiptRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.ReceiptService;

@Stateless(name = "ReceiptService", mappedName = "ReceiptService")
@DependsOn({ "ReceiptRepository" })
@Local
public class ReceiptServiceBean implements ReceiptService {

	@EJB(name = "ReceiptRepository")
	ReceiptRepository repo;

	@Override
	public Receipt insertReceipt(Receipt Receipt) {
		try {
			return repo.save(Receipt);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Receipt;
	}

	@Override
	public Receipt updateReceipt(Receipt Receipt) {
		try {
			return this.repo.merge(Receipt);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteReceipt(Receipt Receipt) {
		try {
			this.repo.delete(Receipt);
		} catch (RepositoryException e) {

		}
		
	}

	@Override
	public List<Receipt> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public Receipt getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
