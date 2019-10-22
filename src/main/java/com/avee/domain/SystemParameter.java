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


@SuppressWarnings("serial")
@Entity
@Table(name = "system_parameter")
public class SystemParameter implements java.io.Serializable{
	
	private int id;
	private String parameterName;
	private String parameterValue;
	private String description;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	
	public SystemParameter() {
	}

	public SystemParameter(int id, String parameterName, String parameterValue, String description, String createdBy,
			Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}
	
	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="system_parameter_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id",nullable = false,unique = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "parameter_name", length = 50, nullable = false)
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	@Column(name = "parameter_value", nullable = false)
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "created_by", length = 30 ,updatable=false)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 7,updatable=false)
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
