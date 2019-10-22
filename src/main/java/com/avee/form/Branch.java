package com.avee.form;

@SuppressWarnings("serial")
public class Branch implements java.io.Serializable  {

	private int id;
	private String branchName;
	private String branchManager;
	private String managerMobile;
	private String address;
	private String managerTelephone;
	private String Email;
	private int cdMonthlyTarget;
	private int noOfTrillsAtBranch;
	private String branchFax;
	
	public Branch() {
		super();
	}

	public Branch(int id, String branchName, String branchManager, String managerMobile, String address,
			String managerTelephone, String email,int cdMonthlyTarget,int noOfTrillsAtBranch,String branchFax) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.branchManager = branchManager;
		this.managerMobile = managerMobile;
		this.address = address;
		this.managerTelephone = managerTelephone;
		this.Email = email;
		this.cdMonthlyTarget = cdMonthlyTarget;
		this.noOfTrillsAtBranch = noOfTrillsAtBranch;
		this.branchFax = branchFax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManagerTelephone() {
		return managerTelephone;
	}

	public void setManagerTelephone(String managerTelephone) {
		this.managerTelephone = managerTelephone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public int getCdMonthlyTarget() {
		return cdMonthlyTarget;
	}

	public void setCdMonthlyTarget(int cdMonthlyTarget) {
		this.cdMonthlyTarget = cdMonthlyTarget;
	}

	public int getNoOfTrillsAtBranch() {
		return noOfTrillsAtBranch;
	}

	public void setNoOfTrillsAtBranch(int noOfTrillsAtBranch) {
		this.noOfTrillsAtBranch = noOfTrillsAtBranch;
	}

	public String getBranchFax() {
		return branchFax;
	}

	public void setBranchFax(String branchFax) {
		this.branchFax = branchFax;
	}


	
	
}
