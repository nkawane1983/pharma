package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class CareHomesReport implements java.io.Serializable {
	private int id;
	private double weekday;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private int total7form;
	private int total7items;
	private int total28form;
	private int total28items;
	private int comm7form;
	private int comm7items;
	private int comm28form;
	private int comm28items;
	private int form7day;
	private int items7day;
	private int form28day;
	private int items28day;
	
	private String carehomeName; 

	public CareHomesReport() {
		
	}

	
	public CareHomesReport(int id, double weekday, Date edate, int branchid, String branchInternalId, String branchName,
			int total7form, int total7items, int total28form, int total28items, int comm7form, int comm7items,
			int comm28form, int comm28items, int form7day, int items7day, int form28day, int items28day,
			String carehomeName) {
		
		this.id = id;
		this.weekday = weekday;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.total7form = total7form;
		this.total7items = total7items;
		this.total28form = total28form;
		this.total28items = total28items;
		this.comm7form = comm7form;
		this.comm7items = comm7items;
		this.comm28form = comm28form;
		this.comm28items = comm28items;
		this.form7day = form7day;
		this.items7day = items7day;
		this.form28day = form28day;
		this.items28day = items28day;
		this.carehomeName = carehomeName;
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

	public int getTotal7form() {
		return total7form;
	}

	public void setTotal7form(int total7form) {
		this.total7form = total7form;
	}

	public int getTotal7items() {
		return total7items;
	}

	public void setTotal7items(int total7items) {
		this.total7items = total7items;
	}

	public int getTotal28form() {
		return total28form;
	}

	public void setTotal28form(int total28form) {
		this.total28form = total28form;
	}

	public int getTotal28items() {
		return total28items;
	}

	public void setTotal28items(int total28items) {
		this.total28items = total28items;
	}

	public int getComm7form() {
		return comm7form;
	}

	public void setComm7form(int comm7form) {
		this.comm7form = comm7form;
	}

	public int getComm7items() {
		return comm7items;
	}

	public void setComm7items(int comm7items) {
		this.comm7items = comm7items;
	}

	public int getComm28form() {
		return comm28form;
	}

	public void setComm28form(int comm28form) {
		this.comm28form = comm28form;
	}

	public int getComm28items() {
		return comm28items;
	}

	public void setComm28items(int comm28items) {
		this.comm28items = comm28items;
	}

	public int getForm7day() {
		return form7day;
	}

	public void setForm7day(int form7day) {
		this.form7day = form7day;
	}

	public int getItems7day() {
		return items7day;
	}

	public void setItems7day(int items7day) {
		this.items7day = items7day;
	}

	public int getForm28day() {
		return form28day;
	}

	public void setForm28day(int form28day) {
		this.form28day = form28day;
	}

	public int getItems28day() {
		return items28day;
	}

	public void setItems28day(int items28day) {
		this.items28day = items28day;
	}


	public String getCarehomeName() {
		return carehomeName;
	}


	public void setCarehomeName(String carehomeName) {
		this.carehomeName = carehomeName;
	}

	
}
