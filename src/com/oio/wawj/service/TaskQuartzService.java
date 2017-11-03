package com.oio.wawj.service;

import java.util.Map;

import com.oio.wawj.util.PageListData;


public interface TaskQuartzService {
	public PageListData findAccountList(Map<String, Object> param, int currentPage, int pageSize);

}
