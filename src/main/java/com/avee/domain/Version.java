package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pharma_version")
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

	@Id
	// @SequenceGenerator(name="my_seq",
	// sequenceName="system_parameter_id_seq",allocationSize=1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "version_date", length = 7)
	public Date getVersionDate() {
		return versionDate;
	}
		
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name = "version_no", length = 30)
	public String getVersionNo() {
		return versionNo;
	}

	
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	@Column(name = "version_year", length = 50)
	public String getVersionYear() {
		return versionYear;
	}

	
	public void setVersionYear(String versionYear) {
		this.versionYear = versionYear;
	}

	@Column(name = "version_desc", length = 250)
	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	@Column(name = "created_by", length = 30, updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 7, updatable = false)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 7)
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
