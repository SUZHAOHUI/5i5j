/**
* Copyright (c) REALSTONE CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Function;
import com.oio.wawj.util.PageListData;




public interface FunctionService{



	public void save(Function Function, Long userId);

	List<Function> findLeftFunctionNameById(Short userId, Short functionId);
	
	public String findParentList();
	List<Function> findAllValid();
	
	public PageListData findList(Map param, int pageNum, int pageSize);
	List<Function> findList(Integer category);


	Function findFunctionById(Short functionId);
	public List<Function> findNoUseFunctionById(List<Short> functionIds);

	public void update(Function Function);
	public void deleteById(Short id);
	public List findSubListById(String id);
	int getTopFunctionNumber(Long custId);


	List<String> findNameByIdList(List<Integer> functionIdList);
	public List<Function> findTopFunctionNameById(Short id);
}