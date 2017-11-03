
package com.oio.wawj.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;


/**
 * 主要功能：customer相关的action，用于充当与customer相关的用户请求和业务逻辑的桥梁
 * 
 * @author 
 */
public class ProviderAction extends BaseAction {

	// 属性定义
	private static final long serialVersionUID = 1L;

	
	
	public String init(){
		return "init";
	}
	@Override
	/**
	 * 校验方法
	 */
	public void validate() {
		this.clearErrorsAndMessages();
	}

	/**
	 * 转到增加页面
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	public String toCreate() {
	
		return "create";
	}

	/**
	 * 添加客户
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String create() {
		Date tmpDate = getNowDate();
		// 获取页面 上的客户信息
	
		return "create";

	}
	/**
	 * 添加成员
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	@SuppressWarnings("unchecked")
	public String membercreate() {
		Date tmpDate = getNowDate();
		// 获取页面 上的成员信息

		Map request = (Map) ActionContext.getContext().get("request");
		request.put("createResultInfo", "SUCCESS");
		return  delete();
		
        
	}
	
	/**
	 * 获取系统当前日期
	 * 
	 * @return 日期
	 */
	private Date getNowDate() {
		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @param date
	 * @return 日期
	 */
	private Date getNowDate(String date) {
		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 转到修改页面
	 * 
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	@SuppressWarnings("rawtypes")
	public String toUpdate() {
		// customer = customerService.findById(customer.getCustId());
		// 设置证件类型下拉框
		Map request = (Map) ActionContext.getContext().get("request");
		return "update";
	}

	/**
	 * 更新信息
	 * 
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String update() {
		Date tmpDate = getNowDate();
		// 获取页面 上的客户信息

		Map request = (Map) ActionContext.getContext().get("request");
		// 设置添加成功提醒
		request.put("updateResultInfo", "SUCCESS");
		return "update";
	}

	/**
	 * 转到查询列表
	 * 
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	public String toQuery() {
		// 设置证件类型
		Map request = (Map) ActionContext.getContext().get("request");
		return "query";
	}

	/**
	 * 查询列表
	 * 
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	public String query() {
		// 拼装查询条件，并填充到后台给sql查询时使用

		// 调用sevice查询相应数据
//		pageListData = customerService.findList(param, currentPage,
//				pageSize);
			
		return "query";
	}
	/**
	 * 转到删除客户页面
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	public String toDelete() {
		// customer = customerService.findById(customer.getCustId());
		// 设置下拉框
		Map request = (Map) ActionContext.getContext().get("request");
		
		return "delete";
	}

	/**
	 * 删除客户
	 * @return 映射参数，并到struts.xml中查找返回地址
	 */
	public String delete() {
		Date tmpDate = getNowDate();
		// 获取页面 上的客户信息
//		Customer tempCustomer = null;
//		// 设置下拉框
//		Map request = (Map) ActionContext.getContext().get("request");
//				tempCustomer = customerService.findById(customer.getCustId());
//				tempCustomer.setStatusDate(tmpDate);
//				tempCustomer.setStatus("T");

//				// 设置添加成功提醒
//				request.put("deleteResultInfo", "DELETESUCCESS");
//				// 操作日志
//				setOperationLogResult("result:'" + getText("operatelog.customer.cancelcustomer.delete.success") 
//						+"', reason:''");
				//operationLogService.recordLog(null, "Cancel Customer", "customer delete complete", null);
				return "delete";
			
		
		
//		return "delete";
		
	}



	


	



}