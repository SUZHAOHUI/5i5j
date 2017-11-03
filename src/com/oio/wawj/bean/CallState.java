package com.oio.wawj.bean;

/**
 * CallState entity. @author MyEclipse Persistence Tools
 */

public class CallState implements java.io.Serializable {

	// Fields

	private short id;
	private String name;

	// Constructors

	/** default constructor */
	public CallState() {
	}

	/** full constructor */
	public CallState(String name) {
		this.name = name;
	}

	// Property accessors

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}