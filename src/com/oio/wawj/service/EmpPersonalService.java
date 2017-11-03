package com.oio.wawj.service;

import com.oio.wawj.bean.EmpPersonal;

public interface EmpPersonalService {

	public void save(String emplId,String countryNmForm,String nameFormat,String sex,String highestEducLvl,String birthDate,String cMobile,String operator,String 
    		company,String cEmail,String cCompEmail,String setId,String transferId,String batchNum,String pushDataType,String transferDateTime);

	public void flush();
}
