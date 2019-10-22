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
@Table(name = "nhs")
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

	public NHS() {
		super();
	}

	public NHS(int id, int branchId, Date date, int paidForm, int paidItem, int noChargeItem, double prescriptionCharge,
			int paidFormOld, int paidItemOld, int noChargeItemOld, double prescriptionChargeOld, int exemptForm,
			int exemptItem, int contraceptiveForm, int contraceptiveItem, int privatePrescriptionItems,
			double privatePrescriptionValue, int otherPrescriptionItems, double otherPrescriptionValue,
			double otherPrescriptionVat, int ncsoItems, String userId, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt, int subMisForm, int subMisItems, int repDisForm, int repDisItems,
			int subMisPatients, int repDisPatients) {
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
	}

	@Id
	// @SequenceGenerator(name = "my_seq", sequenceName = "nhs_id_seq",
	// allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "branch_id", length = 50)
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "paid_form")
	public int getPaidForm() {
		return paidForm;
	}

	public void setPaidForm(int paidForm) {
		this.paidForm = paidForm;
	}

	@Column(name = "paid_item")
	public int getPaidItem() {
		return paidItem;
	}

	public void setPaidItem(int paidItem) {
		this.paidItem = paidItem;
	}

	@Column(name = "no_charge_item")
	public int getNoChargeItem() {
		return noChargeItem;
	}

	public void setNoChargeItem(int noChargeItem) {
		this.noChargeItem = noChargeItem;
	}

	@Column(name = "prescription_charge")
	public double getPrescriptionCharge() {
		return prescriptionCharge;
	}

	public void setPrescriptionCharge(double prescriptionCharge) {
		this.prescriptionCharge = prescriptionCharge;
	}

	@Column(name = "paid_form_old")
	public int getPaidFormOld() {
		return paidFormOld;
	}

	public void setPaidFormOld(int paidFormOld) {
		this.paidFormOld = paidFormOld;
	}

	@Column(name = "paid_item_old")
	public int getPaidItemOld() {
		return paidItemOld;
	}

	public void setPaidItemOld(int paidItemOld) {
		this.paidItemOld = paidItemOld;
	}

	@Column(name = "no_charge_item_old")
	public int getNoChargeItemOld() {
		return noChargeItemOld;
	}

	public void setNoChargeItemOld(int noChargeItemOld) {
		this.noChargeItemOld = noChargeItemOld;
	}

	@Column(name = "prescription_charge_old")
	public double getPrescriptionChargeOld() {
		return prescriptionChargeOld;
	}

	public void setPrescriptionChargeOld(double prescriptionChargeOld) {
		this.prescriptionChargeOld = prescriptionChargeOld;
	}

	@Column(name = "exempt_form")
	public int getExemptForm() {
		return exemptForm;
	}

	public void setExemptForm(int exemptForm) {
		this.exemptForm = exemptForm;
	}

	@Column(name = "exempt_item")
	public int getExemptItem() {
		return exemptItem;
	}

	public void setExemptItem(int exemptItem) {
		this.exemptItem = exemptItem;
	}

	@Column(name = "contraceptive_forms")
	public int getContraceptiveForm() {
		return contraceptiveForm;
	}

	public void setContraceptiveForm(int contraceptiveForm) {
		this.contraceptiveForm = contraceptiveForm;
	}

	@Column(name = "contraceptive_items")
	public int getContraceptiveItem() {
		return contraceptiveItem;
	}

	public void setContraceptiveItem(int contraceptiveItem) {
		this.contraceptiveItem = contraceptiveItem;
	}

	@Column(name = "private_prescription_items")
	public int getPrivatePrescriptionItems() {
		return privatePrescriptionItems;
	}

	public void setPrivatePrescriptionItems(int privatePrescriptionItems) {
		this.privatePrescriptionItems = privatePrescriptionItems;
	}

	@Column(name = "rivate_prescription_value")
	public double getPrivatePrescriptionValue() {
		return privatePrescriptionValue;
	}

	public void setPrivatePrescriptionValue(double privatePrescriptionValue) {
		this.privatePrescriptionValue = privatePrescriptionValue;
	}

	@Column(name = "other_prescription_items")
	public int getOtherPrescriptionItems() {
		return otherPrescriptionItems;
	}

	public void setOtherPrescriptionItems(int otherPrescriptionItems) {
		this.otherPrescriptionItems = otherPrescriptionItems;
	}

	@Column(name = "other_prescription_value")
	public double getOtherPrescriptionValue() {
		return otherPrescriptionValue;
	}

	public void setOtherPrescriptionValue(double otherPrescriptionValue) {
		this.otherPrescriptionValue = otherPrescriptionValue;
	}

	@Column(name = "other_prescription_vat")
	public double getOtherPrescriptionVat() {
		return otherPrescriptionVat;
	}

	public void setOtherPrescriptionVat(double otherPrescriptionVat) {
		this.otherPrescriptionVat = otherPrescriptionVat;
	}

	@Column(name = "ncso_items")
	public int getNcsoItems() {
		return ncsoItems;
	}

	public void setNcsoItems(int ncsoItems) {
		this.ncsoItems = ncsoItems;
	}

	@Column(name = "user_id", length = 10)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "created_by", length = 30)
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

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Column(name = "submisform")
	public int getSubMisForm() {
		return subMisForm;
	}

	public void setSubMisForm(int subMisForm) {
		this.subMisForm = subMisForm;
	}

	@Column(name = "submisitems")
	public int getSubMisItems() {
		return subMisItems;
	}

	public void setSubMisItems(int subMisItems) {
		this.subMisItems = subMisItems;
	}

	@Column(name = "repdisform")
	public int getRepDisForm() {
		return repDisForm;
	}

	public void setRepDisForm(int repDisForm) {
		this.repDisForm = repDisForm;
	}

	@Column(name = "repdisitems")
	public int getRepDisItems() {
		return repDisItems;
	}

	public void setRepDisItems(int repDisItems) {
		this.repDisItems = repDisItems;
	}
	@Column(name = "submisPatients")
	public int getSubMisPatients() {
		return subMisPatients;
	}

	public void setSubMisPatients(int subMisPatients) {
		this.subMisPatients = subMisPatients;
	}
	@Column(name = "repdisPatients")
	public int getRepDisPatients() {
		return repDisPatients;
	}

	public void setRepDisPatients(int repDisPatients) {
		this.repDisPatients = repDisPatients;
	}

}
