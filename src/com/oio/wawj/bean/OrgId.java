package com.oio.wawj.bean;

/**
 * OrgId entity. @author MyEclipse Persistence Tools
 */

public class OrgId implements java.io.Serializable {

	// Fields

	private Integer orgId;
	private String setId;

	// Constructors

	/** default constructor */
	public OrgId() {
	}

	/** full constructor */
	public OrgId(Integer orgId, String setId) {
		this.orgId = orgId;
		this.setId = setId;
	}

	// Property accessors

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrgId))
			return false;
		OrgId castOther = (OrgId) other;

		return ((this.getOrgId() == castOther.getOrgId()) || (this.getOrgId() != null
				&& castOther.getOrgId() != null && this.getOrgId().equals(
				castOther.getOrgId())))
				&& ((this.getSetId() == castOther.getSetId()) || (this
						.getSetId() != null && castOther.getSetId() != null && this
						.getSetId().equals(castOther.getSetId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgId() == null ? 0 : this.getOrgId().hashCode());
		result = 37 * result
				+ (getSetId() == null ? 0 : this.getSetId().hashCode());
		return result;
	}

}