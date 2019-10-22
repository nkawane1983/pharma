package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "amendmentTilHO")
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
	@Id
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
	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	@Column(name = "cash_id")
	public int getCashId() {
		return cashId;
	}

	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	@Column(name = "user_id",length=50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "comments_by_HO")
	public String getCommentsByHO() {
		return commentsByHO;
	}

	public void setCommentsByHO(String commentsByHO) {
		this.commentsByHO = commentsByHO;
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

	
	
}
