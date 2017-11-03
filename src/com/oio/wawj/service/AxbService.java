package com.oio.wawj.service;


import java.util.Map;

import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public interface AxbService{
	
	public void save(SubsRela asr);

	public void deleteById(Long id);
	public void update(SubsRela asr);
	public PageListData findList(Map param, int currentPage, int pageSize);
	public SubsRela findById(Long id);
	 public SubsRela findByXnumAndAnum(String xnum,String anum);
}
