package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Complaint;


@Local
public interface ComplaintService {
	Complaint insertComplaint(Complaint complaint);

	Complaint updateComplaint(Complaint complaint);

	void deleteComplaint(Complaint complaint);
	
	Complaint getById(int id);
	
	List<Complaint> getAll();
}
