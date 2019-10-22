package com.avee.form;

import java.util.Date;



@SuppressWarnings("serial")
public class BranchUserAssocs implements java.io.Serializable{

	private int id;
	private int branchId;
	private String userId;
	private Date startDate;
	private Date endDate;
	private int workingDay;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private AppUser appUser;
	private BranchDetails branchdetails;
	public BranchUserAssocs() {
		super();
	}

	public BranchUserAssocs(int id, int branchId, String userId, Date startDate, Date endDate, int workingDay,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workingDay = workingDay;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(int workingDay) {
		this.workingDay = workingDay;
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

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public BranchDetails getBranchdetails() {
		return branchdetails;
	}

	public void setBranchdetails(BranchDetails branchdetails) {
		this.branchdetails = branchdetails;
	}

}
