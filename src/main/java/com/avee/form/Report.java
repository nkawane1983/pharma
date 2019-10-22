package com.avee.form;

import java.util.Date;

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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	public String getReportSheetName() {
		return reportSheetName;
	}


	public void setReportSheetName(String reportSheetName) {
		this.reportSheetName = reportSheetName;
	}


	public String getReportFileName() {
		return reportFileName;
	}


	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}


	public String getReportDesc() {
		return reportDesc;
	}


	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}


	public int getReportOrder() {
		return reportOrder;
	}


	public void setReportOrder(int reportOrder) {
		this.reportOrder = reportOrder;
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


	@Override
	public String toString() {
		return "Report [id=" + id + ", reportType=" + reportType + ", reportName=" + reportName + ", reportSheetName="
				+ reportSheetName + ", reportFileName=" + reportFileName + ", reportDesc=" + reportDesc
				+ ", reportOrder=" + reportOrder + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", updatedBy=" + updatedBy + ", updatedDt=" + updatedDt + "]";
	}
	

	
	
}
