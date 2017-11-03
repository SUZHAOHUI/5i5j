/**
* Copyright (c) REALSTONE CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.service;

import com.oio.wawj.util.PageListData;


public interface CdrService{


	public PageListData findList(String callingNo,String calledNo,String callStartTime,String callEndTime,String appkey, String callType, int currentPage, int pageSize);
	

}