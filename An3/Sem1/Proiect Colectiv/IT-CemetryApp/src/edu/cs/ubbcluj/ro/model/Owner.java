package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the owners database table.
 * 
 */
@Entity
@Table(name="owners")
@NamedQuery(name="Owner.findAll", query="SELECT o FROM Owner o")
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String cnp;

	private boolean deleted;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to Concession
	@OneToMany(mappedBy="owner")
	private List<Concession> concessions;

	public Owner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnp() {
		return this.cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
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

	public List<Concession> getConcessions() {
		return this.concessions;
	}

	public void setConcessions(List<Concession> concessions) {
		this.concessions = concessions;
	}

	public Concession addConcession(Concession concession) {
		getConcessions().add(concession);
		concession.setOwner(this);

		return concession;
	}

	public Concession removeConcession(Concession concession) {
		getConcessions().remove(concession);
		concession.setOwner(null);

		return concession;
	}

}