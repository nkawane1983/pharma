package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class Category implements java.io.Serializable {
	private int id;
	private String categoryName;
	private String categoryGroup;
	private String debitorCredit;
	private int amount;
	private int displayOrder;
	private int vatRate;
	private int includeInBanking;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public Category() {
		super();
	}

	public Category(int id, String categoryName, String categoryGroup, String debitorCredit, int amount,
			int displayOrder, int vatRate, int includeInBanking, String createdBy, Date createdDt, String updatedBy,
			Date updatedDt) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryGroup = categoryGroup;
		this.debitorCredit = debitorCredit;
		this.amount = amount;
		this.displayOrder = displayOrder;
		this.vatRate = vatRate;
		this.includeInBanking = includeInBanking;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public String getDebitorCredit() {
		return debitorCredit;
	}

	public void setDebitorCredit(String debitorCredit) {
		this.debitorCredit = debitorCredit;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getVatRate() {
		return vatRate;
	}

	public void setVatRate(int vatRate) {
		this.vatRate = vatRate;
	}

	public int getIncludeInBanking() {
		return includeInBanking;
	}

	public void setIncludeInBanking(int includeInBanking) {
		this.includeInBanking = includeInBanking;
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

}
