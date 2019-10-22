package com.avee.domain;
// Generated 30-Mar-2018 12:11:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ppa800 generated by hbm2java
 */

@Entity
@Table(name = "PPA_800")
public class Ppa800 implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private Date paymentDate;
	private String sbOcsCode;
	private String mbTradename;
	private String sbConLaLine;
	private String expensiveItemLine;
	private String expItemDescription;
	private Double expItemPack;
	private Short expItemQty;
	private Double expItemValue;
	private String expensiveItemForm;

	public Ppa800() {
	}

	public Ppa800(int id, Date paymentDate) {
		this.id = id;
		this.paymentDate = paymentDate;
	}

	public Ppa800(int id, Date paymentDate, String sbOcsCode, String mbTradename, String sbConLaLine,
			String expensiveItemLine, String expItemDescription, Double expItemPack, Short expItemQty,
			Double expItemValue, String expensiveItemForm) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.sbOcsCode = sbOcsCode;
		this.mbTradename = mbTradename;
		this.sbConLaLine = sbConLaLine;
		this.expensiveItemLine = expensiveItemLine;
		this.expItemDescription = expItemDescription;
		this.expItemPack = expItemPack;
		this.expItemQty = expItemQty;
		this.expItemValue = expItemValue;
		this.expensiveItemForm = expensiveItemForm;
	}
	@Id
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "payment_date", length = 27)
	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "sb_ocs_code", length = 30)
	public String getSbOcsCode() {
		return this.sbOcsCode;
	}

	public void setSbOcsCode(String sbOcsCode) {
		this.sbOcsCode = sbOcsCode;
	}

	@Column(name = "mb_tradename", length = 30)
	public String getMbTradename() {
		return this.mbTradename;
	}

	public void setMbTradename(String mbTradename) {
		this.mbTradename = mbTradename;
	}

	@Column(name = "sb_con_la_line", length = 30)
	public String getSbConLaLine() {
		return this.sbConLaLine;
	}

	public void setSbConLaLine(String sbConLaLine) {
		this.sbConLaLine = sbConLaLine;
	}

	@Column(name = "expensive_item_line", length = 30)
	public String getExpensiveItemLine() {
		return this.expensiveItemLine;
	}

	public void setExpensiveItemLine(String expensiveItemLine) {
		this.expensiveItemLine = expensiveItemLine;
	}

	@Column(name = "exp_item_description", length = 300)
	public String getExpItemDescription() {
		return this.expItemDescription;
	}

	public void setExpItemDescription(String expItemDescription) {
		this.expItemDescription = expItemDescription;
	}

	@Column(name = "exp_item_pack")
	public Double getExpItemPack() {
		return this.expItemPack;
	}

	public void setExpItemPack(Double expItemPack) {
		this.expItemPack = expItemPack;
	}

	@Column(name = "exp_item_qty")
	public Short getExpItemQty() {
		return this.expItemQty;
	}

	public void setExpItemQty(Short expItemQty) {
		this.expItemQty = expItemQty;
	}

	@Column(name = "exp_item_value")
	public Double getExpItemValue() {
		return this.expItemValue;
	}

	public void setExpItemValue(Double expItemValue) {
		this.expItemValue = expItemValue;
	}

	@Column(name = "expensive_item_form", length = 30)
	public String getExpensiveItemForm() {
		return this.expensiveItemForm;
	}

	public void setExpensiveItemForm(String expensiveItemForm) {
		this.expensiveItemForm = expensiveItemForm;
	}
	

}
