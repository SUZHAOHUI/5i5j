package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.NumberDAO;
import com.oio.wawj.dao.StaffDAO;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;


/**
 *  主要功能：Acms相关的DAO实现类，主要包括对Acms表，UserAcmsRela的操作方法
 * @author 
 */

public class NumberDAOImpl extends BaseDAOImpl<Acms,Integer> implements NumberDAO {

	
	public PageListData findNumberList(Map<String, Object> param, int currentPage,
			int pageSize) {
		// TODO Auto-generated method stub
		
		PageListData listdata=null;	
		String hql1 ="";
		 
		String hql="  select distinct  a.acms as acms,c.channel_name as channel_name," +
				   "  a.state as state,u.code as code ,u.name as name,asr.descr as city,sr.anum as prtms";
		if(AboutOperator.getUser().getFunctionPermission()==0)
		   {  
			 hql1 = " from acms a" +
		           	  " left join (select * from user_acms_rela where state = 'V') uar on uar.acms_id=a.acms_id " +
			          " left join channel c on c.channel_id=uar.channel_id " +
			          " left join user u on u.user_id=uar.user_id " +
			          " left join (select * from subs_rela where state ='B') sr on sr.user_id=u.user_id and sr.xnum=a.acms   " +
			          " inner join areacode_setid_rela  asr on asr.code=a.code " ;			        
		     hql1 +=  " where 1=1  " +
		     		  " and (a.status='I' or (a.status='V' and u.set_id= "+ (String)param.get("setId")+" )) " +
		     		  " and asr.set_id= "+(String)param.get("setId") ;
		  }else{
			   hql1 = " from acms a" +
			           	  " left join (select * from user_acms_rela where state = 'V') uar on uar.acms_id=a.acms_id " +
				          " left join channel c on c.channel_id=uar.channel_id " +
				          " left join user u on u.user_id=uar.user_id " +
				          " left join (select * from subs_rela where state ='B') sr on sr.user_id=u.user_id and sr.xnum=a.acms " +
				          " inner join areacode_setid_rela  asr on asr.code=a.code " ;			        
			   hql1 += " where 1=1 and (a.status='V' or a.status='I') ";   
		   }
		     
		if (param.get("status") != null && !((String)param.get("status")).equals(""))
			{
			    if(((String)param.get("status")).equals("all")){
			    	hql1 += " and 1=1 ";
			    } else{	
			    	hql1 += " and a.status = '" + (String)param.get("status") + "'";
			    }
			} 
		if (param.get("channel") != null && !((String)param.get("channel")).equals(""))
			{
	            if(((String)param.get("channel")).equals("nothing"))
	            	hql1 += " and 1=1 ";
	            else
	            	hql1 += " and " + "c.channel_id = " + (String)param.get("channel");
			}
		if (param.get("UnameOrNum") != null && !((String)param.get("UnameOrNum")).equals(""))
			{
	            if( param.get("nameOrNum").equals("name"))
	            	hql1 += " and ( " + "u.name like '%" + (String)param.get("UnameOrNum") + "%'" +
						" or u.code like '% "+(String)param.get("UnameOrNum") + "%' )" ;
	            else
	            	hql1 += " and a.acms like '%" + (String)param.get("UnameOrNum") + "%'";
			}	
		
		try {
		SQLQuery query = getSession().createSQLQuery("select count(*) as total from (" + hql + hql1+")tj");
		query.addScalar("total", Hibernate.LONG);
		int total = ((Long) query.uniqueResult()).intValue();
		System.out.println(hql+hql1);
		logger.debug("总记录数:", total);	
			     query = getSession().createSQLQuery(hql + hql1)
				.addScalar("acms",Hibernate.STRING)
				.addScalar("channel_name",Hibernate.STRING)
				.addScalar("state",Hibernate.STRING)
				.addScalar("code",Hibernate.STRING)
				.addScalar("name",Hibernate.STRING)
				.addScalar("city",Hibernate.STRING)
				.addScalar("prtms",Hibernate.STRING);
		
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize).setMaxResults(pageSize);
			}
			List data = query.list();
			//System.out.println(data.size());
		   
			listdata = new PageListData(total, pageSize, currentPage, data);
	     	} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		    }
	
		     return listdata;
	}  
		
	/**
	 * 查询所有acms的number
	 * */
	@SuppressWarnings("unchecked")
	public List<String> findAllAcms()
	{
		return  (List<String>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " select a.acms from Acms a where 1=1 ";						
						try{
							Query query =  s.createQuery(hql);
							Set<String> numberSet = new HashSet<String>();
							numberSet.addAll((List<String>)query.list());
							return (List<String>)query.list();
						}catch(Exception e0){
							return null;
						}
					}
				});
	}

	/**
	 * 通过渠道的名称找到渠道ID
	 * */
	@SuppressWarnings("unchecked")
	public Channel findChannelByName(final String name)
	{
		return  (Channel) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from Channel c where c.id.channelName= ?";						
						try{
							Query query =  s.createQuery(hql);
						    query.setString(0, name);
							return query.list().get(0);
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	public User findUserByCode(String code) {
		String hql="from User u where u.code=? ";
		Query q=getSession().createQuery(hql);
		q.setString(0,code);
		return q.list()!=null&&q.list().size()>0?(User)q.list().get(0):null;
	}
	
	public PageListData findStaffByAcms(Map<String, Object> param, int currentPage,
			int pageSize) {
		// TODO Auto-generated method stub
		
		PageListData listdata=null;	
       String acms=((String)param.get("acms"));
		
		String hql=" select distinct u.name as name,u.code as code , u.duty as duty, u.status as status," +
				" o.org_name as org_name ,u.data_permission as data_permission,u.user_id as user_id ";
		String      hql1 = " from user u "+
                     " left join (select * from user_acms_rela where state = 'V') uar on u.user_id=uar.user_id"+
                     " left join acms a on a.acms_id=uar.acms_id "+
                     " left join org o on o.org_id=u.org_id and o.set_id = u.set_id "+
                     " left join channel c on c.channel_id=uar.channel_id ";			        
		       hql1 += " where u.type='S' and a.state='U' and a.acms= "+acms;
	
		listdata = findListForSql(hql,hql1, currentPage, pageSize);
		return listdata;
	}  


	

}