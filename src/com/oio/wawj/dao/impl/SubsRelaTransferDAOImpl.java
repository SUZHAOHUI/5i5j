package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.dao.SubsRelaTransferDAO;

public class SubsRelaTransferDAOImpl extends BaseDAOImpl<Object,String> implements SubsRelaTransferDAO{

	
	public void  creatTempSubsRela(){
		try{
			String sqlSubsRela=" CREATE TABLE If Not Exists temp_relas_transfer(batchNum VARCHAR(10),code VARCHAR(16),acms VARCHAR(15),set_id VARCHAR(16))" ; 
			 SQLQuery query1 = getSession().createSQLQuery(sqlSubsRela);
	         query1.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getSession().close();
		}
	}
	public void  insetTempSubsRela(){
		try{
			String sql=" INSERT INTO temp_relas_transfer SELECT srt.batchNum,srt.ucode,srt.xnum,srt.set_id  FROM " +
					" (SELECT xnum,set_id,u.code as ucode ,DATE_FORMAT(NOW(),'%m%d%H%i') as batchNum FROM subs_rela sr, user u ,user_acms_rela uar,acms a" +
					" where u.user_id = uar.user_id and uar.acms_id = a.acms_id" +
					" and sr.xnum=a.acms  AND uar.channel_id='11' AND uar.state='V' AND u.`status`='V' " +
					" AND sr.state='B' ORDER BY sr.user_id )srt";
			 SQLQuery query2 =getSession().createSQLQuery(sql);
	         query2.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getSession().close();
		}
	}
	public void  deleteTempSubsRela(){
		try{
			 String sqlDrop="DELETE FROM temp_relas_transfer" ; 
			 SQLQuery query3 = getSession().createSQLQuery(sqlDrop);
	         query3.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getSession().close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Object> findSubsRelaList()
	{	
		return ( List<Object>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
		try {
			 
			String sql2="select code ,acms,set_id,batchNum from temp_relas_transfer";
			SQLQuery query4 = s.createSQLQuery(sql2)
					.addScalar("code",Hibernate.STRING)
					.addScalar("acms",Hibernate.STRING)
					.addScalar("set_id",Hibernate.STRING)
					.addScalar("batchNum",Hibernate.STRING);
			
			   List data1 = query4.list();
	     	return (data1.size()==0)?null:query4.list();
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;	
		}
				
					}
				});
	}

}
