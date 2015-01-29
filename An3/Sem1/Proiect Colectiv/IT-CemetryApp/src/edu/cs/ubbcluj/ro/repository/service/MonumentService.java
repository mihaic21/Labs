package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Monument;
@Local
public interface MonumentService {
	Monument insertMonument(Monument Monument);

	Monument updateMonument(Monument Monument);

	void deleteMonument(Monument Monument);

	List<Monument> getAll();

    boolean isMonument(int graveId);
}
