package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class BankingOutstanging implements java.io.Serializable {
	private int id;
	private int branchId;
	private Date bankDate;
	private int bankingId;
	private double amount;
	private double outstanging;
	private String status;
	private String comments;
	
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public BankingOutstanging() {
		
	}

	public BankingOutstanging(int id, int branchId, Date bankDate, int bankingId, double amount, double outstanging,
			String status, String comments, String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.bankDate = bankDate;
		this.bankingId = bankingId;
		this.amount = amount;
		this.outstanging = outstanging;
		this.status = status;
		this.comments = comments;
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

	public Date getBankDate() {
		return bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}

	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOutstanging() {
		return outstanging;
	}

	public void setOutstanging(double outstanging) {
		this.outstanging = outstanging;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
