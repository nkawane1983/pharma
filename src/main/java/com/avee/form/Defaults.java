package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class Defaults implements java.io.Serializable {

	private int id;
	private String defaultName;
	private String defaultFor;
	private boolean multiValue;
	private boolean markDelete;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private int defaultOrderNo;
	private String defaultDisplayName;

	public Defaults() {
		
	}

	public Defaults(String defaultName, String defaultFor, boolean multiValue, boolean markDelete, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate, int defaultOrderNo, String defaultDisplayName) {
		this.defaultName = defaultName;
		this.defaultFor = defaultFor;
		this.multiValue = multiValue;
		this.markDelete = markDelete;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.defaultOrderNo = defaultOrderNo;
		this.defaultDisplayName = defaultDisplayName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public String getDefaultFor() {
		return defaultFor;
	}

	public void setDefaultFor(String defaultFor) {
		this.defaultFor = defaultFor;
	}

	public boolean isMultiValue() {
		return multiValue;
	}

	public void setMultiValue(boolean multiValue) {
		this.multiValue = multiValue;
	}

	public boolean isMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDefaultOrderNo() {
		return defaultOrderNo;
	}

	public void setDefaultOrderNo(int defaultOrderNo) {
		this.defaultOrderNo = defaultOrderNo;
	}

	public String getDefaultDisplayName() {
		return defaultDisplayName;
	}

	public void setDefaultDisplayName(String defaultDisplayName) {
		this.defaultDisplayName = defaultDisplayName;
	}
}
