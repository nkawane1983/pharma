package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class CustomerStatsReport implements java.io.Serializable {
	private int id;
	private double weekday;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private int nhsItems;
	private int noOfSales;
	private double salesIncActual;
	private double salesIncAvg;
	private double salesExcActual;
	private double salesExcAvg;
	private double counterSale;
	private double excVat;
	private double nhslevy;

	public CustomerStatsReport() {

	}

	public CustomerStatsReport(int id, double weekday, Date edate, int branchid, String branchInternalId,
			String branchName, int nhsItems, int noOfSales, double salesIncActual, double salesIncAvg,
			double salesExcActual, double salesExcAvg, double counterSale, double excVat, double nhslevy) {
		this.id = id;
		this.weekday = weekday;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.nhsItems = nhsItems;
		this.noOfSales = noOfSales;
		this.salesIncActual = salesIncActual;
		this.salesIncAvg = salesIncAvg;
		this.salesExcActual = salesExcActual;
		this.salesExcAvg = salesExcAvg;
		this.counterSale = counterSale;
		this.excVat = excVat;
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

	public int getNhsItems() {
		return nhsItems;
	}

	public void setNhsItems(int nhsItems) {
		this.nhsItems = nhsItems;
	}

	public int getNoOfSales() {
		return noOfSales;
	}

	public void setNoOfSales(int noOfSales) {
		this.noOfSales = noOfSales;
	}

	public double getSalesIncActual() {
		return salesIncActual;
	}

	public void setSalesIncActual(double salesIncActual) {
		this.salesIncActual = salesIncActual;
	}

	public double getSalesIncAvg() {
		return salesIncAvg;
	}

	public void setSalesIncAvg(double salesIncAvg) {
		this.salesIncAvg = salesIncAvg;
	}

	public double getSalesExcActual() {
		return salesExcActual;
	}

	public void setSalesExcActual(double salesExcActual) {
		this.salesExcActual = salesExcActual;
	}

	public double getSalesExcAvg() {
		return salesExcAvg;
	}

	public void setSalesExcAvg(double salesExcAvg) {
		this.salesExcAvg = salesExcAvg;
	}

	public double getCounterSale() {
		return counterSale;
	}

	public void setCounterSale(double counterSale) {
		this.counterSale = counterSale;
	}

	public double getExcVat() {
		return excVat;
	}

	public void setExcVat(double excVat) {
		this.excVat = excVat;
	}

	public double getNhslevy() {
		return nhslevy;
	}

	public void setNhslevy(double nhslevy) {
		this.nhslevy = nhslevy;
	}

}
