
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.Org;
import com.oio.wawj.dao.CallRecordsDAO;
import com.oio.wawj.dao.OverviewDAO;
import com.oio.wawj.service.CallRecordsService;
import com.oio.wawj.service.OverviewService;
import com.oio.wawj.util.PageListData;



    public  class OverviewServiceImpl implements OverviewService {
	private OverviewDAO dao;
	

	public Org findOrgNameByOrgId(Integer orgId){
		return dao.findOrgNameByOrgId(orgId);
	}
    
	public List getSecretNoCondition( Long orgId,Long operatorId,String roleName){
		return dao.getSecretNoCondition(orgId,operatorId,roleName);
	}
	public List getCallStatistic( long orgId,Long operatorId,String roleName){
		return dao.getCallStatistic(orgId,operatorId,roleName);
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return CdrCall
	 */
	public OverviewDAO getDao() {
		return dao;
	}
	
	/**
	 * set锟斤拷锟斤拷
	 */
	public void setDao(OverviewDAO dao) {
		this.dao = dao;
	}

	@Override
	public String findRoleNameByUserId(Long userId) {
		// TODO Auto-generated method stub
		return dao.getFindRoleNameByUserId(userId);
	}

	}




	

