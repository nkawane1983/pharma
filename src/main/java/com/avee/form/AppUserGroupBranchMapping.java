package com.avee.form;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsrDisplayName() {
		return usrDisplayName;
	}

	public void setUsrDisplayName(String usrDisplayName) {
		this.usrDisplayName = usrDisplayName;
	}

	public String getUsrFirstName() {
		return usrFirstName;
	}

	public void setUsrFirstName(String usrFirstName) {
		this.usrFirstName = usrFirstName;
	}

	public String getUsrMiddleName() {
		return usrMiddleName;
	}

	public void setUsrMiddleName(String usrMiddleName) {
		this.usrMiddleName = usrMiddleName;
	}

	public String getUsrLastName() {
		return usrLastName;
	}

	public void setUsrLastName(String usrLastName) {
		this.usrLastName = usrLastName;
	}

	public String getUsrDesignation() {
		return usrDesignation;
	}

	public void setUsrDesignation(String usrDesignation) {
		this.usrDesignation = usrDesignation;
	}

	public String getUsrMobile() {
		return usrMobile;
	}

	public void setUsrMobile(String usrMobile) {
		this.usrMobile = usrMobile;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public int getBrnachid() {
		return brnachid;
	}

	public void setBrnachid(int brnachid) {
		this.brnachid = brnachid;
	}

	public String getInternalBranchId() {
		return internalBranchId;
	}

	public void setInternalBranchId(String internalBranchId) {
		this.internalBranchId = internalBranchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddrline1() {
		return branchAddrline1;
	}

	public void setBranchAddrline1(String branchAddrline1) {
		this.branchAddrline1 = branchAddrline1;
	}

	public String getBranchTown() {
		return branchTown;
	}

	public void setBranchTown(String branchTown) {
		this.branchTown = branchTown;
	}

	public String getBranchPostcode() {
		return branchPostcode;
	}

	public void setBranchPostcode(String branchPostcode) {
		this.branchPostcode = branchPostcode;
	}

	public int getBranchCounty_code() {
		return branchCounty_code;
	}

	public void setBranchCounty_code(int branchCounty_code) {
		this.branchCounty_code = branchCounty_code;
	}

	public String getBranchTelephoneNo() {
		return branchTelephoneNo;
	}

	public void setBranchTelephoneNo(String branchTelephoneNo) {
		this.branchTelephoneNo = branchTelephoneNo;
	}

	public int getBranchCdMontlyTarget() {
		return branchCdMontlyTarget;
	}

	public void setBranchCdMontlyTarget(int branchCdMontlyTarget) {
		this.branchCdMontlyTarget = branchCdMontlyTarget;
	}

	public int getNoOfTills() {
		return noOfTills;
	}

	public void setNoOfTills(int noOfTills) {
		this.noOfTills = noOfTills;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupAddrLine1() {
		return groupAddrLine1;
	}

	public void setGroupAddrLine1(String groupAddrLine1) {
		this.groupAddrLine1 = groupAddrLine1;
	}

	public String getGroupTown() {
		return groupTown;
	}

	public void setGroupTown(String groupTown) {
		this.groupTown = groupTown;
	}

	public String getGroupPostcode() {
		return groupPostcode;
	}

	public void setGroupPostcode(String groupPostcode) {
		this.groupPostcode = groupPostcode;
	}

	public int getGroupCountryCode() {
		return groupCountryCode;
	}

	public void setGroupCountryCode(int groupCountryCode) {
		this.groupCountryCode = groupCountryCode;
	}

	public String getGroupTelephoneNo() {
		return groupTelephoneNo;
	}

	public void setGroupTelephoneNo(String groupTelephoneNo) {
		this.groupTelephoneNo = groupTelephoneNo;
	}

	public String getGroupFaxNo() {
		return groupFaxNo;
	}

	public void setGroupFaxNo(String groupFaxNo) {
		this.groupFaxNo = groupFaxNo;
	}

}
