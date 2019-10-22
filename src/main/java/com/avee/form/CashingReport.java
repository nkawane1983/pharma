package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class CashingReport implements java.io.Serializable {
	private double weekday;
	private Date edate;
	private double zReading;
	private double diff;
	private int voids;
	private double refunds;
	private double coupons;
	private double actualtill;
	private double expenses;
	private double card;
	private double cash;
	private double actualCash;
	private double cheques;
	private double zero;
	private double low;
	private double std;
	private double avg;
	private int sales;
	private int cashid;
	private int tillno;
	private String ref;
	private int branchid;
	private int branchInternalId;
	private String branchName;
	private double levy;
	private int totalitem;
	private int groupId;

	public CashingReport() {
		super();
	}

	public CashingReport(double weekday, Date edate, double zReading, double diff, int voids, double refunds,
			double coupons, double actualtill, double expenses, double card, double cash, double cheques, double zero,
			double low, double std, double avg, int sales, int cashid, int tillno, String ref, int branchid,
			int branchInternalId, String branchName, double levy, int totalitem,double actualCash,int groupId) {
		super();
		this.weekday = weekday;
		this.edate = edate;
		this.zReading = zReading;
		this.diff = diff;
		this.voids = voids;
		this.refunds = refunds;
		this.coupons = coupons;
		this.actualtill = actualtill;
		this.expenses = expenses;
		this.card = card;
		this.cash = cash;
		this.cheques = cheques;
		this.zero = zero;
		this.low = low;
		this.std = std;
		this.avg = avg;
		this.sales = sales;
		this.cashid = cashid;
		this.tillno = tillno;
		this.ref = ref;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.levy = levy;
		this.totalitem = totalitem;
		this.actualCash=actualCash;
		this.groupId= groupId;
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

	public double getzReading() {
		return zReading;
	}

	public void setzReading(double zReading) {
		this.zReading = zReading;
	}

	public double getDiff() {
		return diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public int getVoids() {
		return voids;
	}

	public void setVoids(int voids) {
		this.voids = voids;
	}

	public double getRefunds() {
		return refunds;
	}

	public void setRefunds(double refunds) {
		this.refunds = refunds;
	}

	public double getCoupons() {
		return coupons;
	}

	public void setCoupons(double coupons) {
		this.coupons = coupons;
	}

	public double getActualtill() {
		return actualtill;
	}

	public void setActualtill(double actualtill) {
		this.actualtill = actualtill;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getCard() {
		return card;
	}

	public void setCard(double card) {
		this.card = card;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCheques() {
		return cheques;
	}

	public void setCheques(double cheques) {
		this.cheques = cheques;
	}

	public double getZero() {
		return zero;
	}

	public void setZero(double zero) {
		this.zero = zero;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getCashid() {
		return cashid;
	}

	public void setCashid(int cashid) {
		this.cashid = cashid;
	}

	public int getTillno() {
		return tillno;
	}

	public void setTillno(int tillno) {
		this.tillno = tillno;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public int getBranchInternalId() {
		return branchInternalId;
	}

	public void setBranchInternalId(int branchInternalId) {
		this.branchInternalId = branchInternalId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getLevy() {
		return levy;
	}

	public void setLevy(double levy) {
		this.levy = levy;
	}

	public int getTotalitem() {
		return totalitem;
	}

	public void setTotalitem(int totalitem) {
		this.totalitem = totalitem;
	}

	public double getActualCash() {
		return actualCash;
	}

	public void setActualCash(double actualCash) {
		this.actualCash = actualCash;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}



	
}
