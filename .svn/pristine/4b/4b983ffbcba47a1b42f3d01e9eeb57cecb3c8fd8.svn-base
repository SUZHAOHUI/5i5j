package com.oio.wawj.dao.impl;

import java.sql.SQLException;
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

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Customer;
import com.oio.wawj.bean.CustomerSource;
import com.oio.wawj.bean.CustomerStatus;
import com.oio.wawj.bean.CustomerType;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.CustomerDAO;
import com.oio.wawj.util.PageListData;

public class CustomerDAOImpl extends BaseDAOImpl<Customer,Long> implements CustomerDAO {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCustomerList(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where 1=1 ";
							
							if (param.get("tel") != null
									&& !((String) param.get("tel")).equals("")) {
								chql += " and c.tel like '%"
										+ (String) param.get("tel") +  "%' ";
							}
							if (param.get("statusId") != null
									&& !((String) param.get("statusId")).equals("")) {
								chql += " and c.customer_status_id = '"
										+ (String) param.get("statusId") + "' ";
							}
							if (param.get("clevel") != null
									&& !((String) param.get("clevel")).equals("")) {
								chql += " and c.clevel = "
										+ (String) param.get("clevel") ;
							}
							if (param.get("plevel") != null
									&& !((String) param.get("plevel")).equals("")) {
								chql += " and c.plevel = "
										+ (String) param.get("plevel")  ;
							}
							if (param.get("sourceId") != null
									&& !((String) param.get("sourceId")).equals("")) {
								chql += " and c.source_id = "
										+ (String) param.get("sourceId") ;
							}

							if (param.get("nameOrNumber") != null
									&& !((String) param.get("nameOrNumber")).equals("")) {
								
									chql += " and (c.name like '%"
											+ (String) param.get("nameOrNumber") + "%' ";
									
									chql += " or  c.code like '%"
											+ (String) param.get("nameOrNumber") + "%' ";
									
									chql += " or  c.comments like '%"
											+ (String) param.get("nameOrNumber") + "%' )";

							}
//							if (param.get("customer") != null 
//									&& !((String) param.get("customer")).equals("")) {
//								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
//									
//									chql += " and cc.otherms like '%"
//											+ (String) param.get("CustomerNumber")+ "%' " ;
//							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(c.create_date) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(c.create_date) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							
							SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1+chql);	
							System.out.println("select count(*) as total" + hql1+chql);
							query.addScalar("total", Hibernate.LONG);
							System.err.println(hql+hql1+chql);
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							
							query.addScalar("telList", Hibernate.STRING)
							.addScalar("name", Hibernate.STRING)
							.addScalar("code", Hibernate.STRING)
							.addScalar("comments", Hibernate.STRING)
							.addScalar("custId",Hibernate.LONG )
							.addScalar("gender", Hibernate.STRING)
							.addScalar("sourceId", Hibernate.SHORT)
							.addScalar("clevel", Hibernate.SHORT)
							.addScalar("plevel", Hibernate.SHORT)
							.addScalar("belongWith", Hibernate.STRING)
							.addScalar("statusId", Hibernate.SHORT)
							.addScalar("situation", Hibernate.STRING)
							.addScalar("userId", Hibernate.INTEGER)
							.addScalar("statusName", Hibernate.STRING)
							.addScalar("typeName", Hibernate.STRING)
							.addScalar("typeLeve", Hibernate.STRING)
							.addScalar("sourceName", Hibernate.STRING)
							.addScalar("userName", Hibernate.STRING)
							.addScalar("clevelId", Hibernate.INTEGER)
							.addScalar("plevelId", Hibernate.INTEGER);
							
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
	

	public  String getfindSource(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  CustomerSource  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
					
						for(int i = 0; i < list.size(); i++){
							CustomerSource sf = (CustomerSource)list.get(i);	
							jsonObject.put("sourceId",sf.getId());
							jsonObject.put("sourceName",sf.getName());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
	public  String getfindCustomerStatus(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  CustomerStatus  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							CustomerStatus sf = (CustomerStatus)list.get(i);	
							jsonObject.put("statusId",sf.getId());
							jsonObject.put("statusName",sf.getName());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
	public  String getfindCustomerType(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  CustomerType  where level='P' ";
						System.out.println(hql);
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							CustomerType sf = (CustomerType)list.get(i);	
							jsonObject.put("typeId",sf.getId());
							jsonObject.put("typeName",sf.getName());		
							jsonObject.put("typeLevel",sf.getLevel());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
	public  String getfindCustomerProductType(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  CustomerType  where level='C' ";
						System.out.println(hql);
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							CustomerType sf = (CustomerType)list.get(i);	
							jsonObject.put("typeId",sf.getId());
							jsonObject.put("typeName",sf.getName());		
							jsonObject.put("typeLevel",sf.getLevel());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	public  String getfindUserName(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  User ";
						System.out.println(hql);
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							User sf = (User)list.get(i);	
							jsonObject.put("userId",sf.getUserId());
							jsonObject.put("userName",sf.getName());		
							
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
	public  Customer getfindByCustId(final Long id){
		return  ( Customer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  Customer where custId =:id ";
						System.out.println(hql);
						Query query = s.createQuery(hql);
						query.setParameter("id",id);
						List list = query.list();
						
						return list.get(0); 
					}
				});
	}

}
