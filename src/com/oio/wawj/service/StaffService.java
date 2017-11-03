/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public interface StaffService{
	//锟斤拷臃锟斤拷锟�
	public void save(User sysUser);
	//通锟斤拷ID删锟斤拷
	public void deleteById(Long id);
	public void update(User sysRole);
	public PageListData findList(Map param, int currentPage, int pageSize);
	public PageListData findExportList(Map param, int pageNum, int pageSize);
	public boolean isExistName(String name,String statusName, String statusValue);
	public boolean isExist(String param,String statusName, String statusValue,Boolean flag);
	public String importExcel( String path, String excelFilename,File  efile);
	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param name 锟斤拷锟斤拷锟斤拷
	 * @param type 锟斤拷锟斤拷锟斤拷
	 * @return boolean
	 */
	//通锟斤拷ID锟斤拷询
	public User findById(Long id);
	public JSONArray getChannelList(Long operator);
	public Acms  getAcmsByacms(String acms);
	public  String getDutyList( final Long operator);
	 public User findUserByCode(String code);
	   public User findUserByName(String name,String setId);
	 public List getUserAcmsRela(Long userId,String channel);
	 public Channel findChannelById(Integer channelId);
	 public List<Object> findSubsRelaByUserId(String userId);
	 public List<Object> findUnSubsRelaByUserId(String code);
	 public List<Object> findSubsRelaByXnum(String xnum);
	 public Org findOrgById(Long orgId);
	 public ParameterOrgRela findRingBySetId(String setId);
	 public String exportExcel(List list,List<Object> channelList, String targetDirectory,String code,String setId);
	 public List<Object> findSubsRelaByCodeAndAcms(String code,String acms);
	public List<Object> findChannelRela();
}
