package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class CareHome implements java.io.Serializable {

	private int id;
	private int branchId;
	private String description;
	private String addLine1;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public CareHome() {
		super();
	}

	public CareHome(int id, int branchId, String description, String addLine1, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.description = description;
		this.addLine1 = addLine1;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddLine1() {
		return addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
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
