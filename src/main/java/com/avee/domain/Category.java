package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "category")
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
	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "category_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "category_name",length = 50)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Column(name = "category_group",length = 50)
	public String getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	@Column(name = "debitor_credit",length = 1)
	public String getDebitorCredit() {
		return debitorCredit;
	}

	public void setDebitorCredit(String debitorCredit) {
		this.debitorCredit = debitorCredit;
	}
	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Column(name = "display_order")
	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	@Column(name = "vat_rate")
	public int getVatRate() {
		return vatRate;
	}

	public void setVatRate(int vatRate) {
		this.vatRate = vatRate;
	}
	@Column(name = "includeinbanking")
	public int getIncludeInBanking() {
		return includeInBanking;
	}

	public void setIncludeInBanking(int includeInBanking) {
		this.includeInBanking = includeInBanking;
	}

	@Column(name = "created_by",length = 30)
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
	@Column(name = "updated_by",length = 30)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "updated_date ")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
