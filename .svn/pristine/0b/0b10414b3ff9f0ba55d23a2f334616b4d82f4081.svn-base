
package com.oio.wawj.dao;


import java.util.Map;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.util.PageListData;



public interface CallRecordsDAO extends BaseDAO<CdrCall, Long> {
	public PageListData getList(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallInMessage(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutRecord(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutComeRecord(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutStranger(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutMessage(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutReplyRecord(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public PageListData getCallOutStrangerRecords(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
}