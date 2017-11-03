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

import com.oio.wawj.bean.CollectionId;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.dao.CollectionIdDAO;
import com.oio.wawj.util.PageListData;


public class CollectionIdDAOImpl extends BaseDAOImpl<CollectionId,String> implements CollectionIdDAO{

	public String getfindCollectionId(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  CollectionId   ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							CollectionId sf = (CollectionId)list.get(i);	
							jsonObject.put("setId",sf.getSetId());
							jsonObject.put("descrShort",sf.getDescrShort());			

							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
	}
	
	public String getfindCollectionIdBySetId(final String setId){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray(); 
						String hql = " from  CollectionId  where setId='"+setId+"'";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							CollectionId sf = (CollectionId)list.get(i);	
							jsonObject.put("setId",sf.getSetId());
							jsonObject.put("descrShort",sf.getDescrShort());			
							
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
	}
	
	public String getfindCollectionIdAddUser(final Map param){
		try {
			return (String) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {

							 JSONObject jsonObject = new JSONObject();
							 JSONArray jsonArray = new JSONArray(); 
						    
							    String hql = "from  CollectionId ";

								if (param.get("duty") != null
										&& ((String) param.get("duty")).equals("超级管理员")) {
									     hql = "from  CollectionId ";	
								}else{
									
									if ( param.get("setId") != null && !((String) param.get("setId")).equals("")) {
										 hql = "from  CollectionId where setId ="+(String) param.get("setId");
									}else{
										 hql = "from  CollectionId ";
									}		
								}
								System.out.println(hql);
	
							Query query = s.createQuery(hql);
							List list = query.list();
							
							for(int i = 0; i < list.size(); i++){
								CollectionId sf = (CollectionId)list.get(i);	
								jsonObject.put("setId",sf.getSetId());
								jsonObject.put("descrShort",sf.getDescrShort());			

								jsonArray.add(jsonObject);
							}
							return jsonArray.toString(); 
						}
					});
					
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
		
	}
	
	
	
}
