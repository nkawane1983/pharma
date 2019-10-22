package com.avee.form;

import java.util.Date;
public class GroupUserAssocs {

	private int id;
	private Date startDate;
	private Date endDate;
	private int workingDay;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private AppUser appUser;
	private GroupDetails groupDetails;
	public GroupUserAssocs() {
		super();
	}

	public GroupUserAssocs(int id,Date startDate, Date endDate, int workingDay,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
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

	public GroupDetails getGroupDetails() {
		return groupDetails;
	}

	public void setGroupDetails(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
	}

	
}
