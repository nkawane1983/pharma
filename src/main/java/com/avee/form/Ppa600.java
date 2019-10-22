package com.avee.form;
import java.util.Date;

/**
 * Ppa600 generated by hbm2java
 */

public class Ppa600 implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date paymentDate;
	private String sbOcsCode;
	private String mbTradename;
	private String sbConLaLine;
	private Short wgAeLineType;
	private String wgAeIdentifier;
	private String wgAeReasonCode;
	private String wgAeDesc;
	private Short wgAeNumPxs;

	public Ppa600() {
	}

	public Ppa600(int id, Date paymentDate) {
		this.id = id;
		this.paymentDate = paymentDate;
	}

	public Ppa600(int id, Date paymentDate, String sbOcsCode, String mbTradename, String sbConLaLine,
			Short wgAeLineType, String wgAeIdentifier, String wgAeReasonCode, String wgAeDesc, Short wgAeNumPxs) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.sbOcsCode = sbOcsCode;
		this.mbTradename = mbTradename;
		this.sbConLaLine = sbConLaLine;
		this.wgAeLineType = wgAeLineType;
		this.wgAeIdentifier = wgAeIdentifier;
		this.wgAeReasonCode = wgAeReasonCode;
		this.wgAeDesc = wgAeDesc;
		this.wgAeNumPxs = wgAeNumPxs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSbOcsCode() {
		return sbOcsCode;
	}

	public void setSbOcsCode(String sbOcsCode) {
		this.sbOcsCode = sbOcsCode;
	}

	public String getMbTradename() {
		return mbTradename;
	}

	public void setMbTradename(String mbTradename) {
		this.mbTradename = mbTradename;
	}

	public String getSbConLaLine() {
		return sbConLaLine;
	}

	public void setSbConLaLine(String sbConLaLine) {
		this.sbConLaLine = sbConLaLine;
	}

	public Short getWgAeLineType() {
		return wgAeLineType;
	}

	public void setWgAeLineType(Short wgAeLineType) {
		this.wgAeLineType = wgAeLineType;
	}

	public String getWgAeIdentifier() {
		return wgAeIdentifier;
	}

	public void setWgAeIdentifier(String wgAeIdentifier) {
		this.wgAeIdentifier = wgAeIdentifier;
	}

	public String getWgAeReasonCode() {
		return wgAeReasonCode;
	}

	public void setWgAeReasonCode(String wgAeReasonCode) {
		this.wgAeReasonCode = wgAeReasonCode;
	}

	public String getWgAeDesc() {
		return wgAeDesc;
	}

	public void setWgAeDesc(String wgAeDesc) {
		this.wgAeDesc = wgAeDesc;
	}

	public Short getWgAeNumPxs() {
		return wgAeNumPxs;
	}

	public void setWgAeNumPxs(Short wgAeNumPxs) {
		this.wgAeNumPxs = wgAeNumPxs;
	}

	

}
