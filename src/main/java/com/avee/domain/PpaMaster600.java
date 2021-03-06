// default package
// Generated 30-Mar-2018 12:11:34 by Hibernate Tools 5.1.0.Alpha1
package com.avee.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PpaMaster600 generated by hbm2java
 */

@Entity
@Table(name = "PPA_MASTER_600", catalog = "Pharma")
public class PpaMaster600 implements java.io.Serializable {
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

	public PpaMaster600() {
	}

	public PpaMaster600(int id, Date paymentDate) {
		this.id = id;
		this.paymentDate = paymentDate;
	}

	public PpaMaster600(int id, Date paymentDate, String sbOcsCode, String mbTradename, String sbConLaLine,
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
	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "payment_date", nullable = false, length = 27)
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

	@Column(name = "wg_ae_line_type")
	public Short getWgAeLineType() {
		return this.wgAeLineType;
	}

	public void setWgAeLineType(Short wgAeLineType) {
		this.wgAeLineType = wgAeLineType;
	}

	@Column(name = "wg_ae_identifier", length = 30)
	public String getWgAeIdentifier() {
		return this.wgAeIdentifier;
	}

	public void setWgAeIdentifier(String wgAeIdentifier) {
		this.wgAeIdentifier = wgAeIdentifier;
	}

	@Column(name = "wg_ae_reason_code", length = 30)
	public String getWgAeReasonCode() {
		return this.wgAeReasonCode;
	}

	public void setWgAeReasonCode(String wgAeReasonCode) {
		this.wgAeReasonCode = wgAeReasonCode;
	}

	@Column(name = "wg_ae_desc", length = 300)
	public String getWgAeDesc() {
		return this.wgAeDesc;
	}

	public void setWgAeDesc(String wgAeDesc) {
		this.wgAeDesc = wgAeDesc;
	}

	@Column(name = "wg_ae_num_pxs")
	public Short getWgAeNumPxs() {
		return this.wgAeNumPxs;
	}

	public void setWgAeNumPxs(Short wgAeNumPxs) {
		this.wgAeNumPxs = wgAeNumPxs;
	}


}
