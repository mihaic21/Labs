package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Transaction;
import edu.cs.ubbcluj.ro.repository.TransactionRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "TransactionRepository", mappedName = "TransactionRepository")
public class TransactionRepositoryBean extends BaseRepositoryBean<Transaction, Integer>
		implements TransactionRepository {

	public TransactionRepositoryBean() {
		super(Transaction.class);
	}

	@Override
	public Transaction getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Transaction.class, id);

	}

}
