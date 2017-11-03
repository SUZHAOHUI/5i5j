package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.ChannelDAO;
import com.oio.wawj.dao.ParameterDAO;
import com.oio.wawj.util.PageListData;

@SuppressWarnings("rawtypes")
public class  ParameterDAOImpl extends BaseDAOImpl<Parameter,Short> implements ParameterDAO {

	
	/**
	 * 
	 */
	public Parameter getFindById(final Integer id) {
		return  (Parameter) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
					
						String hql = "from Parameter p " +
										"where  p.parameterId = "+id;		
						System.out.println(hql);
						Query query = s.createQuery(hql);
//						query.setLong("custId", id);
						//int total =  ((Number) query.uniqueResult()).intValue();
						
						return query.list().get(0);	

					}
				});
	}
	

	
	public Parameter getFindByParameter(final String parameter) {
		return  (Parameter) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
						
						String hql = "from Parameter p " +
								"where  p.parameter = "+parameter;		
						System.out.println(hql);
						Query query = s.createQuery(hql);
//						query.setLong("custId", id);
						//int total =  ((Number) query.uniqueResult()).intValue();
						
						return query.list().get(0);	
						
					}
				});
	}

	
	public String getfindTemplateTORList(final Map param,final String sql){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						
						 String chql = "where tr.state ='V' ";
						 
						 if (param.get("setId") != null
									&& !((String) param.get("setId")).equals("")) {
								chql += " and tr.org_id in ("
										+ (String) param.get("setId")+")";
						  }
						
							Query query = s.createSQLQuery(sql+chql);
							System.out.println(sql+chql);
							List list = query.list();
						    if(list.size()==0){

									jsonObject.put("id"+108,"");
									jsonObject.put("id"+109,"");
									jsonObject.put("id"+110,"");
									jsonObject.put("id"+111,"");
									jsonObject.put("id"+112,"");
									jsonObject.put("id"+113,"");
									
						    	
						    }else{
							
							for(int i = 0; i < list.size(); i++){
								Object [] sf = (Object[])list.get(i);	
								
								jsonObject.put("id"+sf[1],""+sf[2]+"");	
								jsonArray.add(jsonObject);
							}
						   }
							System.out.println(jsonObject.toString());
							return jsonObject.toString(); 
							
						
					}
				});
		
	}
	
	
	
	
	/**
	 * 
	 * @return 查询呼入号显
	 */
	public  String getfindCallInNumber(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						
						String hql = "from  Parameter as p where p.sectionId=1 and p.parameterId>=101 and  p.parameterId<=104 ";
						
					
						Query query = s.createQuery(hql);
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
//							if(sf.getSectionId()==1 && sf.getItemId()<=4){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//								
//							}
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());
						}
						return jsonObject.toString(); 
					}
				});
	}
	
	
	/**
	 * 
	 * @return 查询呼入彩铃
	 */
	public  String getfindCallInMusic(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						
						String hql = "from  Parameter as p where p.sectionId=1 and p.itemId>4 and  p.itemId<8 ";
						Query query = s.createQuery(hql);
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
							
//							if(sf.getSectionId()==1 && sf.getItemId()>4 &&sf.getItemId()<8){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//							}
						
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						System.out.println(jsonObject.toString());
						return jsonObject.toString(); 
					}
				});
	}
	
	
	
	/**
	 * 
	 * @return 查询呼入短信
	 */	
	public  String getfindCallInMessage(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray ja = new JSONArray();
						String hql = " from  Parameter as p where p.sectionId=1 and p.parameterId>=108 and  p.parameterId<=113   ";
						System.out.println(hql);
						Query query = s.createQuery(hql);
						
						List list = query.list();
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
							
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
	
							//jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						return jsonObject.toString(); 
					}
				});
	}	
	
	

	

	/**
	 * 
	 * @return 查询外呼号显
	 */
	public  String getfindCallOutNumber(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Parameter as p where p.sectionId=2 and p.parameterId>=201 and  p.parameterId<=212  ";
						
					
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
//							if(sf.getSectionId()==2 && sf.getItemId()<=12){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//							}
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						return jsonObject.toString(); 
					}
				});
	}
	
	/**
	 * 
	 * @return 查询外呼彩铃
	 */
	public  String getfindCallOutMusic(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Parameter as p where p.sectionId=2 and p.parameterId>=213 and  p.parameterId<=219  ";
						
					
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
//							if(sf.getSectionId()==2 && sf.getItemId()>12 && sf.getItemId()<20){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//							}
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						return jsonObject.toString(); 
					}
				});
	}
	
	/**
	 * 
	 * @return 查询外呼挂机短息
	 */
	public  String getfindCallOutMessage(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Parameter as p where p.sectionId=2 and p.parameterId>=220 and  p.parameterId<=237  ";
						
					
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
//							if(sf.getSectionId()==2 && sf.getItemId()>19 && sf.getItemId()<38){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//							}
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						return jsonObject.toString(); 
					}
				});
	}	
	
	

	
	
	/**
	 * 
	 * @return 查询外呼绑定
	 */
	public  String getfindCallOutBinding(){
		return  ( String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						 JSONObject jsonObject = new JSONObject();
						 JSONArray jsonArray = new JSONArray(); 
						String hql = "from  Parameter as p where p.sectionId=2 and p.parameterId>=238 and  p.parameterId<=241  ";
						
					
						Query query = s.createQuery(hql);
						List list = query.list();
						
						for(int i = 0; i < list.size(); i++){
							Parameter sf = (Parameter)list.get(i);
//							if(sf.getSectionId()==2 && sf.getItemId()>37 && sf.getItemId()<42){
//								jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
//							}
							jsonObject.put("id"+sf.getParameterId(),sf.getValue());	
						}
						return jsonObject.toString(); 
					}
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 */
	@Override
	public String getfindParameterAll(final Short id) {
		
		return  (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = "from Parameter where 1=1 and ParameterId=:id";
	
						Query query = s.createQuery(hql);
						short idd=id;
						query.setShort("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}
	


	@Override
	public void deleteParameterById(short id) {
		
	}

	@Override
	public Object getObjectById(String id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getParameterIdByRoleId(final Short id) {
		
		return  (User) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
					
						String hql = "from Parameter where 1=1 and ParameterId=:id";
                        	
						Query query = s.createQuery(hql);
						short idd=id;
						query.setShort("id", idd);
						List list = query.list();
						return list.get(0);
					}
				});
	}


}