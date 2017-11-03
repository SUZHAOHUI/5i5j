package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * RoleFunctions entity. @author MyEclipse Persistence Tools
 */

public class RoleFunctions implements java.io.Serializable {

	// Fields

	private RoleFunctionsId id;
	private Long userid;
	private String status;
	private Timestamp statusDate;

	// Constructors

	/** default constructor */
	public RoleFunctions() {
	}

	/** minimal constructor */
	public RoleFunctions(RoleFunctionsId id) {
		this.id = id;
	}

	/** full constructor */
	public RoleFunctions(RoleFunctionsId id, Long userid, String status,
			Timestamp statusDate) {
		this.id = id;
		this.userid = userid;
		this.status = status;
		this.statusDate = statusDate;
	}

	// Property accessors

	public RoleFunctionsId getId() {
		return this.id;
	}

	public void setId(RoleFunctionsId id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Timestamp statusDate) {
		this.statusDate = statusDate;
	}

}