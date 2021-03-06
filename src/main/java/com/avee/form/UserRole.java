package com.avee.form;

/**
 * UserRole generated by hbm2java
 */
@SuppressWarnings("serial")
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
	

	public String getUsrRolId() {
		return this.usrRolId;
	}

	public void setUsrRolId(String usrRolId) {
		this.usrRolId = usrRolId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}
