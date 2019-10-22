package com.avee.form;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@SuppressWarnings("serial")
public class Cashing implements java.io.Serializable {
	
	private int id;
	private int branchId;
	private int bankingId;
	private Date date;
	private int tillNo;
	private int tillStatus;
	private int floatvalue;
	private String refNo;
	private double zReading;
	private int voids;
	private double refunds;
	private int sales;
	private double cash;
	private double cheques;
	private double cards;
	private double coupuns;
	private double paidOut1;
	private double discount;
	private double enett;
	private double lnett;
	private double snett;
	private double znett;

	private double evat;
	private double lvat;
	private double svat;
	private double zvat;

	private String userId;
	private double miscCash;
	private double zReadPrivateValue;
	private double zReadLevy;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	private String discrepancy1;
	private String notes;
	private String manager;

	private String deleteComment;
	private boolean markDelete;
	private int weekNo;
	private double diff;
	private double actualTill;

	private List<TakingsCash> takingscash = new ArrayList<>();
	private List<TakingsCheques> takingscheques = new ArrayList<>();
	private List<TakingsCards> takingscards = new ArrayList<>();
	private List<TakingCoupons> takingcoupons = new ArrayList<>();
	private List<PaidOuts> paidouts = new ArrayList<>();

	public Cashing() {
		this.id = 0;
		this.branchId = 0;
		this.date = null;
		this.tillNo = 0;
		this.refNo = null;
		this.zReading = 0;
		this.voids = 0;
		this.refunds = 0;
		this.sales = 0;
		this.cash = 0;
		this.cheques = 0;
		this.cards = 0;
		this.coupuns = 0;
		this.paidOut1 = 0;
		this.tillStatus = 0;
		this.floatvalue = 0;
		this.enett = 0;
		this.lnett = 0;
		this.snett = 0;
		this.znett = 0;
		this.evat = 0;
		this.lvat = 0;
		this.svat = 0;
		this.zvat = 0;
		this.userId = null;
		this.miscCash = 0;
		this.zReadPrivateValue = 0;
		this.zReadLevy = 0;
		this.createdBy = null;
		this.createdDt = null;
		this.updatedBy = null;
		this.updatedDt = null;
		this.takingscash = null;
		this.takingscheques = null;
		this.takingscards = null;
		this.takingcoupons = null;
		this.paidouts = null;
		this.bankingId = 0;
		this.discrepancy1 = null;
		this.notes = null;
		this.manager = null;
		this.discount = 0;
		this.weekNo=0;
		this.diff=0;
		this.actualTill=0;
	}

	public Cashing(int id, int branchId, Date date, int tillNo, String refNo, double zReading, int voids,
			double refunds, int sales, double cash, double cheques, double cards, double coupuns, double paidOut1,
			double enett, double lnett, double snett, double znett, double evat, double lvat, double svat, double zvat,
			String userId, double miscCash, double zReadPrivateValue, double zReadLevy, String createdBy,
			Date createdDt, String updatedBy, Date updatedDt, List<TakingsCash> takingscash,
			List<TakingsCheques> takingscheques, List<TakingsCards> takingscards, List<TakingCoupons> takingcoupons,
			List<PaidOuts> paidouts, int bankingId, String discrepancy1, String notes, String manager, int tillStatus,
			int floatvalue, double discount, String deleteComment, boolean markDelete,int weekNo,double diff,double actualTill) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.date = date;
		this.tillNo = tillNo;
		this.refNo = refNo;
		this.zReading = zReading;
		this.voids = voids;
		this.refunds = refunds;
		this.sales = sales;
		this.cash = cash;
		this.cheques = cheques;
		this.cards = cards;
		this.coupuns = coupuns;
		this.paidOut1 = paidOut1;
		this.enett = enett;
		this.lnett = lnett;
		this.snett = snett;
		this.znett = znett;
		this.evat = evat;
		this.lvat = lvat;
		this.svat = svat;
		this.zvat = zvat;
		this.userId = userId;
		this.miscCash = miscCash;
		this.zReadPrivateValue = zReadPrivateValue;
		this.zReadLevy = zReadLevy;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.takingscash = takingscash;
		this.takingscheques = takingscheques;
		this.takingscards = takingscards;
		this.takingcoupons = takingcoupons;
		this.paidouts = paidouts;
		this.bankingId = bankingId;
		this.discrepancy1 = discrepancy1;
		this.notes = notes;
		this.manager = manager;
		this.tillStatus = tillStatus;
		this.floatvalue = floatvalue;
		this.discount = discount;
		this.deleteComment = deleteComment;
		this.markDelete = markDelete;
		this.weekNo=weekNo;
		this.diff=diff;
		this.actualTill=actualTill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTillNo() {
		return tillNo;
	}

	public void setTillNo(int tillNo) {
		this.tillNo = tillNo;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public double getzReading() {
		return zReading;
	}

	public void setzReading(double zReading) {
		this.zReading = zReading;
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

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
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

	public double getCards() {
		return cards;
	}

	public void setCards(double cards) {
		this.cards = cards;
	}

	public double getCoupuns() {
		return coupuns;
	}

	public void setCoupuns(double coupuns) {
		this.coupuns = coupuns;
	}

	public double getPaidOut1() {
		return paidOut1;
	}

	public void setPaidOut1(double paidOut1) {
		this.paidOut1 = paidOut1;
	}

	public double getEnett() {
		return enett;
	}

	public void setEnett(double enett) {
		this.enett = enett;
	}

	public double getLnett() {
		return lnett;
	}

	public void setLnett(double lnett) {
		this.lnett = lnett;
	}

	public double getSnett() {
		return snett;
	}

	public void setSnett(double snett) {
		this.snett = snett;
	}

	public double getZnett() {
		return znett;
	}

	public void setZnett(double znett) {
		this.znett = znett;
	}

	public double getEvat() {
		return evat;
	}

	public void setEvat(double evat) {
		this.evat = evat;
	}

	public double getLvat() {
		return lvat;
	}

	public void setLvat(double lvat) {
		this.lvat = lvat;
	}

	public double getSvat() {
		return svat;
	}

	public void setSvat(double svat) {
		this.svat = svat;
	}

	public double getZvat() {
		return zvat;
	}

	public void setZvat(double zvat) {
		this.zvat = zvat;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getMiscCash() {
		return miscCash;
	}

	public void setMiscCash(double miscCash) {
		this.miscCash = miscCash;
	}

	public double getzReadPrivateValue() {
		return zReadPrivateValue;
	}

	public void setzReadPrivateValue(double zReadPrivateValue) {
		this.zReadPrivateValue = zReadPrivateValue;
	}

	public double getzReadLevy() {
		return zReadLevy;
	}

	public void setzReadLevy(double zReadLevy) {
		this.zReadLevy = zReadLevy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public List<TakingsCash> getTakingscash() {
		return takingscash;
	}

	public void setTakingscash(List<TakingsCash> takingscash) {
		this.takingscash = takingscash;
	}

	public List<TakingsCheques> getTakingscheques() {
		return takingscheques;
	}

	public void setTakingscheques(List<TakingsCheques> takingscheques) {
		this.takingscheques = takingscheques;
	}

	public List<TakingsCards> getTakingscards() {
		return takingscards;
	}

	public void setTakingscards(List<TakingsCards> takingscards) {
		this.takingscards = takingscards;
	}

	public List<TakingCoupons> getTakingcoupons() {
		return takingcoupons;
	}

	public void setTakingcoupons(List<TakingCoupons> takingcoupons) {
		this.takingcoupons = takingcoupons;
	}

	public List<PaidOuts> getPaidouts() {
		return paidouts;
	}

	public void setPaidouts(List<PaidOuts> paidouts) {
		this.paidouts = paidouts;
	}

	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
	}

	public String getdiscrepancy1() {
		return discrepancy1;
	}

	public void setdiscrepancy1(String discrepancy1) {
		this.discrepancy1 = discrepancy1;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getTillStatus() {
		return tillStatus;
	}

	public void setTillStatus(int tillStatus) {
		this.tillStatus = tillStatus;
	}

	public int getFloatvalue() {
		return floatvalue;
	}

	public void setFloatvalue(int floatvalue) {
		this.floatvalue = floatvalue;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
	}

	public boolean isMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	public int getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}

	public double getDiff() {
		return diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public double getActualTill() {
		return actualTill;
	}

	public void setActualTill(double actualTill) {
		this.actualTill = actualTill;
	}

}
