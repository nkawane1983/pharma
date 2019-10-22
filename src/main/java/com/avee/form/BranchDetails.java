package com.avee.form;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@SuppressWarnings("serial")
public class BranchDetails implements java.io.Serializable {
	private int id;
	private String internalBranchId;
	private String description;
	private int groupId;
	private String addrline1;
	private String addrline2;
	private String town;
	private String postcode;
	private int county_code;
	private String telephoneNo;
	private String faxNo;
	private String email;
	private String imsNumber;
	private int cdMontlyTarget;
	private String notes;
	private int period;
	private int noOfTills;
	private int workingDay;
	private boolean isActive;
	private Date startDate;
	private Date endDate;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private List<BranchUserAssocs> userId = new ArrayList<BranchUserAssocs>();

	public BranchDetails() {
		super();
	}

	public BranchDetails(int id, String internalBranchId, String addrline1, String addrline2, String town,
			String postcode, int county_code, String telephoneNo, String faxNo, String email, String imsNumber,
			int cdMontlyTarget, String notes, int period, int noOfTills, int workingDay, boolean isActive,
			Date startDate, Date endDate, String createdBy, Date createdDt, String updatedBy, Date updatedDt,
			String description, int groupId) {
		super();
		this.id = id;
		this.internalBranchId = internalBranchId;
		this.addrline1 = addrline1;
		this.addrline2 = addrline2;
		this.town = town;
		this.postcode = postcode;
		this.county_code = county_code;
		this.telephoneNo = telephoneNo;
		this.faxNo = faxNo;
		this.email = email;
		this.imsNumber = imsNumber;
		this.cdMontlyTarget = cdMontlyTarget;
		this.notes = notes;
		this.period = period;
		this.noOfTills = noOfTills;
		this.workingDay = workingDay;
		this.isActive = isActive;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.description = description;
		this.groupId = groupId;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInternalBranchId() {
		return internalBranchId;
	}

	public void setInternalBranchId(String internalBranchId) {
		this.internalBranchId = internalBranchId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getAddrline1() {
		return addrline1;
	}

	public void setAddrline1(String addrline1) {
		this.addrline1 = addrline1;
	}

	public String getAddrline2() {
		return addrline2;
	}

	public void setAddrline2(String addrline2) {
		this.addrline2 = addrline2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getCounty_code() {
		return county_code;
	}

	public void setCounty_code(int county_code) {
		this.county_code = county_code;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImsNumber() {
		return imsNumber;
	}

	public void setImsNumber(String imsNumber) {
		this.imsNumber = imsNumber;
	}

	public int getCdMontlyTarget() {
		return cdMontlyTarget;
	}

	public void setCdMontlyTarget(int cdMontlyTarget) {
		this.cdMontlyTarget = cdMontlyTarget;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getNoOfTills() {
		return noOfTills;
	}

	public void setNoOfTills(int noOfTills) {
		this.noOfTills = noOfTills;
	}

	public int getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(int workingDay) {
		this.workingDay = workingDay;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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

	public List<BranchUserAssocs> getUserId() {
		return userId;
	}

	public void setUserId(List<BranchUserAssocs> userId) {
		this.userId = userId;
	}

}
