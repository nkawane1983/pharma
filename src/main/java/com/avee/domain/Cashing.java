package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "cash_summary")
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
	private double discount;
	private double cash;
	private double cheques;
	private double cards;
	private double coupuns;
	private double paidOut1;

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

	public Cashing() {
		super();
	}

	public Cashing(int id, int branchId, int bankingId, Date date, int tillNo, String refNo, double zReading,
			int voids, double refunds, int sales, double cash, double cheques, double cards, double coupuns,
			double paidOut1, double enett, double lnett, double snett, double znett, double evat, double lvat,
			double svat, double zvat, String userId, double miscCash, double zReadPrivateValue, double zReadLevy,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt, String discrepancy1, String notes,
			String manager,int tillStatus,int floatvalue,double discount, String deleteComment, boolean markDelete) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.bankingId = bankingId;
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
		this.discrepancy1 = discrepancy1;
		this.notes = notes;
		this.manager = manager;
		this.tillStatus = tillStatus;
		this.floatvalue = floatvalue;
		this.discount = discount;
		this.deleteComment = deleteComment;
		this.markDelete = markDelete;
		
	}

	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "cash_summary_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "branch_id")
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "till_no")
	public int getTillNo() {
		return tillNo;
	}

	public void setTillNo(int tillNo) {
		this.tillNo = tillNo;
	}

	@Column(name = "ref_no", length = 15)
	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	@Column(name = "z_reading")
	public double getzReading() {
		return zReading;
	}

	public void setzReading(double zReading) {
		this.zReading = zReading;
	}

	@Column(name = "voids")
	public int getVoids() {
		return voids;
	}

	public void setVoids(int voids) {
		this.voids = voids;
	}

	@Column(name = "refunds")
	public double getRefunds() {
		return refunds;
	}

	public void setRefunds(double refunds) {
		this.refunds = refunds;
	}

	@Column(name = "sales")
	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Column(name = "cash")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Column(name = "cheques")
	public double getCheques() {
		return cheques;
	}

	public void setCheques(double cheques) {
		this.cheques = cheques;
	}

	@Column(name = "cards")
	public double getCards() {
		return cards;
	}

	public void setCards(double cards) {
		this.cards = cards;
	}

	@Column(name = "coupuns")
	public double getCoupuns() {
		return coupuns;
	}

	public void setCoupuns(double coupuns) {
		this.coupuns = coupuns;
	}

	@Column(name = "paidouts")
	public double getPaidOut1() {
		return paidOut1;
	}

	public void setPaidOut1(double paidOut1) {
		this.paidOut1 = paidOut1;
	}

	@Column(name = "nett3")
	public double getEnett() {
		return enett;
	}

	public void setEnett(double enett) {
		this.enett = enett;
	}

	@Column(name = "nett2")
	public double getLnett() {
		return lnett;
	}

	public void setLnett(double lnett) {
		this.lnett = lnett;
	}

	@Column(name = "nett1")
	public double getSnett() {
		return snett;
	}

	public void setSnett(double snett) {
		this.snett = snett;
	}

	@Column(name = "nett0")
	public double getZnett() {
		return znett;
	}

	public void setZnett(double znett) {
		this.znett = znett;
	}

	@Column(name = "vat3")
	public double getEvat() {
		return evat;
	}

	public void setEvat(double evat) {
		this.evat = evat;
	}

	@Column(name = "vat2")
	public double getLvat() {
		return lvat;
	}

	public void setLvat(double lvat) {
		this.lvat = lvat;
	}

	@Column(name = "vat1")
	public double getSvat() {
		return svat;
	}

	public void setSvat(double svat) {
		this.svat = svat;
	}

	@Column(name = "vat0")
	public double getZvat() {
		return zvat;
	}

	public void setZvat(double zvat) {
		this.zvat = zvat;
	}

	@Column(name = "user_id", length = 10)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "misc_cash")
	public double getMiscCash() {
		return miscCash;
	}

	public void setMiscCash(double miscCash) {
		this.miscCash = miscCash;
	}

	@Column(name = "z_read_private_value")
	public double getzReadPrivateValue() {
		return zReadPrivateValue;
	}

	public void setzReadPrivateValue(double zReadPrivateValue) {
		this.zReadPrivateValue = zReadPrivateValue;
	}

	@Column(name = "z_read_levy")
	public double getzReadLevy() {
		return zReadLevy;
	}

	public void setzReadLevy(double zReadLevy) {
		this.zReadLevy = zReadLevy;
	}

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Column(name = "banking_id")
	public int getBankingId() {
		return bankingId;
	}

	public void setBankingId(int bankingId) {
		this.bankingId = bankingId;
	}
	@Column(name = "discrepancy", length = 100)
	public String getdiscrepancy1() {
		return discrepancy1;
	}

	public void setdiscrepancy1(String discrepancy1) {
		this.discrepancy1 = discrepancy1;
	}
	@Column(name = "notes", length = 100)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Column(name = "manager", length = 30)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	@Column(name = "till_status")
	public int getTillStatus() {
		return tillStatus;
	}

	public void setTillStatus(int tillStatus) {
		this.tillStatus = tillStatus;
	}
	@Column(name = "floatvalue")
	public int getFloatvalue() {
		return floatvalue;
	}

	public void setFloatvalue(int floatvalue) {
		this.floatvalue = floatvalue;
	}
	@Column(name = "discount")
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Column(name = "delete_comment", length = 75)
	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
	}
	@Column(name = "mark_delete")
	public boolean getMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}
}
