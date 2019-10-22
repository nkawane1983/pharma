package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class SearchForm implements java.io.Serializable {

	public int id;
	public Date startDate;
	public Date endDate;
	public String startDateStr;
	public String endDateStr;
	public int groupId;
	public int branchId;
	public String groupIdStr;
	public String branchIdStr;

	public SearchForm() {
		super();
	}

	public SearchForm(int id, Date startDate, Date endDate, String startDateStr, String endDateStr, int groupId,
			int branchId, String groupIdStr, String branchIdStr) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startDateStr = startDateStr;
		this.endDateStr = endDateStr;
		this.groupId = groupId;
		this.branchId = branchId;
		this.groupIdStr = groupIdStr;
		this.branchIdStr = branchIdStr;
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

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getGroupIdStr() {
		return groupIdStr;
	}

	public void setGroupIdStr(String groupIdStr) {
		this.groupIdStr = groupIdStr;
	}

	public String getBranchIdStr() {
		return branchIdStr;
	}

	public void setBranchIdStr(String branchIdStr) {
		this.branchIdStr = branchIdStr;
	}

	

}
