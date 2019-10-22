package com.avee.form;



@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	private int rolId;
	private String rolName;
	private String rolDescription;
	private String rolIsactive;
	private String rolComment;
	private int rolParentId;
	
//	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Role() {
	}

	public Role(int rolId) {
		this.rolId = rolId;
	}

	public Role(int rolId, String rolName, String rolDescription, String rolIsactive, String rolComment) {
		this.rolId = rolId;
		this.rolName = rolName;
		this.rolDescription = rolDescription;
		this.rolIsactive = rolIsactive;
		this.rolComment = rolComment;
//		this.userRoles = userRoles;
	}

	public int getRolId() {
		return this.rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return this.rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public String getRolDescription() {
		return this.rolDescription;
	}
	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}
	
	public String getRolComment() {
		return this.rolComment;
	}
	public void setRolComment(String rolComment) {
		this.rolComment = rolComment;
	}

	public String getRolIsactive() {
		return this.rolIsactive;
	}
	public void setRolIsactive(String rolIsactive) {
		this.rolIsactive = rolIsactive;
	}

//	public Set<UserRole> getUserRoles() {
//		return this.userRoles;
//	}
//	public void setUserRoles(Set<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}

	public int getRolParentId() {
		return rolParentId;
	}
	public void setRolParentId(int rolParentId) {
		this.rolParentId = rolParentId;
	}
}
