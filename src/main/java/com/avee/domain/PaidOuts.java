package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "paid_outs")
public class PaidOuts implements java.io.Serializable {
	private int id;
	private int branchId;
	private int cashId;
	private int ctype;
	private double amount;
	private String description;
	private double vat;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public PaidOuts() {
		super();
	}

	public PaidOuts(int id, int branchId, int cashId, int ctype, double amount, String description, double vat,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.cashId = cashId;
		this.ctype = ctype;
		this.amount = amount;
		this.description = description;
		this.vat = vat;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}
	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "paid_outs_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "branch_id", nullable = false)
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	@Column(name = "cash_id", nullable = false)
	public int getCashId() {
		return cashId;
	}

	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	@Column(name = "type", nullable = false)
	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Column(name = "description",length = 10)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "vat")
	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}
	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_date ")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	
}
