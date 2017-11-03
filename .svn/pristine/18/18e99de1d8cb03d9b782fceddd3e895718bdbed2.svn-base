/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao;



import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.util.PageListData;



public interface ChannelDAO extends BaseDAO<Channel,ChannelId>{


	public void deleteChannelById(Short id);

	public Channel getFindId(final ChannelId id);
	public String getfindAll();
	public PageListData getfindAllChannel(final String hql,final String hql1,final int currentPage,final int pageSize);

	public String getfindUserAll(Short id);
	//public String getfindRoleAll();
	public Object getObjectById(String id);
	
	public Channel getChannelIdByRoleId(final Short id);
	public  String getChannelList( final Long operator);
	public  Integer getChannelListCount(final String hql,final String hql1,final Integer id);
	
}
