
package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Org;
import com.oio.wawj.dao.OrgDAO;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.TreeNode;



/**
 * 主要功能：Org相关的DAO实现类，主要包括对Org表的操作方法
 * @author 
 * @lastmodify 
 */
@SuppressWarnings("rawtypes")
public class OrgDAOImpl extends BaseDAOImpl<Org,Integer> implements OrgDAO{
	/**
	 * 
	 * @param id org的Id
	 * @return Org
	 */
	@SuppressWarnings("unchecked")
	public Object getObjectById(final String id) {
				return  (Object) getHibernateTemplate().execute(
						new HibernateCallback() {
							@SuppressWarnings("unused")
							public Object doInHibernate(Session s)
									throws HibernateException, SQLException {
								int idd = Integer.parseInt(id);
								String hql = "from  Org o where o.orgId="+id+"";
								Query query = s.createQuery(hql);
								List list = query.list();
								return list.get(0);
							}
						});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Org> findParentNodeList(){
		return  (List<Org>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = "from  Org o where  o.orgId = 1 and o.status!='T'";
						Query query = s.createQuery(hql);
						return query.list();
					}
				});
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Org> getTreeStr(Map param)
	{
		String hql = "from Org o where 1=1 and o.status!='T' ";
		Object params[] = param.values().toArray();
		for (Object o : param.keySet()) {
			hql += " and o." + o.toString() + " ? ";
		}
		
		Query q = getSession().createQuery(hql);
		
		if (null != params && 0 != params.length) {
			for (int j = 0; j < params.length; j++) {
				q.setParameter(j, params[j]);
			}
		} 

		return q.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Org> getOrgChildNodesById(Integer orgId,String setId) {
		String hql = "from Org o where o.status ='V' and o.id.setId = "+setId;
	
			    hql += " and o.parentOrgId = ? ";
			    Query q = getSession().createQuery(hql);
			    q.setString(0, orgId.toString());
		return q.list();
	}
	/**
	 * 通过orgId查询员工数量
	 * @return 员工数量
	 * 
	 */
	public Integer getStaffNumberByOrgId(Integer orgId,String setId,Long pOrgId) {
		String hql=null;
	    Integer number = null;
		    
//	        if(pOrgId==0){
//	        	 String sql = " select count(*) as number from user u where u.set_id = " + setId;
//	        	 SQLQuery    sq = getSession().createSQLQuery(sql);
//	        	 sq.addScalar("number", Hibernate.LONG);
//		    	 number = ((Long) sq.uniqueResult()).intValue();
//	        }else{
			 
	       	 String sql1=" select getChildList("+orgId+","+setId+") as childStr  ";
				// String childStr = (String) getSession().createSQLQuery(sql1).uniqueResult();
				 SQLQuery query0 =getSession().createSQLQuery(sql1);
				 query0.addScalar("childStr", Hibernate.STRING);
				 String childStr=  query0.uniqueResult().toString();
			 String sql = " select count(*) as number from user u where  u.org_id in ("+childStr+")";
			        sql+=" and u.set_id = " + setId;
			 SQLQuery    sq = getSession().createSQLQuery(sql);
	
		     try{
		    	 sq.addScalar("number", Hibernate.LONG);
		    	 number = ((Long) sq.uniqueResult()).intValue();	
		     }catch(Exception e){
		    	 e.printStackTrace();
		     }
			 number = ((Long) sq.uniqueResult()).intValue();
//	        }
		return number;
	}
	@SuppressWarnings("unchecked")
	public Org findById(String id) {
		// TODO Auto-generated method stub

	    String orgid=id.substring(0, 10);
	    String setid=id.substring(10,15);
		String hql = "from Org o  where o.status='V' and o.id.orgId = "+orgid+" and o.id.setId = "+setid;
	     
		List<Org> o = getHibernateTemplate().find(hql); 
		return o.size()==0?null:o.get(0);
	}
	/**
	 * 查询所有Org
	 * @return org列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Org> findAllBySetId() {
		// TODO Auto-generated method stub

	
		String hql = "from Org o  where o.status='V' ";
	    
		if(AboutOperator.getUser().getFunctionPermission()==0){
			 String setId=AboutOperator.getUser().getSetId();
		        hql += " and o.id.setId="+setId;
		}
		if(!AboutOperator.getUser().getType().equals("A")){
			String setId=AboutOperator.getUser().getSetId();
			       Long orgId = AboutOperator.getUser().getOrgId(); 	  
		        hql += " and o.id.setId="+setId;
		        hql += " and o.id.orgId = "+orgId;
		}else{
			    hql += " and o.parentOrgId = 0 ";
		}
		System.out.println(hql);
		List<Org> o = getHibernateTemplate().find(hql); 
		return o;
	}
	@SuppressWarnings("unchecked")
	public List<Org> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Org o  where o.status='V' ";
	     
		if(!AboutOperator.getUser().getType().equals("A")){
			  String setId=AboutOperator.getUser().getSetId();
		        hql += " and o.id.setId="+setId;
		}
		List<Org> o = getHibernateTemplate().find(hql); 
		return o;
	}
	@SuppressWarnings("unchecked")
	public List<Org> findAll(Long orgId) {
		String hql = "  from Org o  where o.status='V' ";
		String setId=AboutOperator.getUser().getSetId();
		// TODO Auto-generated method stub
	 	 String sql1=" select getChildList("+orgId+","+setId+") as childStr  ";
			// String childStr = (String) getSession().createSQLQuery(sql1).uniqueResult();
			 SQLQuery query0 =getSession().createSQLQuery(sql1);
			 query0.addScalar("childStr", Hibernate.STRING);
			 String childStr=  query0.uniqueResult().toString();
	
		    
			if(AboutOperator.getUser().getFunctionPermission()==0){
				
			        hql += " and o.id.setId="+setId;
			}
			if(!AboutOperator.getUser().getType().equals("A")){	  
			        hql += " and o.id.setId="+setId;
			        hql += " and o.id.orgId = "+orgId;
			}else{
				    hql += " and o.parentOrgId = 0 ";
			}
	
		 List<Org> o = getHibernateTemplate().find(hql); 
		return o;
	}
	/**
	 * 查询所有org的name
	 * */
	@SuppressWarnings("unchecked")
	public List<String> findAllOrg()
	{
		return  (List<String>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						String hql = " select o.orgName from Org o where 1=1 and o.status ='V' ";						
						try{
							Query query =  s.createQuery(hql);
							Set<String> nameSet = new HashSet<String>();
							nameSet.addAll((List<String>)query.list());
							return (List<String>)query.list();
						}catch(Exception e0){
							return null;
						}
					}
				});
	}
	public TreeNode getreeNode(final int cid){
		String hql = "  from Org o  where o.status='V' and o.id.orgId="+cid;
		@SuppressWarnings("unchecked")
		List<Org> orgList = getHibernateTemplate().find(hql); 
		TreeNode tn=new TreeNode();
		for(Org o:orgList){
			tn.setCid(o.getId().getOrgId());
			tn.setCname(o.getOrgName());
			tn.setPid(o.getParentOrgId());
		}
		return tn;
	}
	public List<TreeNode> queryTreeNode(final int cid){
		String hql = "  from Org o  where o.status='V' and o.parentOrgId="+cid;

		@SuppressWarnings("unchecked")
		List<Org> orgList= getHibernateTemplate().find(hql); 
		List<TreeNode> treeView = new ArrayList();
		for(Org o:orgList){
		TreeNode tn=new TreeNode();
		tn.setCid(o.getId().getOrgId());
		tn.setCname(o.getOrgName());
		tn.setPid(o.getParentOrgId());
		treeView.add(tn);
		}
		return treeView ;
	}
}