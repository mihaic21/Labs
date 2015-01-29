package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the receipts database table.
 * 
 */
@Entity
@Table(name="receipts")
@NamedQuery(name="Receipt.findAll", query="SELECT r FROM Receipt r")
public class Receipt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean deleted;

	@Temporal(TemporalType.DATE)
	@Column(name="ending_date")
	private Date endingDate;

	@Column(name="entity_id")
	private int entityId;

	private String receiptscol;

	@Column(name="recepit_number")
	private BigInteger recepitNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="starting_date")
	private Date startingDate;

	//bi-directional many-to-one association to Concession
	@ManyToOne
	private Concession concession;

	public Receipt() {
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

	public Date getEndingDate() {
		return this.endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public int getEntityId() {
		return this.entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getReceiptscol() {
		return this.receiptscol;
	}

	public void setReceiptscol(String receiptscol) {
		this.receiptscol = receiptscol;
	}

	public BigInteger getReceiptNumber() {
		return this.recepitNumber;
	}

	public void setReceiptNumber(BigInteger recepitNumber) {
		this.recepitNumber = recepitNumber;
	}

	public Date getStartingDate() {
		return this.startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Concession getConcession() {
		return this.concession;
	}

	public void setConcession(Concession concession) {
		this.concession = concession;
	}

}