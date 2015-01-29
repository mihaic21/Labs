package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Request;


@Local
public interface RequestService {
	Request insertRequest(Request Request);

	Request updateRequest(Request Request);

	void deleteRequest(Request Request);

	List<Request> getAll();
	
	Request getById(int id);
}
