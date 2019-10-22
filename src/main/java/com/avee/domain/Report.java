package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "pharma_report")
public class Report implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	
	private String reportType;
	private String reportName;
	private String reportSheetName;
	private String reportFileName;
	private String reportDesc;
	private int reportOrder;
	
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
	
	
	public Report() {
	}


	public Report(int id, String reportType, String reportName, String reportSheetName, String reportFileName,
			String reportDesc, int reportOrder, String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		this.id = id;
		this.reportType = reportType;
		this.reportName = reportName;
		this.reportSheetName = reportSheetName;
		this.reportFileName = reportFileName;
		this.reportDesc = reportDesc;
		this.reportOrder = reportOrder;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "report_type", length = 100)
	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "report_name", length = 100)
	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "report_sheet_name", length = 50)
	public String getReportSheetName() {
		return reportSheetName;
	}


	public void setReportSheetName(String reportSheetName) {
		this.reportSheetName = reportSheetName;
	}

	@Column(name = "report_file_name_jrxml_jasper", length = 100)
	public String getReportFileName() {
		return reportFileName;
	}


	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	@Column(name = "rport_desc", length = 250)
	public String getReportDesc() {
		return reportDesc;
	}


	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	@Column(name = "report_order")
	public int getReportOrder() {
		return reportOrder;
	}


	public void setReportOrder(int reportOrder) {
		this.reportOrder = reportOrder;
	}


	@Column(name = "created_by", length = 30, updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 7, updatable = false)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 7)
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}



	@Override
	public String toString() {
		return "Report [id=" + id + ", reportType=" + reportType + ", reportName=" + reportName + ", reportSheetName="
				+ reportSheetName + ", reportFileName=" + reportFileName + ", reportDesc=" + reportDesc
				+ ", reportOrder=" + reportOrder + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", updatedBy=" + updatedBy + ", updatedDt=" + updatedDt + "]";
	}
	

	
	
}
