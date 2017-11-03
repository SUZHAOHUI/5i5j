package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.Role;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.dao.StaffDAO;
import com.oio.wawj.struts.action.StaffAction;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.AxOrder;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;



/**
 *  主要功能：Staff相关的DAO实现类，主要包括对User表，UserAcmsRela的操作方法
 * @author 
 */

public class StaffDAOImpl extends BaseDAOImpl<User,Long> implements StaffDAO {

	public static Logger logger1 = Logger.getLogger(StaffDAOImpl.class);
	public PageListData findList(Map<String, Object> param, int currentPage,
			int pageSize) {
		PageListData listdata = null;

		String hql = "select  distinct u.name as name,u.code as code , u.duty as duty, u.status as status,"
				+ " o.org_name as org_name ,u.data_permission as data_permission,u.user_id as user_id ,o.org_id as org_id ,"
				+ " u.tel as tel ,u.set_id as setId ";// ,c.channel_name,a.acms as acms
		
		String hql1 = " from user u "
				+ " left join (select * from user_acms_rela where state = 'V') uar on u.user_id=uar.user_id "
				+ " left join org o on o.org_id=u.org_id and o.set_id = u.set_id " ;
				//+ " left join channel c on c.channel_id=uar.channel_id ";
		hql1 += " where u.type='S'";

		if (param.get("duty") != null
				&& !((String) param.get("duty")).equals("")) {
			hql1 += " and u.duty='" + (String) param.get("duty") + "' ";
		}
		if (param.get("permission") != null
				&& !((String) param.get("permission")).equals("")) {
			if (!((String) param.get("permission")).equals("all"))
				hql1 += " and u.data_permission = " + param.get("permission");
		}
		if (param.get("status") != null
				&& !((String) param.get("status")).equals("")) {
			if (!((String) param.get("status")).equals("all"))
				hql1 += " and u.status='" + (String) param.get("status") + "' ";
		}
		if (param.get("channel") != null
				&& !((String) param.get("channel")).equals("")) {
			hql1 += " and uar.channel_id =" + (String) param.get("channel");
		}
		if (param.get("nameOrNum") != null
				&& !((String) param.get("nameOrNum")).equals("")) {
			if (((String) param.get("name")).equals("name"))
				hql1 += " and " + " u.name like '%"
						+ (String) param.get("nameOrNum") + "%' ";
			else
				hql1 += " and " + " u.tel like '%"
						+ (String) param.get("nameOrNum") + "%' ";
		}

		if (param.get("orgId") != null
				&& !((String) param.get("orgId")).equals("")) {

			// TODO Auto-generated method stub
			String sql1 = " select getChildList(" + param.get("orgId") + ","
					+ (String) param.get("setId") + ") as childStr  ";
			// String childStr = (String)
			// getSession().createSQLQuery(sql1).uniqueResult();
			SQLQuery query0 = getSession().createSQLQuery(sql1);
			query0.addScalar("childStr", Hibernate.STRING);
			String childStr = query0.uniqueResult().toString();

			hql1 += " and " + "u.org_id in (" + childStr + ") and u.set_id = "
					+ (String) param.get("setId");
		} else {
			if (AboutOperator.getUser().getFunctionPermission() == 0) {
				Long orgId = AboutOperator.getUser().getOrgId();
				String sql1 = " select getChildList(" + orgId + ","
						+ (String) param.get("setId") + ") as childStr  ";
				SQLQuery query0 = getSession().createSQLQuery(sql1);
				query0.addScalar("childStr", Hibernate.STRING);
				String childStr = query0.uniqueResult().toString();

				hql1 += " and " + "u.org_id in (" + childStr + ") and u.set_id = " + (String) param.get("setId");
			}
		}

		if (AboutOperator.getUser().getFunctionPermission() == 0) {
			hql1 += " and u.set_id= " + (String) param.get("setId");
		}
		if (!param.get("type").equals("A")) {
			hql1 += " and u.set_id= " + (String) param.get("setId");
		}
		System.out.println(hql + hql1);
		  hql1 += " order by u.user_id ";
		try {
			SQLQuery query = getSession().createSQLQuery("select count(distinct u.user_id) as total " + hql1);
			query.addScalar("total", Hibernate.LONG);
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			query = getSession().createSQLQuery(hql + hql1)
					.addScalar("name", Hibernate.STRING)
					.addScalar("code", Hibernate.STRING)
					.addScalar("duty", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("data_permission", Hibernate.SHORT)
					.addScalar("user_id", Hibernate.LONG)
					.addScalar("org_id", Hibernate.LONG)
					.addScalar("tel", Hibernate.STRING)
			       .addScalar("setId", Hibernate.STRING);
			// .addScalar("acms", Hibernate.STRING);
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
						.setMaxResults(pageSize);
			}
			List data = query.list();
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
		return listdata;
	}  

	//导出表查询列表
	@SuppressWarnings({ "rawtypes"})
	public PageListData findExportList(Map<String, Object> param, int currentPage,
			int pageSize) {
		PageListData listdata = null;

		String hql = "select  distinct u.user_id as userId, u.name as name,u.code as code , u.duty as duty, u.status as status,"
				+ " o.org_name as org_name ,u.data_permission as data_permission,u.user_id as user_id ,o.org_id as org_id ,"
				+ " u.tel as tel ";// ,c.channel_name,a.acms as acms
		
		String hql1 = " from user u "
				+ " left join (select * from user_acms_rela where state = 'V') uar on u.user_id=uar.user_id "
				+ " left join org o on o.org_id=u.org_id and o.set_id = u.set_id " ;
				//+ " left join channel c on c.channel_id=uar.channel_id ";
		hql1 += " where u.type='S'";

		if (param.get("duty") != null
				&& !((String) param.get("duty")).equals("")) {
			hql1 += " and u.duty='" + (String) param.get("duty") + "' ";
		}
		if (param.get("permission") != null
				&& !((String) param.get("permission")).equals("")) {
			if (!((String) param.get("permission")).equals("all"))
				hql1 += " and u.data_permission = " + param.get("permission");
		}
		if (param.get("status") != null
				&& !((String) param.get("status")).equals("")) {
			if (!((String) param.get("status")).equals("all"))
				hql1 += " and u.status='" + (String) param.get("status") + "' ";
		}
		if (param.get("channel") != null
				&& !((String) param.get("channel")).equals("")) {
			hql1 += " and uar.channel_id =" + (String) param.get("channel");
		}
		if (param.get("nameOrNum") != null
				&& !((String) param.get("nameOrNum")).equals("")) {
			if (((String) param.get("name")).equals("name"))
				hql1 += " and " + " u.name like '%"
						+ (String) param.get("nameOrNum") + "%' ";
			else
				hql1 += " and " + " u.tel like '%"
						+ (String) param.get("nameOrNum") + "%' ";
		}

		if (param.get("orgId") != null
				&& !((String) param.get("orgId")).equals("")) {

			// TODO Auto-generated method stub
			String sql1 = " select getChildList(" + param.get("orgId") + ","
					+ (String) param.get("setId") + ") as childStr  ";
			// String childStr = (String)
			// getSession().createSQLQuery(sql1).uniqueResult();
			SQLQuery query0 = getSession().createSQLQuery(sql1);
			query0.addScalar("childStr", Hibernate.STRING);
			String childStr = query0.uniqueResult().toString();

			hql1 += " and " + "u.org_id in (" + childStr + ") and u.set_id = "
					+ (String) param.get("setId");
		} else {
			if (AboutOperator.getUser().getFunctionPermission() == 0) {
				Long orgId = AboutOperator.getUser().getOrgId();
				String sql1 = " select getChildList(" + orgId + ","
						+ (String) param.get("setId") + ") as childStr  ";
				SQLQuery query0 = getSession().createSQLQuery(sql1);
				query0.addScalar("childStr", Hibernate.STRING);
				String childStr = query0.uniqueResult().toString();

				hql1 += " and " + "u.org_id in (" + childStr + ") and u.set_id = " + (String) param.get("setId");
			}
		}

		if (AboutOperator.getUser().getFunctionPermission() == 0) {
			hql1 += " and u.set_id= " + (String) param.get("setId");
		}
		if (!param.get("type").equals("A")) {
			hql1 += " and u.set_id= " + (String) param.get("setId");
		}
		System.out.println(hql + hql1);
		hql1 += " order by u.user_id ";
		try {
			SQLQuery query = getSession().createSQLQuery("select count(distinct u.user_id) as total " + hql1);
			query.addScalar("total", Hibernate.LONG);
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			query = getSession().createSQLQuery(hql + hql1)
					.addScalar("userId", Hibernate.LONG)
					.addScalar("name", Hibernate.STRING)
					.addScalar("code", Hibernate.STRING)
					.addScalar("duty", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("data_permission", Hibernate.SHORT)
					.addScalar("user_id", Hibernate.LONG)
					.addScalar("org_id", Hibernate.LONG)
					.addScalar("tel", Hibernate.STRING);
			//.addScalar("channel_name", Hibernate.STRING);
			// .addScalar("acms", Hibernate.STRING);
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
						.setMaxResults(pageSize);
			}
			List data = query.list();
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
		return listdata;
	}  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> findChannelRela(){
		try {
			SQLQuery query = getSession().createSQLQuery("select distinct uar.user_id as userId, uar.channel_id as channelId, " +
					"c.channel_name as channelName, sr.anum as anum, sr.xnum as xnum " +
					"from acms a, user_acms_rela uar, channel c, subs_rela sr " +
					"where uar.acms_id=a.acms_id and c.channel_id=uar.channel_id and uar.state = 'V' " +
					"and sr.user_id=uar.user_id and sr.state='B' and sr.xnum=a.acms " +
					"and uar.acms_id=a.acms_id and uar.state = 'V' and a.status ='V' ")
					.addScalar("userId", Hibernate.LONG)
					.addScalar("channelId", Hibernate.INTEGER)
					.addScalar("anum", Hibernate.STRING)
					.addScalar("xnum", Hibernate.STRING)
					.addScalar("channelName", Hibernate.STRING);
			// and u.status = 'V' and uar.state = 'V' and a.status ='V' and sr.state='B' 
			List data1 = query.list();
			return data1;
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public  JSONArray getChannelList( final Long operator){
		return  ( JSONArray) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Channel c where 1=1 and c.state='V' ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						Map<String, Integer> param = new HashMap<String, Integer>();
						for(int i = 0; i < list.size(); i++){	
							Channel sf = (Channel)list.get(i);	
							
							Integer cId = sf.getId().getChannelId();
							String cname = sf.getId().getChannelName();
							jsonObject.put("cId", cId);
							jsonObject.put("cname", cname);
							jsonArray.add(jsonObject);
						}
						return jsonArray; 
					}
				});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  String getDutyList( final Long operator){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray();  
						String hql = "from  Role r where 1=1 and r.status='V'  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Role role = (Role)list.get(i);	
							short rId = role.getRoleId();
							jsonObject.put("value", rId);
							jsonObject.put("label",role.getRoleName());
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
	public Acms findByAcms(final String acms) {
		return  (Acms) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);	
						String hql = "from Acms a " +
										" where a.acms = "+acms;		
						Query query = s.createQuery(hql);

						List list = query.list();
						return (list.size()==0)?null:query.list().get(0);	
					}
				});
	}
	@SuppressWarnings("unchecked")
	public Org findOrgByName(final String orgName) {
		return  (Org) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
					
						String hql = "from Org o " +
										" where o.orgName = "+orgName;		
						Query query = s.createQuery(hql);
						List list = query.list();
						return (list.size()==0)?null:query.list().get(0);	

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
	@SuppressWarnings("unchecked")
	public Channel findChannelById(final Integer id)
	{
		return  (Channel) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from Channel c where c.id.channelId= ?";						
						try{
							Query query =  s.createQuery(hql);
						    query.setInteger(0, id);
							return query.list().get(0);
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public ParameterOrgRela findRingBySetId(final String id)
	{
		return  (ParameterOrgRela) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from ParameterOrgRela por where por.orgId= ?";						
						try{
							Query query =  s.createQuery(hql);
						    query.setString(0, id);
						    List list=query.list();
							return  list.size()==0?null: query.list().get(0);
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public Org findOrgById(final Long id,final String setId)
	{
		return  (Org) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from Org o where o.id.orgId= ? and o.id.setId= ?";						
						try{
							Query query =  s.createQuery(hql);
						    query.setLong(0, id);
						    query.setString(1, setId);
						   return query.list().get(0);
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public Org findOrgById(final Long id)
	{
		return  (Org) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from Org o where o.id.orgId= ? ";						
						try{
							Query query =  s.createQuery(hql);
						    query.setLong(0, id);
				
						   return query.list().get(0);
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getUserAcmsRela(final Long userId,final String channel) {
		return  (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String	hql ="";
						Integer  channelId=0;
						if(!channel.equals("")){
							channelId=Integer.valueOf(channel);
						}
					    
						hql = "from Acms a , UserAcmsRela uar, User u ,SubsRela sr,Channel c " +
							" where a.acmsId = uar.acmsId " +
							" and uar.userId=u.userId" +
							" and sr.userId=u.userId" +
							" and a.acms = sr.xnum " +
							" and uar.channelId = c.id.channelId " +
							" and u.status = 'V' and uar.state = 'V' and a.status ='V' and sr.state='B' " +
							" and u.userId = "+userId;
						if(channelId!=0)
					       hql += " and c.id.channelId = "+channelId;	
					 
						Query query = s.createQuery(hql);
						List list = query.list();
						return list;	
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List findAcmsByChNameAndCode(final String chName,final String code,final String name) {
		return  (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String	hql ="";
    
							hql = "from Acms a ,UserAcmsRela uar , User u ,Channel c " +
								" where a.acmsId = uar.acmsId " +
								" and uar.userId=u.userId" +
								" and uar.channelId = c.id.channelId " +
								" and u.status = 'V' and uar.state = 'V' and a.status ='V' ";
							   if(!code.equals("")){
								 hql+=" and u.code like "+"'%"+code+"%'";
							   }else{
								 hql+=" and u.name= "+name;  
							   }
						     hql+="  and c.id.channelName like "+"'%"+chName+"%'";
						   
						Query query = s.createQuery(hql);
						List list = query.list();
						return list.size()==0?null:list;	
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<Object> findSubsRelaByUserId(final String id)
	{
		return  ( List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from SubsRela s,UserAcmsRela uar,Acms ac,User u where s.xnum=ac.acms " +
								" and ac.acmsId=uar.acmsId " +
								" and s.userId=u.userId " +
								" and uar.state='V'" +
								" and s.state ='B' and u.code=?";						
						try{
							Query query =  s.createQuery(hql);
							query.setString(0, id);
							System.out.println(hql);
							return query.list();
						}catch(Exception e0){
							e0.printStackTrace();
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<Object> findSubsRelaByCodeAndAcms(final String id,final String acms)
	{
		return  ( List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from SubsRela s,UserAcmsRela uar,Acms ac,User u where s.xnum=ac.acms " +
								" and ac.acmsId=uar.acmsId " +
								" and s.userId=u.userId " +
								" and uar.state='V'" +
								" and s.state ='B' and u.code=? and s.xnum=? ";						
						try{
							Query query =  s.createQuery(hql);
							query.setString(0, id);
							query.setString(1, acms);
							List list=query.list();
							System.out.println(hql);
							return list.size()==0?null:list;
						}catch(Exception e0){
							e0.printStackTrace();
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<Object> findUnSubsRelaByUserId(final String id)
	{
		return  ( List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from SubsRela s,UserAcmsRela uar,Acms ac,User u " +
								" where s.xnum=ac.acms " +
								" and ac.acmsId=uar.acmsId and s.userId = u.userId " +
								" and uar.state='V'" +
								" and s.state ='U' and u.code= ?";						
						try{
							Query query =  s.createQuery(hql);
						    query.setString(0, id);
						    List list=query.list();
							return (list.size()==0)?null:query.list();
							//(list.size()==0)?null:query.list().get(0)
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List<Object> findSubsRelaByXnum(final String xnum)
	{
		return  ( List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " from SubsRela s,UserAcmsRela uar,Acms ac,User u where s.xnum=ac.acms " +
								" and ac.acmsId=uar.acmsId " +
								" and s.userId=u.userId " +
								" and uar.state='V' and s.state='B'" +
								" and s.xnum= "+xnum;						
						try{
							Query query =  s.createQuery(hql);
							List list=query.list();
							return query.list();
						}catch(Exception e0){
							
							return null;
						}
					}
				});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String importExcel(final List<Object[]> list) {
		return (String)getHibernateTemplate().execute(
			new HibernateCallback() {
				public Object doInHibernate(Session s) 
					throws HibernateException, SQLException {
					logger1.info("员工-----导入----");
					Transaction tx = s.beginTransaction();
					try{
						tx.begin();
						
						String errorCode = "";
						if (list != null) {
							Integer orgId;
							Org org = null;
							for (int i = 0; i < list.size(); i++) {
								Object[] o = list.get(i);
								String code = o[0] == null ? "" : o[0].toString(); // user
																					// code
								String name = o[3] == null ? "" : o[3].toString(); // name
								String jaString = (String) o[1];// channelName:cname
																// Acms:acms
								JSONArray ja = JSONArray.fromObject(jaString);
								for (int j = 0; j < ja.size(); j++) {
									JSONObject json = (JSONObject) ja.get(j);
									String channelName = json.getString("cname");
									Integer cId = 0;
									Integer ringchannel = 0;
									if (!channelName.isEmpty()) {
										Channel ch = findChannelByName(channelName);
										cId = ch.getId().getChannelId();
										ringchannel = ch.getRingId();
									}
									String calling = (String) o[2];
									List<User> userList =null;
									 if(!code.equals("")){
									     userList = findByProperty(User.class, "code", code);
									 }else{
										 userList = findByProperty(User.class, "name", name);
									 }
									if (userList == null || userList.size() == 0) {
										continue;
									}
									User staff = userList.get(0);
									Long userId = staff.getUserId();
									String status = staff.getStatus();
									String setId = staff.getSetId();

									String[] acms0 = (String[]) (json.get("acms") == null ? null : json.getString("acms").split(","));
									if(acms0 != null){
										for(int x = 0; x < acms0.length; x++){
											Acms ac = null;
											String acms = acms0[x].replace(",", "");
											if (!acms.isEmpty())
												ac = findByAcms(acms);
			
											if (ac != null) {
												Integer acmsId = ac.getAcmsId();
												String hql = "from UserAcmsRela where acmsId=" + acmsId + " and state='V'";
												Query q = s.createQuery(hql);
												List<UserAcmsRela> uarList = q.list();
												
												uarList = uarList.size() == 0 ? null : uarList;
												if (status != null && status.equals("V")) {
													if (uarList != null) {
														errorCode = "1";
														logger1.info(acms+":-----已绑定");
														continue;
													} else {
														
														ParameterOrgRela por = findRingBySetId(setId);
														String cityRing = "307";
														if (por != null) {
															cityRing = por.getValueId().toString();
														}
														// 若号码关系不存在于UserAcmsRela表中，则新增,若存在则根据是否上线，来绑定
														//String res=null;
														String res = AxOrder.axOrder(calling, acms, cityRing, ringchannel.toString());
														logger1.info("导入绑定---acms:"+acms+"------prtms:"+calling);
														JSONObject resjo = JSONObject.fromObject(res);
														logger1.info("导入绑定---result:"+resjo.getInt("result"));
														if (resjo.getInt("result") == 200) {
															String dateTime = DateTime.DateToString(new Date());
															String sql = "INSERT INTO user_acms_rela(user_id,channel_id,acms_id,state,state_date,purpose) VALUES (";
															sql += Integer.valueOf(userId.toString()) + "," + cId + "," + ac.getAcmsId() + ",'V','" 
																	+ DateTime.getCurrentDateTime() + "','C' )";
															SQLQuery sqlQuery = s.createSQLQuery(sql);
															sqlQuery.executeUpdate();

															String subId = resjo.getString("subid");
															String operatorId = String.valueOf(AboutOperator.getOperatorId());
															sql = "INSERT INTO subs_rela(anum,xnum,sub_id,user_id,state,state_date,operator) VALUES ('";
															sql += calling + "'," + acms + ",'" + subId + "'," + Integer.valueOf(userId.toString()) + ",'B','" 
																	+ dateTime + "'," + Integer.valueOf(operatorId) + ")";
															sqlQuery = s.createSQLQuery(sql);
															sqlQuery.executeUpdate();
															
															ac.setState("U");
															ac.setStateDate(AboutOperator.getCurrentDate());
															ac.setStatus("V");
															ac.setStatusDate(AboutOperator.getCurrentDate());
															s.update(ac);
															logger1.info("绑定成功---"+resjo.getInt("result")+"--acms:"+acms+"------prtms:"+calling);
														}
													}
												} else {
													errorCode = "3";// 员工已离职
													continue;
												}
											} else {
												errorCode = "2";
												continue;
											}
										}
									}
									
								}
							}
	
						}

						tx.commit();
						tx.begin();
						return errorCode;
					}catch(RuntimeException re){
						tx.rollback();
						tx.begin();
						logger.error("***Error: ", re);
					}
					return "";
				}
			});		
	}

}