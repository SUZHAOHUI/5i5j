/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.dao.UserDAO;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.PageListData;




/**
 * @author 
 */
@Entity
public class UserServiceImpl implements UserService {
	@ManyToOne
	private UserDAO dao;
	
	public UserDAO getDao() {
		return dao;
	}

	/**
	 * 锟斤拷询锟斤拷锟斤拷锟斤拷锟�
	 * @return 锟斤拷织锟叫憋拷
	 */
	public PageListData findAll(Map param,int currentPage, int pageSize) {
		String sql = " select  u.user_id as userId,u.name as name,u.code as code,u.status as status," +
		 		     " u.data_permission as dataPermission, u.function_permission as functionPermission, " +
		 		     " u.password as password,u.set_id as setId,u.org_id as orgId,o.org_name as orgName " ;
		
		String sql1=" from user u" +
				    " left join org o on o.org_id=u.org_id and o.set_id=u.set_id";
		
		return dao.getfindAll(param,sql,sql1,currentPage, pageSize);
	}
	public String findUserAll(short roleId) {
		return dao.getfindUserAll(roleId);
	}

	public String findRoleAll() {
		 //return dao.getfindRoleAll();
		 return null;
	}
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	public Integer findId(short id) {
		return dao.getFindId(id);
	}


	/**
	 * 锟斤拷锟斤拷锟铰�
	 * @param User 要锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void save(User sysUser) {
		dao.save(sysUser);
	}
   

	/**
	 * 删锟斤拷锟铰�
	 * @param id 要删锟斤拷锟斤拷锟斤拷
	 */
	public void deleteById(Long id) {
		dao.deleteById(User.class, id);
	}

	/**
	 * 删锟斤拷锟铰�
	 * @param id 要删锟斤拷锟斤拷锟斤拷
	 */
	public void deleteUserById(Short id) {
		dao.deleteUserById(id);
	}
	
	/**
	 * 锟斤拷锟铰硷拷录
	 * @param simc 要锟斤拷锟铰碉拷锟斤拷锟�
	 */
	public void update(User sysUser) {
		dao.saveOrUpdate(sysUser);
	}
	
	public User findUserByEmail(String email){
      return dao.findByProperty(User.class, "email", email).get(0);		
	}
	public User findUserByCode(String code){
		  dao.findByProperty(User.class, "code", code).get(0);
	      return dao.findByProperty(User.class, "code", code).get(0);		
		}
	/**
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map param, int pageNum, int pageSize) {
		String sql = " select  u.user_id as userId,u.name as name,u.code as code,u.sex as sex," +
				     " u.email as email,u.tel as tel,u.status as status,u.status_date as statusDate," +
				     " u.address as address,o.org_id as orgId,o.org_name as orgName";
		
        String sql1=" from user u"+
		   		    " left join org o on o.org_id=u.org_id ";   

		return dao.getList(sql,sql1, param, pageNum, pageSize);
	}
	
	

	public User findById(Long id) {

	//	User sysUser = dao.findById(User.class, id);
		return dao.findById(User.class, id);
	}

	public boolean isExistName(String name,String statusName, String statusValue) {
		boolean flag = false;
	        
			List<User> list = dao.findByProperty(User.class, "code",
					name, 1,statusName, statusValue);
			flag = (list != null && list.size() > 0 ? true : false);
		
		return flag;
	}
	public boolean isExist(String param,String statusName, String statusValue,Boolean flag) {
		List<User> list;
	       if(flag){
			 list = dao.findByProperty(User.class, "code",
					param, 1,statusName, statusValue);
	       }else{
	    	 list = dao.findByProperty(User.class, "email",
						param, 1,statusName, statusValue); 
	       }
			flag = (list != null && list.size() > 0 ? true : false);
		
		return flag;
	 }



	/**
	 * 锟斤拷录校锟斤拷
	 * @param sysUser 锟斤拷锟斤拷锟斤拷
	 * @return sysUser 锟斤拷息
	 */
	public User checkLogin(User user) {

		return dao.checkLogin(user);
	}

	public List<UserRole> findByUserId(Long userId) {
		
		return dao.findByUserId(userId);
	}
	
	public User findUserByName(String name){
		  List<User> list = dao.findByProperty(User.class, "name", name);
		  return (list.size()==0)?null:list.get(0);
	}
    public String getChildOrgList(Long orgId){
    	return dao.getChildOrgList(orgId);
    }
    public String getChildOrgLists(Long orgId,String setId){
    	return dao.getChildOrgLists(orgId,setId);
    }

	
}
