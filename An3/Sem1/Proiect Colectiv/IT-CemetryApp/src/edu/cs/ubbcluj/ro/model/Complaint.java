package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the complaints database table.
 * 
 */
@Entity
@Table(name="complaints")
@NamedQuery(name="Complaint.findAll", query="SELECT c FROM Complaint c")
public class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	private BigInteger number;

	public Complaint() {
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

	public BigInteger getNumber() {
		return this.number;
	}

	public void setNumber(BigInteger number) {
		this.number = number;
	}

}