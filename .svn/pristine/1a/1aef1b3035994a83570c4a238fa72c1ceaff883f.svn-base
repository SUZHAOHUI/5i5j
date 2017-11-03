
package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Role;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.dao.UserRoleDAO;




/**
 * ��Ҫ���ܣ�
 * @author 
 */
@Entity
@SuppressWarnings("unchecked")
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole, Short>implements UserRoleDAO {
	/**
	 * ��ѯUserRole�б���Ϣ
	 * @param id ������
	 * @return UserRole��Ϣ
	 */
	public List<UserRole> getByRoleId(Short roleId) {
		String hql = "from UserRole s where s.id.roleId=?";
//		String hql = "from UserRole s where 1=1";
		Query q = getSession().createQuery(hql);
		q.setInteger(0, roleId);

		return q.list();
	}
	
	public List<UserRole> getByRoleIds(Short roleId) {
		String hql = "from UserRole s where s.id.roleId=?";
//		String hql = "from UserRole s where 1=1";
		Query q = getSession().createQuery(hql);
		q.setInteger(0, roleId);

		return q.list();
	}
	public  List getByRoleIdsfp(Short roleId,String setId) {
		String hql = "select s.id.userId from UserRole s , User u where u.userId=s.id.userId and s.id.roleId=? and u.setId=?";

		Query q = getSession().createQuery(hql);
		q.setInteger(0, roleId);
		q.setString(1, setId);
		
		List userIds = q.list();
		
/*        for (int i = 0; i < userIds.size(); i++) {
	       System.out.println("userIds-------********------"+userIds.get(i));
        }*/
		return userIds;

	}


	

	public String getNoUserRoleByIds() {
		return (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							
							String hql = "select sf.user_id,sf.name from user sf left join user_role ur on ur.user_id=sf.user_id where sf.status='V' and sf.type='A' and ur.role_id is NULL  " ;
							
							System.out.println("no++++"+hql);
							Query query = s.createSQLQuery(hql);
							List list = query.list();
							
					        JSONArray ja = new JSONArray();
					    	JSONObject jo = new JSONObject();

								for(int i = 0; i < list.size(); i++){
									Object [] sf = (Object[])list.get(i);	
									jo.put("id", sf[0]);
									jo.put("name", sf[1]);
									ja.add(jo);
								}
								return ja.toString(); 
							
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}
	
	public List<User> getNoUserRoleById( final List<Long> userIds) {
		return (List<User>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
						try{
							
							String hql = "from User sf where 1=1 and sf.status='V' and sf.duty="+null;
							if (userIds != null) {
								for (Long ig : userIds) {
									hql += " and sf.userId !=";
									hql += ig.toString();
								}
							}
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}


	public User getUserById(final Long userId) {
		return (User) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							String hql = "from User su where 1=1 and su.status='V' and su.userId= " + userId;
//							if (userId != null){
//								
//							}
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list.size()>0?list.get(0):null;
//							return q;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}
	public UserRole getById(final Long userId) {
		return (UserRole) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
							throws HibernateException, SQLException {
						try{
							
							String hql = "from UserRole su where  su.status='V' and su.id.userId= " + userId;
//							if (userId != null){
//								
//							}
							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list.size()>0?list.get(0):null;
//							return q;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}

	public UserRole getRoleIdByUserId(final Long userId) {
		return (UserRole) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							String hql = "from UserRole sur where 1=1 and sur.status='V' and sur.id.userId= " + userId;

							Query q = s.createQuery(hql);
							q.setCacheable(true);
							List list = q.list();
							return list.size()>0?list.get(0):null;
//							return q;
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}
	/*@Override
	public String getUserByIds(final Short roleId) {
		return (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							
							String hql = "select su.userId ,su.name from User su ,UserRole ur where  su.userId=ur.id.userId and su.type='A' and su.status='V' "
									+" and ur.id.roleId= " + roleId ;
                            
							System.out.println("yse++"+hql);
							
							Query query = s.createQuery(hql);
							List list = query.list();
							
					        JSONArray ja = new JSONArray();
					    	JSONObject jo = new JSONObject();
					        
							for(int i = 0; i < list.size(); i++){
								Object [] sf = (Object[])list.get(i);	
								jo.put("id", sf[0]);
								jo.put("name", sf[1]);
								ja.add(jo);
							}
							return ja.toString(); 
							
							
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}*/

	
	public String getUserByIds(final Map param) {
		return (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					@SuppressWarnings("rawtypes")
					public Object doInHibernate(Session s) 
						throws HibernateException, SQLException {
						try{
							
							String hql = "select su.userId ,su.name from User su ,UserRole ur where  su.userId=ur.id.userId and su.type='A' and su.status='V' ";
							
							
							 if (param.get("roleId") != null
								&& !((String) param.get("roleId")).equals("")) {
								 hql += " and ur.id.roleId= '"
											+ (String) param.get("roleId") + "' ";
					          }
							  
							 if (param.get("fp") != null
										&& !((String) param.get("fp")).equals("1")) {
									hql += " and su.setId = '"
											+ (String) param.get("setId")+ "' ";
							  }
                            
							System.out.println("yse++"+hql);
							
							Query query = s.createQuery(hql);
							List list = query.list();
							
					        JSONArray ja = new JSONArray();
					    	JSONObject jo = new JSONObject();
					        
							for(int i = 0; i < list.size(); i++){
								Object [] sf = (Object[])list.get(i);	
								jo.put("id", sf[0]);
								jo.put("name", sf[1]);
								ja.add(jo);
							}
							return ja.toString(); 
							
							
						}catch(RuntimeException re){
							logger.error("***Error: ", re);
							return null;
						}
					}
				});
	}


}