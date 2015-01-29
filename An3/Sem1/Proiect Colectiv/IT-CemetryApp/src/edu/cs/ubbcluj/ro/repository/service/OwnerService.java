package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Owner;


@Local
public interface OwnerService {
	Owner insertOwner(Owner Owner);

	Owner updateOwner(Owner Owner);

	void deleteOwner(Owner Owner);

	List<Owner> getAll();
	
	Owner getById(int id);
}
