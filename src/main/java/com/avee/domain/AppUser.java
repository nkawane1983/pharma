package com.avee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
///import com.avee.form.BranchUserAssocs;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_user",uniqueConstraints = @UniqueConstraint(columnNames = "USR_NAME") )
public class AppUser implements java.io.Serializable {

	private String usrId;
	private String usrName;
	private String usrPasswd;
	private String usrDisplayName;
	private String usrFirstName;
	private String usrMiddleName;
	private String usrLastName;
	private String usrTelephone;
	private String usrMobile;
	private String usrFax;
	private String usrEmail;
	private String usrIsactiveYn;
	private String usrEmployeeYn;
	private String usrLockYn;
	private Date usrExpiryDt;
	private String usrType;
	private Date usrLastLogin;
	private String usrPasswdHistory;
	private Date usrPasswdExpirationDt;
	private String usrDesignation;
	private String usrDepartmentCd;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	
	private List<BranchUserAssocs> branchId = new ArrayList<BranchUserAssocs>();
	private List<GroupUserAssocs> groupId = new ArrayList<GroupUserAssocs>();
//	private List<UserRole> userRoles = new ArrayList<UserRole>();
//	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public AppUser() {
	}

	public AppUser(String usrId, String usrName, String usrPasswd) {
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrPasswd = usrPasswd;
	}

	public AppUser(String usrId, String usrName, String usrPasswd, String usrDisplayName, String usrFirstName,
			String usrMiddleName, String usrLastName, String usrTelephone, String usrMobile, String usrFax,
			String usrEmail, String usrIsactiveYn, String usrEmployeeYn, String usrLockYn,
			Date usrExpiryDt, String usrType, Date usrLastLogin, String usrPasswdHistory,
			Date usrPasswdExpirationDt, String usrDesignation, String usrDepartmentCd,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrPasswd = usrPasswd;
		this.usrDisplayName = usrDisplayName;
		this.usrFirstName = usrFirstName;
		this.usrMiddleName = usrMiddleName;
		this.usrLastName = usrLastName;
		this.usrTelephone = usrTelephone;
		this.usrMobile = usrMobile;
		this.usrFax = usrFax;
		this.usrEmail = usrEmail;
		this.usrIsactiveYn = usrIsactiveYn;
		this.usrEmployeeYn = usrEmployeeYn;
		this.usrLockYn = usrLockYn;
		this.usrExpiryDt = usrExpiryDt;
		this.usrType = usrType;
		this.usrLastLogin = usrLastLogin;
		this.usrPasswdHistory = usrPasswdHistory;
		this.usrPasswdExpirationDt = usrPasswdExpirationDt;
		this.usrDesignation = usrDesignation;
		this.usrDepartmentCd = usrDepartmentCd;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
//		this.userRoles = userRoles;
	}

	@Id
	@Column(name = "USR_ID", unique = true, nullable = false, length = 30)
	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	@Column(name = "USR_NAME", unique = true, nullable = false, length = 30)
	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	@Column(name = "USR_PASSWD", nullable = false)
	public String getUsrPasswd() {
		return this.usrPasswd;
	}

	public void setUsrPasswd(String usrPasswd) {
		this.usrPasswd = usrPasswd;
	}

	@Column(name = "USR_DISPLAY_NAME")
	public String getUsrDisplayName() {
		return this.usrDisplayName;
	}

	public void setUsrDisplayName(String usrDisplayName) {
		this.usrDisplayName = usrDisplayName;
	}

	@Column(name = "USR_FIRST_NAME", length = 50)
	public String getUsrFirstName() {
		return this.usrFirstName;
	}

	public void setUsrFirstName(String usrFirstName) {
		this.usrFirstName = usrFirstName;
	}

	@Column(name = "USR_MIDDLE_NAME", length = 50)
	public String getUsrMiddleName() {
		return this.usrMiddleName;
	}

	public void setUsrMiddleName(String usrMiddleName) {
		this.usrMiddleName = usrMiddleName;
	}

	@Column(name = "USR_LAST_NAME", length = 50)
	public String getUsrLastName() {
		return this.usrLastName;
	}

	public void setUsrLastName(String usrLastName) {
		this.usrLastName = usrLastName;
	}

	@Column(name = "USR_TELEPHONE", length = 30)
	public String getUsrTelephone() {
		return this.usrTelephone;
	}

	public void setUsrTelephone(String usrTelephone) {
		this.usrTelephone = usrTelephone;
	}

	@Column(name = "USR_MOBILE", length = 30)
	public String getUsrMobile() {
		return this.usrMobile;
	}

	public void setUsrMobile(String usrMobile) {
		this.usrMobile = usrMobile;
	}

	@Column(name = "USR_FAX", length = 30)
	public String getUsrFax() {
		return this.usrFax;
	}

	public void setUsrFax(String usrFax) {
		this.usrFax = usrFax;
	}

	@Column(name = "USR_EMAIL", length = 30)
	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	@Column(name = "USR_ISACTIVE_YN", length = 1)
	public String getUsrIsactiveYn() {
		return this.usrIsactiveYn;
	}

	public void setUsrIsactiveYn(String usrIsactiveYn) {
		this.usrIsactiveYn = usrIsactiveYn;
	}

	@Column(name = "USR_EMPLOYEE_YN", length = 1)
	public String getUsrEmployeeYn() {
		return this.usrEmployeeYn;
	}

	public void setUsrEmployeeYn(String usrEmployeeYn) {
		this.usrEmployeeYn = usrEmployeeYn;
	}


	@Column(name = "USR_LOCK_YN", length = 1)
	public String getUsrLockYn() {
		return this.usrLockYn;
	}

	public void setUsrLockYn(String usrLockYn) {
		this.usrLockYn = usrLockYn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USR_EXPIRY_DT", length = 7)
	public Date getUsrExpiryDt() {
		return this.usrExpiryDt;
	}

	public void setUsrExpiryDt(Date usrExpiryDt) {
		this.usrExpiryDt = usrExpiryDt;
	}

	@Column(name = "USR_TYPE", length = 20)
	public String getUsrType() {
		return this.usrType;
	}

	public void setUsrType(String usrType) {
		this.usrType = usrType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USR_LAST_LOGIN", length = 7)
	public Date getUsrLastLogin() {
		return this.usrLastLogin;
	}

	public void setUsrLastLogin(Date usrLastLogin) {
		this.usrLastLogin = usrLastLogin;
	}

	@Column(name = "USR_PASSWD_HISTORY", length = 2000)
	public String getUsrPasswdHistory() {
		return this.usrPasswdHistory;
	}

	public void setUsrPasswdHistory(String usrPasswdHistory) {
		this.usrPasswdHistory = usrPasswdHistory;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USR_PASSWD_EXPIRATION_DT", length = 7)
	public Date getUsrPasswdExpirationDt() {
		return this.usrPasswdExpirationDt;
	}

	public void setUsrPasswdExpirationDt(Date usrPasswdExpirationDt) {
		this.usrPasswdExpirationDt = usrPasswdExpirationDt;
	}


	@Column(name = "USR_DESIGNATION", length = 100)
	public String getUsrDesignation() {
		return this.usrDesignation;
	}

	public void setUsrDesignation(String usrDesignation) {
		this.usrDesignation = usrDesignation;
	}

	@Column(name = "USR_DEPARTMENT_CD", length = 15)
	public String getUsrDepartmentCd() {
		return this.usrDepartmentCd;
	}

	public void setUsrDepartmentCd(String usrDepartmentCd) {
		this.usrDepartmentCd = usrDepartmentCd;
	}

	@Column(name = "CREATED_BY", length = 30,updatable=false)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", length = 7 , updatable=false)
	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "UPDATED_BY", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DT", length = 7)
	public Date getUpdatedDt() {
		return this.updatedDt;
	}
	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@OneToMany(mappedBy = "appUser")
	public List<BranchUserAssocs> getBranchId() {
		return branchId;
	}

	public void setBranchId(List<BranchUserAssocs> branchId) {
		this.branchId = branchId;
	}
	@OneToMany(mappedBy = "appUser")
	public List<GroupUserAssocs> getGroupId() {
		return groupId;
	}

	public void setGroupId(List<GroupUserAssocs> groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "AppUser [usrId=" + usrId + ", usrName=" + usrName + ", usrPasswd=" + usrPasswd + ", usrDisplayName="
				+ usrDisplayName + ", usrFirstName=" + usrFirstName + ", usrMiddleName=" + usrMiddleName
				+ ", usrLastName=" + usrLastName + ", usrTelephone=" + usrTelephone + ", usrMobile=" + usrMobile
				+ ", usrFax=" + usrFax + ", usrEmail=" + usrEmail + ", usrIsactiveYn=" + usrIsactiveYn
				+ ", usrEmployeeYn=" + usrEmployeeYn + ", usrLockYn="
				+ usrLockYn + ", usrExpiryDt=" + usrExpiryDt + ", usrType=" + usrType
				+ ", usrLastLogin=" + usrLastLogin + ", usrPasswdHistory=" + usrPasswdHistory
				+ ", usrPasswdExpirationDt=" + usrPasswdExpirationDt 
				+ ", usrDesignation=" + usrDesignation + ", usrDepartmentCd=" + usrDepartmentCd + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", updatedBy=" + updatedBy + ", updatedDt=" + updatedDt
				+ ", userRoles="+ "]";
	}

	
	
	
	
}
