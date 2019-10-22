package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhsitems")
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
	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "nhsitems_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
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
	@Column(name = "event_date")
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	@Column(name = "code_id")
	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	@Column(name = "patients")
	public int getPatients() {
		return patients;
	}

	public void setPatients(int patients) {
		this.patients = patients;
	}
	@Column(name = "form")
	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}
	@Column(name = "items")
	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
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

	@Column(name = "updated_date ")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}


}
