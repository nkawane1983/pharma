package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class Denomination implements java.io.Serializable {
	private int id;
	private String denominations;
	private double multiplier;
	private int quantity;
	private double amount;
	private int displayOrder;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public Denomination() {
		super();
	}

	public Denomination(int id, String denominations, double multiplier, int quantity, double amount, int displayOrder,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.denominations = denominations;
		this.multiplier = multiplier;
		this.quantity = quantity;
		this.amount = amount;
		this.displayOrder = displayOrder;
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

	public String getDenominations() {
		return denominations;
	}

	public void setDenominations(String denominations) {
		this.denominations = denominations;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
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
