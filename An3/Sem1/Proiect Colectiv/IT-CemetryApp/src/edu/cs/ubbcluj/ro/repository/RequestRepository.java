package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Request;


@Local
public interface RequestRepository extends BaseRepository<Request,Integer>{

}
