package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avee.form.UserTypeRole;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public List<UserTypeRole> getUserTypeRoles() {
		return userTypeRoles;
	}
	public void setUserTypeRoles(List<UserTypeRole> userTypeRoles) {
		this.userTypeRoles = userTypeRoles;
	}
}
