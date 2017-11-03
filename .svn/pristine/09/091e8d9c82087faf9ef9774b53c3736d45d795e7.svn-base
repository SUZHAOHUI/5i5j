/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;


import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.dao.RoleFunctionsDAO;
import com.oio.wawj.service.RoleFunctionsService;
import com.oio.wawj.util.PageListData;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;



/**
 * ��Ҫ���ܣ�RoleFunction��ص�service�ӿڣ���Ҫ���ڴ���ҵ���߼���������dao��Ĳ�������
 * @author 

 */
@Entity
public class RoleFunctionsServiceImpl implements RoleFunctionsService {
	@ManyToOne
	private RoleFunctionsDAO dao;
	

	/**
	 * �����¼
	 * @param Function Ҫ��������
	 */
	public void save(RoleFunctions Function) {
		dao.save(Function);
	}
	
	/**
	 * ɾ���¼
	 * @param Function Ҫɾ������
	 */
	public void delete(RoleFunctions Function) {
		dao.delete(Function);
	}

//	/**
//	 * ɾ���¼
//	 * @param id Ҫɾ������
//	 */
//	public void deleteById(Integer id) {
//		dao.deleteById(RoleFunctions.class, id);
//	}
	
	/**
	 * ���¼�¼
	 * @param Function Ҫ���µ����
	 */
	public void update(RoleFunctions Function) {
		dao.saveOrUpdate(Function);
	}
	
	/**
	 * ��ݴ������������ѯFunction�б�
	 * @param param ������Ͳ���ֵ����
	 * @param pageNum ������
	 * @param pageSize ������
	 * @return Function�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map param, int pageNum, int pageSize) {
		String hql = "from RoleFunction where 1=1 ";
		for (Object o : param.keySet()) {
			hql += " and " + o.toString() + " ? ";
		}
		Object params[] = param.values().toArray();
		return dao.findList(RoleFunctions.class, hql, params, pageNum,
				pageSize);
	}
	
	/**
	 * �ж��Ƿ������ͬ����
	 * @param param ������Ͳ���ֵ����
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean isExistSameProperty(Map param) {
		return true;
	}
	
//	/**
//	 * ��ݴ������������ѯRoleFunction�б�
//	 * @param id 
//	 * @return RoleFunction�б����Ϣ
//	 */
//	public RoleFunctions findById(Integer id) {
//		return dao.findById(RoleFunctions.class, id);
//	}
	
	/**
	 * ��ѯ����SimCard�б�
	 * @return SimCard�б���Ϣ
	 */
	public List<RoleFunctions> findAll() {
		return dao.findAll(RoleFunctions.class);
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
			List<RoleFunctions> list = dao.findByProperty(
					RoleFunctions.class, "name", name, 1);
			flag = (list != null && list.size() > 0 ? true : false);
		} else if ("update".equals(type)) {
			String temp[] = name.split(":");
			List<RoleFunctions> list = dao.findByProperty(
					RoleFunctions.class, "name", temp[0], 1);
			if (Boolean.parseBoolean(temp[1]))
				flag = (list != null && list.size() > 1 ? true : false);
			else
				flag = (list != null && list.size() > 0 ? true : false);
		}
		return flag;
	}
	
	/**
	 * ��ݴ������������ѯSimCard�б�
	 * @param param ������Ͳ���ֵ����
	 * @param type ������
	 * @return SimCard�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public List<RoleFunctions> findByCondition(Map param, int type) {
		return dao.findByProperty(RoleFunctions.class, null, null, type);
	}


	/**
	 * ��ѯRoleFunction�б���Ϣ
	 * @param id ������
	 * @return RoleFunction��Ϣ
	 */
	public List<RoleFunctions> findByRoleId(Short id) {
		
		return dao.findByRoleId(id);
	}

	/**
	 * ��ѯ����Function�б���Ϣ
	 * @return Function��Ϣ
	 */
	public List<Function> findAllFunction() {
	
		return	dao.findAllFunction();
		
	}

	/**
	 * ��ѯFunction���ڵ��б���Ϣ
	 * @param id ������
	 * @return Function���ڵ��б���Ϣ
	 */
	public List<Function> findListByParentId(Long id) {
	
		return	dao.getListByParentId(id);
		
	}

	/**
	 * ��ѯFunction����
	 * @param id ������
	 * @return Function�б���Ϣ
	 */
	public List<Function> findListByLevelId(Integer id){
		return	dao.getListByLevelId(id);
	}
	
	public RoleFunctionsDAO getDao() {
		return dao;
	}

	public void setDao(RoleFunctionsDAO dao) {
		this.dao = dao;
	}
}
