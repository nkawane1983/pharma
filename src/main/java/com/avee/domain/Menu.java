package com.avee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "menu")
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "menu_type", length = 30)
	public String getMenuType() {
		return menuType;
	}



	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	@Column(name = "menu_element_name", length = 512)
	public String getMenuElementName() {
		return menuElementName;
	}

	public void setMenuElementName(String menuElementName) {
		this.menuElementName = menuElementName;
	}
	@Column(name = "menu_element_url", length = 200)
	public String getMenuElementURL() {
		return menuElementURL;
	}

	public void setMenuElementURL(String menuElementURL) {
		this.menuElementURL = menuElementURL;
	}
	@Column(name = "menu_element_extra", length = 200)
	public String getMenuElementExtra() {
		return menuElementExtra;
	}

	public void setMenuElementExtra(String menuElementExtra) {
		this.menuElementExtra = menuElementExtra;
	}
	@Column(name = "menu_description", length = 50)
	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	@Column(name = "menu_key", length = 20)
	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	@Column(name = "menu_order_by")
	public int getMenuOrderBy() {
		return menuOrderBy;
	}

	public void setMenuOrderBy(int menuOrderBy) {
		this.menuOrderBy = menuOrderBy;
	}
	@Column(name = "parent_key", length = 20)
	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	@Column(name = "display_menu_name", length = 30)
	public String getDisplayMenuName() {
		return displayMenuName;
	}

	public void setDisplayMenuName(String displayMenuName) {
		this.displayMenuName = displayMenuName;
	}
	@Column(name = "menu_icon", length = 30)
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "created_by", length = 30, updatable = false)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 7, updatable = false)
	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 7)
	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
