/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Role;
import com.oio.wawj.util.PageListData;



public interface RoleDAO extends BaseDAO<Role,Short>{

	void deleteRoleFunctionById(Short id);
	
	public Object getObjectById(String id);
	public List<Role> selectList(Class<Role> entityClass , String roleName,String status);
	
	public PageListData getList(final String hql,final Map param,final int pageNum,final int pageSize);

	public Role getRoleIdByUserId(final Long id);
	public  String getRoleList();
}
