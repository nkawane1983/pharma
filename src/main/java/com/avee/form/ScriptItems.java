package com.avee.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ScriptItems implements java.io.Serializable {
	private int id;
	private Date eventDate;
	private int branchId;
	private int sevenForm;
	private int sevenItems;
	private int twentyEightForm;
	private int twentyEightItems;
	private boolean markDelete;
	private String deleteComment;
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	private List<CareHomeScriptItems> careHomeScriptId = new ArrayList<CareHomeScriptItems>();

	private int noPatients;
	private int noCareHomePatients;

	public ScriptItems() {
		this.sevenForm = 0;
	}

	public ScriptItems(int id, Date eventDate, int branchId, int sevenForm, int sevenItems, int twentyEightForm,
			int twentyEightItems, boolean markDelete, String deleteComment, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt, List<CareHomeScriptItems> careHomeScriptId, int noPatients,
			int noCareHomePatients) {

		this.id = id;
		this.eventDate = eventDate;
		this.branchId = branchId;
		this.sevenForm = sevenForm;
		this.sevenItems = sevenItems;
		this.twentyEightForm = twentyEightForm;
		this.twentyEightItems = twentyEightItems;
		this.markDelete = markDelete;
		this.deleteComment = deleteComment;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
		this.careHomeScriptId = careHomeScriptId;
		this.noPatients = noPatients;
		this.noCareHomePatients = noCareHomePatients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getSevenForm() {
		return sevenForm;
	}

	public void setSevenForm(int sevenForm) {
		this.sevenForm = sevenForm;
	}

	public int getSevenItems() {
		return sevenItems;
	}

	public void setSevenItems(int sevenItems) {
		this.sevenItems = sevenItems;
	}

	public int getTwentyEightForm() {
		return twentyEightForm;
	}

	public void setTwentyEightForm(int twentyEightForm) {
		this.twentyEightForm = twentyEightForm;
	}

	public int getTwentyEightItems() {
		return twentyEightItems;
	}

	public void setTwentyEightItems(int twentyEightItems) {
		this.twentyEightItems = twentyEightItems;
	}

	public boolean isMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
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

	public List<CareHomeScriptItems> getCareHomeScriptId() {
		return careHomeScriptId;
	}

	public void setCareHomeScriptId(List<CareHomeScriptItems> careHomeScriptId) {
		this.careHomeScriptId = careHomeScriptId;
	}

	public int getNoPatients() {
		return noPatients;
	}

	public void setNoPatients(int noPatients) {
		this.noPatients = noPatients;
	}

	public int getNoCareHomePatients() {
		return noCareHomePatients;
	}

	public void setNoCareHomePatients(int noCareHomePatients) {
		this.noCareHomePatients = noCareHomePatients;
	}

}
