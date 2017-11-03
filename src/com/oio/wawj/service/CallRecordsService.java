
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.util.PageListData;



@SuppressWarnings("rawtypes")
public interface CallRecordsService{
	public void save(CdrCall entity);
	public void delete(CdrCall entity);
	public void deleteById(Long id);	
	public void update(CdrCall entity);
	public PageListData findList(Map param,int currentPage, int pageSize);
	public PageListData findCallInMessage(Map param,int currentPage, int pageSize);
	public PageListData findCallOutRecord(Map param,int currentPage, int pageSize);
	public PageListData findCallOutComeRecord(Map param,int currentPage, int pageSize);
	public PageListData findCallOutStranger(Map param,int currentPage, int pageSize);
	public PageListData findCallOutMessage(Map param,int currentPage, int pageSize);
	public PageListData findCallOutReplyRecord(Map param,int currentPage, int pageSize);
	public PageListData findCallOutStrangerRecords(Map param,int currentPage, int pageSize);
	public boolean isExistSameProperty(Map param);
	
	
	public String callInListExportExcel(List list, String targetDirectory,String code);

}
              