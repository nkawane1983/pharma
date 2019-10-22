package com.avee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "branch")
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
	//private Set<BranchUserAssocs> userId = new HashSet<BranchUserAssocs>();
	
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
	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="branch_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID",unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "branch_name", length = 50)
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name = "branch_manager", length = 50)
	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}
	@Column(name = "branch_mobile", length = 30)
	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	@Column(name = "address", length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "branch_telephone", length = 30)
	public String getManagerTelephone() {
		return managerTelephone;
	}

	public void setManagerTelephone(String managerTelephone) {
		this.managerTelephone = managerTelephone;
	}
	@Column(name = "branch_email", length = 30)
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
	/*@OneToMany(mappedBy = "branch")
	public Set<BranchUserAssocs> getUserId() {
		return userId;
	}

	public void setUserId(Set<BranchUserAssocs> userId) {
		this.userId = userId;
	}*/

	


	
	
}
