package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * Ringtone entity. @author MyEclipse Persistence Tools
 */

public class Ringtone implements java.io.Serializable {

	// Fields

	private Short id;
	private String code;
	private String name;
	private String savepath;
	private String state;
	private Timestamp stateDate;
	private Integer operator;
	private String setId;

	// Constructors

	/** default constructor */
	public Ringtone() {
	}

	/** full constructor */
	public Ringtone(String code, String name, String savepath, String state,
			Timestamp stateDate, Integer operator, String setId) {
		this.code = code;
		this.name = name;
		this.savepath = savepath;
		this.state = state;
		this.stateDate = stateDate;
		this.operator = operator;
		this.setId = setId;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSavepath() {
		return this.savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getStateDate() {
		return this.stateDate;
	}

	public void setStateDate(Timestamp stateDate) {
		this.stateDate = stateDate;
	}

	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

}