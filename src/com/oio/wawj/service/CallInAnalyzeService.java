package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.util.PageListData;

@SuppressWarnings("rawtypes")
public interface CallInAnalyzeService {

	
	public PageListData findList(Map param,int currentPage, int pageSize);
	public PageListData findDeptList(Map param,int currentPage, int pageSize);
	public PageListData findStaffList(Map param,int currentPage, int pageSize);
	public String findChannelName();
	
	 public String deptExportExcel(List list, String targetDirectory,String code);
	 public String staffExportExcel(List list, String targetDirectory,String code);
	 public String channelExportExcel(List list, String targetDirectory,String code);
}
