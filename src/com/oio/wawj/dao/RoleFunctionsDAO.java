/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao;

import java.util.List;

import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;



public interface RoleFunctionsDAO extends BaseDAO<RoleFunctions, Short> {
	
	 List<RoleFunctions> findByRoleId(Short id);
	 
	 List<Function> findAllFunction();
	 
	 List<Function> getListByParentId(Long id);
	 
	 List<Function> getListByLevelId(Integer id);
	 
}