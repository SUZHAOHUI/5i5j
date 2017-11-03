/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import com.oio.wawj.bean.Role;
import com.oio.wawj.dao.RoleDAO;
import com.oio.wawj.dao.UserDAO;
import com.oio.wawj.service.RoleService;
import com.oio.wawj.util.PageListData;



/**
 * ��Ҫ���ܣ�Role��ص�service�ӿڣ���Ҫ���ڴ���ҵ���߼���������dao��Ĳ�������
 * @author 
 */
@Entity
public class RoleServiceImpl implements RoleService {
	
	private RoleDAO dao;
	private UserDAO Userdao;

	
	
	
	/**
	 * �����¼
	 * @param Role Ҫ��������
	 */
	@SuppressWarnings("unused")
	public void save(Role Role, Long userId) {
		//��ӽ�ɫ�����
		Short roleId = dao.save(Role);
//		RoleUserId RoleUserId = new RoleUserId();
//		RoleUserId.setRoleId(roleId);
//		RoleUserId.setUserId(Userdao.getUserIdByRoleId(roleId).getUserId());
//		RoleUserId.setUserId(userId);
//		RoleUser RoleUser = new RoleUser();
//		RoleUser.setRoleUserid(RoleUserId);
//		RoleUser.setUserid(userId);
//		RoleUser.setStatus("V");
//		RoleUser.setStatusDate(DateTime.getCurrentDateTime());
//		//��ӹ�ϵ�����
//		UserRoledao.save(RoleUser);
//		
	} 
		
		
	
	
	/**
	 * ɾ���¼
	 * @param Role Ҫɾ������
	 */
	public void delete(Role Role) {
		dao.delete(Role);
	}

	/**
	 * ɾ���¼
	 * @param id Ҫɾ������
	 */
	public void deleteById(short id) {
		dao.deleteById(Role.class, id);
	}

	/**
	 * ɾ���¼
	 * @param id Ҫɾ������
	 */
	public void deleteRoleFunctionById(Short id) {
		dao.deleteRoleFunctionById(id);
	}
	
	/**
	 * ���¼�¼
	 * @param simc Ҫ���µ����
	 */
	public void update(Role Role) {
		dao.saveOrUpdate(Role);
	}
	
	/**
	 * ��ݴ������������ѯRole�б�
	 * @param param ������Ͳ���ֵ����
	 * @param pageNum ������
	 * @param pageSize ������
	 * @return Role�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map param, int pageNum, int pageSize) {
		//ƴװsql����ѯ�����
		String hql = " select r.role_name as roleName,r.role_id as roleId,r.status as status,r.status_date as statusDate, " +
				     " r.comment as comment" +
				     " from role as r ";

		
		return dao.getList(hql,param, pageNum, pageSize);
	}
	
	
	
	
	/**
	 * ��ݴ������������ѯRole�б�
	 * @param param ������Ͳ���ֵ����
	 * @param pageNum ������
	 * @param pageSize ������
	 * @return 
	 * @return Role�б���Ϣ
	 */
	public List<Role> select(String roleName,String status) {

//		for (Object o : param.keySet()) {
//			hql += " and " + o.toString() + " ? " ;
//		}
//		Object params[] = param.values().toArray();
		return dao.selectList(Role.class, roleName,status);
	}
	
//	public PageListData select(int pageNum,int pageSize) {
//		String hql = "from Role where 1 = 1 ";
//		return dao.select(Role.class,hql,pageNum,pageSize);
//	}
	
	/**
	 * �ж��Ƿ������ͬ����
	 * @param param ������Ͳ���ֵ����
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean isExistSameProperty(Map param) {
		return true;
	}

	/**
	 * ��ݴ������������ѯRole�б�
	 * @param id 
	 * @return Role�б����Ϣ
	 */
	public Role findById(short id) {

	//	Role Role = dao.findById(Role.class, id);
		return dao.findById(Role.class, id);
	}
	
	/**
	 * ��ѯ����Role�б�
	 * @return Role�б���Ϣ
	 */
	public List<Role> findAll() {
		return dao.findAll(Role.class);
	}
	
	/**
	 * �ж��Ƿ������ͬ����
	 * @param name ������
	 * @param type ������
	 * @return boolean
	 */
	public boolean isExistName(String name, String type) {
		boolean flag = false;
		if ("add".equals(type)) {
			List<Role> list = dao.findByProperty(Role.class, "roleName",
					name, 1);
			flag = (list != null && list.size() > 0 ? true : false);
		} else if ("update".equals(type)) {
			String temp[] = name.split(":");
			List<Role> list = dao.findByProperty(Role.class, "roleName",
					temp[0], 1);
			if (Boolean.parseBoolean(temp[1]))
				flag = (list != null && list.size() > 1 ? true : false);
			else
				flag = (list != null && list.size() > 0 ? true : false);
		}
		return flag;
	}
	
	/**
	 * ��ݴ������������ѯRole�б�
	 * @param param ������Ͳ���ֵ����
	 * @param type ������
	 * @return Role�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public List<Role> findByCondition(Map param, int type) {
		return dao.findByProperty(Role.class, null, null, type);
	}
	
	/**
	 * ��ѯRole�б���Ϣ
	 * @param id ������
	 * @return Function��Ϣ
	 */
	public  String findRoleList(){
		return dao.getRoleList();
	}
	/**
	 * get����
	 * @return BalDAO
	 */
	public RoleDAO getDao() {
		return dao;
	}
	
	/**
	 * set����
	 */
	public void setDao(RoleDAO dao) {
		this.dao = dao;
	}

	/**
	 * ��ѯRole�б���Ϣ
	 * @param id ������
	 * @return Role��Ϣ
	 */
	public Object findObjectById(String id) {
		// TODO Auto-generated method stub
		return dao.getObjectById(id);
	}




	public UserDAO getUserdao() {
		return Userdao;
	}




	public void setUserdao(UserDAO Userdao) {
		this.Userdao = Userdao;
	}






	

}
