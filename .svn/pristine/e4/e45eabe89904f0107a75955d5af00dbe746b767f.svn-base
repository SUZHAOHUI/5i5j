package com.oio.wawj.service.impl;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.dao.AxbDAO;
import com.oio.wawj.service.AxbService;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.util.PageListData;




/**
 * SubsRela相关的DAO实现类，主要包括对SubsRela表的操作方法
 * @author 
 */
@Entity
public class AxbServiceImpl implements AxbService {
	@ManyToOne
	private AxbDAO dao;
	
	public AxbDAO getDao() {
		return dao;
	}

	public void setDao(AxbDAO dao) {
		this.dao = dao;
	}


	public void save(SubsRela uar) {
		dao.save(uar);
	}
   

	public void deleteById(Long id) {
		dao.deleteById(SubsRela.class, id);
	}


	
	
	public void update(SubsRela asr) {
		dao.saveOrUpdate(asr);
	}
	

	
	@SuppressWarnings( "unchecked" )
	public PageListData findList(Map param, int pageNum, int pageSize) {

		return dao.findList(param, pageNum, pageSize);
	}

    public SubsRela findByXnumAndAnum(String xnum,String anum){
    	
    	return dao.findByXnumAndAnum(xnum,anum);
    }
	
	public SubsRela findById(Long id) {

		return dao.findById(SubsRela.class, id);
	}


}
