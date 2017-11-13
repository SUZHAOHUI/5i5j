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

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.OnHookSmsRecord;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.dao.OnHookSmsRecordDAO;
import com.oio.wawj.util.PageListData;


public class OnHookSmsRecordDAOImpl extends BaseDAOImpl<OnHookSmsRecord,Short> implements OnHookSmsRecordDAO {

	public PageListData getOnHookSmsRecord(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		return  ( PageListData) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						PageListData listdata = null;
						
						
						String chql=" where 1=1 ";
						
						 if (param.get("targetNumber") != null
									&& !((String) param.get("targetNumber")).equals("")) {
								chql += " and o.target_number like '%"
										+ (String) param.get("targetNumber")+ "%' " ;
						  }
						 if (param.get("state") != null
									&& !((String) param.get("state")).equals("")) {
					
								chql += " and  o.report_state = '"
											+ (String) param.get("state") +"'";

						  }
						 if (param.get("userName") != null 
									&& !((String) param.get("userName")).equals("")) {
							
							 
							 chql += " and o.user_name like '%"
										+ (String) param.get("userName")+ "%' " ;
						  }
						 if (param.get("AllScenes") != null 
								 && !((String) param.get("AllScenes")).equals("")) {
							 
							 
							 chql += " and o.scenario = '"
									 + (String) param.get("AllScenes")+ "' " ;
						 }
						 				 
			                
							if ( param.get("setId") != null
									&& !((String) param.get("setId")).equals("")) {
								
								chql += " and u.set_id in ("
										+ (String) param.get("setId")+")";
								  }

							 if (param.get("orgId") != null
										&& !((String) param.get("orgId")).equals("")) {
								 
									chql += " and u.org_id in ("
											+ (String) param.get("orgId")+")";
							  }
							 
						 
						 if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(o.timestamp) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
									+" and UNIX_TIMESTAMP(o.timestamp) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
						  }
						 chql +=" order by o.timestamp desc";
						
						
					    SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1 +chql);	
					    
					   //System.out.println("select count(*) as total" + hql1+chql);
						query.addScalar("total", Hibernate.LONG);
				
						int total = ((Long) query.uniqueResult()).intValue();
						
						query = getSession().createSQLQuery(hql+hql1+chql) ;
						//System.out.println(hql+hql1+chql);
						query.addScalar("timestamp",Hibernate.STRING )
						     .addScalar("targetNumber", Hibernate.STRING)
						     .addScalar("state", Hibernate.STRING)
						     .addScalar("scenario", Hibernate.STRING)
						     .addScalar("content", Hibernate.STRING)
						     .addScalar("callId", Hibernate.STRING)
						     .addScalar("userName", Hibernate.STRING)
						     .addScalar("chargeInfo", Hibernate.STRING)
						     .addScalar("cntsms", Hibernate.SHORT) ;
						 
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
		
	}
	
	@SuppressWarnings("unchecked")
	public List getCdrCallById(final String callId) {
		return  (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = " select c.prtms as prtms ,c.acms as acms,c.otherms as otherms,c.callTime as callTime,c.releaseTime as releaseTime ," +
								     " o.userName as userName,c.callType as callType from CdrCall c , OnHookSmsRecord o " +
									 " where c.callId=o.callId and c.callId = '"+ callId+ "'";		
						//System.out.println(hql);
						Query query = s.createQuery(hql);
						
						return query.list();	

					}
				});
	}
	
	
	
}
