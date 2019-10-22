package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class BankingReport implements java.io.Serializable {
	private double weekday;
	private Date edate;
	private int branchId;
	private int bankingid;
	private String internalId;
	private String ref;
	private String branchName;
	private double cashTotal;
	private double chequesTotal;
	private double giroTotal;
	private double switchTotal;
	private double otherTotal;
	private double cardTotal;
	private double bankTotal;
	private List<Double> banked=new ArrayList<Double>();
	private List<ListStringAndDouble> bankingList = new ArrayList<>();
	private double branchTotal;

	public BankingReport() {
		super();
	}

	public BankingReport(double weekday, Date edate, int branchId, int bankingid, String internalId, String ref,
			String branchName, double cashTotal, double chequesTotal, double giroTotal, double switchTotal,
			double otherTotal, double cardTotal, double bankTotal, List<Double> banked,List<ListStringAndDouble> bankingList,double branchTotal) {
		super();
		this.weekday = weekday;
		this.edate = edate;
		this.branchId = branchId;
		this.bankingid = bankingid;
		this.internalId = internalId;
		this.ref = ref;
		this.branchName = branchName;
		this.cashTotal = cashTotal;
		this.chequesTotal = chequesTotal;
		this.giroTotal = giroTotal;
		this.switchTotal = switchTotal;
		this.otherTotal = otherTotal;
		this.cardTotal = cardTotal;
		this.bankTotal = bankTotal;
		this.banked = banked;
		this.bankingList = bankingList;
		this.branchTotal = branchTotal;
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBankingid() {
		return bankingid;
	}

	public void setBankingid(int bankingid) {
		this.bankingid = bankingid;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(double cashTotal) {
		this.cashTotal = cashTotal;
	}

	public double getChequesTotal() {
		return chequesTotal;
	}

	public void setChequesTotal(double chequesTotal) {
		this.chequesTotal = chequesTotal;
	}

	public double getGiroTotal() {
		return giroTotal;
	}

	public void setGiroTotal(double giroTotal) {
		this.giroTotal = giroTotal;
	}

	public double getSwitchTotal() {
		return switchTotal;
	}

	public void setSwitchTotal(double switchTotal) {
		this.switchTotal = switchTotal;
	}

	public double getOtherTotal() {
		return otherTotal;
	}

	public void setOtherTotal(double otherTotal) {
		this.otherTotal = otherTotal;
	}

	public double getCardTotal() {
		return cardTotal;
	}

	public void setCardTotal(double cardTotal) {
		this.cardTotal = cardTotal;
	}

	public double getBankTotal() {
		return bankTotal;
	}

	public void setBankTotal(double bankTotal) {
		this.bankTotal = bankTotal;
	}

	public List<Double> getBanked() {
		return banked;
	}

	public void setBanked(List<Double> banked) {
		this.banked = banked;
	}

	public List<ListStringAndDouble> getBankingList() {
		return bankingList;
	}

	public void setBankingList(List<ListStringAndDouble> bankingList) {
		this.bankingList = bankingList;
	}

	public double getBranchTotal() {
		return branchTotal;
	}

	public void setBranchTotal(double branchTotal) {
		this.branchTotal = branchTotal;
	}

}
