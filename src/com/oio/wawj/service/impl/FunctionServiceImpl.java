/**
* Copyright (c) REALSTONE CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;




import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.RoleFunctionsId;
import com.oio.wawj.dao.FunctionDAO;
import com.oio.wawj.dao.RoleDAO;
import com.oio.wawj.dao.RoleFunctionsDAO;
import com.oio.wawj.service.FunctionService;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;


public class FunctionServiceImpl implements FunctionService{
	private FunctionDAO dao;
	private RoleFunctionsDAO RoleFunctiondao;
	private RoleDAO Roledao;
	


	/**
	 * �����¼
	 * @param Function Ҫ��������
	 */
	public void save(Function Function, Long userId) {
		Short functionId = dao.save(Function);
		RoleFunctionsId roleFunctionId = new RoleFunctionsId();
		roleFunctionId.setFunctionId(functionId);
		roleFunctionId.setRoleId(Roledao.getRoleIdByUserId(userId).getRoleId());
		RoleFunctions roleFunction = new RoleFunctions();
		roleFunction.setId(roleFunctionId);
		roleFunction.setUserid(userId);
		roleFunction.setStatus("V");
		roleFunction.setStatusDate(DateTime.getCurrentDateTime());
		RoleFunctiondao.save(roleFunction);
	}
	
	/**
	 * 删除Function数据
	 */
	public void delete(Function Function) {
		dao.delete(Function);
	}
	
/*
	
	*//**
	 * 更新Function数据
	 *//*
	public void update(Function Function) {
		dao.saveOrUpdate(Function);
	}
	
	*//**
	 * 通过ID查询Function数据
	 *//*
	public Function findById(Long id) {
		return dao.findById(Function.class, id);
	}
	*/
	
	public void deleteById(Short id) {
		dao.deleteById(Function.class,id);
	}
	
	
	
	public void update(Function Function) {
		dao.saveOrUpdate(Function);
	}
	/**
	 * ��ѯ���ڵ�Function�б���Ϣ
	 * @param id ������
	 * @return Function��Ϣ
	 */
	public  String findParentList(){
		return dao.getParentList();
	}



	/**
	 * 通过客户类别获取权限列表
	* <p>Title: findList </p>
	* <p>category: 类别</p>
	* return 权限列表
	*/
	public List<Function> findList(Integer category) {
		return dao.findList(category);
	}


	/**
	 * ���������ѯcustomer
	 * @param param ������Ͳ���ֵ����
	 * @param pageNum ��ǰҳ��
	 * @param pageSize ÿҳ��¼��
	 * @return ��ҳ��ݼ�����ҳ����ݼ�¼����ҳ�����Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map param, int pageNum, int pageSize) {
		
		String hql = "select function_id as functionId,function_name as functionName,status as status,status_date as statusDate " +
				     " from function ";

		return dao.getList(hql, param, pageNum, pageSize);
	}


	public List<Function> findLeftFunctionNameById(Short userId,
			Short functionId) {
		return dao.findLeftFunctionNameById(userId,functionId);
	}

	public Function findFunctionById(Short functionId) {
		return dao.getFunctionById(functionId);
	}
	public List<Function> findNoUseFunctionById(List<Short> functionIds) {
		return dao.getNoUseFunctionById(functionIds);
	}



	public List<String> findNameByIdList(List<Integer> functionIds) {
		return dao.getNameByIdList(functionIds);
	}

	@Override
	public List<Function> findAllValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTopFunctionNumber(Long custId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * ͨ��ID��ѯҶ��
	 * @param id ������
	 * @return Function��Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public List findSubListById(String id) {
		return dao.getSubListById(id);
	}
	/**
	 * ��ݴ������������ѯFunction�б���Ϣ
	 * @param id ������
	 * @return Function�б���Ϣ
	 */
	public List<Function> findTopFunctionNameById(Short id) {
		
		return dao.findTopFunctionNameById(id);
	}
	public RoleFunctionsDAO getRoleFunctiondao() {
		return RoleFunctiondao;
	}

	public void setRoleFunctiondao(RoleFunctionsDAO RoleFunctiondao) {
		this.RoleFunctiondao = RoleFunctiondao;
	}

	public RoleDAO getRoledao() {
		return Roledao;
	}

	public void setRoledao(RoleDAO Roledao) {
		this.Roledao = Roledao;
	}

	/**
	 * 获取FunctionDAO
	 */
	public FunctionDAO getDao() {
		return dao;
	}
	
	/**
	 * 设置FunctionDAO
	 */
	public void setDao(FunctionDAO dao) {
		this.dao = dao;
	}
}