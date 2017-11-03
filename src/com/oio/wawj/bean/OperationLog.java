package com.oio.wawj.bean;

import java.sql.Timestamp;

/**
 * OperationLog entity. @author MyEclipse Persistence Tools
 */

public class OperationLog implements java.io.Serializable {

	// Fields

	private Long logId;
	private Long userId;
	private String functionName;
	private String statement;
	private Timestamp createTime;
	private String comments;

	// Constructors

	/** default constructor */
	public OperationLog() {
	}

	/** full constructor */
	public OperationLog(Long userId, String functionName, String statement,
			Timestamp createTime, String comments) {
		this.userId = userId;
		this.functionName = functionName;
		this.statement = statement;
		this.createTime = createTime;
		this.comments = comments;
	}

	// Property accessors

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getStatement() {
		return this.statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}