package com.oio.wawj.dao;

import java.util.Map;

import com.oio.wawj.bean.Template;
import com.oio.wawj.util.PageListData;

public interface TemplateDAO extends BaseDAO<Template,Short> {

	
	public String getfindTemplate();
	
	public PageListData getfindTemplateAll(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);

	public String getfindTemplateName();

	public Template getfindById(short templateId);
	
	public String getfindNotificationScenario();

}
