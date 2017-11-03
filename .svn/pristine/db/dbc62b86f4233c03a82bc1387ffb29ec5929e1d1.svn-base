package com.oio.wawj.service.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.ChannelDAO;
import com.oio.wawj.service.ChannelService;
import com.oio.wawj.util.PageListData;
@Entity
public class ChannelServiceImpl implements ChannelService {

	@ManyToOne
	private ChannelDAO dao;
	
	public ChannelDAO getDao() {
		return dao;
	}
	
	public void setDao(ChannelDAO dao) {
		this.dao = dao;
	}

	
	
	@Override
	public void save(Channel sysChannel) {
		// TODO Auto-generated method stub
		dao.save(sysChannel);
	}

	public void update(Channel channel){
		dao.update(channel);
	}
	//public Integer findId(short id) {
		// TODO Auto-generated method stub
		//return dao.getFindId(id);
	//}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return dao.getfindAll();
	}
	
	public PageListData findAllChannel(int currentPage, int pageSize) {
		String sql = " select c.channel_id as channelId, c.channel_name as channelName, count(uar.channel_id) as count " ;
		
		String sql1=" from user_acms_rela as uar " +
				    " right join channel as c on uar.channel_id = c.channel_id " ;
		
		return dao.getfindAllChannel(sql,sql1,currentPage, pageSize);
	}
	
	public void deleteById(ChannelId id) {
		// TODO Auto-generated method stub
		dao.deleteById(Channel.class, id);
	}

    public Channel findById(ChannelId id){
    	return dao.getFindId( id);
    }
    public String getChannelList(Long operator) {
		// TODO Auto-generated method stub
		return dao.getChannelList(operator);
	}


}
