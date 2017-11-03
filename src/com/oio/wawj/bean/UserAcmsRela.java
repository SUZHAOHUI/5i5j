package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * UserAcmsRela entity. @author MyEclipse Persistence Tools
 */

public class UserAcmsRela implements java.io.Serializable {

	// Fields

	private Integer acmsUserId;
	private Integer userId;
	private Integer channelId;
	private Integer acmsId;
	private String state;
	private Timestamp stateDate;
	private String operatorId;
	private String purpose;

	// Constructors

	/** default constructor */
	public UserAcmsRela() {
	}

	/** full constructor */
	public UserAcmsRela(Integer userId, Integer channelId, Integer acmsId,
			String state, Timestamp stateDate, String operatorId, String purpose) {
		this.userId = userId;
		this.channelId = channelId;
		this.acmsId = acmsId;
		this.state = state;
		this.stateDate = stateDate;
		this.operatorId = operatorId;
		this.purpose = purpose;
	}

	// Property accessors

	public Integer getAcmsUserId() {
		return this.acmsUserId;
	}

	public void setAcmsUserId(Integer acmsUserId) {
		this.acmsUserId = acmsUserId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getAcmsId() {
		return this.acmsId;
	}

	public void setAcmsId(Integer acmsId) {
		this.acmsId = acmsId;
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

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}