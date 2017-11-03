package com.oio.wawj.bean;

/**
 * EmpDuty entity. @author MyEclipse Persistence Tools
 */

public class EmpDuty implements java.io.Serializable {

	// Fields

	private Long id;
	private String transferId;
	private String batchNum;
	private String transferDateTime;
	private String pushDataType;
	private String emplId;
	private String emplRcd;
	private String effdt;
	private String effseq;
	private String setidDept;
	private String deptId;
	private String setidJobCode;
	private String company;
	private String jobCode;
	private String CQuartersId;
	private String supervisorId;
	private String hrStatus;
	private String CWorkShop;
	private String CWorkShopDeptId;
	private String action;

	// Constructors

	/** default constructor */
	public EmpDuty() {
	}

	/** full constructor */
	public EmpDuty(String transferId, String batchNum, String transferDateTime,
			String pushDataType, String emplId, String emplRcd, String effdt,
			String effseq, String setidDept, String deptId,
			String setidJobCode, String company, String jobCode,
			String CQuartersId, String supervisorId, String hrStatus,
			String CWorkShop, String CWorkShopDeptId, String action) {
		this.transferId = transferId;
		this.batchNum = batchNum;
		this.transferDateTime = transferDateTime;
		this.pushDataType = pushDataType;
		this.emplId = emplId;
		this.emplRcd = emplRcd;
		this.effdt = effdt;
		this.effseq = effseq;
		this.setidDept = setidDept;
		this.deptId = deptId;
		this.setidJobCode = setidJobCode;
		this.company = company;
		this.jobCode = jobCode;
		this.CQuartersId = CQuartersId;
		this.supervisorId = supervisorId;
		this.hrStatus = hrStatus;
		this.CWorkShop = CWorkShop;
		this.CWorkShopDeptId = CWorkShopDeptId;
		this.action = action;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransferId() {
		return this.transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getBatchNum() {
		return this.batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getTransferDateTime() {
		return this.transferDateTime;
	}

	public void setTransferDateTime(String transferDateTime) {
		this.transferDateTime = transferDateTime;
	}

	public String getPushDataType() {
		return this.pushDataType;
	}

	public void setPushDataType(String pushDataType) {
		this.pushDataType = pushDataType;
	}

	public String getEmplId() {
		return this.emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getEmplRcd() {
		return this.emplRcd;
	}

	public void setEmplRcd(String emplRcd) {
		this.emplRcd = emplRcd;
	}

	public String getEffdt() {
		return this.effdt;
	}

	public void setEffdt(String effdt) {
		this.effdt = effdt;
	}

	public String getEffseq() {
		return this.effseq;
	}

	public void setEffseq(String effseq) {
		this.effseq = effseq;
	}

	public String getSetidDept() {
		return this.setidDept;
	}

	public void setSetidDept(String setidDept) {
		this.setidDept = setidDept;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSetidJobCode() {
		return this.setidJobCode;
	}

	public void setSetidJobCode(String setidJobCode) {
		this.setidJobCode = setidJobCode;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getCQuartersId() {
		return this.CQuartersId;
	}

	public void setCQuartersId(String CQuartersId) {
		this.CQuartersId = CQuartersId;
	}

	public String getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getHrStatus() {
		return this.hrStatus;
	}

	public void setHrStatus(String hrStatus) {
		this.hrStatus = hrStatus;
	}

	public String getCWorkShop() {
		return this.CWorkShop;
	}

	public void setCWorkShop(String CWorkShop) {
		this.CWorkShop = CWorkShop;
	}

	public String getCWorkShopDeptId() {
		return this.CWorkShopDeptId;
	}

	public void setCWorkShopDeptId(String CWorkShopDeptId) {
		this.CWorkShopDeptId = CWorkShopDeptId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}