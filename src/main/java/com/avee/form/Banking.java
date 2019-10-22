package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
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
	private int weekNo;
	private double totalBanking;

	public Banking() {
		super();
	}

	public Banking(int id, int branchId, Date bankDate, String bankingRef, double cash, double cheques, String userId,
			double miscCash, String createdBy, Date createdDt, String updatedBy, Date updatedDt, String notes,
			String manager, String deleteComment, boolean markDelete,int weekNo,double totalBanking) {
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
		this.weekNo=weekNo;
		this.totalBanking=totalBanking;
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

	public String getBankingRef() {
		return bankingRef.toUpperCase();
	}

	public void setBankingRef(String bankingRef) {
		this.bankingRef = bankingRef;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCheques() {
		return cheques;
	}

	public void setCheques(double cheques) {
		this.cheques = cheques;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getMiscCash() {
		return miscCash;
	}

	public void setMiscCash(double miscCash) {
		this.miscCash = miscCash;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
	}

	public boolean getMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	public int getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}

	public double getTotalBanking() {
		return totalBanking;
	}

	public void setTotalBanking(double totalBanking) {
		this.totalBanking = totalBanking;
	}
	
	
}
