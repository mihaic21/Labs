package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Owner;


@Local
public interface OwnerRepository extends BaseRepository<Owner,Integer>{

}
