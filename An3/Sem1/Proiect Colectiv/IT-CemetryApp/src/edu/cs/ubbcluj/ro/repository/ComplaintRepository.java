package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Complaint;



@Local
public interface ComplaintRepository extends BaseRepository<Complaint,Integer>{

}
