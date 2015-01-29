package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Transaction;


@Local
public interface TransactionRepository extends BaseRepository<Transaction,Integer>{

}
