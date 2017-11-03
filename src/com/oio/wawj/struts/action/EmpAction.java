package com.oio.wawj.struts.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.CollectionId;
import com.oio.wawj.bean.DutyInfo;
import com.oio.wawj.bean.EmpDismission;
import com.oio.wawj.bean.EmpDuty;
import com.oio.wawj.bean.EmpPersonal;
import com.oio.wawj.service.DutyInfoService;
import com.oio.wawj.service.EmpDismissionService;
import com.oio.wawj.service.EmpDutyService;
import com.oio.wawj.service.EmpPersonalService;
import com.opensymphony.xwork2.ActionContext;


public class EmpAction extends BaseAction {
	
	
	private DutyInfo dutyInfo;
	private DutyInfoService dutyInfoService;
	
	private EmpPersonal empPersonal;
	private EmpPersonalService empPersonalService;
	
	private EmpDuty empDuty;
	private EmpDutyService empDutyService;
	
	private EmpDismission empDismission;
	private EmpDismissionService empDismissionService;
	

	
	
	
		
	private JSONObject result;
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public String dutyInfo(){
		 //
	    	ActionContext ct = ActionContext.getContext();
	  		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	  		HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
	  		ServletInputStream inputStrem=null;
	  		try {
	  			 inputStrem = request.getInputStream();
	  		    // 响应时处理数据   
	  					StringBuffer sb = new StringBuffer();   
	  					String readLine;   
	  					BufferedReader responseReader;   
	  		    responseReader = new BufferedReader(new InputStreamReader(inputStrem));
	  		    while ((readLine = responseReader.readLine()) != null) {   
	  			     sb.append(readLine).append("\n");   
	  			   }  	
	  		     System.out.println(sb.toString());
	  		    response.setContentType("application/json;charset=utf-8");      
			    JSONObject resJo=JSONObject.fromObject(sb.toString());
			    
				JSONArray bl=resJo.getJSONArray("batchLines");
				
			    String transferId= resJo.getString("transferId");
			    String batchNum= resJo.getString("batchNum");
			    String pushDataType= resJo.getString("pushDataType");
			    String transferDateTime= resJo.getString("transferDateTime");
				
                for (int i = 0; i <bl.size(); i++) {
                	JSONObject js=  JSONObject.fromObject(bl.get(i));
                	
                	String	setId=js.getString("setId");
                	String jobCode=js.getString("jobCode");
                	String effDt=js.getString("effDt");
                	String effStatus=js.getString("effStatus");
                	String descr=js.getString("descr");
                	String descrShort=js.getString("descrShort");	
                	String cHoldingNm=js.getString("cHoldingNm");    
    			    dutyInfoService.save(setId,jobCode,effDt,effStatus,descr,descrShort,transferId,batchNum,pushDataType,transferDateTime,cHoldingNm);	
			        if(i%20 == 19){
			        	dutyInfoService.flush();
			        }
				}
 
			    

			    System.out.println("dutyInfo---------"+sb.toString()+"batchNum------"+batchNum);
			    
			    JSONObject resqJo=new JSONObject();
			    
			    resqJo.put("success", "S");
			    resqJo.put("msg", "ESB职务信息口同步成功");
			    resqJo.put("reason", "");
			    resqJo.put("code", "000000");
			    resqJo.put("batchNum", batchNum);
			    resqJo.put("data", "");
			    result=resqJo;
	  				responseReader.close();
	  			 } catch (IOException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			} 
	  		   
	  		return SUCCESS;
	     }
		 public String personalInfo(){
			
			    ActionContext ct = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
				HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
				ServletInputStream inputStrem=null;
				
				try {
					 inputStrem = request.getInputStream();
				    // 响应时处理数据   
							StringBuffer sb = new StringBuffer();   
							String readLine;   
							BufferedReader responseReader;   
				    responseReader = new BufferedReader(new InputStreamReader(inputStrem));
				    while ((readLine = responseReader.readLine()) != null) {   
					     sb.append(readLine).append("\n");   
					   }  	
				    System.out.println("personalInfo---------"+sb.toString());
				    response.setContentType("application/json;charset=utf-8");      
				    JSONObject resqJo=JSONObject.fromObject(sb.toString());
					
					
				    String transferId= resqJo.getString("transferId");
				    String batchNum= resqJo.getString("batchNum");
				    String pushDataType= resqJo.getString("pushDataType");
				    String transferDateTime= resqJo.getString("transferDateTime");
			
				    JSONArray bl=resqJo.getJSONArray("batchLines");
	                for (int i = 0; i <bl.size(); i++) {
	                	JSONObject js=  (JSONObject) bl.get(i);
	                	String emplId=js.getString("emplId");
	                	String countryNmForm=js.getString("countryNmForm");
	                	String nameFormat=js.getString("nameFormat");
	                	String sex=js.getString("sex");
	                	String highestEducLvl=js.getString("highestEducLvl");
	                	String birthDate=js.getString("birthDate");
	                	String cMobile=js.getString("cMobile");
	                	String operator=js.getString("operator");
	                	String company=js.getString("company");
	                	String cEmail=js.getString("cEmail");
	                	String cCompEmail=js.getString("cCompEmail");
	                	String setId=js.getString("setId");
	    
					    empPersonalService.save(emplId,countryNmForm,nameFormat,sex,highestEducLvl,birthDate,cMobile,operator,
					    		company,cEmail,cCompEmail,setId,transferId,batchNum,pushDataType,transferDateTime);
				        if(i%20 == 19){
				        	empPersonalService.flush();
					        }
	                	
					}
				      
				    System.out.println("personalInfo---------"+sb.toString()+"batchNum------"+batchNum);
					    resqJo.put("success", "S");
					    resqJo.put("msg", "ESB个人信息接口同步成功");
					    resqJo.put("reason", "");
					    resqJo.put("code", "000000");
					    resqJo.put("batchNum", batchNum);
					    resqJo.put("data", "");
					    result=resqJo;
				
						responseReader.close();
					 } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 	
				return SUCCESS;
		}
		public String staffDuty(){
			
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
			ServletInputStream inputStrem=null;
			try {
				 inputStrem = request.getInputStream();
			    // 响应时处理数据   
						StringBuffer sb = new StringBuffer();   
						String readLine;   
						BufferedReader responseReader;   
			    responseReader = new BufferedReader(new InputStreamReader(inputStrem));
			    while ((readLine = responseReader.readLine()) != null) {   
				     sb.append(readLine).append("\n");   
				   }  	
			    response.setContentType("application/json;charset=utf-8");      
			    JSONObject resqJo=JSONObject.fromObject(sb.toString());
			    
				JSONArray bl=resqJo.getJSONArray("batchLines");
				
			    String transferId= resqJo.getString("transferId");
			    String batchNum= resqJo.getString("batchNum");
			    String pushDataType= resqJo.getString("pushDataType");
			    String transferDateTime= resqJo.getString("transferDateTime");
			    System.out.println("staffDuty---------"+sb.toString());
                for (int i = 0; i <bl.size(); i++) {
                	JSONObject js=  JSONObject.fromObject(bl.get(i));
                	String emplId=js.getString("emplId");
                	String emplRcd=js.getString("emplRcd");
                	String effDt=js.getString("effDt");
                	String effSeq=js.getString("effSeq");
                	String setidDept=js.getString("setidDept");
                	String deptId=js.getString("deptId");
                	String setIdJobCode=js.getString("setIdJobCode");
                	String company=js.getString("company");
                	String jobCode=js.getString("jobCode");
                	String cQuartersId=js.getString("cQuartersId");
                	String supervisorId=js.getString("supervisorId");
                	String hrStatus=js.getString("hrStatus");
                	String cWorkShop=js.getString("cWorkShop");
                	String cWorkShopDeptId=js.getString("cWorkShopDeptId");
                	String action=js.getString("action");
				    
				    empDutyService.save(action,batchNum,company,cQuartersId,cWorkShop,deptId,emplId,emplRcd,hrStatus,jobCode,
				    		pushDataType,setidDept,transferId,cWorkShopDeptId,supervisorId,setIdJobCode,effDt,effSeq,transferDateTime);
                	
			        if(i%20 == 19){
			        	empDutyService.flush();
				        }
                	
				}
			    
			    
			   
				    resqJo.put("success", "S");
				    resqJo.put("msg", "ESB员工职务接口同步成功");
				    resqJo.put("reason", "");
				    resqJo.put("code", "000000");
				    resqJo.put("batchNum", batchNum);
				    resqJo.put("data", "");
				    result=resqJo;

				    
				
					responseReader.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			   return SUCCESS;
		}
	    public String dimissionInfo(){
	    	ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
			ServletInputStream inputStrem=null;
			try {
				 inputStrem = request.getInputStream();
			    // 响应时处理数据   
						StringBuffer sb = new StringBuffer();   
						String readLine;   
						BufferedReader responseReader;   
			    responseReader = new BufferedReader(new InputStreamReader(inputStrem));
			    while ((readLine = responseReader.readLine()) != null) {   
				     sb.append(readLine).append("\n");   
				   }  
			    response.setContentType("application/json;charset=utf-8");
			    System.out.println("dimissionInfo---------"+sb.toString());     
			    JSONObject resqJo=JSONObject.fromObject(sb.toString());
			    
			    JSONArray bl=resqJo.getJSONArray("batchLines");
			    
			    String transferId= resqJo.getString("transferId");
			    String batchNum= resqJo.getString("batchNum");
			    String pushDataType= resqJo.getString("pushDataType");
			    String transferDateTime= resqJo.getString("transferDateTime");
			   
                for (int i = 0; i <bl.size(); i++) {
                	JSONObject js=  JSONObject.fromObject(bl.get(i));
                	String emplId=js.getString("emplId");
                	String setid=js.getString("setid");
                	String cApproveStatus=js.getString("cApproveStatus");
                	String cLeaveStatus=js.getString("cLeaveStatus");
                	
				    
				    empDismissionService.save(batchNum,cApproveStatus,cLeaveStatus,emplId,pushDataType,transferDateTime,transferId,
				    		setid);
			        if(i%20 == 19){
			        	empDutyService.flush();
				        }
                	
				}    
			    
				    resqJo.put("success", "S");
				    resqJo.put("msg", "ESB离职信息接口同步成功");
				    resqJo.put("reason", "");
				    resqJo.put("code", "000000");
				    resqJo.put("batchNum", batchNum);
				    resqJo.put("data", "");
				    result=resqJo;		
				    
					responseReader.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		   return SUCCESS;
		}
	    
	 
	    
		public DutyInfo getDutyInfo() {
			return dutyInfo;
		}
		public void setDutyInfo(DutyInfo dutyInfo) {
			this.dutyInfo = dutyInfo;
		}
		public DutyInfoService getDutyInfoService() {
			return dutyInfoService;
		}
		public void setDutyInfoService(DutyInfoService dutyInfoService) {
			this.dutyInfoService = dutyInfoService;
		}
		public EmpPersonal getEmpPersonal() {
			return empPersonal;
		}
		public void setEmpPersonal(EmpPersonal empPersonal) {
			this.empPersonal = empPersonal;
		}
		public EmpPersonalService getEmpPersonalService() {
			return empPersonalService;
		}
		public void setEmpPersonalService(EmpPersonalService empPersonalService) {
			this.empPersonalService = empPersonalService;
		}
		public EmpDuty getEmpDuty() {
			return empDuty;
		}
		public void setEmpDuty(EmpDuty empDuty) {
			this.empDuty = empDuty;
		}
		public EmpDutyService getEmpDutyService() {
			return empDutyService;
		}
		public void setEmpDutyService(EmpDutyService empDutyService) {
			this.empDutyService = empDutyService;
		}
		public EmpDismission getEmpDismission() {
			return empDismission;
		}
		public void setEmpDismission(EmpDismission empDismission) {
			this.empDismission = empDismission;
		}
		public EmpDismissionService getEmpDismissionService() {
			return empDismissionService;
		}
		public void setEmpDismissionService(EmpDismissionService empDismissionService) {
			this.empDismissionService = empDismissionService;
		}
	    
	
}
