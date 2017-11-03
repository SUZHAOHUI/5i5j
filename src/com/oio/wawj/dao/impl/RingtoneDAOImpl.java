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
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.bean.Template;
import com.oio.wawj.dao.RingtoneDAO;
import com.oio.wawj.util.PageListData;

public class RingtoneDAOImpl extends BaseDAOImpl<Ringtone,Short> implements RingtoneDAO{

	public String getfindRingtone(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Ringtone where 1=1  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Ringtone sf = (Ringtone)list.get(i);	
							jsonObject.put("id",sf.getId());
							jsonObject.put("name",sf.getName());			
							jsonObject.put("status",sf.getState());	
							jsonObject.put("url",sf.getSavepath());	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
	}
	public String getcallInfindRingtone(final Map param){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Ringtone r where r.state='V'  ";
						
						 if (param.get("setId") != null
									&& !((String) param.get("setId")).equals("")) {
								hql += " and r.setId in ("
										+ (String) param.get("setId")+")  or r.setId='' ";
						  }
					    
						Query query = s.createQuery(hql);
						System.out.println(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Ringtone sf = (Ringtone)list.get(i);	
							jsonObject.put("rid",sf.getId());
							jsonObject.put("rname",sf.getName());			
							jsonObject.put("setId",sf.getSetId());	
							jsonObject.put("url",sf.getSavepath());	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
	}
	
	/**
	 * 
	 * 
	 */
	
	public PageListData getfindRingtoneAll(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
																				
							    String chql = " where state='V' ";
							    
								 if (param.get("setId") != null
											&& !((String) param.get("setId")).equals("")) {
										chql += " and r.set_id in ("
												+ (String) param.get("setId")+")  or r.set_id='' ";
								  }
							    
							 
							    SQLQuery query = s.createSQLQuery("select count(*) as total  " +hql1+chql);	
							    System.out.println("select count(*) as total  " +hql1+chql);
							    System.out.println(hql+hql1+chql);
							    
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								System.out.println(total);
							    query =  getSession().createSQLQuery(hql+hql1+chql)
							    		.addScalar("id", Hibernate.SHORT) 
							            .addScalar("name", Hibernate.STRING)
							            .addScalar("state",Hibernate.STRING)
							            .addScalar("url",Hibernate.STRING)
							            .addScalar("descrShort",Hibernate.STRING);
							    
							   
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
	
				
	
	
	
	
	
	public String getfindRingtoneUrl(final short Id){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Ringtone where  id ="+Id;
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Ringtone sf = (Ringtone)list.get(i);	

							jsonObject.put("url",sf.getSavepath());	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
		
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Ringtone getFindId(final short id) {
		return  (Ringtone) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from Ringtone r " +
										"where  r.id = "+id;		
						System.out.println(hql);
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Ringtone sf = (Ringtone)list.get(i);	
							jsonObject.put("id",sf.getId());
							jsonObject.put("name",sf.getName());			
							jsonObject.put("status",sf.getState());	
							jsonObject.put("url",sf.getSavepath());	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 

					}
				});
	}
	
	
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getList(final String hql,final String hql1){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
														
							
							String chql=" where p.section_id = 1 and p.item_id > 4 and p.item_id < 8 ";
							
							SQLQuery query = getSession().createSQLQuery(hql+hql1+chql) ;
							query.addScalar("pId",Hibernate.INTEGER )
							     .addScalar("sectionId", Hibernate.SHORT)
							     .addScalar("section", Hibernate.STRING)
							     .addScalar("itemId", Hibernate.SHORT)
							     .addScalar("item", Hibernate.STRING)
							     .addScalar("value", Hibernate.INTEGER)
							     .addScalar("rId", Hibernate.SHORT)
							     .addScalar("rname", Hibernate.STRING)
							     .addScalar("savepath", Hibernate.STRING);

							List data = query.list();
							listdata = new PageListData(1000, 1000, 1, data);
							try{
								listdata = new PageListData(1000, 1000, 1, data);
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
		
	}*/
	
	
	
	public String getList(final Map param,final String sql){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						
						 String chql = "where ir.state ='V' ";
						 
						 if (param.get("setId") != null
									&& !((String) param.get("setId")).equals("")) {
								chql += " and ir.org_id in ("
										+ (String) param.get("setId")+")";
						  }
						
							Query query = s.createSQLQuery(sql+chql);
							System.out.println(sql+chql);
							List list = query.list();
						    if(list.size()==0){

									jsonObject.put("setId","");
									jsonObject.put("parameterId",105);
									jsonObject.put("rid","");
									jsonObject.put("rname","");
									jsonObject.put("url","");	
									jsonObject.put("item","客户等待接通");	
									jsonArray.add(jsonObject);
								
						    	
						    }else{
							
							for(int i = 0; i < 1; i++){
								Object [] sf = (Object[])list.get(i);	
//								 HashMap   hashmap =new  HashMap();
								jsonObject.put("setId",sf[0]);
								jsonObject.put("parameterId",sf[1]);
								jsonObject.put("rid",sf[2]);
								jsonObject.put("rname",sf[3]);
								jsonObject.put("url",sf[4]);	
								jsonObject.put("item",sf[5]);	
								jsonArray.add(jsonObject);
							}
						   }
							System.out.println(jsonArray.toString());
							return jsonArray.toString(); 
							
						
					}
				});
		
	}
	
	public String getfindChannelRingtone(final String sql){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray(); 
						
						String chql = " where c.state='V' ";
						
						Query query = s.createSQLQuery(sql+chql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Object [] sf = (Object[])list.get(i);	
//								 HashMap   hashmap =new  HashMap();
							jsonObject.put("channelId",sf[0]);
							jsonObject.put("channelName",sf[1]);
							jsonObject.put("rid",sf[2]);
							jsonObject.put("rname",sf[3]);
							jsonObject.put("url",sf[4]);	
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
						
						
					}
				});
		
	}

	
}
