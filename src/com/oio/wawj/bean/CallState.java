package com.oio.wawj.bean;

/**
 * CallState entity. @author MyEclipse Persistence Tools
 */

public class CallState implements java.io.Serializable {

	// Fields

	private Short id;
	private String desc;
	private String name;

	// Constructors

	/** default constructor */
	public CallState() {
	}

	/** minimal constructor */
	public CallState(Short id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	/** full constructor */
	public CallState(Short id, String desc, String name) {
		this.id = id;
		this.desc = desc;
		this.name = name;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}