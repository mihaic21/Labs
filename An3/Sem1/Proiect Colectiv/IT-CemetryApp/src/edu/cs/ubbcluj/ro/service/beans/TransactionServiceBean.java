package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Transaction;
import edu.cs.ubbcluj.ro.repository.TransactionRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.TransactionService;

@Stateless(name = "TransactionService", mappedName = "TransactionService")
@DependsOn({ "TransactionRepository" })
@Local
public class TransactionServiceBean implements TransactionService {

	@EJB(name = "TransactionRepository")
	TransactionRepository repo;

	@Override
	public Transaction insertTransaction(Transaction Transaction) {
		try {
			return repo.save(Transaction);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return Transaction;
	}

	@Override
	public Transaction updateTransaction(Transaction Transaction) {
		try {
			return this.repo.merge(Transaction);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteTransaction(Transaction Transaction) {
		try {
			this.repo.delete(Transaction);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Transaction> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Transaction getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
}
