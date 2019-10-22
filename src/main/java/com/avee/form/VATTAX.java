package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class VATTAX implements java.io.Serializable {
	private int id;
	private double weekday;
	private Date edate;
	private int branchid;
	private String branchInternalId;
	private String branchName;
	private double zeroNett;
	private double lowNett;
	private double stdNett;
	private double totalNett;
	private double lowVat;
	private double stdVat;
	private double totalVat;
	private double zeroGross;
	private double lowGross;
	private double stdGross;
	private double totalGross;
	private double script;
	private double levy;
	private int paiditems;
	private double tax;

	public VATTAX() {
		super();
	}

	public VATTAX(int id, double weekday, Date edate, int branchid, String branchInternalId, String branchName,
			double zeroNett, double lowNett, double stdNett, double totalNett, double lowVat, double stdVat,
			double totalVat, double zeroGross, double lowGross, double stdGross, double totalGross, double script,
			double levy, int paiditems, double tax) {
		super();
		this.id = id;
		this.weekday = weekday;
		this.edate = edate;
		this.branchid = branchid;
		this.branchInternalId = branchInternalId;
		this.branchName = branchName;
		this.zeroNett = zeroNett;
		this.lowNett = lowNett;
		this.stdNett = stdNett;
		this.totalNett = totalNett;
		this.lowVat = lowVat;
		this.stdVat = stdVat;
		this.totalVat = totalVat;
		this.zeroGross = zeroGross;
		this.lowGross = lowGross;
		this.stdGross = stdGross;
		this.totalGross = totalGross;
		this.script = script;
		this.levy = levy;
		this.paiditems = paiditems;
		this.tax = tax;
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

	public double getZeroNett() {
		return zeroNett;
	}

	public void setZeroNett(double zeroNett) {
		this.zeroNett = zeroNett;
	}

	public double getLowNett() {
		return lowNett;
	}

	public void setLowNett(double lowNett) {
		this.lowNett = lowNett;
	}

	public double getStdNett() {
		return stdNett;
	}

	public void setStdNett(double stdNett) {
		this.stdNett = stdNett;
	}

	public double getTotalNett() {
		return totalNett;
	}

	public void setTotalNett(double totalNett) {
		this.totalNett = totalNett;
	}

	public double getLowVat() {
		return lowVat;
	}

	public void setLowVat(double lowVat) {
		this.lowVat = lowVat;
	}

	public double getStdVat() {
		return stdVat;
	}

	public void setStdVat(double stdVat) {
		this.stdVat = stdVat;
	}

	public double getTotalVat() {
		return totalVat;
	}

	public void setTotalVat(double totalVat) {
		this.totalVat = totalVat;
	}

	public double getZeroGross() {
		return zeroGross;
	}

	public void setZeroGross(double zeroGross) {
		this.zeroGross = zeroGross;
	}

	public double getLowGross() {
		return lowGross;
	}

	public void setLowGross(double lowGross) {
		this.lowGross = lowGross;
	}

	public double getStdGross() {
		return stdGross;
	}

	public void setStdGross(double stdGross) {
		this.stdGross = stdGross;
	}

	public double getTotalGross() {
		return totalGross;
	}

	public void setTotalGross(double totalGross) {
		this.totalGross = totalGross;
	}

	public double getScript() {
		return script;
	}

	public void setScript(double script) {
		this.script = script;
	}

	public double getLevy() {
		return levy;
	}

	public void setLevy(double levy) {
		this.levy = levy;
	}

	public int getPaiditems() {
		return paiditems;
	}

	public void setPaiditems(int paiditems) {
		this.paiditems = paiditems;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

}
