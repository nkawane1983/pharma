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
@Table(name = "denominations")
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
	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="denominations_id_seq",allocationSize=1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID",unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "denominations", length = 50)
	public String getDenominations() {
		return denominations;
	}

	public void setDenominations(String denominations) {
		this.denominations = denominations;
	}
	@Column(name = "multiplier")
	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	@Column(name = "display_order")
	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
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
