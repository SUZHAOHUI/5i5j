package com.oio.wawj.service.impl;

import java.util.Map;

import com.oio.wawj.bean.CollectionId;
import com.oio.wawj.dao.CollectionIdDAO;
import com.oio.wawj.service.CollectionIdService;

public class CollectionIdServiceImpl implements CollectionIdService {

	private CollectionIdDAO dao;
	

	public CollectionIdDAO getDao() {
		return dao;
	}

	public void setDao(CollectionIdDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(String setId, String descr, String descrShort,
			String transferId, String batchNum, String pushDataType,
			String transferDateTime) {
		// TODO Auto-generated method stub

	    CollectionId cId = new CollectionId();
	    
	    cId.setSetId(setId);
	    cId.setDescr(descr);
	    cId.setDescrShort(descrShort);
	    cId.setTransferId(transferId);
	    cId.setBatchNum(batchNum);
	    cId.setPushDataType(pushDataType);
	    cId.setTransferDateTime(transferDateTime);

		dao.save(cId);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		dao.flush();
	}

	@Override
	public String findCollectionId() {

		return dao.getfindCollectionId();
	}
	
	public String findCollectionIdBySetId(String setId) {
		
		return dao.getfindCollectionIdBySetId(setId);
	}

	@Override
	public String findCollectionIdAddUser(Map param) {
		// TODO Auto-generated method stub
		return dao.getfindCollectionIdAddUser(param);
	}




}
