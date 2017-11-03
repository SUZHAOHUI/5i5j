/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;


import com.oio.wawj.bean.Role;
import com.oio.wawj.dao.RoleDAO;
import com.oio.wawj.util.PageListData;





/**
 * ��Ҫ���ܣ�Role��ص�DAOʵ���࣬��Ҫ������Role��Ĳ�������
 * @author 
 */
@Entity
public class RoleDAOImpl extends BaseDAOImpl<Role, Short> implements
		RoleDAO {

	/**
	 * ɾ��SysFunction��Ϣ
	 * @param id ������
	 */
	public void deleteRoleFunctionById(Short id) {

		String hql = "delete from Role where  role_id  in("+ id +")";
		//delete from Admin  where id in (" + ids + ")";
		Query query=getSession().createQuery(hql);
		
		query.executeUpdate();
	}

	/**
	 * ��ѯRole�б���Ϣ
	 * @param id ������
	 * @return Role��Ϣ
	 */
	public Object getObjectById(final String id) {
		return  (Object) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						Long idd = Long.parseLong(id);
						String hql = "from Role where 1=1 and roleId=:id";
	
						Query query = s.createQuery(hql);
						query.setLong("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}
	
	/**
	 * ��ѯRole�б���Ϣ
	 * @param id ������
	 * @return Role��Ϣ
	 */
	public Role getRoleIdByUserId(final Long id) {
		return  (Role) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = "from Role where 1=1 and roleId=:id";
	
						Query query = s.createQuery(hql);
						long idd=id;
						query.setLong("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}
	
	/**
	 * �вε�������ѯ
	 * @param pageSize ������
	 * @param roleName
	 * @param commit
	 * @param status
	 * @param statusDate
	 * @param pageNum
	 * @param Role
	 */
	@SuppressWarnings("unchecked")
	public List<Role> selectList(Class<Role> entityClass ,final String roleName,
			String status){
		return getHibernateTemplate().executeFind(new HibernateCallback() {
		
		public Object doInHibernate(Session arg0)throws HibernateException, SQLException {
		
		String hql = "";
		hql = "from Role s where 1=1 ";
		if (roleName != null){
			hql += "and s.roleName like  '%roleName%' ";
		}
		if ("status" != null){
			hql += "and s.status like '%status%' ";
		}
		System.out.println(hql);
		
		Query query = arg0.createQuery(hql);

		List<Role> data = query.list();
		return data;
		
			}
		});
	}
	
	/**
	 * ��ѯ���ڵ�Service�б���Ϣ
	 * @param id ������
	 * @return Service��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public  String getRoleList(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();  
					    String hql = "from Role s where s.status='V' and s.roleId<>1";		
					    Query query = s.createQuery(hql);
					    List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Role sr = (Role)list.get(i);	
							jsonObject.put("roleName",sr.getRoleName());		
							jsonObject.put("roleId",sr.getRoleId());		
							jsonArray.add(jsonObject);	
							
						}
						return jsonArray.toString(); 
					}
				});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageListData getList(final String hql,final Map param, final int currentPage,final int pageSize){
		try {
			return (PageListData) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
							PageListData listdata = null;

							String chql = "where r.role_id<>1 ";

							 if (param.get("roleName") != null
								&& !((String) param.get("roleName")).equals("")) {
								 chql += " and r.role_name like '%"
											+ (String) param.get("roleName") + "%' ";
					          }
							  
							 if (param.get("status") != null
										&& !((String) param.get("status")).equals("")) {
									chql += " and r.status = '"
											+ (String) param.get("status") + "'" ;
							  }
							 

							    
							    SQLQuery query = s.createSQLQuery("select count(*) as total from " +"("+hql+chql+") "+" as roles");	
							    System.out.println("select count(*) as total from " +"("+hql+chql+") "+" as roles");
							    query.addScalar("total", Hibernate.LONG);
						
								int total = ((Long) query.uniqueResult()).intValue();
								
								 System.out.println(total);
								 query =  getSession().createSQLQuery(hql+chql)
							    		.addScalar("roleName", Hibernate.STRING) 
							            .addScalar("roleId", Hibernate.SHORT)
							            .addScalar("status",Hibernate.STRING)
							            .addScalar("statusDate",Hibernate.TIMESTAMP)
							            .addScalar("comment",Hibernate.STRING);
							    System.out.println(hql+chql);
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
