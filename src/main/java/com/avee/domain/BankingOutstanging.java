package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "banking_outstanding")
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
	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="banking_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID",unique = true, nullable = false)
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
	@Column(name = "action_date")
	public Date getBankDate() {
		return bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}
	@Column(name = "banking_id")
	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Column(name = "outstanging")
	public double getOutstanging() {
		return outstanging;
	}

	public void setOutstanging(double outstanging) {
		this.outstanging = outstanging;
	}
	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "comments" , length = 255)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
