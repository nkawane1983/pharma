package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class NhsReport implements java.io.Serializable {
	private int id;
	private double weekday;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private int totalform;
	private int totalitems;
	private int paidform;
	private int paiditems;
	private int nocharge;
	private int exeform;
	private int exeitems;
	private int otheritems;
	private double othervalue;
	private double othervat;
	private int privateitems;
	private double privatevalue;
	private double nhslevy;
	private int nhsid;

	public NhsReport() {
		super();
	}

	public NhsReport(int id, double weekday, Date edate, int branchid, String branchInternalId, String branchName,
			int totalform, int totalitems, int paidform, int paiditems, int nocharge, int exeform, int exeitems,
			int otheritems, double othervalue, double othervat, int privateitems, double privatevalue, double nhslevy,int nhsid) {
		super();
		this.id = id;
		this.weekday = weekday;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.totalform = totalform;
		this.totalitems = totalitems;
		this.paidform = paidform;
		this.paiditems = paiditems;
		this.nocharge = nocharge;
		this.exeform = exeform;
		this.exeitems = exeitems;
		this.otheritems = otheritems;
		this.othervalue = othervalue;
		this.othervat = othervat;
		this.privateitems = privateitems;
		this.privatevalue = privatevalue;
		this.nhslevy = nhslevy;
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

	public int getTotalform() {
		return totalform;
	}

	public void setTotalform(int totalform) {
		this.totalform = totalform;
	}

	public int getTotalitems() {
		return totalitems;
	}

	public void setTotalitems(int totalitems) {
		this.totalitems = totalitems;
	}

	public int getPaidform() {
		return paidform;
	}

	public void setPaidform(int paidform) {
		this.paidform = paidform;
	}

	public int getPaiditems() {
		return paiditems;
	}

	public void setPaiditems(int paiditems) {
		this.paiditems = paiditems;
	}

	public int getNocharge() {
		return nocharge;
	}

	public void setNocharge(int nocharge) {
		this.nocharge = nocharge;
	}

	public int getExeform() {
		return exeform;
	}

	public void setExeform(int exeform) {
		this.exeform = exeform;
	}

	public int getExeitems() {
		return exeitems;
	}

	public void setExeitems(int exeitems) {
		this.exeitems = exeitems;
	}

	public int getOtheritems() {
		return otheritems;
	}

	public void setOtheritems(int otheritems) {
		this.otheritems = otheritems;
	}

	public double getOthervalue() {
		return othervalue;
	}

	public void setOthervalue(double othervalue) {
		this.othervalue = othervalue;
	}

	public double getOthervat() {
		return othervat;
	}

	public void setOthervat(double othervat) {
		this.othervat = othervat;
	}

	public int getPrivateitems() {
		return privateitems;
	}

	public void setPrivateitems(int privateitems) {
		this.privateitems = privateitems;
	}

	public double getPrivatevalue() {
		return privatevalue;
	}

	public void setPrivatevalue(double privatevalue) {
		this.privatevalue = privatevalue;
	}

	public double getNhslevy() {
		return nhslevy;
	}

	public void setNhslevy(double nhslevy) {
		this.nhslevy = nhslevy;
	}

	public int getNhsid() {
		return nhsid;
	}

	public void setNhsid(int nhsid) {
		this.nhsid = nhsid;
	}

}
