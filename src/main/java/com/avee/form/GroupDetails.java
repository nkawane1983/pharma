package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avee.domain.GroupUserAssocs;

@SuppressWarnings("serial")
public class GroupDetails implements java.io.Serializable {
	private int id;
	private String description;
	private String addrLine1;
	private String addrLin2;
	private String town;
	private String postcode;
	private int countryCode;
	private String telephoneNo;
	private String faxNo;
	private boolean IsActive;
	private String customField1;
	private String customField2;
	private String customField3;
	private String customField4;
	private String customField5;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private List<GroupUserAssocs> userId = new ArrayList<GroupUserAssocs>();

	public GroupDetails() {
		super();
	}

	public GroupDetails(int id, String description, String addrLine1, String addrLin2, String town, String postcode,
			int countryCode, String telephoneNo, String faxNo, boolean isActive, String customField1,
			String customField2, String customField3, String customField4, String customField5, String createdBy,
			Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.description = description;
		this.addrLine1 = addrLine1;
		this.addrLin2 = addrLin2;
		this.town = town;
		this.postcode = postcode;
		this.countryCode = countryCode;
		this.telephoneNo = telephoneNo;
		this.faxNo = faxNo;
		IsActive = isActive;
		this.customField1 = customField1;
		this.customField2 = customField2;
		this.customField3 = customField3;
		this.customField4 = customField4;
		this.customField5 = customField5;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLin2() {
		return addrLin2;
	}

	public void setAddrLin2(String addrLin2) {
		this.addrLin2 = addrLin2;
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

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
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

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	public String getCustomField1() {
		return customField1;
	}

	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	public String getCustomField2() {
		return customField2;
	}

	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	public String getCustomField3() {
		return customField3;
	}

	public void setCustomField3(String customField3) {
		this.customField3 = customField3;
	}

	public String getCustomField4() {
		return customField4;
	}

	public void setCustomField4(String customField4) {
		this.customField4 = customField4;
	}

	public String getCustomField5() {
		return customField5;
	}

	public void setCustomField5(String customField5) {
		this.customField5 = customField5;
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

	public List<GroupUserAssocs> getUserId() {
		return userId;
	}

	public void setUserId(List<GroupUserAssocs> userId) {
		this.userId = userId;
	}

}
