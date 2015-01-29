package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the graveyards database table.
 * 
 */
@Entity
@Table(name="graveyards")
@NamedQuery(name="Graveyard.findAll", query="SELECT g FROM Graveyard g")
public class Graveyard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	private String name;

	//bi-directional many-to-one association to Parcel
	@OneToMany(mappedBy="graveyard")
	private List<Parcel> parcels;

	public Graveyard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Parcel> getParcels() {
		return this.parcels;
	}

	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}

	public Parcel addParcel(Parcel parcel) {
		getParcels().add(parcel);
		parcel.setGraveyard(this);

		return parcel;
	}

	public Parcel removeParcel(Parcel parcel) {
		getParcels().remove(parcel);
		parcel.setGraveyard(null);

		return parcel;
	}

}