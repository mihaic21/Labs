package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.State;


@Local
public interface StateService {
	State insertState(State State);

	State updateState(State State);

	void deleteState(State State);

	List<State> getAll();
	
	State getById(int id);
}
