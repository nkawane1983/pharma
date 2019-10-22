package com.avee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.avee.form.BranchUserAssocs;
@SuppressWarnings("serial")
@Entity
@Table(name = "branch_details")
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
			String description,int groupId) {
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

	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "branch_details_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "internal_branch_id", length = 30)
	public String getInternalBranchId() {
		return internalBranchId;
	}

	public void setInternalBranchId(String internalBranchId) {
		this.internalBranchId = internalBranchId;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "group_id")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "addr_line1", length = 50)
	public String getAddrline1() {
		return addrline1;
	}

	public void setAddrline1(String addrline1) {
		this.addrline1 = addrline1;
	}

	@Column(name = "addr_line2", length = 50)
	public String getAddrline2() {
		return addrline2;
	}

	public void setAddrline2(String addrline2) {
		this.addrline2 = addrline2;
	}

	@Column(name = "town", length = 50)
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "postcode", length = 20)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "county_code")
	public int getCounty_code() {
		return county_code;
	}

	public void setCounty_code(int county_code) {
		this.county_code = county_code;
	}

	@Column(name = "telephone_no", length = 20)
	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	@Column(name = "fax_no", length = 20)
	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	@Column(name = "email", length = 20)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ims_number", length = 50)
	public String getImsNumber() {
		return imsNumber;
	}

	public void setImsNumber(String imsNumber) {
		this.imsNumber = imsNumber;
	}

	@Column(name = "cd_montly_target")
	public int getCdMontlyTarget() {
		return cdMontlyTarget;
	}

	public void setCdMontlyTarget(int cdMontlyTarget) {
		this.cdMontlyTarget = cdMontlyTarget;
	}

	@Column(name = "notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "period")
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Column(name = "no_of_tills")
	public int getNoOfTills() {
		return noOfTills;
	}

	public void setNoOfTills(int noOfTills) {
		this.noOfTills = noOfTills;
	}

	@Column(name = "working_day")
	public int getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(int workingDay) {
		this.workingDay = workingDay;
	}

	@Column(name = "is_active")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	@OneToMany(mappedBy = "branchdetails")
	public List<BranchUserAssocs> getUserId() {
		return userId;
	}

	public void setUserId(List<BranchUserAssocs> userId) {
		this.userId = userId;
	}

}
