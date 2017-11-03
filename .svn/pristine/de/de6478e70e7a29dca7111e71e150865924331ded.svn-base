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

import com.oio.wawj.bean.CustomerSource;
import com.oio.wawj.bean.Template;
import com.oio.wawj.dao.TemplateDAO;
import com.oio.wawj.util.PageListData;


public class TemplateDAOImpl extends BaseDAOImpl<Template,Short> implements TemplateDAO {

	
	public String getfindTemplate(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Template where 1=1  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Template sf = (Template)list.get(i);	
							jsonObject.put("id",sf.getTemplateId());
							jsonObject.put("name",sf.getName());		
							jsonObject.put("content",sf.getContent());		
							jsonObject.put("status",sf.getStatus());		
							jsonObject.put("type",sf.getType());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
		
	}
	
	
	
	public PageListData getfindTemplateAll(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
																				
							    String chql = " where 1=1 ";
							    
/*								 if (param.get("setId") != null
											&& !((String) param.get("setId")).equals("")) {
										chql += " and r.set_id in ("
												+ (String) param.get("setId")+")  or r.set_id='' ";
								  }*/
							    
							 
							    SQLQuery query = s.createSQLQuery("select count(*) as total  " +hql1+chql);	
							    System.out.println("select count(*) as total  " +hql1+chql);
							    System.out.println(hql+hql1+chql);
							    
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								System.out.println(total);
							    query =  getSession().createSQLQuery(hql+hql1+chql)
							    		.addScalar("TemplateId", Hibernate.SHORT) 
							            .addScalar("name", Hibernate.STRING)
							            .addScalar("content",Hibernate.STRING)
							            .addScalar("status",Hibernate.STRING)
							            .addScalar("type",Hibernate.STRING);
							    
							   
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
	
	

	
	
	public String getfindTemplateName(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Template where 1=1  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Template sf = (Template)list.get(i);	
							jsonObject.put("id",sf.getTemplateId());
							jsonObject.put("name",sf.getName());		
/*							jsonObject.put("content",sf.getContent());		
							jsonObject.put("status",sf.getStatus());		
							jsonObject.put("type",sf.getType());	*/	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
		
	}
	
	public Template getfindById(final short templateId){
		return  ( Template) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Template where templateId = "+templateId;
						System.out.println(hql);
						Query query = s.createQuery(hql);
						
						return query.list().get(0);	 
					}
				});
		
		
	}
	
	
	public  String getfindNotificationScenario(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Template  where status='c'";
						
						Query query = s.createQuery(hql);
						List list = query.list();
					
						for(int i = 0; i < list.size(); i++){
							Template sf = (Template)list.get(i);	
							jsonObject.put("templateId",String.valueOf(sf.getTemplateId()));
							jsonObject.put("templateName",sf.getName());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
}
