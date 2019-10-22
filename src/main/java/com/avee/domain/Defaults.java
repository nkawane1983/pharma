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
@Table(name = "default")
public class Defaults implements java.io.Serializable {

	private int id;
	private String defaultName;
	private String defaultFor;
	private boolean multiValue;
	private boolean markDelete;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private int defaultOrderNo;
	private String defaultDisplayName;

	public Defaults() {

	}

	public Defaults(String defaultName, String defaultFor, boolean multiValue, boolean markDelete, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate, int defaultOrderNo, String defaultDisplayName) {
		this.defaultName = defaultName;
		this.defaultFor = defaultFor;
		this.multiValue = multiValue;
		this.markDelete = markDelete;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.defaultOrderNo = defaultOrderNo;
		this.defaultDisplayName = defaultDisplayName;
	}

	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "default_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "default_name")
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	@Column(name = "default_for")
	public String getDefaultFor() {
		return defaultFor;
	}

	public void setDefaultFor(String defaultFor) {
		this.defaultFor = defaultFor;
	}

	@Column(name = "multi_value")
	public boolean isMultiValue() {
		return multiValue;
	}

	public void setMultiValue(boolean multiValue) {
		this.multiValue = multiValue;
	}

	@Column(name = "mark_delete")
	public boolean isMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "default_order_no")
	public int getDefaultOrderNo() {
		return defaultOrderNo;
	}

	public void setDefaultOrderNo(int defaultOrderNo) {
		this.defaultOrderNo = defaultOrderNo;
	}

	@Column(name = "display_name")
	public String getDefaultDisplayName() {
		return defaultDisplayName;
	}

	public void setDefaultDisplayName(String defaultDisplayName) {
		this.defaultDisplayName = defaultDisplayName;
	}
}
