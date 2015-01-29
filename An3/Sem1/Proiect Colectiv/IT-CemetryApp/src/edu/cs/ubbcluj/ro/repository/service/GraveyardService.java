package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Graveyard;

@Local
public interface GraveyardService {
	Graveyard insertGraveyard(Graveyard graveyard);

	Graveyard updateGraveyard(Graveyard graveyard);

	void deleteGraveyard(Graveyard graveyard);

	List<Graveyard> getAll();
	
	Graveyard getById(int id);
}
