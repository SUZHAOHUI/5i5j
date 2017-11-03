/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.util.PageListData;


@SuppressWarnings("rawtypes")
public interface UserAcmsRelaService{
	//锟斤拷臃锟斤拷锟�
	public void save(UserAcmsRela uar);
	//通锟斤拷ID删锟斤拷
	public void deleteById(Integer id);
	public void update(UserAcmsRela uar);

	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param name 锟斤拷锟斤拷锟斤拷
	 * @param type 锟斤拷锟斤拷锟斤拷
	 * @return boolean
	 */
	//通锟斤拷ID锟斤拷询
	public UserAcmsRela findById(Integer id);
    public List<UserAcmsRela> findbyAcmsId(Integer acmsId);
   
}
