package com.avee.form;

import java.util.Date;

public class AmendmentTillHO implements java.io.Serializable {
	/** This class working only send request from branch  to H.O 
	 *  form AmendmentTill EPOST system data only
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int branchId;
	private Date actionDate;
	private int cashId;
	private String userId;

	private String commentsByHO;
	
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	
	public AmendmentTillHO() {
	}

	public AmendmentTillHO(int id, int branchId, Date actionDate, int cashId, String userId, String commentsByHO,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.actionDate = actionDate;
		this.cashId = cashId;
		this.userId = userId;
		this.commentsByHO = commentsByHO;
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

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public int getCashId() {
		return cashId;
	}

	public void setCashId(int cashId) {
		this.cashId = cashId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentsByHO() {
		return commentsByHO;
	}

	public void setCommentsByHO(String commentsByHO) {
		this.commentsByHO = commentsByHO;
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
