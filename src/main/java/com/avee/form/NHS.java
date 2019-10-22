package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class NHS implements java.io.Serializable {
	private int id;
	private int branchId;
	private Date date;
	private int paidForm;
	private int paidItem;
	private int noChargeItem;
	private double prescriptionCharge;
	private int paidFormOld;
	private int paidItemOld;
	private int noChargeItemOld;
	private double prescriptionChargeOld;
	private int exemptForm;
	private int exemptItem;
	private int contraceptiveForm;
	private int contraceptiveItem;
	private int privatePrescriptionItems;
	private double privatePrescriptionValue;
	private int otherPrescriptionItems;
	private double otherPrescriptionValue;
	private double otherPrescriptionVat;
	private int ncsoItems;
	private String userId;
	private int companyId;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	private int subMisForm;
	private int subMisItems;
	private int repDisForm;
	private int repDisItems;

	private int subMisPatients;
	private int repDisPatients;
	private int weekNo;
	private int totalForm;
	private int totalItems;

	public NHS() {
		super();
	}

	public NHS(int id, int branchId, Date date, int paidForm, int paidItem, int noChargeItem, double prescriptionCharge,
			int paidFormOld, int paidItemOld, int noChargeItemOld, double prescriptionChargeOld, int exemptForm,
			int exemptItem, int contraceptiveForm, int contraceptiveItem, int privatePrescriptionItems,
			double privatePrescriptionValue, int otherPrescriptionItems, double otherPrescriptionValue,
			double otherPrescriptionVat, int ncsoItems, String userId, int companyId, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt, int subMisForm, int subMisItems, int repDisForm, int repDisItems,
			int subMisPatients, int repDisPatients, int weekNo, int totalForm, int totalItems) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.date = date;
		this.paidForm = paidForm;
		this.paidItem = paidItem;
		this.noChargeItem = noChargeItem;
		this.prescriptionCharge = prescriptionCharge;
		this.paidFormOld = paidFormOld;
		this.paidItemOld = paidItemOld;
		this.noChargeItemOld = noChargeItemOld;
		this.prescriptionChargeOld = prescriptionChargeOld;
		this.exemptForm = exemptForm;
		this.exemptItem = exemptItem;
		this.contraceptiveForm = contraceptiveForm;
		this.contraceptiveItem = contraceptiveItem;
		this.privatePrescriptionItems = privatePrescriptionItems;
		this.privatePrescriptionValue = privatePrescriptionValue;
		this.otherPrescriptionItems = otherPrescriptionItems;
		this.otherPrescriptionValue = otherPrescriptionValue;
		this.otherPrescriptionVat = otherPrescriptionVat;
		this.ncsoItems = ncsoItems;
		this.userId = userId;
		this.companyId = companyId;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.subMisForm = subMisForm;
		this.subMisItems = subMisItems;
		this.repDisForm = repDisForm;
		this.repDisItems = repDisItems;
		this.subMisPatients = subMisPatients;
		this.repDisPatients = repDisPatients;
		this.weekNo = weekNo;
		this.totalForm = totalForm;
		this.totalItems = totalItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPaidForm() {
		return paidForm;
	}

	public void setPaidForm(int paidForm) {
		this.paidForm = paidForm;
	}

	public int getPaidItem() {
		return paidItem;
	}

	public void setPaidItem(int paidItem) {
		this.paidItem = paidItem;
	}

	public int getNoChargeItem() {
		return noChargeItem;
	}

	public void setNoChargeItem(int noChargeItem) {
		this.noChargeItem = noChargeItem;
	}

	public double getPrescriptionCharge() {
		return prescriptionCharge;
	}

	public void setPrescriptionCharge(double prescriptionCharge) {
		this.prescriptionCharge = prescriptionCharge;
	}

	public int getPaidFormOld() {
		return paidFormOld;
	}

	public void setPaidFormOld(int paidFormOld) {
		this.paidFormOld = paidFormOld;
	}

	public int getPaidItemOld() {
		return paidItemOld;
	}

	public void setPaidItemOld(int paidItemOld) {
		this.paidItemOld = paidItemOld;
	}

	public int getNoChargeItemOld() {
		return noChargeItemOld;
	}

	public void setNoChargeItemOld(int noChargeItemOld) {
		this.noChargeItemOld = noChargeItemOld;
	}

	public double getPrescriptionChargeOld() {
		return prescriptionChargeOld;
	}

	public void setPrescriptionChargeOld(double prescriptionChargeOld) {
		this.prescriptionChargeOld = prescriptionChargeOld;
	}

	public int getExemptForm() {
		return exemptForm;
	}

	public void setExemptForm(int exemptForm) {
		this.exemptForm = exemptForm;
	}

	public int getExemptItem() {
		return exemptItem;
	}

	public void setExemptItem(int exemptItem) {
		this.exemptItem = exemptItem;
	}

	public int getContraceptiveForm() {
		return contraceptiveForm;
	}

	public void setContraceptiveForm(int contraceptiveForm) {
		this.contraceptiveForm = contraceptiveForm;
	}

	public int getContraceptiveItem() {
		return contraceptiveItem;
	}

	public void setContraceptiveItem(int contraceptiveItem) {
		this.contraceptiveItem = contraceptiveItem;
	}

	public int getPrivatePrescriptionItems() {
		return privatePrescriptionItems;
	}

	public void setPrivatePrescriptionItems(int privatePrescriptionItems) {
		this.privatePrescriptionItems = privatePrescriptionItems;
	}

	public int getOtherPrescriptionItems() {
		return otherPrescriptionItems;
	}

	public void setOtherPrescriptionItems(int otherPrescriptionItems) {
		this.otherPrescriptionItems = otherPrescriptionItems;
	}

	public double getOtherPrescriptionValue() {
		return otherPrescriptionValue;
	}

	public void setOtherPrescriptionValue(double otherPrescriptionValue) {
		this.otherPrescriptionValue = otherPrescriptionValue;
	}

	public double getOtherPrescriptionVat() {
		return otherPrescriptionVat;
	}

	public void setOtherPrescriptionVat(double otherPrescriptionVat) {
		this.otherPrescriptionVat = otherPrescriptionVat;
	}

	public int getNcsoItems() {
		return ncsoItems;
	}

	public void setNcsoItems(int ncsoItems) {
		this.ncsoItems = ncsoItems;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public double getPrivatePrescriptionValue() {
		return privatePrescriptionValue;
	}

	public void setPrivatePrescriptionValue(double privatePrescriptionValue) {
		this.privatePrescriptionValue = privatePrescriptionValue;
	}

	public int getSubMisForm() {
		return subMisForm;
	}

	public void setSubMisForm(int subMisForm) {
		this.subMisForm = subMisForm;
	}

	public int getSubMisItems() {
		return subMisItems;
	}

	public void setSubMisItems(int subMisItems) {
		this.subMisItems = subMisItems;
	}

	public int getRepDisForm() {
		return repDisForm;
	}

	public void setRepDisForm(int repDisForm) {
		this.repDisForm = repDisForm;
	}

	public int getRepDisItems() {
		return repDisItems;
	}

	public void setRepDisItems(int repDisItems) {
		this.repDisItems = repDisItems;
	}

	public int getSubMisPatients() {
		return subMisPatients;
	}

	public void setSubMisPatients(int subMisPatients) {
		this.subMisPatients = subMisPatients;
	}

	public int getRepDisPatients() {
		return repDisPatients;
	}

	public void setRepDisPatients(int repDisPatients) {
		this.repDisPatients = repDisPatients;
	}

	public int getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}

	public int getTotalForm() {
		return totalForm;
	}

	public void setTotalForm(int totalForm) {
		this.totalForm = totalForm;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

}
