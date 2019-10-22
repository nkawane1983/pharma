package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coll_dev_singups")
@SuppressWarnings("serial")
public class CollDelvSingup implements java.io.Serializable {
	private int id;
	private int branchId;
	private Date eventDate;
	private String userId;
	private int delvItems;
	private int collItems;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public CollDelvSingup() {
		super();
	}

	public CollDelvSingup(int id, int branchId, Date eventDate, String userId, int delvItems, int collItems,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.eventDate = eventDate;
		this.userId = userId;
		this.delvItems = delvItems;
		this.collItems = collItems;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}

	@Id
//	@SequenceGenerator(name = "my_seq", sequenceName = "coll_dev_singups_id_seq", allocationSize = 1)
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

	@Column(name = "user_id", length = 10)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "delv_items")
	public int getDelvItems() {
		return delvItems;
	}

	public void setDelvItems(int delvItems) {
		this.delvItems = delvItems;
	}

	@Column(name = "coll_items")
	public int getCollItems() {
		return collItems;
	}

	public void setCollItems(int collItems) {
		this.collItems = collItems;
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
