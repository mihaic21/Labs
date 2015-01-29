package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Receipt;


@Local
public interface ReceiptRepository extends BaseRepository<Receipt,Integer>{

}
