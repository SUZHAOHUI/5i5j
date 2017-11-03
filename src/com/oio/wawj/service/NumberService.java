
package com.oio.wawj.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.User;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public interface NumberService{
	//锟斤拷臃锟斤拷锟�
	public void save(Acms acms);
	//通锟斤拷ID删锟斤拷
	public void deleteById(Integer id);
	public void update(Acms acms);
	
	public PageListData findNumberList(Map param, int pageNum, int pageSize);
	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param name 锟斤拷锟斤拷锟斤拷
	 * @param type 锟斤拷锟斤拷锟斤拷
	 * @return boolean
	 */
	//通锟斤拷ID锟斤拷询
	public Acms findById(Integer id);
	public void importExcel(User user, String targetDirectory,
			String excelFilename, File numFile);
	public List<Acms> findAcmsByNo(String acms);
	public PageListData findStaffByAcms(Map param, int pageNum, int pageSize);
	//public String exportExcel(List list,String targetDirectory,String code);
	public String exportExcel(List list,String targetDirectory,String code,String status);
}
