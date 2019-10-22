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
@Table(name = "banking")
public class Banking implements java.io.Serializable {
	private int id;
	private int branchId;
	private Date bankDate;
	private String bankingRef;
	private double cash;
	private double cheques;
	private String userId;
	private double miscCash;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private String notes;
	private String manager;

	private String deleteComment;
	private boolean markDelete;
	public Banking() {
		super();
	}

	public Banking(int id, int branchId, Date bankDate, String bankingRef, double cash, double cheques, String userId,
			double miscCash, String createdBy, Date createdDt, String updatedBy, Date updatedDt,String notes,String manager,String deleteComment, boolean markDelete) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.bankDate = bankDate;
		this.bankingRef = bankingRef;
		this.cash = cash;
		this.cheques = cheques;
		this.userId = userId;
		this.miscCash = miscCash;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.notes = notes;
		this.notes = manager;
		this.deleteComment = deleteComment;
		this.markDelete = markDelete;
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
	@Column(name = "bank_date")
	public Date getBankDate() {
		return bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}
	@Column(name = "banking_ref",length=30)
	public String getBankingRef() {
		return bankingRef;
	}

	public void setBankingRef(String bankingRef) {
		this.bankingRef = bankingRef;
	}
	@Column(name = "cash")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}
	@Column(name = "cheques")
	public double getCheques() {
		return cheques;
	}

	public void setCheques(double cheques) {
		this.cheques = cheques;
	}
	@Column(name = "user_id",length = 30)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "misc_cash")
	public double getMiscCash() {
		return miscCash;
	}

	public void setMiscCash(double miscCash) {
		this.miscCash = miscCash;
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

	@Column(name = "updated_date")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	@Column(name = "notes", length = 100)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Column(name = "manager", length = 50)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	@Column(name = "delete_comment", length = 75)
	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
	}
	@Column(name = "mark_delete")
	public boolean getMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

}
