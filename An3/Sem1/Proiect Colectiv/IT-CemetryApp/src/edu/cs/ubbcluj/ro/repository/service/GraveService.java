package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Grave;
@Local
public interface GraveService {
	Grave insertGrave(Grave grave);

	Grave updateGrave(Grave grave);

	void deleteGrave(Grave grave);

	List<Grave> getAll();

    List<String> getData(int graveid);
}
