package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Concession;


@Local
public interface ConcessionService {
	Concession insertConcession(Concession Concession);

	Concession updateConcession(Concession Concession);

	void deleteConcession(Concession Concession);

	List<Concession> getAll();

    List<String> getData(int graveid);
}
