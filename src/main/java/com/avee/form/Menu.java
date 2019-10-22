package com.avee.form;

import java.util.Date;

@SuppressWarnings("serial")
public class Menu implements java.io.Serializable {
	private int id;
	private String menuType;
	private String menuElementName;
	private String menuElementURL;
	private String menuElementExtra;

	private String menuDesc;
	private String menuKey;
	private int menuOrderBy;
	private String parentKey;
	private String displayMenuName;
	private String menuIcon;

	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;

	public Menu() {
		super();
	}

	public Menu(int id, String menuType, String menuElementName, String menuElementURL, String menuElementExtra,
			String menuDesc, String menuKey, int menuOrderBy, String parentKey, String displayMenuName, String menuIcon,
			String createdBy, Date createdDt, String updatedBy, Date updatedDt) {
		super();
		this.id = id;
		this.menuType = menuType;
		this.menuElementName = menuElementName;
		this.menuElementURL = menuElementURL;
		this.menuElementExtra = menuElementExtra;
		this.menuDesc = menuDesc;
		this.menuKey = menuKey;
		this.menuOrderBy = menuOrderBy;
		this.parentKey = parentKey;
		this.displayMenuName = displayMenuName;
		this.menuIcon = menuIcon;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.updatedBy = updatedBy;
		this.updatedDt = updatedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuElementName() {
		return menuElementName;
	}

	public void setMenuElementName(String menuElementName) {
		this.menuElementName = menuElementName;
	}

	public String getMenuElementURL() {
		return menuElementURL;
	}

	public void setMenuElementURL(String menuElementURL) {
		this.menuElementURL = menuElementURL;
	}

	public String getMenuElementExtra() {
		return menuElementExtra;
	}

	public void setMenuElementExtra(String menuElementExtra) {
		this.menuElementExtra = menuElementExtra;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public int getMenuOrderBy() {
		return menuOrderBy;
	}

	public void setMenuOrderBy(int menuOrderBy) {
		this.menuOrderBy = menuOrderBy;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getDisplayMenuName() {
		return displayMenuName;
	}

	public void setDisplayMenuName(String displayMenuName) {
		this.displayMenuName = displayMenuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
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

}
