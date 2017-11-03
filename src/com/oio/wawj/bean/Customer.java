package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private Long custId;
	private String code;
	private String name;
	private String gender;
	private Short sourceId;
	private String memo2;
	private Short clevel;
	private Short plevel;
	private String belongWith;
	private Short customerStatusId;
	private String situation;
	private String tel;
	private Integer userId;
	private String memo1;
	private Timestamp createDate;
	private String comments;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(String code, String name, String gender, Short sourceId,
			String memo2, Short clevel, Short plevel, String belongWith,
			Short customerStatusId, String situation, String tel,
			Integer userId, String memo1, Timestamp createDate, String comments) {
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.sourceId = sourceId;
		this.memo2 = memo2;
		this.clevel = clevel;
		this.plevel = plevel;
		this.belongWith = belongWith;
		this.customerStatusId = customerStatusId;
		this.situation = situation;
		this.tel = tel;
		this.userId = userId;
		this.memo1 = memo1;
		this.createDate = createDate;
		this.comments = comments;
	}

	// Property accessors

	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
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

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Short getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Short sourceId) {
		this.sourceId = sourceId;
	}

	public String getMemo2() {
		return this.memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public Short getClevel() {
		return this.clevel;
	}

	public void setClevel(Short clevel) {
		this.clevel = clevel;
	}

	public Short getPlevel() {
		return this.plevel;
	}

	public void setPlevel(Short plevel) {
		this.plevel = plevel;
	}

	public String getBelongWith() {
		return this.belongWith;
	}

	public void setBelongWith(String belongWith) {
		this.belongWith = belongWith;
	}

	public Short getCustomerStatusId() {
		return this.customerStatusId;
	}

	public void setCustomerStatusId(Short customerStatusId) {
		this.customerStatusId = customerStatusId;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}