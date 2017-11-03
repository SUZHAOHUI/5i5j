package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;






public interface UserRoleService {

	public User findUserById(Long userId);
	public UserRole findById(Long userId);
//	public String findUserByIds(Short roleId);
	public String findUserByIds(Map param);
	public List<UserRole> findByRoleId(Short roleId);
	public List<UserRole> findByRoleIds(Short roleId);
	public List findByRoleIdsfp(Short roleId,String setId);
	public List<User> findNoUseFunctionById(List<Long> userIds);
	public String findNoUseFunctionByIds();
	public void delete(UserRole UserRole);
	public void save(UserRole UserRole);
	public void saveOrUpdate(UserRole UserRole);
	public Short findRoleIdByUserId(Long userId);

}
