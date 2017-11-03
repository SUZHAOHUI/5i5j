package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */

public class UserRole implements java.io.Serializable {

	// Fields

	private UserRoleId id;
	private String status;
	private Timestamp statusDate;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** minimal constructor */
	public UserRole(UserRoleId id) {
		this.id = id;
	}

	/** full constructor */
	public UserRole(UserRoleId id, String status, Timestamp statusDate) {
		this.id = id;
		this.status = status;
		this.statusDate = statusDate;
	}

	// Property accessors

	public UserRoleId getId() {
		return this.id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
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