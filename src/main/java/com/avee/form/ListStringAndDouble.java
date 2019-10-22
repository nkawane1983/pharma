package com.avee.form;

public class ListStringAndDouble {

	private int id;
	private String listName;
	private double listValue;

	public ListStringAndDouble() {

	}

	public ListStringAndDouble(int id, String listName, double listValue) {
		this.id = id;
		this.listName = listName;
		this.listValue = listValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public double getListValue() {
		return listValue;
	}

	public void setListValue(double listValue) {
		this.listValue = listValue;
	}

	@Override
	public String toString() {
		return "ListStringAndDouble [id=" + id + ", listName=" + listName + ", listValue=" + listValue + "]";
	}

}
