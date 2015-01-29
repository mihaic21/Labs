package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Transaction;


@Local
public interface TransactionService {
	Transaction insertTransaction(Transaction Transaction);

	Transaction updateTransaction(Transaction Transaction);

	void deleteTransaction(Transaction Transaction);

	List<Transaction> getAll();
	
	Transaction getById(int id);
}
