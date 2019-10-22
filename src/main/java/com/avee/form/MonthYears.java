package com.avee.form;

import java.util.Date;

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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
