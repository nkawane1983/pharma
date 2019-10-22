package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class TakingsCheques implements java.io.Serializable {
	private int id;
	private int branchId;
	private int cashId;
	private String accountNo;
	private String cname;
	private double amount;
	private int bankingId;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public TakingsCheques() {
		super();
	}

	public TakingsCheques(int id, int branchId, int cashId, String cname, double amount, int bankingId, String createdBy,
			Date createdDt, String updatedBy, Date updatedDt,String accountNo) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.cashId = cashId;
		this.cname = cname;
		this.amount = amount;
		this.bankingId = bankingId;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.accountNo = accountNo;
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

	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
