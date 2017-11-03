package com.oio.wawj.service.impl;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.bean.Template;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.RingtoneDAO;
import com.oio.wawj.service.RingtoneService;
import com.oio.wawj.util.PageListData;
@Entity
public class RingtoneServiceImpl implements RingtoneService {

	@ManyToOne
	private RingtoneDAO dao;
	
	
	
	public RingtoneDAO getDao() {
		return dao;
	}



	public void setDao(RingtoneDAO dao) {
		this.dao = dao;
	}



	@Override
	public String findRingtone() {
		// TODO Auto-generated method stub
		return dao.getfindRingtone();
	}
	
	public PageListData findRingtoneAll(Map param, int currentPage, int pageSize) {
	
		String sql = " select r.id as id,r.name as name,r.state as state,r.savepath as url,cd.descrShort ";
		String sql1= " from ringtone as r " +
				     " left join collection_id cd on r.set_id=cd.set_id ";
		return dao.getfindRingtoneAll(sql,sql1,param, currentPage, pageSize);
	}
	
	public String callInfindRingtone(Map param) {
		
/*		String sql = " select r.id as id,r.name as name,r.state as state,r.savepath as url,cd.descrShort ";
		String sql1= " from ringtone as r " +
				" left join collection_id cd on r.set_id=cd.set_id ";*/
		return dao.getcallInfindRingtone(param);
	}
	
	
	@Override
	public String findRingtoneUrl(short Id) {
		// TODO Auto-generated method stub
		return dao.getfindRingtoneUrl(Id);
	}

	
	/**
	 * 保存记录
	 */
	public void save(Ringtone sysRingtone) {
		dao.save(sysRingtone);
	}
	
	/**
	 * 更新记录
	 */
	public void update(Ringtone ringtone) {
		dao.update(ringtone);
	}
	
	/**
	 * delete ringtone
	 */
	public void deleteById(short id) {
		dao.deleteById(Ringtone.class,id);
	}
	
    public Ringtone findById(short id){
    	return dao.getFindId(id);
    }
    

	public String findList(Map param) {
		// TODO Auto-generated method stub 
		
		String sql=" select ir.org_id, ir.parameter_id, ir.value_id,r.name,r.savepath,p.item " +
				   " from parameter_org_rela as ir " +
				   " left join ringtone r on r.id=ir.value_id " +
				   " left join parameter p on p.parameter_id = ir.parameter_id ";
		return dao.getList(param,sql);
	}
	
	public String findChannelRingtone() {
		
		String sql=" select c.channel_id,c.channel_name,c.ring_id,r.name,r.savepath " +
				   " from channel c " +
				   " left join ringtone r on r.id=c.ring_id ";
		return dao.getfindChannelRingtone(sql);
	}
	
}
