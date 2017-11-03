
package com.oio.wawj.service;

import java.util.Map;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.Customer;
import com.oio.wawj.bean.User;
import com.oio.wawj.util.PageListData;



@SuppressWarnings("rawtypes")
public interface CustomerService{

	public PageListData findCustomerList(Map param,int currentPage, int pageSize);
	public void deleteById(Long id);
	public String findSource();
	public String findCustomerStatus();
	public String findCustomerType();
	public String findCustomerProductType();
	public String findUserName();
	public void save(Customer customer);
	public void update(Customer customer);
	public Customer findById(Long id);
	public Customer findByCustId(Long id);

}
              