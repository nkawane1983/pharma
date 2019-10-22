package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class DefaultValues implements java.io.Serializable {
	private int id;
	private int defaultId;
	private String defaultValue;
	private boolean markDelete;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;

	public DefaultValues() {

	}

	public DefaultValues(int defaultId, String defaultValue, boolean markDelete, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate) {
		this.defaultId = defaultId;
		this.defaultValue = defaultValue;
		this.markDelete = markDelete;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDefaultId() {
		return defaultId;
	}

	public void setDefaultId(int defaultId) {
		this.defaultId = defaultId;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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

}
