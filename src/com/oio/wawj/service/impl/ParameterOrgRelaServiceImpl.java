package com.oio.wawj.service.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.TemplateOrgRela;
import com.oio.wawj.dao.ParameterOrgRelaDAO;
import com.oio.wawj.service.ParameterOrgRelaService;
@Entity
public class ParameterOrgRelaServiceImpl implements ParameterOrgRelaService {

	@ManyToOne
	private ParameterOrgRelaDAO dao;
	
	
	
	
	public ParameterOrgRelaDAO getDao() {
		return dao;
	}


	public void setDao(ParameterOrgRelaDAO dao) {
		this.dao = dao;
	}


	public void saveOrUpdate(ParameterOrgRela parameterOrgRela) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(parameterOrgRela);
	}
	
	public void update(ParameterOrgRela parameterOrgRela) {
		// TODO Auto-generated method stub
		dao.update(parameterOrgRela);
	}
	
	public void save(ParameterOrgRela parameterOrgRela) {
		// TODO Auto-generated method stub
		dao.save(parameterOrgRela);
	}


	public ParameterOrgRela findPORById(Integer setId, Integer parameterId) {
		// TODO Auto-generated method stub
		return dao.getFindPORById(setId,parameterId);
	}
	
	public TemplateOrgRela findTORById(Integer setId, Integer parameterId) {
		// TODO Auto-generated method stub
		return dao.getFindTORById(setId,parameterId);
	}



}
