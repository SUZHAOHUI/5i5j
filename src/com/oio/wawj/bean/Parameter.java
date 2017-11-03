package com.oio.wawj.bean;

/**
 * Parameter entity. @author MyEclipse Persistence Tools
 */

public class Parameter implements java.io.Serializable {

	// Fields

	private Integer parameterId;
	private Short sectionId;
	private String section;
	private Short itemId;
	private String item;
	private String value;
	private String parameter;

	// Constructors

	/** default constructor */
	public Parameter() {
	}

	/** full constructor */
	public Parameter(Short sectionId, String section, Short itemId,
			String item, String value, String parameter) {
		this.sectionId = sectionId;
		this.section = section;
		this.itemId = itemId;
		this.item = item;
		this.value = value;
		this.parameter = parameter;
	}

	// Property accessors

	public Integer getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public Short getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Short sectionId) {
		this.sectionId = sectionId;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Short getItemId() {
		return this.itemId;
	}

	public void setItemId(Short itemId) {
		this.itemId = itemId;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}