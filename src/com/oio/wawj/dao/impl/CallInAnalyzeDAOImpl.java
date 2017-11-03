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
import com.oio.wawj.bean.CustomerStatus;
import com.oio.wawj.dao.CallInAnalyzeDAO;
import com.oio.wawj.util.PageListData;

public class CallInAnalyzeDAOImpl extends BaseDAOImpl<CdrCall,Long> implements CallInAnalyzeDAO{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getList(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
														
							
/*							String chql=" where (((unix_timestamp(cc.release_time) - unix_timestamp(cc.call_time)) >= 0) " +
									" AND ((unix_timestamp(cc.release_time) - unix_timestamp(cc.call_time) "+
				                    " ) < 30)) ";*/
							
							String chql = "where 1=1 ";
							
							String chql2=" group by c.channel_name,cc.release_time,cc.start_time) as c_t " +
									" group by c_t.channel_name ";
							
							 if (param.get("duration") != null
										&& !((String) param.get("duration")).equals("")) {
									chql += " AND (((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)) >= 0)" +
											"AND ((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)" +
											" ) >= " + (String) param.get("duration") +"))" ;
							  }
							 
							 if (param.get("orgId") != null
								&& !((String) param.get("orgId")).equals("")) {
								 chql += " and o.org_id in ("
											+ (String) param.get("orgId") + ")";
					          }
							 if (param.get("setId") != null
										&& !((String) param.get("setId")).equals("")) {
									chql += " and o.set_id in ("
											+ (String) param.get("setId")+")";
							  }
							 
							 if (param.get("name") != null
								&& !((String) param.get("name")).equals("")) {
								 chql += " and u.name = '"
											+ (String) param.get("name") + "' ";
					          }
							  
							 if (param.get("channelName") != null
										&& !((String) param.get("channelName")).equals("")) {
									chql += " and c.channel_id = "
											+ (String) param.get("channelName");
							  }

							 if (param.get("beginTime") != null
										&& !((String) param.get("beginTime")).equals("")) {
									chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							  }
							    //��¼����
							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as channels");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as channels");
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								//System.out.println(total);
							    query =  getSession().createSQLQuery(hql+hql1+chql+chql2)
							    		.addScalar("channelName", Hibernate.STRING) 
							            .addScalar("connTotal", Hibernate.BIG_DECIMAL)
							            .addScalar("disconnTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("callTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("connRate",Hibernate.BIG_DECIMAL);
							    System.out.println(hql+hql1+chql+chql2);
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
	public PageListData getDeptList(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
														
							
/*							String chql=" where (((unix_timestamp(cc.release_time) - unix_timestamp(cc.call_time)) >= 0) " +
									" AND ((unix_timestamp(cc.release_time) - unix_timestamp(cc.call_time) "+
				                    " ) < 30)) ";*/
							
							String chql = " where 1=1 ";
							
							String chql2=" group by org_name,cc.release_time,cc.start_time) as c_t " +
									" group by c_t.org_name ";
							
							 if (param.get("duration") != null
										&& !((String) param.get("duration")).equals("")) {
									chql += " AND (((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)) >= 0)" +
											"AND ((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)" +
											" ) >= " + (String) param.get("duration") +"))" ;
							  }
							 
							 if (param.get("orgId") != null
								&& !((String) param.get("orgId")).equals("")) {
								 chql += " and o.org_id in ("
											+ (String) param.get("orgId") + ")";
					          }
							 if (param.get("setId") != null
										&& !((String) param.get("setId")).equals("")) {
									chql += " and o.set_id in ("
											+ (String) param.get("setId")+")";
							  }
								
							 
							 if (param.get("name") != null
								&& !((String) param.get("name")).equals("")) {
								 chql += " and u.name = '"
											+ (String) param.get("name") + "' ";
					          }
							  
							 if (param.get("channelName") != null
										&& !((String) param.get("channelName")).equals("")) {
									chql += " and c.channel_id = "
											+ (String) param.get("channelName");
							  }

							 if (param.get("beginTime") != null
										&& !((String) param.get("beginTime")).equals("")) {
									chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							  }
							    //��¼����
							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as depts");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as depts");
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								//System.out.println(total);
							    query =  getSession().createSQLQuery(hql+hql1+chql+chql2)
							    		.addScalar("orgName", Hibernate.STRING) 
							            .addScalar("connTotal", Hibernate.BIG_DECIMAL)
							            .addScalar("disconnTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("callTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("connRate",Hibernate.BIG_DECIMAL);
							    //System.out.println(hql+hql1+chql+chql2);
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
	public PageListData getStaffList(final String hql,final String hql1,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
														
							
							String chql = "where 1=1 ";
							
							String chql2=" group by u.name,cc.release_time,cc.start_time,c.channel_name,u.code) as c_t " +
									" group by c_t.name,c_t.channel_name,c_t.code ";
							
							 if (param.get("duration") != null
										&& !((String) param.get("duration")).equals("")) {
									chql += " AND (((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)) >= 0)" +
											"AND ((unix_timestamp(cc.release_time) - unix_timestamp(cc.start_time)" +
											" ) >= " + (String) param.get("duration") +"))" ;
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
								
							 
							 if (param.get("name") != null
								&& !((String) param.get("name")).equals("")) {
								 chql += " and u.name = '"
											+ (String) param.get("name") + "' ";
					          }
							  
							 if (param.get("channelName") != null
										&& !((String) param.get("channelName")).equals("")) {
									chql += " and c.channel_id = '"
											+ (String) param.get("channelName") + "'" ;
							  }

							 if (param.get("beginTime") != null
										&& !((String) param.get("beginTime")).equals("")) {
									chql += " and UNIX_TIMESTAMP(cc.call_time) >= UNIX_TIMESTAMP('"+param.get("beginTime")+"') "
										+" and UNIX_TIMESTAMP(cc.call_time) <= UNIX_TIMESTAMP('"+param.get("endTime")+"') " ;
							  }
							    //��¼����
							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as staffs");	
							    //System.out.println("select count(*) as total from " +"("+hql+hql1+chql+chql2+") "+" as staffs");
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								//System.out.println(total);
							    query =  getSession().createSQLQuery(hql+hql1+chql+chql2)
							    		.addScalar("name", Hibernate.STRING)
							    		.addScalar("channelName", Hibernate.STRING)
							    		.addScalar("code", Hibernate.STRING)		
							            .addScalar("connTotal", Hibernate.BIG_DECIMAL)
							            .addScalar("disconnTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("callTotal",Hibernate.BIG_DECIMAL)
							            .addScalar("connRate",Hibernate.BIG_DECIMAL);
							    //System.out.println(hql+hql1+chql+chql2);
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
	
	
	public  String getfindChannelName(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
						String hql = "from  Channel  ";
						
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Channel sf = (Channel)list.get(i);	
							jsonObject.put("channelId",sf.getId().getChannelId());
							jsonObject.put("channelName",sf.getId().getChannelName());		
							jsonArray.add(jsonObject);
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	
}