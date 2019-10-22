package com.avee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_type")
public class UserType {
	
	private int id;
	private String name;
	private String description;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private List<UserTypeRole> userTypeRoles = new ArrayList<>();
	
	public UserType() {
	}

	public UserType(int id, String name, String description, String createdBy, Date createdDt, String updatedBy,
			Date updatedDt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}
	
	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="user_type_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id",nullable = false,unique = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description", length = 100)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public List<UserTypeRole> getUserTypeRoles() {
		return userTypeRoles;
	}
	public void setUserTypeRoles(List<UserTypeRole> userTypeRoles) {
		this.userTypeRoles = userTypeRoles;
	}
	
}
