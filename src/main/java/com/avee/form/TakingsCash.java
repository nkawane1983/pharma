package com.avee.form;

import java.util.Date;


@SuppressWarnings("serial")
public class TakingsCash implements java.io.Serializable {
	private int id;
	private int branchId;
	private int cashId;
	private int quantity;
	private int denominationId;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public TakingsCash() {
		super();
	}

	public TakingsCash(int id, int branchId, int cashId, int quantity, int denominationId, String createdBy,
			Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.cashId = cashId;
		this.quantity = quantity;
		this.denominationId = denominationId;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getCashId() {
		return cashId;
	}

	public void setCashId(int cashId) {
		this.cashId = cashId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(int denominationId) {
		this.denominationId = denominationId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
