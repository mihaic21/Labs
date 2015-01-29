package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.State;


@Local
public interface StateRepository extends BaseRepository<State,Integer>{

}
