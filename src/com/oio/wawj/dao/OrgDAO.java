package com.oio.wawj.dao;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Org;
import com.oio.wawj.util.TreeNode;

//extends BaseDAO<Org, Long>

@SuppressWarnings("rawtypes")
public interface OrgDAO  extends BaseDAO<Org, Integer>{
	
	public List<Org> findParentNodeList();
	
	public Object getObjectById(String id);

	public List<Org> getTreeStr(Map param);

	public List<Org> getOrgChildNodesById(Integer orgId,String setId);

//	public List<Staff> getOrgStaffById(Long orgId);
	
	//public List<Org> findAll();
	public List<String> findAllOrg();
	public Integer getStaffNumberByOrgId(Integer orgId,String setId,Long pOrgId);
	public TreeNode getreeNode(final int cid);
	public List queryTreeNode(final int cid);
	public List<Org> findAll(Long orgId) ;
	public List<Org> findAllBySetId();
	public List<Org> findAll();
	public Org findById(String id);
}