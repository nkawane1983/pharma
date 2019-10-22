package com.avee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_users_group_branch")
@SuppressWarnings("serial")
public class AppUserGroupBranchMapping implements java.io.Serializable {
	private int id;
	private String uId;
	private String userId;
	private String usrDisplayName;
	private String usrFirstName;
	private String usrMiddleName;
	private String usrLastName;
	private String usrDesignation;
	private String usrMobile;
	private String usrEmail;
	private int brnachid;
	private String internalBranchId;
	private String branchName;
	private String branchAddrline1;
	private String branchTown;
	private String branchPostcode;
	private int branchCounty_code;
	private String branchTelephoneNo;
	private int branchCdMontlyTarget;
	private int noOfTills;
	private int groupId;
	private String groupName;
	private String groupAddrLine1;
	private String groupTown;
	private String groupPostcode;
	private int groupCountryCode;
	private String groupTelephoneNo;
	private String groupFaxNo;

	public AppUserGroupBranchMapping() {
		super();
	}

	public AppUserGroupBranchMapping(String uId, String userId, String usrDisplayName, String usrFirstName,
			String usrMiddleName, String usrLastName, String usrDesignation, String usrMobile, int brnachid,
			String internalBranchId, String branchName, String branchAddrline1, String branchTown,
			String branchPostcode, int branchCounty_code, String branchTelephoneNo, int branchCdMontlyTarget,
			int groupId, String groupName, String groupAddrLine1, String groupTown, String groupPostcode,
			int groupCountryCode, String groupTelephoneNo, String groupFaxNo, int id, String usrEmail, int noOfTills) {
		super();
		this.id = id;
		this.uId = uId;
		this.userId = userId;
		this.usrDisplayName = usrDisplayName;
		this.usrFirstName = usrFirstName;
		this.usrMiddleName = usrMiddleName;
		this.usrLastName = usrLastName;
		this.usrDesignation = usrDesignation;
		this.usrMobile = usrMobile;
		this.usrEmail = usrEmail;
		this.brnachid = brnachid;
		this.internalBranchId = internalBranchId;
		this.branchName = branchName;
		this.branchAddrline1 = branchAddrline1;
		this.branchTown = branchTown;
		this.branchPostcode = branchPostcode;
		this.branchCounty_code = branchCounty_code;
		this.branchTelephoneNo = branchTelephoneNo;
		this.branchCdMontlyTarget = branchCdMontlyTarget;
		this.noOfTills = noOfTills;
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupAddrLine1 = groupAddrLine1;
		this.groupTown = groupTown;
		this.groupPostcode = groupPostcode;
		this.groupCountryCode = groupCountryCode;
		this.groupTelephoneNo = groupTelephoneNo;
		this.groupFaxNo = groupFaxNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "uid", length = 30)
	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	@Column(name = "usr_id", length = 30)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "usr_display_name", length = 255)
	public String getUsrDisplayName() {
		return usrDisplayName;
	}

	public void setUsrDisplayName(String usrDisplayName) {
		this.usrDisplayName = usrDisplayName;
	}

	@Column(name = "usr_first_name", length = 50)
	public String getUsrFirstName() {
		return usrFirstName;
	}

	public void setUsrFirstName(String usrFirstName) {
		this.usrFirstName = usrFirstName;
	}

	@Column(name = "usr_middle_name", length = 50)
	public String getUsrMiddleName() {
		return usrMiddleName;
	}

	public void setUsrMiddleName(String usrMiddleName) {
		this.usrMiddleName = usrMiddleName;
	}

	@Column(name = "usr_last_name", length = 50)
	public String getUsrLastName() {
		return usrLastName;
	}

	public void setUsrLastName(String usrLastName) {
		this.usrLastName = usrLastName;
	}

	@Column(name = "usr_designation", length = 100)
	public String getUsrDesignation() {
		return usrDesignation;
	}

	public void setUsrDesignation(String usrDesignation) {
		this.usrDesignation = usrDesignation;
	}

	@Column(name = "usr_mobile", length = 30)
	public String getUsrMobile() {
		return usrMobile;
	}

	public void setUsrMobile(String usrMobile) {
		this.usrMobile = usrMobile;
	}

	@Column(name = "usr_email", length = 30)
	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	@Column(name = "branch_id")
	public int getBrnachid() {
		return brnachid;
	}

	public void setBrnachid(int brnachid) {
		this.brnachid = brnachid;
	}

	@Column(name = "internal_branch_id", length = 30)
	public String getInternalBranchId() {
		return internalBranchId;
	}

	public void setInternalBranchId(String internalBranchId) {
		this.internalBranchId = internalBranchId;
	}

	@Column(name = "branch_name", length = 100)
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "branch_addr_line1", length = 50)
	public String getBranchAddrline1() {
		return branchAddrline1;
	}

	public void setBranchAddrline1(String branchAddrline1) {
		this.branchAddrline1 = branchAddrline1;
	}

	@Column(name = "branch_town", length = 50)
	public String getBranchTown() {
		return branchTown;
	}

	public void setBranchTown(String branchTown) {
		this.branchTown = branchTown;
	}

	@Column(name = "branch_postcode", length = 20)
	public String getBranchPostcode() {
		return branchPostcode;
	}

	public void setBranchPostcode(String branchPostcode) {
		this.branchPostcode = branchPostcode;
	}

	@Column(name = "branch_county_name")
	public int getBranchCounty_code() {
		return branchCounty_code;
	}

	public void setBranchCounty_code(int branchCounty_code) {
		this.branchCounty_code = branchCounty_code;
	}

	@Column(name = "branch_telephone_no", length = 50)
	public String getBranchTelephoneNo() {
		return branchTelephoneNo;
	}

	public void setBranchTelephoneNo(String branchTelephoneNo) {
		this.branchTelephoneNo = branchTelephoneNo;
	}

	@Column(name = "cd_montly_target")
	public int getBranchCdMontlyTarget() {
		return branchCdMontlyTarget;
	}

	public void setBranchCdMontlyTarget(int branchCdMontlyTarget) {
		this.branchCdMontlyTarget = branchCdMontlyTarget;
	}

	@Column(name = "no_of_tills")
	public int getNoOfTills() {
		return noOfTills;
	}

	public void setNoOfTills(int noOfTills) {
		this.noOfTills = noOfTills;
	}

	@Column(name = "group_id")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name", length = 100)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_addr_line1", length = 50)
	public String getGroupAddrLine1() {
		return groupAddrLine1;
	}

	public void setGroupAddrLine1(String groupAddrLine1) {
		this.groupAddrLine1 = groupAddrLine1;
	}

	@Column(name = "group_town", length = 50)
	public String getGroupTown() {
		return groupTown;
	}

	public void setGroupTown(String groupTown) {
		this.groupTown = groupTown;
	}

	@Column(name = "group_postcode", length = 20)
	public String getGroupPostcode() {
		return groupPostcode;
	}

	public void setGroupPostcode(String groupPostcode) {
		this.groupPostcode = groupPostcode;
	}

	@Column(name = "group_county_id")
	public int getGroupCountryCode() {
		return groupCountryCode;
	}

	public void setGroupCountryCode(int groupCountryCode) {
		this.groupCountryCode = groupCountryCode;
	}

	@Column(name = "group_telephone_no", length = 20)
	public String getGroupTelephoneNo() {
		return groupTelephoneNo;
	}

	public void setGroupTelephoneNo(String groupTelephoneNo) {
		this.groupTelephoneNo = groupTelephoneNo;
	}

	@Column(name = "group_fax_no", length = 20)
	public String getGroupFaxNo() {
		return groupFaxNo;
	}

	public void setGroupFaxNo(String groupFaxNo) {
		this.groupFaxNo = groupFaxNo;
	}

}
