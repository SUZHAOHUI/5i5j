
package com.oio.wawj.dao;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.oio.wawj.bean.Role;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;



public interface UserRoleDAO extends BaseDAO<UserRole, Short> {
	public List<User> getNoUserRoleById( final List<Long> userIds);
	public String getNoUserRoleByIds();
	public List<UserRole> getByRoleId(Short roleId);
	public List<UserRole> getByRoleIds(Short RoleId);
	public List getByRoleIdsfp(Short RoleId,String setId);
	public User getUserById(Long userId);
	public UserRole getById(Long userId);
//	public String getUserByIds(Short roleId);
	public String getUserByIds(Map param);
	public UserRole getRoleIdByUserId(final Long userId);


	 
}