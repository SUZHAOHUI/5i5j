package com.oio.wawj.service.impl;

import java.util.Map;

import com.oio.wawj.bean.Customer;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.CustomerDAO;
import com.oio.wawj.service.CustomerService;
import com.oio.wawj.util.PageListData;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO dao;

	public CustomerDAO getDao() {
		return dao;
	}

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("rawtypes")
	public PageListData findCustomerList( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select c.tel as telList, c.name as name, c.code as code,c.comments as comments, c.cust_id as custId, c.gender as gender, c.source_id as sourceId, " +
				     " c.clevel as clevel, c.plevel as plevel, c.belong_with as belongWith, c.customer_status_id as statusId," +
				     " c.situation as situation, c.user_id as userId, cst.name as statusName, ct.name as typeName," +
				     " ct.level as typeLeve, cso.name as sourceName,u.name  as  userName ,ct.id as clevelId, pt.id as plevelId";
		String sql1=	" from customer c " +
				        " left join customer_status cst on c.customer_status_id = cst.id " +
				        " left join customer_type ct on c.clevel = ct.id " +
				        " left join customer_type pt on c.plevel = pt.id " +
				        " left join customer_source cso on c.source_id = cso.id" +
				        " left join user u on u.user_id = c.user_id";       
		return dao.getCustomerList(sql,sql1,param, currentPage, pageSize);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(Customer.class, id);
	}
	
	@Override
	public String findSource() {
		// TODO Auto-generated method stub
		return dao.getfindSource();
	}
	@Override
	public String findCustomerStatus() {
		// TODO Auto-generated method stub
		return dao.getfindCustomerStatus();
	}
	@Override
	public String findCustomerType() {
		// TODO Auto-generated method stub
		return dao.getfindCustomerType();
	}
	@Override
	public String findCustomerProductType() {
		// TODO Auto-generated method stub
		return dao.getfindCustomerProductType();
	}
	@Override
	public String findUserName() {
		// TODO Auto-generated method stub
		return dao.getfindUserName();
	}
	
	public void save(Customer customer) {
		dao.save(customer);
	}
	
	public void update(Customer customer) {
		dao.saveOrUpdate(customer);
	}
	
	public Customer findById(Long id) {

		return dao.findById(Customer.class, id);
	}
	public Customer findByCustId(Long id) {
		
		return dao.getfindByCustId(id);
	}
}
