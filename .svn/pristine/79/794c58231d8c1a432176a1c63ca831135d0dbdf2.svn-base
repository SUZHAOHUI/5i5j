package com.oio.wawj.service.impl;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.dao.ChannelDAO;
import com.oio.wawj.dao.ParameterDAO;
import com.oio.wawj.service.ParameterService;
@Entity
public class ParameterServiceImpl implements ParameterService {

	@ManyToOne
	private ParameterDAO dao;
	
	
	
	
	public ParameterDAO getDao() {
		return dao;
	}

	public void setDao(ParameterDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Parameter sysParmater) {
		// TODO Auto-generated method stub
		dao.save(sysParmater);
	}

	@Override
	public Parameter findById(Integer id) {
		
		// TODO Auto-generated method stub
		 return dao.getFindById(id);
	}

	@Override
	public Parameter findByParameter(String parameter) {
		
		// TODO Auto-generated method stub
		 return dao.getFindByParameter(parameter);
	}
	
	public String findTemplateTORList(Map param) {
		// TODO Auto-generated method stub 
		
		String sql=" select tr.org_id, tr.parameter_id, tr.value_id,t.name,t.content,p.item " +
				   " from template_org_rela as tr " +
				   " left join template t on t.template_id=tr.value_id " +
				   " left join parameter p on p.parameter_id = tr.parameter_id ";
		return dao.getfindTemplateTORList(param,sql);
	}
	
	@Override
	public String findCallInNumber() {
		// TODO Auto-generated method stub
		return dao.getfindCallInNumber();
	}
	
	@Override
	public String findCallInMusic() {
		// TODO Auto-generated method stub
		return dao.getfindCallInMusic();
	}
	@Override
	public String findCallInMessage() {
		// TODO Auto-generated method stub
		return dao.getfindCallInMessage();
	}

	@Override
	public String findCallOutNumber() {
		// TODO Auto-generated method stub
		return dao.getfindCallOutNumber();
	}
	
	@Override
	public String findCallOutMusic() {
		// TODO Auto-generated method stub
		return dao.getfindCallOutMusic();
	}

	
	@Override
	public String findCallOutMessage() {
		// TODO Auto-generated method stub
		return dao.getfindCallOutMessage();
	}
	
	@Override
	public String findCallOutBinding() {
		// TODO Auto-generated method stub
		return dao.getfindCallOutBinding();
	}
	

	@Override
	public void update(Parameter parmater) {
		// TODO Auto-generated method stub
		dao.update(parmater);
	}
	

}
