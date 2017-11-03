package com.oio.wawj.service.impl;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.Template;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.TemplateDAO;
import com.oio.wawj.service.TemplateService;
import com.oio.wawj.util.PageListData;
@Entity
public class TemplateServiceImpl implements TemplateService{

	@ManyToOne
	private TemplateDAO dao;
	
	
	
	public TemplateDAO getDao() {
		return dao;
	}



	public void setDao(TemplateDAO dao) {
		this.dao = dao;
	}


	@Override
	public String findTemplate(){
		return dao.getfindTemplate();
	}
	
	public PageListData findTemplateAll(Map param, int currentPage, int pageSize) {
		
		String sql = " select template_id as templateId , name , content, status , type  ";
		String sql1= " from template " ;
		return dao.getfindTemplateAll(sql,sql1,param, currentPage, pageSize);
	}
	
	@Override
	public String findTemplateName(){
		return dao.getfindTemplateName();
	}
	
	@Override
	public Template findById(short templateId){
		return dao.getfindById(templateId);
	}
	
	/**
	 * 保存
	 */
	public void save(Template template) {
		dao.save(template);
	}
	/**
	 * 删除
	 * @param id 要删除的数据
	 */
	public void deleteById(Short id) {
		dao.deleteById(Template.class, id);
	}
	/**
	 * 更新记录
	 * @param
	 */
	public void update(Template template) {
		dao.update(template);
	}
	
	@Override
	public String findNotificationScenario() {
		// TODO Auto-generated method stub
		return dao.getfindNotificationScenario();
	}
	
	
	
}
