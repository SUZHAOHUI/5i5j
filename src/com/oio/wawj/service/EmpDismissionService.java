package com.oio.wawj.service;

import com.oio.wawj.bean.EmpDismission;

public interface EmpDismissionService {

	public void save(String batchNum,String cApproveStatus,String cLeaveStatus,String emplId,String pushDataType,String transferDateTime,String transferId,
			String setid);
	
	public void flush();
}
