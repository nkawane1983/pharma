package com.avee.form;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	
	public int getCareHomeId() {
		return careHomeId;
	}

	public void setCareHomeId(int careHomeId) {
		this.careHomeId = careHomeId;
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

	public boolean getMarkDelete() {
		return markDelete;
	}

	public void setMarkDelete(boolean markDelete) {
		this.markDelete = markDelete;
	}

}
