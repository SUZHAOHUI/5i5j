package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * Org entity. @author MyEclipse Persistence Tools
 */

public class Org implements java.io.Serializable {

	// Fields

	private OrgId id;
	private Long parentOrgId;
	private String orgName;
	private String orgType;
	private String status;
	private Timestamp statusDate;
	private Long operator;

	// Constructors

	/** default constructor */
	public Org() {
	}

	/** minimal constructor */
	public Org(OrgId id) {
		this.id = id;
	}

	/** full constructor */
	public Org(OrgId id, Long parentOrgId, String orgName, String orgType,
			String status, Timestamp statusDate, Long operator) {
		this.id = id;
		this.parentOrgId = parentOrgId;
		this.orgName = orgName;
		this.orgType = orgType;
		this.status = status;
		this.statusDate = statusDate;
		this.operator = operator;
	}

	// Property accessors

	public OrgId getId() {
		return this.id;
	}

	public void setId(OrgId id) {
		this.id = id;
	}

	public Long getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
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

	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

}