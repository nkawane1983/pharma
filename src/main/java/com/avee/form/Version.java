package com.avee.form;

import java.util.Date;

public class Version implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date versionDate;
	private int status;
	private String versionNo;
	private String versionYear;
	private String versionDesc;

	
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	public Version() {
	}
	public Version(int id, Date versionDate, int status, String versionNo, String versionYear, String versionDesc,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		this.id = id;
		this.versionDate = versionDate;
		this.status = status;
		this.versionNo = versionNo;
		this.versionYear = versionYear;
		this.versionDesc = versionDesc;
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
	public Date getVersionDate() {
		return versionDate;
	}
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getVersionYear() {
		return versionYear;
	}
	public void setVersionYear(String versionYear) {
		this.versionYear = versionYear;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
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
	@Override
	public String toString() {
		return "Version [id=" + id + ", versionDate=" + versionDate + ", status=" + status + ", versionNo=" + versionNo
				+ ", versionYear=" + versionYear + ", versionDesc=" + versionDesc + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", updatedBy=" + updatedBy + ", updatedDt=" + updatedDt + "]";
	}

	
	
}
