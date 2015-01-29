package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Monument;


@Local
public interface MonumentRepository extends BaseRepository<Monument,Integer>{

}
