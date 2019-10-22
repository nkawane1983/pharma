package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class CouponReport implements java.io.Serializable {
	private int id;
	private double weekday;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private List<ListStringAndDouble> listCoupon = new ArrayList<>();
	private double branchTotal;

	public CouponReport() {
		super();
	}

	public CouponReport(int id, double weekday, Date edate, int branchid, String branchInternalId, String branchName,
			List<ListStringAndDouble> listCoupon, double branchTotal) {
		super();
		this.id = id;
		this.weekday = weekday;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.listCoupon = listCoupon;
		this.branchTotal = branchTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeekday() {
		return weekday;
	}

	public void setWeekday(double weekday) {
		this.weekday = weekday;
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

	public List<ListStringAndDouble> getListCoupon() {
		return listCoupon;
	}

	public void setListCoupon(List<ListStringAndDouble> listCoupon) {
		this.listCoupon = listCoupon;
	}

	public double getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(double branchTotal) {
		this.branchTotal = branchTotal;
	}

}
