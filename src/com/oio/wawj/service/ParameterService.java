package com.oio.wawj.service;

import java.util.Map;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;

public interface ParameterService {

	public void save(Parameter sysParmater);

	public Parameter findById(Integer id);
	
	public Parameter findByParameter(String parameter);
	
	public String findTemplateTORList(Map param);

	public String findCallInNumber();
	
	public String findCallInMusic();
	
	public String findCallInMessage();
	
	public String findCallOutNumber();
	
	public String findCallOutMusic();
	
	public String findCallOutMessage();
	
	public String findCallOutBinding();
	
	public void update(Parameter parmater);
	
}
