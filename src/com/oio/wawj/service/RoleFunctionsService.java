/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.util.PageListData;





@SuppressWarnings("rawtypes")
public interface RoleFunctionsService {
	public void save(RoleFunctions RoleFunction);

	public void delete(RoleFunctions RoleFunction);

//	public void deleteById(Integer id);

	public void update(RoleFunctions RoleFunction);

	public PageListData findList(Map param, int currentPage, int pageSize);

	public boolean isExistSameProperty(Map param);

//	public RoleFunctions findById(Integer id);

	public List<RoleFunctions> findAll();

	public List<RoleFunctions> findByCondition(Map param, int type);

	public List<RoleFunctions> findByRoleId(Short id);
	
	public List<Function> findAllFunction();

	public List<Function> findListByParentId(Long id);
	
	public List<Function> findListByLevelId(Integer id);
}
