/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;


import com.oio.wawj.bean.Role;
import com.oio.wawj.util.PageListData;




@SuppressWarnings("rawtypes")
public interface RoleService{
//	public void save(Role Role);
	public void save(Role role, Long userId);
	public void delete(Role role);
	public void deleteById(short s);
	public void update(Role role);
	public PageListData findList(Map param, int pageNum, int pageSize);
//	public PageListData select(int currentPage,int pageSize);
	public boolean isExistSameProperty(Map param);
	public Role findById(short id);
	public List<Role> findAll();
	public List<Role> select(String roleName,String comment);
	public List<Role> findByCondition(Map param,int type);	
	public void deleteRoleFunctionById(Short id);
	public Object findObjectById(String id);
	public  String findRoleList();
	/**
	 * �ж��Ƿ������ͬ����
	 * @param name ������
	 * @param type ������
	 * @return boolean
	 */
	public boolean isExistName(String name, String type);
}
