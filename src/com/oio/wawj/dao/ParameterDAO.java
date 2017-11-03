package com.oio.wawj.dao;

import java.util.Map;

import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.User;


public interface ParameterDAO extends BaseDAO<Parameter,Short>{


	public void deleteParameterById(short id);

	public Parameter getFindById(Integer id);
	public Parameter getFindByParameter(String id);
	
	public String getfindTemplateTORList(Map param,String sql);

	public String getfindCallInNumber();
	public String getfindCallInMusic();
	public String getfindCallInMessage();
	public String getfindCallOutNumber();
	public String getfindCallOutMusic();
	public String getfindCallOutMessage();
	public String getfindCallOutBinding();
	
	public String getfindParameterAll(Short id);
	//public String getfindRoleAll();
	public Object getObjectById(String id);
	
	public User getParameterIdByRoleId(final Short id);
	
}