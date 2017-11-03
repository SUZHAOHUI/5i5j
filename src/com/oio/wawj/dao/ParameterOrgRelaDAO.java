package com.oio.wawj.dao;

import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.TemplateOrgRela;

public interface ParameterOrgRelaDAO extends BaseDAO<ParameterOrgRela,Short>{

	public ParameterOrgRela getFindPORById(Integer setId,Integer parameterId);
	
	public TemplateOrgRela getFindTORById(Integer setId,Integer parameterId);
}
