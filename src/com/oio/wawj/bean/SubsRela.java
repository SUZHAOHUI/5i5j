package com.oio.wawj.bean;

import java.util.Date;

/**
 * SubsRela entity. @author MyEclipse Persistence Tools
 */

public class SubsRela implements java.io.Serializable {

	// Fields

	private long id;
	private String anum;
	private String xnum;
	private String bnum;
	private String subId;
	private Integer userId;
	private String msg;
	private String state;
	private Date stateDate;
	private Integer operator;

	// Constructors

	/** default constructor */
	public SubsRela() {
	}

	/** full constructor */
	public SubsRela(String anum, String xnum, String bnum, String subId,
			Integer userId, String msg, String state, Date stateDate,
			Integer operator) {
		this.anum = anum;
		this.xnum = xnum;
		this.bnum = bnum;
		this.subId = subId;
		this.userId = userId;
		this.msg = msg;
		this.state = state;
		this.stateDate = stateDate;
		this.operator = operator;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnum() {
		return this.anum;
	}

	public void setAnum(String anum) {
		this.anum = anum;
	}

	public String getXnum() {
		return this.xnum;
	}

	public void setXnum(String xnum) {
		this.xnum = xnum;
	}

	public String getBnum() {
		return this.bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStateDate() {
		return this.stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

}