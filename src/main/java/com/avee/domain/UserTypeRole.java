package com.avee.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avee.domain.Role;
import com.avee.domain.UserType;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_type_role")
public class UserTypeRole implements java.io.Serializable {

	private Role role;
	private UserType userType;
	
	public UserTypeRole(){
	}
	
	public UserTypeRole(Role role, UserType userType) {
		this.role = role;
		this.userType = userType;
	}
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole(){
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_type_id", nullable = false)
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
