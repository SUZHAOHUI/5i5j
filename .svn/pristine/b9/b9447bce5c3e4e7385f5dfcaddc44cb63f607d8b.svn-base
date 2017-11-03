package com.oio.wawj.bean;

/**
 * RoleFunctionsId entity. @author MyEclipse Persistence Tools
 */

public class RoleFunctionsId implements java.io.Serializable {

	// Fields

	private Short roleId;
	private Short functionId;

	// Constructors

	/** default constructor */
	public RoleFunctionsId() {
	}

	/** full constructor */
	public RoleFunctionsId(Short roleId, Short functionId) {
		this.roleId = roleId;
		this.functionId = functionId;
	}

	// Property accessors

	public Short getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Short roleId) {
		this.roleId = roleId;
	}

	public Short getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Short functionId) {
		this.functionId = functionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleFunctionsId))
			return false;
		RoleFunctionsId castOther = (RoleFunctionsId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getFunctionId() == castOther.getFunctionId()) || (this
						.getFunctionId() != null
						&& castOther.getFunctionId() != null && this
						.getFunctionId().equals(castOther.getFunctionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getFunctionId() == null ? 0 : this.getFunctionId()
						.hashCode());
		return result;
	}

}