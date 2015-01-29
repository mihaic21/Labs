package edu.cs.ubbcluj.ro.repository.service;

import java.util.List;

import javax.ejb.Local;

import edu.cs.ubbcluj.ro.model.Parcel;


@Local
public interface ParcelService {
	Parcel insertParcel(Parcel parcel);

	Parcel updateParcel(Parcel parcel);
	void deleteParcel(Parcel parcel);
	
	List<Parcel> getAll();
	
	Parcel getById(int id);
}
