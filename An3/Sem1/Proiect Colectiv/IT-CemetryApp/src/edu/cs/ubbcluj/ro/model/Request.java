package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the requests database table.
 * 
 */
@Entity
@Table(name="requests")
@NamedQuery(name="Request.findAll", query="SELECT r FROM Request r")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="current_number")
	private BigInteger currentNumber;

	private boolean deleted;

	@Column(name="infocet_number")
	private BigInteger infocetNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;

	//bi-directional many-to-one association to State
	@ManyToOne
	private State state;

	public Request() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getCurrentNumber() {
		return this.currentNumber;
	}

	public void setCurrentNumber(BigInteger currentNumber) {
		this.currentNumber = currentNumber;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public BigInteger getInfocetNumber() {
		return this.infocetNumber;
	}

	public void setInfocetNumber(BigInteger infocetNumber) {
		this.infocetNumber = infocetNumber;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}