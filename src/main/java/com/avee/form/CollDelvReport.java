package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class CollDelvReport implements java.io.Serializable {
	private int id;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private int totalColl;
	private int totaldelv;
	private int total;

	public CollDelvReport() {
		super();
	}

	public CollDelvReport(int id, Date edate, int branchid, String branchInternalId, String branchName, int totalColl,
			int totaldelv, int total) {
		super();
		this.id = id;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.totalColl = totalColl;
		this.totaldelv = totaldelv;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public String getBranchInternalId() {
		return branchInternalId;
	}

	public void setBranchInternalId(String branchInternalId) {
		this.branchInternalId = branchInternalId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getTotalColl() {
		return totalColl;
	}

	public void setTotalColl(int totalColl) {
		this.totalColl = totalColl;
	}

	public int getTotaldelv() {
		return totaldelv;
	}

	public void setTotaldelv(int totaldelv) {
		this.totaldelv = totaldelv;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
