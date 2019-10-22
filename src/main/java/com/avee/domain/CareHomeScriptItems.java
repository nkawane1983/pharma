package com.avee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "care_home_script_items")
@SuppressWarnings("serial")
public class CareHomeScriptItems implements java.io.Serializable {
	private int id;
	private int scriptId;
	private int careHomeId;
	private int sevenForm;
	private int sevenItems;
	private int twentyEightForm;
	private int twentyEightItems;
	private boolean markDelete;

	public CareHomeScriptItems() {
		super();
	}

	

	public CareHomeScriptItems(int id, int scriptId, int careHomeId, int sevenForm, int sevenItems, int twentyEightForm,
			int twentyEightItems, boolean markDelete) {
		super();
		this.id = id;
		this.scriptId = scriptId;
		this.careHomeId = careHomeId;
		this.sevenForm = sevenForm;
		this.sevenItems = sevenItems;
		this.twentyEightForm = twentyEightForm;
		this.twentyEightItems = twentyEightItems;
		this.markDelete = markDelete;
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

	@Column(name = "script_id")
	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	@Column(name = "carehome_id")
	public int getCareHomeId() {
		return careHomeId;
	}

	public void setCareHomeId(int careHomeId) {
		this.careHomeId = careHomeId;
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

}
