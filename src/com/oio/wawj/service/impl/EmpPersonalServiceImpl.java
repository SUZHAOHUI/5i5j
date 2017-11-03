package com.oio.wawj.service.impl;

import com.oio.wawj.bean.EmpPersonal;
import com.oio.wawj.dao.EmpPersonalDAO;
import com.oio.wawj.service.EmpPersonalService;

public class EmpPersonalServiceImpl implements EmpPersonalService {

	private EmpPersonalDAO dao;
	
	
	public EmpPersonalDAO getDao() {
		return dao;
	}


	public void setDao(EmpPersonalDAO dao) {
		this.dao = dao;
	}


	public void save(String emplId, String countryNmForm, String nameFormat,
			String sex, String highestEducLvl, String birthDate,
			String cMobile, String operator, String company, String cEmail,
			String cCompEmail, String setId, String transferId,
			String batchNum, String pushDataType, String transferDateTime) {
		// TODO Auto-generated method stub
							    //save
	    EmpPersonal ep = new EmpPersonal();
	    
	    ep.setEmplId(emplId);
	    ep.setCountryNmForm(countryNmForm);
	    ep.setNameFormat(nameFormat);
	    ep.setSex(sex);
	    ep.setHighestEducLvl(highestEducLvl);
	    ep.setBirthDate(birthDate);
	    ep.setCMobile(cMobile);
	    ep.setOperator(operator);
	    ep.setCompany(company);
	    ep.setCEmail(cEmail);
	    ep.setCCompEmail(cCompEmail);
	    ep.setSetId(setId);
	    ep.setTransferId(transferId);
	    ep.setBatchNum(batchNum);
	    ep.setPushDataType(pushDataType);
	    ep.setTransferDateTime(transferDateTime);
	    
	    dao.save(ep);
	}



	public void flush() {
		// TODO Auto-generated method stub
		dao.flush();
	}




}
