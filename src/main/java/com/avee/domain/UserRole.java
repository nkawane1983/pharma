package com.avee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_role")
public class UserRole implements java.io.Serializable {

	private String usrRolId;
	private Role role;
	private AppUser appUser;

	public UserRole() {
	}

	public UserRole(String usrRolId, Role role, AppUser appUser) {
		this.usrRolId = usrRolId;
		this.role = role;
		this.appUser = appUser;
	}
	

	@Id
	@Column(name = "USR_ROL_ID", unique = true, nullable = false, length = 60)
	public String getUsrRolId() {
		return this.usrRolId;
	}

	public void setUsrRolId(String usrRolId) {
		this.usrRolId = usrRolId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USR_ROL_ROLE_ID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USR_ROL_USER_ID", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
}
