package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private String encodeId;
	private List<CollDelvSingup> collDelv=new ArrayList<CollDelvSingup>();
	public CollDelvSingup() {
		super();
	}

	public CollDelvSingup(int id, int branchId, Date eventDate, String userId, int delvItems,
			int collItems, String createdBy, Date createdDt, String updatedBy, Date updatedDt,String encodeId) {
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
		this.encodeId = encodeId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getDelvItems() {
		return delvItems;
	}

	public void setDelvItems(int delvItems) {
		this.delvItems = delvItems;
	}

	public int getCollItems() {
		return collItems;
	}

	public void setCollItems(int collItems) {
		this.collItems = collItems;
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

	public List<CollDelvSingup> getCollDelv() {
		return collDelv;
	}

	public void setCollDelv(List<CollDelvSingup> collDelv) {
		this.collDelv = collDelv;
	}

	public String getEncodeId() {
		return encodeId;
	}

	public void setEncodeId(String encodeId) {
		this.encodeId = encodeId;
	}

}
