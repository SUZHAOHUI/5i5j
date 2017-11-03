package com.oio.wawj.service;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.util.PageListData;

public interface ChannelService {

	public void save(Channel sysChannel);
	
	//public Integer findId(short id);

	public String findAll();
	public PageListData findAllChannel(int currentPage, int pageSize);
	
	public void deleteById(ChannelId id);
    public Channel findById(ChannelId id);
	public void update(Channel channel);
	public String getChannelList(Long operator);
	
}
