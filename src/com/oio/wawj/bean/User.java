package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Long userId;
	private Long orgId;
	private String code;
	private String name;
	private String address;
	private String tel;
	private String sex;
	private String idcard;
	private String email;
	private String password;
	private Short dataPermission;
	private Short functionPermission;
	private String type;
	private String duty;
	private String status;
	private Timestamp statusDate;
	private String setId;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Long orgId, String code, String name, String address,
			String tel, String sex, String idcard, String email,
			String password, Short dataPermission, Short functionPermission,
			String type, String duty, String status, Timestamp statusDate,
			String setId) {
		this.orgId = orgId;
		this.code = code;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.sex = sex;
		this.idcard = idcard;
		this.email = email;
		this.password = password;
		this.dataPermission = dataPermission;
		this.functionPermission = functionPermission;
		this.type = type;
		this.duty = duty;
		this.status = status;
		this.statusDate = statusDate;
		this.setId = setId;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getDataPermission() {
		return this.dataPermission;
	}

	public void setDataPermission(Short dataPermission) {
		this.dataPermission = dataPermission;
	}

	public Short getFunctionPermission() {
		return this.functionPermission;
	}

	public void setFunctionPermission(Short functionPermission) {
		this.functionPermission = functionPermission;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
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

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

}