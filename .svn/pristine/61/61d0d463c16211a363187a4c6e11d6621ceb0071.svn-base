package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.OperationLog;
import com.oio.wawj.util.PageListData;




@SuppressWarnings("rawtypes")
public interface OperationLogService{
	public void save(OperationLog entity);
	public void delete(OperationLog entity);
	public void deleteById(Long id);	
	public void update(OperationLog entity);
	public PageListData findList(Map param, int currentPage, int pageSize);
	public boolean isExistSameProperty(Map param);
	public OperationLog findById(Long id);
	public List<OperationLog> findAll();
	public List<OperationLog> findByCondition(Map param,int type);
	public PageListData findList( Map cParam, Map param3,
			int currentPage, int pageSize,String roleName);
	public void recordLog( String functionName, String statements, String comments);

}
