
package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Function;
import com.oio.wawj.dao.FunctionDAO;
import com.oio.wawj.util.PageListData;






@SuppressWarnings({ "unchecked", "rawtypes" })
public class FunctionDAOImpl extends BaseDAOImpl<Function, Short> implements FunctionDAO{



	/**
	 * 查询Function列表信息
	 * @param id 参数名
	 * @return Function信息
	 */
	public List<Function> findTopFunctionNameById(Short id) {

		String hql =" from User u, UserRole sur, Role sr," +
				"RoleFunctions srf, Function sf where " +
				"u.userId = sur.id.userId and " +
				"sur.id.roleId = sr.roleId and " +
				"sr.roleId = srf.id.roleId and " +
				"srf.id.functionId = sf.functionId and " +
				"u.status='V' and sur.status='V' and sr.status='V' and " +
				"srf.status='V' and sf.status='V' and sf.levelId=1 " +
				"and  u.userId = " + id + ")";

		Query q = getSession().createQuery(hql);
		return (List<Function>)q.list();
	}

	/**
	 * 查询Function列表信息
	 * @param id 参数名
	 * @return Function信息
	 */
	public List<Function> findLeftFunctionNameById(Short id,Short functionId) {

		String hql ="from Function ret_sf where exists " +
				"(select distinct sf.functionId " +
				"from User u, UserRole sur, Role sr," +
				"RoleFunctions srf, Function sf where " +
				"u.userId = sur.id.userId and " +
				"sur.id.roleId = sr.roleId and " +
				"sr.roleId = srf.id.roleId and " +
				"srf.id.functionId = sf.functionId and " +
				"u.status='V' and sur.status='V' and sr.status='V' and " +
				"srf.status='V' and sf.status='V' and " +
				"ret_sf.functionId = sf.functionId and " +
				"sf.functionId !=1 and " +
				"u.userId = " + id + 
				" and " +
				"sf.parentFunctionId = " + functionId +
				")";
		
		Query q = getSession().createQuery(hql);
		
	
		return (List<Function>)q.list();
	}


	public List<Function> findList(final Integer category) {
		return (List<Function>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							String hql = "from Function sf where 1=1 and sf.status='V' ";
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							return q.list();
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}



	public Function getFunctionById(final Short functionId) {
		return (Function) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							String hql = "from Function sf where 1=1 and sf.status='V' and sf.functionId= " + functionId;
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list.size()>0?list.get(0):null;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}
	public List<Function> getNoUseFunctionById( final List<Short> functionIds) {
		return (List<Function>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							
							String hql = "from Function sf where 1=1 and sf.status='V'";
							if (functionIds != null) {
								for (Short ig : functionIds) {
									hql += " and sf.functionId !=";
									hql += ig.toString();
								}
							}
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}
	@Override
	public List<String> getNameByIdList(List<Integer> functionIds) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 查询父节点Function列表信息
	 * @param id 参数名
	 * @return Function信息
	 */
	public  String getParentList(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Function where 1=1 and levelId=1 ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Function sf = (Function)list.get(i);	
//							 HashMap   hashmap =new  HashMap();
							jsonObject.put(sf.getFunctionId(),sf.getFunctionName());		
							
						}
						return jsonObject.toString(); 
					}
				});
	}

	public  List<Function> findAllFunctionList(){
		return  ( List<Function>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Function where 1=1";
						
						Query query = s.createQuery(hql);
						List list = query.list();
					
						return list; 
					}
				});
	}
	/**
	 * 查询子节点Function列表信息
	 * @param id 参数名
	 * @return Function信息
	 */
	public List getSubListById(final String id) {
		return  (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						//Long idd = Long.parseLong(id);
						String hql = "from  Function s where s.parentFunctionId="+id+"";

						Query query = s.createQuery(hql);
						return query.list();
					}
				});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getList(final String hql,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;

							String chql = "where 1=1 and status='V' ";

							 if (param.get("functionName") != null
								&& !((String) param.get("functionName")).equals("")) {
								 chql += " and function_name like '%"
											+ (String) param.get("functionName")  + "%' ";
					          }
							  
    
							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+chql+") "+" as functions");	
							    System.out.println("select count(*) as total from " +"("+hql+chql+") "+" as functions");
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								 System.out.println(total);
								 query =  getSession().createSQLQuery(hql+chql)
							    		.addScalar("functionId", Hibernate.SHORT) 
							            .addScalar("functionName", Hibernate.STRING)
							            .addScalar("status",Hibernate.STRING)
							            .addScalar("statusDate",Hibernate.TIMESTAMP);
							    System.out.println(hql+chql);
							if (0 != pageSize) {
								query.setFirstResult(
										(currentPage == 0 ? 0 : currentPage - 1) * pageSize).setMaxResults(pageSize);
							}
							List data = query.list();
						    listdata = new PageListData(total, pageSize, currentPage, data);
							try{
							listdata = new PageListData(total, pageSize, currentPage, data);
							}catch(Exception e){
								e.printStackTrace();
							}
							return listdata;
						}
					});
					
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
		
	}
	
	
	
	

}