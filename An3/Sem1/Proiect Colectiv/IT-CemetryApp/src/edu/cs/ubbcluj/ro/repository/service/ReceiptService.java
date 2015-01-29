package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Receipt;

@Local
public interface ReceiptService {
	Receipt insertReceipt(Receipt Receipt);

	Receipt updateReceipt(Receipt Receipt);

	void deleteReceipt(Receipt Receipt);

	List<Receipt> getAll();
	
	Receipt getById(int id);
}
