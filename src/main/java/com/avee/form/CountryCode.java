package com.avee.form;

@SuppressWarnings("serial")
public class CountryCode implements java.io.Serializable{
	private int id;
	private String countryName;

	public CountryCode() {
		super();
	}

	public CountryCode(int id, String countryName) {
		super();
		this.id = id;
		this.countryName = countryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
