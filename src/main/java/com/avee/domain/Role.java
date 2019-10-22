package com.avee.domain;
// Generated Dec 10, 2015 10:59:22 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

	private int rolId;
	private String rolName;
	private String rolDescription;
	private String rolIsactive;
	private int rolParentId;

	public Role() {
	}

	public Role(int rolId) {
		this.rolId = rolId;
	}

	public Role(int rolId, String rolName, String rolDescription, String rolIsactive) {
		this.rolId = rolId;
		this.rolName = rolName;
		this.rolDescription = rolDescription;
		this.rolIsactive = rolIsactive;
	}

	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="role_rol_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ROL_ID",unique = true, nullable = false)
	public int getRolId() {
		return this.rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	@Column(name = "ROL_NAME", length = 30)
	public String getRolName() {
		return this.rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	@Column(name = "ROL_DESCRIPTION", length = 100)
	public String getRolDescription() {
		return this.rolDescription;
	}

	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}

	@Column(name = "ROL_ISACTIVE", length = 1)
	public String getRolIsactive() {
		return this.rolIsactive;
	}

	public void setRolIsactive(String rolIsactive) {
		this.rolIsactive = rolIsactive;
	}

	@Column(name = "ROL_PARENT_ID")
	public int getRolParentId() {
		return rolParentId;
	}
	public void setRolParentId(int rolParentId) {
		this.rolParentId = rolParentId;
	}
}