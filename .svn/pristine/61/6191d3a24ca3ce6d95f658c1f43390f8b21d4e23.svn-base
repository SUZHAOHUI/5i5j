package com.oio.wawj.dao;

import java.util.Map;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.util.PageListData;

public interface RingtoneDAO extends BaseDAO<Ringtone,Short>{

	public String getfindRingtone();
	
	public PageListData getfindRingtoneAll(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	
	public String getcallInfindRingtone( Map param);
	
	public String getfindRingtoneUrl(short Id);
	
	public Ringtone getFindId(final short id);
	
	public String getList(Map param,String sql);
	
	public String getfindChannelRingtone(String sql);
}
