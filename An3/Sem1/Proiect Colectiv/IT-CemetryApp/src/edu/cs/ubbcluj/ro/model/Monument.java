package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the monuments database table.
 * 
 */
@Entity
@Table(name="monuments")
@NamedQuery(name="Monument.findAll", query="SELECT m FROM Monument m")
public class Monument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Grave
	@ManyToOne
	private Grave grave;

	public Monument() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grave getGrave() {
		return this.grave;
	}

	public void setGrave(Grave grave) {
		this.grave = grave;
	}

}