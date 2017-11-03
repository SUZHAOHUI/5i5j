
package com.oio.wawj.dao;


import java.util.Map;

import com.oio.wawj.bean.Customer;
import com.oio.wawj.util.PageListData;



public interface CustomerDAO extends BaseDAO<Customer, Long> {
	
	public PageListData getCustomerList(final String hql,final String hql1,final Map param,final int currentPage,final int pageSize);
	public String getfindSource();
	public String getfindCustomerStatus();
	public String getfindCustomerType();
	public String getfindCustomerProductType();
	public String getfindUserName();
	public Customer getfindByCustId(Long id);
}