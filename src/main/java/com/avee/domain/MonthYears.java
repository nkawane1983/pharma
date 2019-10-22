package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "monthYears")
public class MonthYears implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date eventDate;
	private int groupId;

	public MonthYears() {

	}

	public MonthYears(int id, Date eventDate, int groupId) {

		this.id = id;
		this.eventDate = eventDate;
		this.groupId = groupId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "event_date",  length = 27)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	@Column(name = "group_id")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
