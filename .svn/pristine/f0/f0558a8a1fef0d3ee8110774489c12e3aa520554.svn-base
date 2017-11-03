/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.User;
import com.oio.wawj.util.PageListData;
import com.oio.wawj.util.TreeNode;


@SuppressWarnings("rawtypes")
public interface OrgService{
	public void save(Org sysOrg);
	public void delete(Org sysOrg);
	public void deleteById(Integer s);
	public void update(Org sysOrg);	
	public PageListData findList(Map param, int pageNum, int pageSize);
	public boolean isExistSameProperty(Map param);
	public Org findById(String id);
	//public List<Org> findAll();
	public List<Org> findAllBySetId();
	public List<Org> findAll(Long userId);
	public List<Org> findAll();
	public List<Org> findByCondition(Map param,int type);
	public Object findObjectById(String id);
//	public String getTreeStr();
	public List<Org> findParentNodeList();
	public List<Org> findOrgChildNodesById(Integer orgId,String setId);
	
//	public List<Staff> findOrgStaffById(Long orgId);
	//public List<Org> findVaildOrg();
	public void importExcel(User user, String path, String excelFilename,File  efile);
	public Integer getStaffNumberByOrgId(Integer orgId,String setId,Long pOrgId);
	public TreeNode getreeNode(int cid);
	public List queryTreeNode(int cid);
}
