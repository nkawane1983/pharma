package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class NhsItems implements java.io.Serializable {
	private int id;
	private int branchId;
	private Date eventDate;
	private int codeId;
	private int patients;
	private int form;
	private int items;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public NhsItems() {
		super();
	}

	public NhsItems(int id, int branchId, Date eventDate, int codeId, int patients, int form, int items,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.eventDate = eventDate;
		this.codeId = codeId;
		this.patients = patients;
		this.form = form;
		this.items = items;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public int getPatients() {
		return patients;
	}

	public void setPatients(int patients) {
		this.patients = patients;
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
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
