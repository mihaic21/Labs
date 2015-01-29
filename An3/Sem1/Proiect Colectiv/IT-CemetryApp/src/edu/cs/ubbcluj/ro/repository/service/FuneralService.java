package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Funeral;


@Local
public interface FuneralService {
	Funeral insertFuneral(Funeral Funeral);

	Funeral updateFuneral(Funeral Funeral);

	void deleteFuneral(Funeral Funeral);

	List<Funeral> getAll();
	
	Funeral getById(int id);
}
