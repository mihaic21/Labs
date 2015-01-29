package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the funerals database table.
 * 
 */
@Entity
@Table(name="funerals")
@NamedQuery(name="Funeral.findAll", query="SELECT f FROM Funeral f")
public class Funeral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean deleted;

	private Time time;

	//bi-directional many-to-one association to Dead
	@ManyToOne
	private Dead dead;

	public Funeral() {
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

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Dead getDead() {
		return this.dead;
	}

	public void setDead(Dead dead) {
		this.dead = dead;
	}

}