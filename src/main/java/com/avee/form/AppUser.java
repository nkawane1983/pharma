package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.avee.domain.GroupUserAssocs;

@SuppressWarnings("serial")
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
	private String usrLevel;
	private Date usrLastLogin;
	private String usrPasswdHistory;
	private Date usrPasswdExpirationDt;
	private MultipartFile usrSignature;
	private String usrDesignation;
	private String usrDepartmentCd;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	private String gid;
	private String bid;
	// private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	// private byte bytes[];
	private List<BranchUserAssocs> branchId = new ArrayList<BranchUserAssocs>();
	private List<GroupUserAssocs> groupId = new ArrayList<GroupUserAssocs>();

	public AppUser() {
	}

	public AppUser(String usrId, String usrName, String usrPasswd) {
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrPasswd = usrPasswd;
	}

	public AppUser(String usrId, String usrName, String usrPasswd, String usrDisplayName, String usrFirstName,
			String usrMiddleName, String usrLastName, String usrTelephone, String usrMobile, String usrFax,
			String usrEmail, String usrIsactiveYn, String usrEmployeeYn, String usrLockYn, Date usrExpiryDt,
			String usrType, String usrLevel, Date usrLastLogin, String usrPasswdHistory, Date usrPasswdExpirationDt,
			MultipartFile usrSignature, String usrDesignation, String usrDepartmentCd, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt, String gid, String bid, List<BranchUserAssocs> branchId,
			List<GroupUserAssocs> groupId) {
		super();
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
		this.usrLevel = usrLevel;
		this.usrLastLogin = usrLastLogin;
		this.usrPasswdHistory = usrPasswdHistory;
		this.usrPasswdExpirationDt = usrPasswdExpirationDt;
		this.usrSignature = usrSignature;
		this.usrDesignation = usrDesignation;
		this.usrDepartmentCd = usrDepartmentCd;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.gid = gid;
		this.bid = bid;
		this.branchId = branchId;
		this.groupId = groupId;
	}

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPasswd() {
		return this.usrPasswd;
	}

	public void setUsrPasswd(String usrPasswd) {
		this.usrPasswd = usrPasswd;
	}

	public String getUsrDisplayName() {
		return this.usrDisplayName;
	}

	public void setUsrDisplayName(String usrDisplayName) {
		this.usrDisplayName = usrDisplayName;
	}

	public String getUsrFirstName() {
		return this.usrFirstName;
	}

	public void setUsrFirstName(String usrFirstName) {
		this.usrFirstName = usrFirstName;
	}

	public String getUsrMiddleName() {
		return this.usrMiddleName;
	}

	public void setUsrMiddleName(String usrMiddleName) {
		this.usrMiddleName = usrMiddleName;
	}

	public String getUsrLastName() {
		return this.usrLastName;
	}

	public void setUsrLastName(String usrLastName) {
		this.usrLastName = usrLastName;
	}

	public String getUsrTelephone() {
		return this.usrTelephone;
	}

	public void setUsrTelephone(String usrTelephone) {
		this.usrTelephone = usrTelephone;
	}

	public String getUsrMobile() {
		return this.usrMobile;
	}

	public void setUsrMobile(String usrMobile) {
		this.usrMobile = usrMobile;
	}

	public String getUsrFax() {
		return this.usrFax;
	}

	public void setUsrFax(String usrFax) {
		this.usrFax = usrFax;
	}

	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrIsactiveYn() {
		return this.usrIsactiveYn;
	}

	public void setUsrIsactiveYn(String usrIsactiveYn) {
		this.usrIsactiveYn = usrIsactiveYn;
	}

	public String getUsrEmployeeYn() {
		return this.usrEmployeeYn;
	}

	public void setUsrEmployeeYn(String usrEmployeeYn) {
		this.usrEmployeeYn = usrEmployeeYn;
	}

	public String getUsrLockYn() {
		return this.usrLockYn;
	}

	public void setUsrLockYn(String usrLockYn) {
		this.usrLockYn = usrLockYn;
	}

	public Date getUsrExpiryDt() {
		return this.usrExpiryDt;
	}

	public void setUsrExpiryDt(Date usrExpiryDt) {
		this.usrExpiryDt = usrExpiryDt;
	}

	public String getUsrType() {
		return this.usrType;
	}

	public void setUsrType(String usrType) {
		this.usrType = usrType;
	}

	public String getUsrLevel() {
		return this.usrLevel;
	}

	public void setUsrLevel(String usrLevel) {
		this.usrLevel = usrLevel;
	}

	public Date getUsrLastLogin() {
		return this.usrLastLogin;
	}

	public void setUsrLastLogin(Date usrLastLogin) {
		this.usrLastLogin = usrLastLogin;
	}

	public String getUsrPasswdHistory() {
		return this.usrPasswdHistory;
	}

	public void setUsrPasswdHistory(String usrPasswdHistory) {
		this.usrPasswdHistory = usrPasswdHistory;
	}

	public Date getUsrPasswdExpirationDt() {
		return this.usrPasswdExpirationDt;
	}

	public void setUsrPasswdExpirationDt(Date usrPasswdExpirationDt) {
		this.usrPasswdExpirationDt = usrPasswdExpirationDt;
	}

	public MultipartFile getUsrSignature() {
		return this.usrSignature;
	}

	public void setUsrSignature(MultipartFile usrSignature) {
		this.usrSignature = usrSignature;
	}

	public String getUsrDesignation() {
		return this.usrDesignation;
	}

	public void setUsrDesignation(String usrDesignation) {
		this.usrDesignation = usrDesignation;
	}

	public String getUsrDepartmentCd() {
		return this.usrDepartmentCd;
	}

	public void setUsrDepartmentCd(String usrDepartmentCd) {
		this.usrDepartmentCd = usrDepartmentCd;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public List<BranchUserAssocs> getBranchId() {
		return branchId;
	}

	public void setBranchId(List<BranchUserAssocs> branchId) {
		this.branchId = branchId;
	}

	public List<GroupUserAssocs> getGroupId() {
		return groupId;
	}

	public void setGroupId(List<GroupUserAssocs> groupId) {
		this.groupId = groupId;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	// public Set<UserRole> getUserRoles() {
	// return userRoles;
	// }
	// public void setUserRoles(Set<UserRole> userRoles) {
	// this.userRoles = userRoles;
	// }

	// public byte[] getBytes() {
	// return bytes;
	// }
	// public void setBytes(byte[] bytes) {
	// this.bytes = bytes;
	// }
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
