package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the deads database table.
 * 
 */
@Entity
@Table(name="deads")
@NamedQuery(name="Dead.findAll", query="SELECT d FROM Dead d")
public class Dead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String religion;

	//bi-directional many-to-one association to Grave
	@ManyToOne
	private Grave grave;

	//bi-directional many-to-one association to Funeral
	@OneToMany(mappedBy="dead")
	private List<Funeral> funerals;

	public Dead() {
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Grave getGrave() {
		return this.grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

	public List<Funeral> getFunerals() {
		return this.funerals;
	}

	public void setFunerals(List<Funeral> funerals) {
		this.funerals = funerals;
	}

	public Funeral addFuneral(Funeral funeral) {
		getFunerals().add(funeral);
		funeral.setDead(this);

		return funeral;
	}

	public Funeral removeFuneral(Funeral funeral) {
		getFunerals().remove(funeral);
		funeral.setDead(null);

		return funeral;
	}

}