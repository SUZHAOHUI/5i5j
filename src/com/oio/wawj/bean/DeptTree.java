package com.oio.wawj.bean;

/**
 * DeptTree entity. @author MyEclipse Persistence Tools
 */

public class DeptTree implements java.io.Serializable {

	// Fields

	private Long id;
	private String transferId;
	private String batchNum;
	private String transferDateTime;
	private String pushDataType;
	private String setId;
	private String effdt;
	private String treeName;
	private String treeNode;
	private String parentNodeName;
	private String treeLevelNum;

	// Constructors

	/** default constructor */
	public DeptTree() {
	}

	/** full constructor */
	public DeptTree(String transferId, String batchNum,
			String transferDateTime, String pushDataType, String setId,
			String effdt, String treeName, String treeNode,
			String parentNodeName, String treeLevelNum) {
		this.transferId = transferId;
		this.batchNum = batchNum;
		this.transferDateTime = transferDateTime;
		this.pushDataType = pushDataType;
		this.setId = setId;
		this.effdt = effdt;
		this.treeName = treeName;
		this.treeNode = treeNode;
		this.parentNodeName = parentNodeName;
		this.treeLevelNum = treeLevelNum;
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

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getEffdt() {
		return this.effdt;
	}

	public void setEffdt(String effdt) {
		this.effdt = effdt;
	}

	public String getTreeName() {
		return this.treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getTreeNode() {
		return this.treeNode;
	}

	public void setTreeNode(String treeNode) {
		this.treeNode = treeNode;
	}

	public String getParentNodeName() {
		return this.parentNodeName;
	}

	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}

	public String getTreeLevelNum() {
		return this.treeLevelNum;
	}

	public void setTreeLevelNum(String treeLevelNum) {
		this.treeLevelNum = treeLevelNum;
	}

}