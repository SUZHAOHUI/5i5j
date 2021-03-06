

package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;


import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.dao.CallRecordsDAO;
import com.oio.wawj.util.PageListData;



/**
 * ��Ҫ���ܣ�CdrCall��ص�DAOʵ���࣬��Ҫ������CdrCall��Ĳ�������
 
 */
public class CallRecordsDAOImpl extends BaseDAOImpl<CdrCall,Long> implements CallRecordsDAO{
	/**
	 * ��ȡBill�б���Ϣ
	 * @param mParam ������
	 * @param cParam ������
	 * @param tParam ������
	 * @return ��ӡ�б���Ϣ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getList(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
														
							
							String chql=" where cc.call_type=1 and uar.purpose='C' ";
							//cc.call_type=11 or cc.call_type=21
							 if (param.get("channel") != null
										&& !((String) param.get("channel")).equals("all")) {
									chql += " and c.channel_id = "
											+ (String) param.get("channel") ;
							  }
							 
								 if (param.get("orgId") != null
											&& !((String) param.get("orgId")).equals("")) {
										chql += " and o.org_id in ("
												+ (String) param.get("orgId")+")";
								  }
								 if (param.get("setId") != null
											&& !((String) param.get("setId")).equals("")) {
										chql += " and o.set_id in ("
												+ (String) param.get("setId")+")";
								  }
															  
			
							 if (param.get("nameOrUser") != null
										&& !((String) param.get("nameOrUser")).equals("")) {
								 if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
												+ (String) param.get("nameOrUser") + "%' ";
								 else 
									chql += " and cc.prtms like '%"
												+ (String) param.get("nameOrUser") + "%' ";
							  }
/*							 if (param.get("customer") != null 
										&& !((String) param.get("customer")).equals("")) {
								 if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
								 
								 chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							  }
							 */
							 if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals("")){
								 
							 chql += " and cc.otherms like '%"
										+ (String) param.get("CustomerNumber")+ "%' " ;
							 
							 }
							 if (param.get("beginTime") != null
										&& !((String) param.get("beginTime")).equals("")) {
									chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							  }
							 
							 //System.out.println(param.get("isDiff")+"----------isDiff333");
							 
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 chql +=" order by cc.call_time desc ) ds group by ds.name, ds.otherms, ds.channel_name, " +
								 		" ds.prtms,ds.code,ds.acms " +
								 		" order by ds.call_time desc";
							 }else{
								  chql +=" order by cc.call_time desc ";
							  }
							 

							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");
								query.addScalar("total", Hibernate.LONG);
						       System.out.println(hql+hql1+chql);
								int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 
							    query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)								     
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.STRING)
								     .addScalar("acms", Hibernate.STRING);			 
								 
							 }else{
									query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.STRING)
								     .addScalar("duration", Hibernate.INTEGER)
								     .addScalar("csName", Hibernate.STRING)
								     .addScalar("oname", Hibernate.STRING)
								     .addScalar("acms", Hibernate.STRING)
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("callId", Hibernate.STRING)
								     .addScalar("callDuration", Hibernate.INTEGER)
									 .addScalar("releaseDir", Hibernate.STRING)
					                 .addScalar("csDesc", Hibernate.STRING);
							  }


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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallInMessage(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type=3 and uar.purpose='C' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ( "
										+ (String) param.get("orgId") +")";
							}
							if (param.get("setId") != null
									&& !((String) param.get("setId")).equals("")) {
								chql += " and o.set_id in ( "
										+ (String) param.get("setId") +")";
							}
							
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
/*							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}*/
							
							if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals("")){
								
								chql += " and cc.otherms like '%"
										+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							 chql +=" order by cc.call_time desc";
							//��¼����
							SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1+chql);	
							//System.out.println("select count(*) as total" + hql1+chql);
							query.addScalar("total", Hibernate.LONG);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							query.addScalar("name",Hibernate.STRING )
							.addScalar("otherms", Hibernate.STRING)
							.addScalar("channel_name", Hibernate.STRING)
							.addScalar("prtms", Hibernate.STRING)
							.addScalar("call_time", Hibernate.STRING)
							.addScalar("duration", Hibernate.INTEGER)
							.addScalar("csName", Hibernate.STRING)
							.addScalar("oname", Hibernate.STRING)
							.addScalar("acms", Hibernate.STRING)
							.addScalar("code", Hibernate.STRING)
							.addScalar("callId", Hibernate.STRING)
							.addScalar("content", Hibernate.STRING);
							
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutRecord(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;						
							
							String chql=" where (cc.call_type=0 or cc.call_type=128) and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")" ;
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 chql +=" order by cc.call_time desc ) ds group by ds.name, ds.otherms, ds.channel_name, " +
								 		" ds.prtms,ds.code";
							 }else{
								  chql +=" order by cc.call_time desc ";
							  }
							 

							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");
								query.addScalar("total", Hibernate.LONG);
						       //System.out.println(hql+hql1+chql);
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 
							    query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)								     
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP);			 
								 
							 }else{
									query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP)
								     .addScalar("duration", Hibernate.INTEGER)
								     .addScalar("csName", Hibernate.STRING)
								     .addScalar("oname", Hibernate.STRING)
								     .addScalar("acms", Hibernate.STRING)
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("callId", Hibernate.STRING)
								     .addScalar("callDuration", Hibernate.INTEGER);
							  }
							
							
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutComeRecord(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type=1 and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")";
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 chql +=" order by cc.call_time desc ) ds group by ds.name, ds.otherms, ds.channel_name, " +
								 		" ds.prtms,ds.code";
							 }else{
								  chql +=" order by cc.call_time desc ";
							  }
							 

							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");
								query.addScalar("total", Hibernate.LONG);
						       //System.out.println(hql+hql1+chql);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 
							    query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)								     
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP);			 
								 
							 }else{
									query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP)
								     .addScalar("duration", Hibernate.INTEGER)
								     .addScalar("csName", Hibernate.STRING)
								     .addScalar("oname", Hibernate.STRING)
								     .addScalar("acms", Hibernate.STRING)
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("callId", Hibernate.STRING)
								     .addScalar("callDuration", Hibernate.INTEGER);
							  }
							
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutStranger(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type=21 and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")";
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 chql +=" order by cc.call_time desc ) ds group by ds.name, ds.otherms, ds.channel_name, " +
								 		" ds.prtms,ds.code";
							 }else{
								  chql +=" order by cc.call_time desc ";
							  }
							 

							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+") "+" as callInList");
								query.addScalar("total", Hibernate.LONG);
						       //System.out.println(hql+hql1+chql);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							 if (param.get("isDiff") != null 
									    && ((String) param.get("isDiff")).equals("true")
								  ) {
								 
							    query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)								     
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP);			 
								 
							 }else{
									query.addScalar("name",Hibernate.STRING )
								     .addScalar("otherms", Hibernate.STRING)
								     .addScalar("channel_name", Hibernate.STRING)
								     .addScalar("prtms", Hibernate.STRING)
								     .addScalar("call_time", Hibernate.TIMESTAMP)
								     .addScalar("duration", Hibernate.INTEGER)
								     .addScalar("csName", Hibernate.STRING)
								     .addScalar("oname", Hibernate.STRING)
								     .addScalar("acms", Hibernate.STRING)
								     .addScalar("code", Hibernate.STRING)
								     .addScalar("callId", Hibernate.STRING)
								     .addScalar("callDuration", Hibernate.INTEGER);
							  }
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutMessage(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type in (2,3) and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")";
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							 chql +=" order by cc.call_time desc";
							//��¼����
							SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1+chql);	
							//System.out.println("select count(*) as total" + hql1+chql);
							query.addScalar("total", Hibernate.LONG);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							query.addScalar("name",Hibernate.STRING )
							.addScalar("otherms", Hibernate.STRING)
							.addScalar("channel_name", Hibernate.STRING)
							.addScalar("prtms", Hibernate.STRING)
							.addScalar("call_time", Hibernate.TIMESTAMP)
							.addScalar("duration", Hibernate.INTEGER)
							.addScalar("csName", Hibernate.STRING)
							.addScalar("oname", Hibernate.STRING)
							.addScalar("acms", Hibernate.STRING)
							.addScalar("code", Hibernate.STRING)
							.addScalar("callId", Hibernate.STRING)
							.addScalar("content", Hibernate.STRING);
							
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutReplyRecord(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type=3 and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")";
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							 chql +=" order by cc.call_time desc";
							//��¼����
							SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1+chql);	
							//System.out.println("select count(*) as total" + hql1+chql);
							query.addScalar("total", Hibernate.LONG);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							query.addScalar("name",Hibernate.STRING )
							.addScalar("otherms", Hibernate.STRING)
							.addScalar("channel_name", Hibernate.STRING)
							.addScalar("prtms", Hibernate.STRING)
							.addScalar("call_time", Hibernate.TIMESTAMP)
							.addScalar("duration", Hibernate.INTEGER)
							.addScalar("csName", Hibernate.STRING)
							.addScalar("oname", Hibernate.STRING)
							.addScalar("acms", Hibernate.STRING)
							.addScalar("code", Hibernate.STRING)
							.addScalar("callId", Hibernate.STRING)
							.addScalar("content", Hibernate.STRING);
							
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getCallOutStrangerRecords(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize)
	{
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
								throws HibernateException, SQLException {
							PageListData listdata = null;
							
							
							String chql=" where cc.call_type=3 and uar.purpose='O' ";
							//cc.call_type=11 or cc.call_type=21
							if (param.get("channel") != null
									&& !((String) param.get("channel")).equals("all")) {
								chql += " and c.channel_id = "
										+ (String) param.get("channel") ;
							}
							if (param.get("orgId") != null
									&& !((String) param.get("orgId")).equals("")) {
								chql += " and o.org_id in ("
										+ (String) param.get("orgId") +")";
							}
							if (param.get("nameOrUser") != null
									&& !((String) param.get("nameOrUser")).equals("")) {
								if(param.get("adviser").equals("name"))	
									chql += " and u.name like '%"
											+ (String) param.get("nameOrUser") + "%' ";
								else 
									chql += " and cc.prtms like '%"
											+ (String) param.get("nameOrUser") + "%' ";
							}
							if (param.get("customer") != null 
									&& !((String) param.get("customer")).equals("")) {
								if((String)param.get("CustomerNumber") != null && !param.get("CustomerNumber").equals(""))
									
									chql += " and cc.otherms like '%"
											+ (String) param.get("CustomerNumber")+ "%' " ;
							}
							if (param.get("beginTime") != null
									&& !((String) param.get("beginTime")).equals("")) {
								chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							}
							
							 chql +=" order by cc.call_time desc";
							//��¼����
							SQLQuery query = s.createSQLQuery("select count(*) as total " + hql1+chql);	
							//System.out.println("select count(*) as total" + hql1+chql);
							query.addScalar("total", Hibernate.LONG);
							
							int total = ((Long) query.uniqueResult()).intValue();
							query = getSession().createSQLQuery(hql+hql1+chql) ;
							query.addScalar("name",Hibernate.STRING )
							.addScalar("otherms", Hibernate.STRING)
							.addScalar("channel_name", Hibernate.STRING)
							.addScalar("prtms", Hibernate.STRING)
							.addScalar("call_time", Hibernate.TIMESTAMP)
							.addScalar("duration", Hibernate.INTEGER)
							.addScalar("csName", Hibernate.STRING)
							.addScalar("oname", Hibernate.STRING)
							.addScalar("acms", Hibernate.STRING)
							.addScalar("code", Hibernate.STRING)
							.addScalar("callId", Hibernate.STRING)
							.addScalar("content", Hibernate.STRING);
							
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