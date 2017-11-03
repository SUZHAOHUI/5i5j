package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.dao.UserAcmsRelaDAO;




/**
 *  主要功能：Acms相关的DAO实现类，主要包括对Acms表，UserAcmsRela的操作方法
 * @author 
 */

public class UserAcmsRelaDAOImpl extends BaseDAOImpl<UserAcmsRela,Integer> implements UserAcmsRelaDAO {

	
	
	
	
	  @SuppressWarnings("unchecked")
	public UserAcmsRela findUserAcmsRela(final Integer channelId,final Integer acmsId){
			
			return  (UserAcmsRela) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s)
								throws HibernateException, SQLException {
						
							String hql = "from UserAcmsRela uar where uar.channelId =:cid and uar.acmsId=:acmsid ";
		
							Query query = s.createQuery(hql);
					
						    query.setInteger("cid", channelId);
						    query.setInteger("acmsid", acmsId);
							List list = query.list();
							return list.get(0);
						}
					});
		  
		   
	   }
	
	  @SuppressWarnings("unchecked")
	public UserAcmsRela findUserAcmsRelaByUserId(final Long userId){
			
			return  (UserAcmsRela) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s)
								throws HibernateException, SQLException {
						
							String hql = "from UserAcmsRela uar where uar.userId =:uid ";
		
							Query query = s.createQuery(hql);
					
						    query.setLong("uid", userId);
							List list = query.list();
							return list.get(0);
						}
					});
		  
		   
	   }

	
	

	

}