package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parcels database table.
 * 
 */
@Entity
@Table(name="parcels")
@NamedQuery(name="Parcel.findAll", query="SELECT p FROM Parcel p")
public class Parcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	private int number;

	//bi-directional many-to-one association to Grave
	@OneToMany(mappedBy="parcel")
	private List<Grave> graves;

	//bi-directional many-to-one association to Graveyard
	@ManyToOne
	private Graveyard graveyard;

	public Parcel() {
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

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Grave> getGraves() {
		return this.graves;
	}

	public void setGraves(List<Grave> graves) {
		this.graves = graves;
	}

	public Grave addGrave(Grave grave) {
		getGraves().add(grave);
		grave.setParcel(this);

		return grave;
	}

	public Grave removeGrave(Grave grave) {
		getGraves().remove(grave);
		grave.setParcel(null);

		return grave;
	}

	public Graveyard getGraveyard() {
		return this.graveyard;
	}

	public void setGraveyard(Graveyard graveyard) {
		this.graveyard = graveyard;
	}

}