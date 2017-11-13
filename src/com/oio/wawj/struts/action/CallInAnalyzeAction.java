package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.CallInAnalyzeService;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.AboutOperator;
import com.opensymphony.xwork2.ActionContext;

/**
 * 呼入分析
 * 
 *
 */
public class CallInAnalyzeAction extends BaseAction {

	
	
	private CallInAnalyzeService callInAnalyzeService ;
	private UserService userService; 
	
	private CdrCall cdrCall;
	
	private Date startDate;
	private Date endDate;
	private List<?> dataList;
	private String result;
	private String channelList;
	

	
	/**
	 * 判断查询条件统计
	 * @return
	 */
	public String queryCallInList() {
		System.out.println("开始查询时间--"+AboutOperator.getCurrentDate());
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		  
        String deptId=jo.getString("deptId");       
        String  orgId="";
        String  setId="";
        if(deptId!=null&&!deptId.equals("")){
        	 orgId=deptId.substring(0,10); 
             setId=deptId.substring(10,15);
        }
        
      
        String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
        if(fp.equals("1")&&(orgId.equals("")||orgId==null)){   
        }else if(fp.equals("1")&&(!orgId.equals("")||orgId!=null)){
        	orgId=userService.getChildOrgLists(Long.valueOf(orgId),setId);
        }else if(!fp.equals("1")&&(orgId.equals("")||orgId==null)){
           orgId=userService.getChildOrgList(AboutOperator.getUser().getOrgId());
  		   setId=AboutOperator.getUser().getSetId();
        }else if(!fp.equals("1")&&!orgId.equals("")&&orgId!=null){
        	orgId=userService.getChildOrgList(Long.valueOf(orgId));

        }
    
          String  duration=jo.getString("duration"); 
          String  button=jo.getString("button"); 
          String  name=jo.getString("name"); 
          String  channelName=jo.getString("channelName"); 
          
          String  currentPage1=jo.getString("currentPage"); 
          String  dateVal="";
          if(jo.has("dateVal")){
        	  dateVal=jo.getString("dateVal");
          }
          
          String  checkBox=jo.getString("checkBox"); 
          
		Map<String, String> param = new HashMap<String, String>();
		if( orgId != null && !orgId.equals("") ){
			param.put("orgId", orgId );			
		}
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}

		if( duration != null && !duration.equals("") ){
			param.put("duration", duration );			
		}
		if( name != null && !name.equals("") ){
			param.put("name", name );			
		}
		if( channelName != null && !channelName.equals("") ){
			param.put("channelName", channelName );			
		}
	
		if( dateVal != null && !dateVal.equals("") ){
			String beginTime=dateVal.substring(0, 19);
			String endTime= dateVal.substring(22);
			param.put("beginTime", beginTime);		
			param.put("endTime", endTime );	
		}
		if(currentPage1 != null && !currentPage1.equals("") ){
			currentPage=Integer.valueOf(currentPage1);
		}
		
		if(checkBox != null && !checkBox.equals("") ){	
			param.put("checkBox", checkBox );	
		}
		
		if(button.equals("dept")){
			return this.queryCallInDeptList(param,currentPage, pageSize);
		}else if(button.equals("rest")){
			return this.queryCallInStaffList(param,currentPage, pageSize);
		}else{
			return this.queryCallInChannelList(param,currentPage, pageSize);
			
		}
		
	
	}

	/**
	 * 根据渠道统计
	 * @return
	 */
	public String queryCallInChannelList(Map<String, String> param,int currentPage,int pageSize) {

		pageListData = callInAnalyzeService.findList(param,currentPage, pageSize);		
  
			JSONObject pageviewjson = pageListData.parseJSON(0,"channelName","connTotal","disconnTotal","callTotal","connRate");	
			setResult(pageviewjson.toString());
			System.out.println("结束查询时间--"+AboutOperator.getCurrentDate());
		return SUCCESS;
	
	}
	
/**
 * 根据部门统计
 * @return
 */
	public String queryCallInDeptList(Map<String, String> param,int currentPage,int pageSize) {
			
		pageListData = callInAnalyzeService.findDeptList(param,currentPage, pageSize);		
	
				JSONObject pageviewjson = pageListData.parseJSON(0,"orgName","connTotal","disconnTotal","callTotal","connRate");	
				  setResult(pageviewjson.toString());
				  System.out.println("结束查询时间--"+AboutOperator.getCurrentDate());
		return SUCCESS;
	
	}
	
	
	
	/**
	 * 根据员工/坐席统计
	 * @return
	 */
		public String queryCallInStaffList(Map<String, String> param,int currentPage,int pageSize) {
			
			pageListData = callInAnalyzeService.findStaffList(param,currentPage, pageSize);		
		
					JSONObject pageviewjson = pageListData.parseJSON(0,"name","channelName","code","connTotal","disconnTotal","callTotal","connRate");	
					  setResult(pageviewjson.toString());
					  System.out.println("结束查询时间--"+AboutOperator.getCurrentDate());
			return SUCCESS;
		
		}

	
		
		/** 
		 *  
		 *  Export excel file
		 */

	public String exportExcel(){
	   	 	User su = (User) ActionContext.getContext().getSession().get("user");
	   	 	if(su == null){
			    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败，请重新登录！\"}";
	   			return "success";
	   	 	}
			
	   	 ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	        
			String jstr=request.getParameter("jsonData");
				
			JSONObject jo = JSONObject.fromObject(jstr);
			  
	        String deptId=jo.getString("deptId");       
	        String  orgId="";
	        String  setId="";
	        if(deptId!=null&&!deptId.equals("")){
	        	 orgId=deptId.substring(0,10); 
	             setId=deptId.substring(10,15);
	        }
	        
	      
	        String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
	        if(fp.equals("1")&&(orgId.equals("")||orgId==null)){   
	        }else if(fp.equals("1")&&(!orgId.equals("")||orgId!=null)){
	        	orgId=userService.getChildOrgLists(Long.valueOf(orgId),setId);
	        }else if(!fp.equals("1")&&(orgId.equals("")||orgId==null)){
	           orgId=userService.getChildOrgList(AboutOperator.getUser().getOrgId());
	  		   setId=AboutOperator.getUser().getSetId();
	        }else if(!fp.equals("1")&&!orgId.equals("")&&orgId!=null){
	        	orgId=userService.getChildOrgList(Long.valueOf(orgId));

	        }
	    
	          String  duration=jo.getString("duration"); 
	          String  button=jo.getString("button"); 
	          String  name=jo.getString("name"); 
	          String  channelName=jo.getString("channelName"); 
	          
	          String  currentPage1=jo.getString("currentPage"); 
	         // String  dateVal=jo.getString("dateVal");
	          String  dateVal="";
	          if(jo.has("dateVal")){
	        	  dateVal=jo.getString("dateVal");
	          }
	          String  checkBox=jo.getString("checkBox"); 

				Map<String, String> param = new HashMap<String, String>();
				if( orgId != null && !orgId.equals("") ){
					param.put("orgId", orgId );			
				}
				if( setId != null && !setId.equals("") ){
					param.put("setId", setId );			
				}

				if( duration != null && !duration.equals("") ){
					param.put("duration", duration );			
				}
				if( name != null && !name.equals("") ){
					param.put("name", name );			
				}
				if( channelName != null && !channelName.equals("") ){
					param.put("channelName", channelName );			
				}
			
				if( dateVal != null && !dateVal.equals("") ){
					String beginTime=dateVal.substring(0, 19);
					String endTime= dateVal.substring(22);
					param.put("beginTime", beginTime);		
					param.put("endTime", endTime );	
				}
				
				if(checkBox != null && !checkBox.equals("") ){	
					param.put("checkBox", checkBox );	
				}
				
				
				
				if(button.equals("dept")){
					return this.callInDeptExportExcel(param);
				}else if(button.equals("rest")){
					return this.callInStaffExportExcel(param);
				}else{
					return this.callInChannelExportExcel(param);
					
				}			
				
			}
		
				
				
       public String callInDeptExportExcel(Map<String, String> param){
    	   
   		  ActionContext ct = ActionContext.getContext();
   		  HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
             try{
				currentPage = 1;
				List<User> staffList = null;
				pageListData = callInAnalyzeService.findDeptList(param, 1, 1);
			    
			    if(pageListData.getTotalcount() > 15000){
			    	result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出数据量大于15000,请选择查询条件!\"}";
			    	return "success";
			    }
			    int totalPageCount = 2;
			    staffList = new ArrayList<User>();
				while(currentPage <= totalPageCount){
					
					pageListData = callInAnalyzeService.findDeptList(param, currentPage++, 7500);
					if(pageListData.getDataList().size() == 0){
						break;
					}
					totalPageCount = pageListData.getTotalPageCount();
				
					 staffList.addAll(pageListData.getDataList());
				}
				
				//List<Object> channelList = service.findChannelRela();
				
		        String code=AboutOperator.getUser().getUserId().toString() + "_" + AboutOperator.getUser().getCode();
				String directory = "/downRecord";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			    String fileName = callInAnalyzeService.deptExportExcel(staffList,targetDirectory,code);
			    if(fileName == null){
				    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			    }else{
			    	String url = request.getContextPath();
				    result="{\"result\":\"success\", \"filepath\":\"" + url + directory + "/" + fileName + "\",\"cause\":\"\"}";
			    }
			    staffList = null;
			} catch (RuntimeException e) {
				e.printStackTrace();
			    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			}
			return "success";
		}


       public String callInStaffExportExcel(Map<String, String> param){
    	   
   		  ActionContext ct = ActionContext.getContext();
   		  HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
             try{
				currentPage = 1;
				List<User> staffList = null;
				pageListData = callInAnalyzeService.findStaffList(param, 1, 1);
			    
			    if(pageListData.getTotalcount() > 15000){
			    	result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出数据量大于15000,请选择查询条件!\"}";
			    	return "success";
			    }
			    int totalPageCount = 2;
			    staffList = new ArrayList<User>();
				while(currentPage <= totalPageCount){
					
					pageListData = callInAnalyzeService.findStaffList(param, currentPage++, 7500);
					if(pageListData.getDataList().size() == 0){
						break;
					}
					totalPageCount = pageListData.getTotalPageCount();
				
					 staffList.addAll(pageListData.getDataList());
				}
				
				//List<Object> channelList = service.findChannelRela();
				
		        String code=AboutOperator.getUser().getUserId().toString() + "_" + AboutOperator.getUser().getCode();
				String directory = "/downRecord";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			    String fileName = callInAnalyzeService.staffExportExcel(staffList,targetDirectory,code);
			    if(fileName == null){
				    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			    }else{
			    	String url = request.getContextPath();
				    result="{\"result\":\"success\", \"filepath\":\"" + url + directory + "/" + fileName + "\",\"cause\":\"\"}";
			    }
			    staffList = null;
			} catch (RuntimeException e) {
				e.printStackTrace();
			    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			}
			return "success";
		}
       
       
       
       public String callInChannelExportExcel(Map<String, String> param){
    	   
   		  ActionContext ct = ActionContext.getContext();
   		  HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
             try{
				currentPage = 1;
				List<User> staffList = null;
				pageListData = callInAnalyzeService.findList(param, 1, 1);
			    
			    if(pageListData.getTotalcount() > 15000){
			    	result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出数据量大于15000,请选择查询条件!\"}";
			    	return "success";
			    }
			    int totalPageCount = 2;
			    staffList = new ArrayList<User>();
				while(currentPage <= totalPageCount){
					
					pageListData = callInAnalyzeService.findList(param, currentPage++, 7500);
					if(pageListData.getDataList().size() == 0){
						break;
					}
					totalPageCount = pageListData.getTotalPageCount();
				
					 staffList.addAll(pageListData.getDataList());
				}
				
				//List<Object> channelList = service.findChannelRela();
				
		        String code=AboutOperator.getUser().getUserId().toString() + "_" + AboutOperator.getUser().getCode();
				String directory = "/downRecord";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			    String fileName = callInAnalyzeService.channelExportExcel(staffList,targetDirectory,code);
			    if(fileName == null){
				    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			    }else{
			    	String url = request.getContextPath();
				    result="{\"result\":\"success\", \"filepath\":\"" + url + directory + "/" + fileName + "\",\"cause\":\"\"}";
			    }
			    staffList = null;
			} catch (RuntimeException e) {
				e.printStackTrace();
			    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
			}
			return "success";
		}


	public CdrCall getCdrCall() {
		return cdrCall;
	}

	public void setCdrCall(CdrCall cdrCall) {
		this.cdrCall = cdrCall;
	}

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public CallInAnalyzeService getCallInAnalyzeService() {
		return callInAnalyzeService;
	}



	public void setCallInAnalyzeService(CallInAnalyzeService callInAnalyzeService) {
		this.callInAnalyzeService = callInAnalyzeService;
	}




	public String getChannelList() {
		return channelList;
	}




	public void setChannelList(String channelList) {
		this.channelList = channelList;
	}



	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	
	
}
