
package com.oio.wawj.dao;



import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.User;
import com.oio.wawj.util.PageListData;


public interface NumberDAO extends BaseDAO<Acms,Integer>{
	
	
	public PageListData findNumberList(Map<String, Object> param, int currentPage,
			int pageSize);
	public List<String> findAllAcms();
	public Channel findChannelByName(final String name);
	public User findUserByCode(String code);
	public PageListData findStaffByAcms(Map<String, Object> param, int currentPage,
			int pageSize);
}
