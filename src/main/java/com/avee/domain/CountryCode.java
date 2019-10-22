package com.avee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "county_codes")
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
	@Id
	//@SequenceGenerator(name = "my_seq", sequenceName = "county_codes_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "county_name", length = 50)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
