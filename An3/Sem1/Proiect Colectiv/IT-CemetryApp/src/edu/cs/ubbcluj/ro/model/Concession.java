package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the concessions database table.
 * 
 */
@Entity
@Table(name="concessions")
@NamedQuery(name="Concession.findAll", query="SELECT c FROM Concession c")
public class Concession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean deleted;

	private BigInteger number;

	private int period;

	//bi-directional many-to-one association to Grave
	@ManyToOne
	private Grave grave;

	//bi-directional many-to-one association to Owner
	@ManyToOne
	private Owner owner;

	//bi-directional many-to-one association to Receipt
	@OneToMany(mappedBy="concession")
	private List<Receipt> receipts;

	public Concession() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Grave getGrave() {
		return this.grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Receipt> getReceipts() {
		return this.receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	public Receipt addReceipt(Receipt receipt) {
		getReceipts().add(receipt);
		receipt.setConcession(this);

		return receipt;
	}

	public Receipt removeReceipt(Receipt receipt) {
		getReceipts().remove(receipt);
		receipt.setConcession(null);

		return receipt;
	}

}