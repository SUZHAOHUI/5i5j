package com.oio.wawj.service.impl;

import com.oio.wawj.bean.EmpDuty;
import com.oio.wawj.dao.EmpDutyDAO;
import com.oio.wawj.service.EmpDutyService;

public class EmpDutyServiceImpl implements EmpDutyService {

	private EmpDutyDAO dao;

	
	public EmpDutyDAO getDao() {
		return dao;
	}



	public void setDao(EmpDutyDAO dao) {
		this.dao = dao;
	}



	@Override
	public void save(String action, String batchNum, String company,
			String cQuartersId, String cWorkShop, String deptId, String emplId,
			String emplRcd, String hrStatus, String jobCode,
			String pushDataType, String setidDept, String transferId,
			String cWorkShopDeptId, String supervisorId, String setIdJobCode,
			String effDt, String effSeq,String transferDateTime) {
		// TODO Auto-generated method stub
	    EmpDuty ed = new EmpDuty();
	    ed.setAction(action);
	    ed.setBatchNum(batchNum);
	    ed.setCompany(company);
	    ed.setCQuartersId(cQuartersId);
	    ed.setCWorkShop(cWorkShop);
	    ed.setDeptId(deptId);

	    ed.setEmplId(emplId);
	    ed.setEmplRcd(emplRcd);
	    ed.setHrStatus(hrStatus);
	    ed.setJobCode(jobCode);
	    ed.setPushDataType(pushDataType);
	    ed.setSetidDept(setidDept);
	    ed.setTransferId(transferId);
	    ed.setCWorkShopDeptId(cWorkShopDeptId);
	    ed.setSupervisorId(supervisorId);
	    ed.setSetidJobCode(setIdJobCode);
	    ed.setEffdt(effDt);
	    ed.setEffseq(effSeq);
	    ed.setTransferDateTime(transferDateTime);
	    
	    dao.save(ed);
	}



	@Override
	public void flush() {
		// TODO Auto-generated method stub
		dao.flush();
	}




}
