package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Concession;

@Local
public interface ConcessionRepository extends BaseRepository<Concession, Integer> {

	Concession getById(Integer id) throws RepositoryException;
}
