package com.oio.wawj.bean;

/**
 * Erp entity. @author MyEclipse Persistence Tools
 */

public class Erp implements java.io.Serializable {

	// Fields

	private Long id;
	private String emplId;
	private String CMobile;
	private String UName;
	private String DName;

	// Constructors

	/** default constructor */
	public Erp() {
	}

	/** full constructor */
	public Erp(String emplId, String CMobile, String UName, String DName) {
		this.emplId = emplId;
		this.CMobile = CMobile;
		this.UName = UName;
		this.DName = DName;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmplId() {
		return this.emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getCMobile() {
		return this.CMobile;
	}

	public void setCMobile(String CMobile) {
		this.CMobile = CMobile;
	}

	public String getUName() {
		return this.UName;
	}

	public void setUName(String UName) {
		this.UName = UName;
	}

	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

}