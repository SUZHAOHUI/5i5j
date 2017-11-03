package com.oio.wawj.service.impl;

import com.oio.wawj.bean.DutyInfo;
import com.oio.wawj.dao.DutyInfoDAO;
import com.oio.wawj.service.DutyInfoService;

public class DutyInfoServiceImpl implements DutyInfoService {

	private DutyInfoDAO dao;
	
	
	public DutyInfoDAO getDao() {
		return dao;
	}


	public void setDao(DutyInfoDAO dao) {
		this.dao = dao;
	}


	@Override
	public void save(String setId, String jobCode, String effDt,
			String effStatus, String descr, String descrShort,
			String transferId, String batchNum, String pushDataType,
			String transferDateTime,String cHoldingNm) {
		// TODO Auto-generated method stub
		
	    DutyInfo di = new DutyInfo(); 
	    
        di.setSetId(setId);        
		di.setJobCode(jobCode);  
		di.setEffdt(effDt);
		di.setEffStatus(effStatus);
		di.setDescr(descr);
		di.setDescrShort(descrShort);
		  
	    di.setTransferId(transferId);
	    di.setBatchNum(batchNum);
	    di.setPushDataType(pushDataType);
	    di.setTransferDateTime(transferDateTime);
	    di.setCHoldingNm(cHoldingNm);
		
		dao.save(di);
		
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		dao.flush();
	}



}
