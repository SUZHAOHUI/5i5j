package com.oio.wawj.service.impl;

import com.oio.wawj.bean.EmpDismission;
import com.oio.wawj.dao.EmpDismissionDAO;
import com.oio.wawj.service.EmpDismissionService;

public class EmpDismissionServiceImpl implements EmpDismissionService{

	private EmpDismissionDAO dao;
	
	

	public EmpDismissionDAO getDao() {
		return dao;
	}



	public void setDao(EmpDismissionDAO dao) {
		this.dao = dao;
	}



	@Override
	public void save(String batchNum, String cApproveStatus,
			String cLeaveStatus, String emplId, String pushDataType,
			String transferDateTime, String transferId, String setid) {
		// TODO Auto-generated method stub
	   EmpDismission ed = new EmpDismission();
		   
	    ed.setBatchNum(batchNum);
	    ed.setCApproveStatus(cApproveStatus);
	    ed.setCLeaveStatus(cLeaveStatus);
	    ed.setEmplId(emplId);
	    ed.setPushDataType(pushDataType);
	    ed.setTransferDateTime(transferDateTime);
	    ed.setTransferId(transferId);
	    ed.setSetId(setid);
	    
	    dao.save(ed);
	}



	@Override
	public void flush() {
		// TODO Auto-generated method stub
		dao.flush();
	}




}
