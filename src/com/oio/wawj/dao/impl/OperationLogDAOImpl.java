package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.OperationLog;
import com.oio.wawj.dao.OperationLogDAO;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.PageListData;





/**
 *

 */
public class OperationLogDAOImpl extends BaseDAOImpl<OperationLog,Long> implements OperationLogDAO{
	

  @SuppressWarnings("unchecked")
public PageListData findOperationLogList(final Map userParam, final Map timeParam, final int currentPage, final int pageSize,final String roleName){
		
	  try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;
		String setId=AboutOperator.getUser().getSetId();
		String hql="";
		if(roleName!=null&&"超级管理员".equals(roleName)){				
		    hql = " from OperationLog o,User s,Org d ,UserRole ur where o.userId=s.userId and s.orgId=d.id.orgId and s.setId=d.id.setId and ur.id.userId=s.userId  and ur.id.roleId between 1 and 5 ";
		}else{
			hql=" from OperationLog o,User s,Org d ,UserRole ur where o.userId=s.userId and s.orgId=d.id.orgId and s.setId=d.id.setId  and ur.id.userId=s.userId  and ur.id.roleId between 1 and 5 and s.setId='"+setId+"'";
		}
	
	   if (userParam.get("name") != null
				&& !((String) userParam.get("name")).equals("")) {
			hql += " and ( s.name like '%"
					+ (String) userParam.get("name")+"%'" +"or s.code like '%" + (String) userParam.get("name")+"%')";
	   }
	   if (userParam.get("duty") != null
				&& !((String) userParam.get("duty")).equals("")) {
			hql += " and ur.id.roleId = '"
					+ (String) userParam.get("duty")+ "'" ;
	   }
	   if (userParam.get("orgId") != null
				&& !((String) userParam.get("orgId")).equals("")) {
		   if(userParam.get("orgId").equals("1"))
		       hql+=" and 1=1";
		   else
		   hql += " and s.orgId = "+ (String) userParam.get("orgId") ;
	   }
	
	  if (timeParam.get("beginTime") != null
				&& !((String) timeParam.get("beginTime")).equals("")) {
			hql += " and UNIX_TIMESTAMP(o.createTime) >= UNIX_TIMESTAMP('"+timeParam.get("beginTime")+"') "
				  +" and UNIX_TIMESTAMP(o.createTime) <= UNIX_TIMESTAMP('"+timeParam.get("endTime")+"') " ;
	   }
	  
            hql +=" order by o.createTime desc";
//                System.out.println(hql);
	     listdata = findListThin(hql, currentPage, pageSize);
			return listdata;
		}
		});
		
		} catch (RuntimeException re) {
		logger.error(re.toString());
		throw re;
		}
		  }
}
