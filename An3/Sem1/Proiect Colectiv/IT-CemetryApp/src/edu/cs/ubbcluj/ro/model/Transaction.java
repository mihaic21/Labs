package edu.cs.ubbcluj.ro.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="after_trans")
	private String afterTrans;

	@Column(name="before_trans")
	private String beforeTrans;

	@Column(name="document_number")
	private BigInteger documentNumber;

    @Column(name="record_id")
    private Integer recordId;

	@Column(name="modification_details")
	private String modificationDetails;

	@Column(name="table_name")
	private String tableName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trans_time")
	private Date transTime;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Transaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Integer getRecordId() {return this.recordId; }

    public void setRecordId(Integer recordId) {this.recordId = recordId; }

	public String getAfterTrans() {
		return this.afterTrans;
	}

	public void setAfterTrans(String afterTrans) {
		this.afterTrans = afterTrans;
	}

	public String getBeforeTrans() {
		return this.beforeTrans;
	}

	public void setBeforeTrans(String beforeTrans) {
		this.beforeTrans = beforeTrans;
	}

	public BigInteger getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(BigInteger documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getModificationDetails() {
		return this.modificationDetails;
	}

	public void setModificationDetails(String modificationDetails) {
		this.modificationDetails = modificationDetails;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getTransTime() {
		return this.transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}