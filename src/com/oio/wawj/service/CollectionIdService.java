package com.oio.wawj.service;

import java.util.Map;

import com.oio.wawj.bean.CollectionId;

public interface CollectionIdService {

	public void save(String setId,String descr,String descrShort,String transferId,String batchNum,String pushDataType,String transferDateTime);

	public String findCollectionId();
	
	public String findCollectionIdBySetId(String setId);
	
	public String findCollectionIdAddUser(Map param);
	
	public void flush();
}
