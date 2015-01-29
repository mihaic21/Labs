package edu.cs.ubbcluj.ro.service.beans;

import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Parcel;
import edu.cs.ubbcluj.ro.repository.ParcelRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;
import edu.cs.ubbcluj.ro.repository.service.ParcelService;




@Stateless(name = "ParcelService", mappedName = "ParcelService")
@DependsOn({ "ParcelRepository" })
@Local
public class ParcelServiceBean implements ParcelService{

	@EJB(name = "ParcelRepository")
	ParcelRepository repo;
	
	
	@Override
	public Parcel insertParcel(Parcel parcel) {
		try {
			return repo.save(parcel);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return parcel;
	}

	@Override
	public Parcel updateParcel(Parcel parcel) {
		try {
			return this.repo.merge(parcel);
		} catch (RepositoryException e) {
			return null;
		}
	}

	@Override
	public void deleteParcel(Parcel parcel) {
		try {
			this.repo.delete(parcel);
		} catch (RepositoryException e) {

		}
	}

	@Override
	public List<Parcel> getAll() {
		try {
			return repo.getAll();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public Parcel getById(int id){
		try{
			return repo.getById(id);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
}
