package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Grave;


@Local
public interface GraveRepository extends BaseRepository<Grave,Integer>{

}
