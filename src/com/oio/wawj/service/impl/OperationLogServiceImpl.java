
package com.oio.wawj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.OperationLog;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.OperationLogDAO;
import com.oio.wawj.service.OperationLogService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 */
public class OperationLogServiceImpl implements OperationLogService{
	private OperationLogDAO dao;
	

	public void recordLog( String functionName, String statements, String comments){
		User user=(User)ActionContext.getContext().getSession().get("user");
		OperationLog ol = new OperationLog();

			ol.setCreateTime(DateTime.getCurrentDateTime());
		if(functionName != null)
			ol.setFunctionName(functionName);
		if(user.getUserId() != 0)
			ol.setUserId(user.getUserId());
		if(statements != null)
			ol.setStatement(statements);
		if(comments != null)
			ol.setComments(comments);
		    dao.save(ol);
	}
	

	/**
	 * �����¼
	 * @param otl Ҫ��������
	 */
	public void save(OperationLog otl) {
		dao.save(otl);
	}
	
	/**
	 * ɾ���¼
	 * @param otl Ҫɾ������
	 */
	public void delete(OperationLog otl) {
		// TODO Auto-generated method stub
		dao.delete(otl);
	}

	/**
	 * ɾ���¼
	 * @param id Ҫɾ������
	 */
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(OperationLog.class, id);
	}
	
	/**
	 * ���¼�¼
	 * @param otl Ҫ���µ����
	 */
	public void update(OperationLog otl) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(otl);
	}
	
	/**
	 * ��ݴ������������ѯOperationLog�б�
	 * @param functionParam ������Ͳ���ֵ����
	 * @param staffParam ������Ͳ���ֵ����
	 * @param timeParam ������Ͳ���ֵ����
	 * @param currentPage ������
	 * @param pageSize ������
	 * @return OperationLog�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map userParam, Map timeParam, int currentPage, int pageSize,String roleName) {
		// TODO Auto-generated method stub
		String hql = null;
		
		
		return dao.findOperationLogList( userParam,timeParam, currentPage, pageSize,roleName);
	}
	
	/**
	 * ��ݴ������������ѯOperationLog�б�
	 * @param param ������Ͳ���ֵ����
	 * @param currentPage ������
	 * @param pageSize ������
	 * @return OperationLog�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Map param, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from OperationLog where 1=1 ";
		for (Object o : param.keySet()) {
			hql += " and " + o.toString() + " ? ";
		}
		Object params[] = param.values().toArray();
		return dao.findList(OperationLog.class, hql, params, currentPage, pageSize);
	}
	
	/**
	 * �ж��Ƿ������ͬ����
	 * @param param ������Ͳ���ֵ����
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean isExistSameProperty(Map param) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * ��ݴ������������ѯOperationLog�б�
	 * @param id 
	 * @return OperationLog�б����Ϣ
	 */
	public OperationLog findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(OperationLog.class, id);
	}
	
	/**
	 * ��ѯ����OperationLog�б�
	 * @return OperationLog�б���Ϣ
	 */
	public List<OperationLog> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll(OperationLog.class);
	}
	
	/**
	 * ��ݴ������������ѯOperationLog�б�
	 * @param param ������Ͳ���ֵ����
	 * @param type ������
	 * @return OperationLog�б���Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public List<OperationLog> findByCondition(Map param, int type) {
		// TODO Auto-generated method stub
		return dao.findByProperty(OperationLog.class, null, null, type);
	}
	
	/**
	 * get����
	 * @return BalDAO
	 */
	public OperationLogDAO getDao() {
		return dao;
	}
	
	/**
	 * set����
	 */
	public void setDao(OperationLogDAO dao) {
		this.dao = dao;
	}
}