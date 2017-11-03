package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.Customer;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.CustomerService;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

public class CustomerAction extends BaseAction{
	
	private CustomerService customerService ;
	private UserService userService ;
	
	private Customer customer ;
	
	private long custId;
	private long code;
	private String name;
	private String gender;
	private String sourceId;
	private String type;
	private short clevel;
	private short plevel;
	private String belongWith;
	private short customerStatusId;
	private String situation;
	private String tel;
	private Integer userId;
	private String pool;
	private Date createDate;
	private String comments;
	
	private String result;
	private String customerList;
	
	
	/**
	 * Display the source drop-down box
	 */
	public String queryCustomerSource() {
		
		customerList=customerService.findSource();
		setResult(customerList);
		return SUCCESS;
	}
	
   /**
    * Display the CustomerStatus drop-down box
    */
	public String queryCustomerStatus() {
		
		customerList=customerService.findCustomerStatus();
		setResult(customerList);
		return SUCCESS;
	}

	/**
     * Display the CustomerType drop-down box
	 */
	public String queryCustomerType() {
		
		customerList=customerService.findCustomerType();
		setResult(customerList);
		return SUCCESS;
	}
	
	/**
     * Display username
	 */
	public String queryUsername() {
		
		customerList=customerService.findUserName();
		setResult(customerList);
		return SUCCESS;
	}
	
	/**
     * Display the CustomerProductType drop-down box
	 */
	public String queryCustomerProductType() {
		
		customerList=customerService.findCustomerProductType();
		setResult(customerList);
		return SUCCESS;
	}
	
	

	public String queryCustomerList() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		
		String  tel=jo.getString("tel"); 
		String  statusId=jo.getString("state"); 
		String  pool=jo.getString("clientPool"); 
		String  clevel=jo.getString("Customer"); 
		String  plevel=jo.getString("Product"); 
		String  sourceId=jo.getString("source"); 
		String  currentPage1=jo.getString("currentPage"); 
		String  nameOrNumber=jo.getString("fuzzySearch"); 
		
//		try {
//			nameOrNumber = new String(nameOrNumber.getBytes("iso8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String  dateVal=jo.getString("dateVal");
//		String  tel=jo.getString("tel");
		Map<String, String> param = new HashMap<String, String>();
		if( tel != null && !tel.equals("") ){
			param.put("tel", tel );			
		}
		if( statusId != null && !statusId.equals("") ){
			param.put("statusId", statusId );			
		}
		if( pool != null && !pool.equals("") ){
			param.put("pool", pool );			
		}
		if( clevel != null && !clevel.equals("") ){
			param.put("clevel", clevel );			
		}
		if( plevel != null && !plevel.equals("") ){
			param.put("plevel", plevel );			
		}
		if( sourceId != null && !sourceId.equals("") ){
			param.put("sourceId", sourceId );			
		}
		if( nameOrNumber != null && !nameOrNumber.equals("") ){
			param.put("nameOrNumber", nameOrNumber );			
		}
/*		if( tel != null && !tel.equals("") ){
			param.put("tel", tel );			
		}*/
		
		if( dateVal != null && !dateVal.equals("") ){
			String beginTime=dateVal.substring(0, 19);
			String endTime= dateVal.substring(22);
			param.put("beginTime", beginTime);		
			param.put("endTime", endTime );	
		}
		if(currentPage1 != null && !currentPage1.equals("") ){
			System.out.println(currentPage1);
			currentPage=Integer.valueOf(currentPage1);
		}
		pageListData = customerService.findCustomerList(param,currentPage, pageSize);		
		System.out.println(pageListData+"--------------");
		if(pageListData.getTotalcount()!=0)
		{		
			JSONObject pageviewjson = pageListData.parseJSON(0,"telList","name","code","comments","custId","gender","sourceId",
					"clevel","plevel","belongWith","statusId","situation","userId","statusName",
					"typeName","typeLeve","sourceName","userName","clevelId","plevelId");	
			System.out.println(pageviewjson.toString());
			setResult(pageviewjson.toString());
		}else{
			
			setResult("[]");	
		}
		
		return SUCCESS;
		
	}
	
	
	/**
	 * CustomerAdd
	 */
	public String CustomerAdd() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		
		String  belongWith=jo.getString("ascription");
		String  userId=jo.getString("user");
		String  tel=jo.getString("telList");

		String  code=jo.getString("code"); 
		String  name=jo.getString("name");
		String  gender=jo.getString("gender");
		String  comments=jo.getString("comments");
		
		String  statusId=jo.getString("state");  
		String  clevel=jo.getString("Customer"); 
		String  plevel=jo.getString("Product"); 
		String  source=jo.getString("source");  

//		try {
//			//user = new String(user.getBytes("iso8859-1"),"UTF-8");
//			name = new String(name.getBytes("iso8859-1"),"UTF-8");
//			comments = new String(comments.getBytes("iso8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//Short userId=null;
		if(belongWith.equals("user")){
			belongWith="U";			
//		       User user1= userService.findUserByName(user);
//		       
//		       if(user1==null){
//		    		  JSONObject job= new JSONObject();
//						job.put("result", "false");
//						setResult("false");
//		    	   return SUCCESS;
//		       }
//		       
//		       userId=user1.getUserId();
			
		}else{
			
			belongWith="P";
		}

	    
		Customer customer1 = new Customer();
		customer1.setCode(code);
		customer1.setName(name);
		customer1.setGender(gender);
		customer1.setSourceId(Short.valueOf(source));	
		customer1.setClevel(Short.valueOf(clevel));
		customer1.setPlevel(Short.valueOf(plevel));
		customer1.setBelongWith(belongWith);
		customer1.setCustomerStatusId(Short.valueOf(statusId));
		customer1.setSituation("跟进测试");
		customer1.setTel(tel);
		customer1.setComments(comments);
		
		
		customer1.setUserId(Integer.valueOf(userId));
		customer1.setCreateDate(DateTime.getCurrentDateTime());
		
		customerService.save(customer1);
		
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		result = "success";
		return "success";
	}

	
	/**
	 * CustomerUpdate
	 */
	public String CustomerUpdate() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);

		String  custId=jo.getString("custId"); 
		String  belongWith=jo.getString("ascription");
		String  userId=jo.getString("user");
		String  tel=jo.getString("telList");
	
		String  code=jo.getString("code"); 
		String  name=jo.getString("name");
		String  gender=jo.getString("gender");
		String  comments=jo.getString("comments");
		
		String  statusId=jo.getString("state");  
		String  clevel=jo.getString("Customer"); 
		String  plevel=jo.getString("Product"); 
		String  source=jo.getString("source");  

//		try {
//			//user = new String(user.getBytes("iso8859-1"),"UTF-8");
//			name = new String(name.getBytes("iso8859-1"),"UTF-8");
//			comments = new String(comments.getBytes("iso8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//Short userId=null;
		if(belongWith.equals("user")){
			belongWith="U";
//			
//		       User user1= userService.findUserByName(user);
//		       
//		       if(user1==null){
// 	    		  JSONObject job= new JSONObject();
//						job.put("result", "false");
//						setResult("false");
//		    	   return SUCCESS;
//		       }
//		       
//		       userId=user1.getUserId();
			
		}else{
			
			belongWith="P";
		}
	    
		Customer customer1 = customerService.findById(Long.valueOf(custId));
		
		customer1.setCode(code);
		customer1.setName(name);
		customer1.setGender(gender);
		customer1.setSourceId(Short.valueOf(source));	
		customer1.setClevel(Short.valueOf(clevel));
		customer1.setPlevel(Short.valueOf(plevel));
		customer1.setBelongWith(belongWith);
		customer1.setCustomerStatusId(Short.valueOf(statusId));
		customer1.setSituation("跟进测试");
		customer1.setTel(tel);
		customer1.setComments(comments);
		
		
		customer1.setUserId(Integer.valueOf(userId));
		customer1.setCreateDate(DateTime.getCurrentDateTime());
		
		customerService.update(customer1);
		
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		result = "success";
		return "success";
	}

	
	/**
	 * CustomerDelete
	 */
	public String CustomerDelete() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String Id=request.getParameter("custId");
		customerService.deleteById(Long.valueOf(Id));
		setOperationLogResult("result:'" + getText("operatelog.role.delete.success") +"', reason:''");
		return "success";
	}

	
	

	
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public short getClevel() {
		return clevel;
	}
	public void setClevel(short clevel) {
		this.clevel = clevel;
	}
	public short getPlevel() {
		return plevel;
	}
	public void setPlevel(short plevel) {
		this.plevel = plevel;
	}
	public String getBelongWith() {
		return belongWith;
	}
	public void setBelongWith(String belongWith) {
		this.belongWith = belongWith;
	}
	public short getCustomerStatusId() {
		return customerStatusId;
	}
	public void setCustomerStatusId(short customerStatusId) {
		this.customerStatusId = customerStatusId;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}



	public long getCode() {
		return code;
	}


	public void setCode(long code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getCustomerList() {
		return customerList;
	}

	public void setCustomerList(String customerList) {
		this.customerList = customerList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


}
