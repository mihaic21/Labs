package edu.cs.ubbcluj.ro.repository.beans;

import javax.ejb.Stateless;

import edu.cs.ubbcluj.ro.model.Parcel;
import edu.cs.ubbcluj.ro.repository.ParcelRepository;
import edu.cs.ubbcluj.ro.repository.RepositoryException;

@Stateless(name = "ParcelRepository", mappedName = "ParcelRepository")
public class ParcelRepositoryBean extends BaseRepositoryBean<Parcel, Integer>
		implements ParcelRepository {

	public ParcelRepositoryBean() {
		super(Parcel.class);
	}

	@Override
	public Parcel getById(Integer id) throws RepositoryException {
		return this.getEntityManager().find(Parcel.class, id);

	}

}
