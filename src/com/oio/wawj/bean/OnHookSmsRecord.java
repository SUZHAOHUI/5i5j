package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * OnHookSmsRecord entity. @author MyEclipse Persistence Tools
 */

public class OnHookSmsRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private Timestamp timestamp;
	private String targetNumber;
	private String state;
	private String scenario;
	private String content;
	private String callId;
	private String userName;
	private String chargeInfo;
	private String sequence;
	private String reportState;
	private Short cntSms;
	private Long userId;

	// Constructors

	/** default constructor */
	public OnHookSmsRecord() {
	}

	/** full constructor */
	public OnHookSmsRecord(String targetNumber, String state, String scenario,
			String content, String callId, String userName, String chargeInfo,
			String sequence, String reportState, Short cntSms, Long userId) {
		this.targetNumber = targetNumber;
		this.state = state;
		this.scenario = scenario;
		this.content = content;
		this.callId = callId;
		this.userName = userName;
		this.chargeInfo = chargeInfo;
		this.sequence = sequence;
		this.reportState = reportState;
		this.cntSms = cntSms;
		this.userId = userId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTargetNumber() {
		return this.targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCallId() {
		return this.callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChargeInfo() {
		return this.chargeInfo;
	}

	public void setChargeInfo(String chargeInfo) {
		this.chargeInfo = chargeInfo;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getReportState() {
		return this.reportState;
	}

	public void setReportState(String reportState) {
		this.reportState = reportState;
	}

	public Short getCntSms() {
		return this.cntSms;
	}

	public void setCntSms(Short cntSms) {
		this.cntSms = cntSms;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}