package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the graves database table.
 * 
 */
@Entity
@Table(name="graves")
@NamedQuery(name="Grave.findAll", query="SELECT g FROM Grave g")
public class Grave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	@Column(name="image_uri")
	private String imageUri;

	private BigInteger number;

	private String remarks;

	private float surface;

	//bi-directional many-to-one association to Concession
	@OneToMany(mappedBy="grave")
	private List<Concession> concessions;

	//bi-directional many-to-one association to Dead
	@OneToMany(mappedBy="grave")
	private List<Dead> deads;

	//bi-directional many-to-one association to Parcel
	@ManyToOne
	private Parcel parcel;

	//bi-directional many-to-one association to Monument
	@OneToMany(mappedBy="grave")
	private List<Monument> monuments;

	public Grave() {
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

	public String getImageUri() {
		return this.imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public BigInteger getNumber() {
		return this.number;
	}

	public void setNumber(BigInteger number) {
		this.number = number;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public float getSurface() {
		return this.surface;
	}

	public void setSurface(float surface) {
		this.surface = surface;
	}

	public List<Concession> getConcessions() {
		return this.concessions;
	}

	public void setConcessions(List<Concession> concessions) {
		this.concessions = concessions;
	}

	public Concession addConcession(Concession concession) {
		getConcessions().add(concession);
		concession.setGrave(this);

		return concession;
	}

	public Concession removeConcession(Concession concession) {
		getConcessions().remove(concession);
		concession.setGrave(null);

		return concession;
	}

	public List<Dead> getDeads() {
		return this.deads;
	}

	public void setDeads(List<Dead> deads) {
		this.deads = deads;
	}

	public Dead addDead(Dead dead) {
		getDeads().add(dead);
		dead.setGrave(this);

		return dead;
	}

	public Dead removeDead(Dead dead) {
		getDeads().remove(dead);
		dead.setGrave(null);

		return dead;
	}

	public Parcel getParcel() {
		return this.parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public List<Monument> getMonuments() {
		return this.monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public Monument addMonument(Monument monument) {
		getMonuments().add(monument);
		monument.setGrave(this);

		return monument;
	}

	public Monument removeMonument(Monument monument) {
		getMonuments().remove(monument);
		monument.setGrave(null);

		return monument;
	}

}