
package com.oio.wawj.dao;


import java.util.List;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.Org;
import com.oio.wawj.util.PageListData;



public interface OverviewDAO extends BaseDAO<CdrCall, Long> {
	
	public Org findOrgNameByOrgId(final Integer orgId);
	public List getSecretNoCondition(final Long orgId,final Long operatorId,final String roleName);
	public List getCallStatistic(final Long orgId,final Long operatorId,final String roleName);
	public String getFindRoleNameByUserId(final Long userId);
}