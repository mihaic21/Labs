package edu.cs.ubbcluj.ro.repository;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Parcel;

@Local
public interface ParcelRepository extends BaseRepository<Parcel, Integer> {

}
