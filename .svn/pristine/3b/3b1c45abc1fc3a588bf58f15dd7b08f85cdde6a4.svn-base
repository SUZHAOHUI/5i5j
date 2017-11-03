/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;

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


import com.oio.wawj.dao.ChannelDAO;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public class  ChannelDAOImpl extends BaseDAOImpl<Channel,ChannelId> implements ChannelDAO {

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Channel getFindId(final ChannelId id) {
		return  (Channel) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
					
						String hql = "from Channel c " +
										"where  c.id.channelId = "+id.getChannelId();		
						System.out.println(hql);
						Query query = s.createQuery(hql);
//						query.setLong("custId", id);
						//int total =  ((Number) query.uniqueResult()).intValue();
						
						return query.list().get(0);	

					}
				});
	}

	/**
	 * @return 查询全部
	 */
	public  String getfindAll(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Channel where 1=1 and state ='V' ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
					
						for(int i = 0; i < list.size(); i++){
							Channel sf = (Channel)list.get(i);	
//							 HashMap   hashmap =new  HashMap();
							jsonObject.put("channelId",sf.getId().getChannelId());
							jsonObject.put("channelName",sf.getId().getChannelName());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getfindAllChannel(final String hql,final String hql1, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
							
							String chql1=" where c.state='V' ";
							String chql2=" group by c.channel_id, c.channel_name ";
							
							  
							   SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql1+chql2+") "+" as channels");
							   System.out.println("   ");
							   System.out.println("select count(*) as total from " +"("+hql+hql1+chql1+chql2+") "+" as channels");							   
							   System.out.println(hql+hql1+chql1+chql2);
								query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql1+chql2) ;
							query.addScalar("channelId",Hibernate.INTEGER)
							     .addScalar("channelName", Hibernate.STRING)
							     .addScalar("count", Hibernate.INTEGER);

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
	

	/**
	 * 
	 */
	@Override
	public String getfindUserAll(final Short id) {
		
		return  (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = "from Channel where 1=1 and id=:id";
	
						Query query = s.createQuery(hql);
						short idd=id;
						query.setShort("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}
	


	@Override
	public void deleteChannelById(Short id) {
		
	}

	@Override
	public Object getObjectById(String id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Channel getChannelIdByRoleId(final Short id) {
		
		return  (Channel) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = "from Channel where 1=1 and channelId=:id";
                        	
						Query query = s.createQuery(hql);
						short idd=id;
						query.setShort("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}
	@SuppressWarnings("unchecked")
	public  String getChannelList( final Long operator){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Channel c where 1=1 and c.state='V'  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Channel sf = (Channel)list.get(i);	
//							 HashMap   hashmap =new  HashMap();
							Integer cId = sf.getId().getChannelId();
							jsonObject.put("value", cId);
							jsonObject.put("label",sf.getId().getChannelName());
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	

	

	
	public  Integer getChannelListCount(final String hql,final String hql1,final Integer cId){
		return  (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
	
						
						String chql="  where ch.channel_id= "+cId;
						
						//SQLQuery query = s.createSQLQuery( hql+hql1+chql);	
						System.out.println("--------------------" + hql+hql1+chql);
						
						SQLQuery query = s.createSQLQuery( hql+hql1+chql);
						System.out.println("--------------------" + hql+hql1+chql);

						//int i = ((Long) query.iterate().next()).intValue();
						

						Integer i = (Integer) query.uniqueResult();

						System.out.println(i+"-------------------"+i);
						return i;
					}
				});
	}

	

}