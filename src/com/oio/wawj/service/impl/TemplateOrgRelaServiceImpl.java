package com.oio.wawj.service.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.TemplateOrgRela;
import com.oio.wawj.dao.TemplateOrgRelaDAO;
import com.oio.wawj.service.TemplateOrgRelaService;

@Entity
public class TemplateOrgRelaServiceImpl implements TemplateOrgRelaService{
	
	@ManyToOne
	private TemplateOrgRelaDAO dao;
	
	public TemplateOrgRelaDAO getDao() {
		return dao;
	}

	public void setDao(TemplateOrgRelaDAO dao) {
		this.dao = dao;
	}

	public void update(TemplateOrgRela  templateOrgRela) {
		// TODO Auto-generated method stub
		dao.update(templateOrgRela);
	}
	
	public void save(TemplateOrgRela templateOrgRela) {
		// TODO Auto-generated method stub
		dao.save(templateOrgRela);
	}

}
