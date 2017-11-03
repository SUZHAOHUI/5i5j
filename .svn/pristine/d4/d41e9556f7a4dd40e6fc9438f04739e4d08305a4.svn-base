package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;


import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.dao.AxbDAO;
import com.oio.wawj.util.PageListData;



/**
 *  主要功能：Axb相关的DAO实现类，主要包括对SubsRela表，SubsRela的操作方法
 * @author 
 */

public class AxbDAOImpl extends BaseDAOImpl<SubsRela,Long> implements AxbDAO {

	
	public PageListData findList(Map<String, Object> param, int currentPage,
			int pageSize) {
		// TODO Auto-generated method stub
		
		PageListData listdata=null;		
		String hql = " from SubsRela asr, User u,UserAcmsRela uar,Acms ac where asr.state = 'B' " +
				     " and asr.userId = u.userId " +
				     " and asr.xnum = ac.acms and ac.acmsId=uar.acmsId" +
				     "  and u.status ='V' and uar.state='V'  ";
		if (param.get("number") != null && !((String)param.get("number")).equals(""))
		{
//			hql += "and p.customer.custId=c.custId ";
			hql += "and (" + "asr.anum = " + (String)param.get("number") ;
			hql += "or " + "asr.xnum = " + (String)param.get("number")+")";
		}	
		listdata = findListThin(hql, currentPage, pageSize);
		return listdata;
	}  
	@SuppressWarnings("unchecked")
	public SubsRela findByXnumAndAnum(final String xnum,final String anum) {
		return  (SubsRela) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
					
						String hql = "from SubsRela s " +
										"where  s.xnum = :xnum and s.anum =:anum and s.state='B' ";		
						System.out.println(hql);
						Query query = s.createQuery(hql);
						query.setString("xnum", xnum);
						query.setString("anum", anum);
						//int total =  ((Number) query.uniqueResult()).intValue();
						List list=query.list();
						return list.size()==0?null:query.list().get(0);	

					}
				});
	}
	

}