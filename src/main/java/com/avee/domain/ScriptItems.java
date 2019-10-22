package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "script_items")
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

	private int noPatients;
	private int noCareHomePatients;

	public ScriptItems() {
		super();
	}

	public ScriptItems(int id, Date eventDate, int branchId, int sevenForm, int sevenItems, int twentyEightForm,
			int twentyEightItems, boolean markDelete, String deleteComment, String createdBy, Date createdDt,
			String updatedBy, Date updatedDt, int noPatients, int noCareHomePatients) {
		super();
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
		this.noPatients = noPatients;
		this.noCareHomePatients = noCareHomePatients;

	}

	@Id
	// @SequenceGenerator(name = "my_seq", sequenceName = "scriptitems_id_seq",
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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "event_date")
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Column(name = "branch_id")
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Column(name = "seven_form")
	public int getSevenForm() {
		return sevenForm;
	}

	public void setSevenForm(int sevenForm) {
		this.sevenForm = sevenForm;
	}

	@Column(name = "seven_items")
	public int getSevenItems() {
		return sevenItems;
	}

	public void setSevenItems(int sevenItems) {
		this.sevenItems = sevenItems;
	}

	@Column(name = "twenty_eight_form")
	public int getTwentyEightForm() {
		return twentyEightForm;
	}

	public void setTwentyEightForm(int twentyEightForm) {
		this.twentyEightForm = twentyEightForm;
	}

	@Column(name = "twenty_eight_items")
	public int getTwentyEightItems() {
		return twentyEightItems;
	}

	public void setTwentyEightItems(int twentyEightItems) {
		this.twentyEightItems = twentyEightItems;
	}

	@Column(name = "mark_delete")
	public boolean getMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	@Column(name = "delete_comment", length = 70)
	public String getDeleteComment() {
		return deleteComment;
	}

	public void setDeleteComment(String deleteComment) {
		this.deleteComment = deleteComment;
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
	@Column(name = "no_patients")
	public int getNoPatients() {
		return noPatients;
	}

	public void setNoPatients(int noPatients) {
		this.noPatients = noPatients;
	}
	@Column(name = "no_carehome_patients")
	public int getNoCareHomePatients() {
		return noCareHomePatients;
	}

	public void setNoCareHomePatients(int noCareHomePatients) {
		this.noCareHomePatients = noCareHomePatients;
	}
}
