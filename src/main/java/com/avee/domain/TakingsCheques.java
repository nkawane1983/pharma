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
@Table(name = "takings_cheques")
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

	public TakingsCheques(int id, int branchId, int cashId, String cname, double amount, int bankingId,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt,String accountNo) {
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

	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "takings_cheques_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "branch_id")
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

	@Column(name = "name")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "banking_id")
	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
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
	@Column(name = "accountno", length = 20)
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
