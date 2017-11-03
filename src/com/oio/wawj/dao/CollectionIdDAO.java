package com.oio.wawj.dao;

import java.util.Map;

import com.oio.wawj.bean.CollectionId;

public interface CollectionIdDAO extends BaseDAO<CollectionId,String> {

	public String getfindCollectionId();
	
	public String getfindCollectionIdBySetId(String setId);
	
	public String getfindCollectionIdAddUser(Map param);

}
