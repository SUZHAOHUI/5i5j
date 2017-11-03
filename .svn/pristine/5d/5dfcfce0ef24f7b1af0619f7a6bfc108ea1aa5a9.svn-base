/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public interface UserService{

	public void save(User sysUser);
	/**
	 * 锟斤拷锟斤拷锟街拷锟斤拷锟絀D锟斤拷锟斤拷锟斤拷织锟斤拷锟脚的革拷锟斤拷
	 * @param id 锟剿伙拷id
	 * @return boolean
	 */	
	public Integer findId(short id);
	/**
	 * 锟斤拷询锟斤拷锟斤拷锟斤拷锟�
	 * @return 锟斤拷织锟叫憋拷
	 */
	public PageListData findAll(Map param,int currentPage, int pageSize);
	/**
	 * 锟斤拷询锟斤拷锟叫诧拷锟斤拷员
	 * @return
	 */
	public String findUserAll(short roleId);
	/**
	 * 锟斤拷询锟斤拷锟叫斤拷色
	 * @return
	 */
	public String findRoleAll();
	//通锟斤拷ID删锟斤拷
	public void deleteById(Long id);
	public void update(User sysRole);
	public PageListData findList(Map param, int currentPage, int pageSize);
	public boolean isExistName(String name,String statusName, String statusValue);
	public void deleteUserById(Short id);
	public boolean isExist(String param,String statusName, String statusValue,Boolean flag);

	//通锟斤拷ID锟斤拷询
	public User findById(Long id);
	public User checkLogin(User user);
	public User findUserByEmail(String email);
	public User findUserByCode(String tel);
	public List<UserRole> findByUserId(Long userId);
	public User findUserByName(String name);
	public String getChildOrgList(Long orgId);
	
	public String getChildOrgLists(Long orgId,String setId);
	
}
