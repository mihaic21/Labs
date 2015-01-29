package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Complaint;
import edu.cs.ubbcluj.ro.model.Dead;


@Local
public interface DeadService {
	Dead insertDead(Dead Dead);

	Dead updateDead(Dead Dead);

	void deleteDead(Dead Dead);

	List<Dead> getAll();
	
	Dead getById(int id);
}
