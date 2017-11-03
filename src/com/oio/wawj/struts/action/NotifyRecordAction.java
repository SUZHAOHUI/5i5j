package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.OnHookSmsRecord;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.OnHookSmsRecordService;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.AboutOperator;
import com.opensymphony.xwork2.ActionContext;
/**
 * 通知记录
 * @author cookie
 *
 */
public class NotifyRecordAction  extends BaseAction{

	
	
	private OnHookSmsRecord onHookSmsRecord ;
	private CdrCall cdrCall ;

	private OnHookSmsRecordService onHookSmsRecordService;
	private UserService userService; 
    private String id;
	
    //private long id;
    private Date timestamp;
    private String targetNumber;
    private String state;
    private String scenario;
    private String content;
    private String callId;
    private String userName;
    private String chargeInfo;
    
	private String plist;
	private String result;
    
	/**通知记录—挂机记录查看
	 * 
	 */
	public String getfindRecord() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
        String  targetNumber=jo.getString("targetNumber"); 
        String  state=jo.getString("state"); 
        String  userName=jo.getString("userName"); 
        String  AllScenes =jo.getString("AllScenes"); 
       
        String  currentPage1=jo.getString("currentPage"); 
        String  dateVal=jo.getString("dateVal");
        
        String deptId=jo.getString("deptId");       
        String  orgId="";
        String  setId="";
        if(deptId!=null&&!deptId.equals("")){
        	
        	 orgId=deptId.substring(0,10); 
             setId=deptId.substring(10,15);
        }
      
        String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
        
/*        if(fp.equals("1")&&(orgId.equals("")||orgId==null)){   
        }else if(fp.equals("1")&&(!orgId.equals("")||orgId!=null)){
        	orgId=userService.getChildOrgList(Long.valueOf(orgId));
        }else if(!fp.equals("1")&&(orgId.equals("")||orgId==null)){
           orgId=userService.getChildOrgList(AboutOperator.getUser().getOrgId());
  		   setId=AboutOperator.getUser().getSetId();
        }else if(!fp.equals("1")&&!orgId.equals("")&&orgId!=null){
        	orgId=userService.getChildOrgList(Long.valueOf(orgId));

        }*/
        
        if(fp.equals("1")&&(orgId.equals("")||orgId==null)){   
        }else if(fp.equals("1")&&(!orgId.equals("")||orgId!=null)){
        	orgId=userService.getChildOrgLists(Long.valueOf(orgId),setId);
        }else if(!fp.equals("1")&&(orgId.equals("")||orgId==null)){
           orgId=userService.getChildOrgList(AboutOperator.getUser().getOrgId());
  		   setId=AboutOperator.getUser().getSetId();
        }else if(!fp.equals("1")&&!orgId.equals("")&&orgId!=null){
        	orgId=userService.getChildOrgList(Long.valueOf(orgId));

        }
    
        
        
		Map<String, String> param = new HashMap<String, String>();
		
		
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}

		
		if( targetNumber != null && !targetNumber.equals("") ){
			param.put("targetNumber", targetNumber );			
		}
		if( state != null && !state.equals("") ){
			param.put("state", state );			
		}
		if( userName != null && !userName.equals("") ){
			param.put("userName", userName );			
		}
		if( AllScenes != null && !AllScenes.equals("") ){
			param.put("AllScenes", AllScenes );			
		}

		if( orgId != null && !orgId.equals("") ){
			param.put("orgId", orgId );			
		}
		
		
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
		
		
		pageListData =  onHookSmsRecordService.findOnHookSmsRecord(param,currentPage, pageSize);		
	     System.out.println(pageListData+"--------------");
/*			if(pageListData.getTotalcount()!=0)
				{		
					JSONObject pageviewjson = pageListData.parseJSON(0,"timestamp","targetNumber","state","scenario",
							"content","callId","userName","chargeInfo","cntsms");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());
				}else{
						  
				setResult("[]");	
			    }*/
		
					JSONObject pageviewjson = pageListData.parseJSON(0,"timestamp","targetNumber","state","scenario",
							"content","callId","userName","chargeInfo","cntsms");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());

		return "success";
	}
	
	
	/**
	 * NotifyRecord export excel
	 */
	
	public String notifyRecordExportExcel(){
   	 	User su = (User) ActionContext.getContext().getSession().get("user");
   	 	if(su == null){
		    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败，请重新登录！\"}";
   			return "success";
   	 	}
		
   	    ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
     
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
     String  targetNumber=jo.getString("targetNumber"); 
     String  state=jo.getString("state"); 
     String  userName=jo.getString("userName"); 
     String  AllScenes =jo.getString("AllScenes"); 

     String  dateVal=jo.getString("dateVal");
     
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
 
       
		Map<String, String> param = new HashMap<String, String>();
		
		
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}

		
		if( targetNumber != null && !targetNumber.equals("") ){
			param.put("targetNumber", targetNumber );			
		}
		if( state != null && !state.equals("") ){
			param.put("state", state );			
		}
		if( userName != null && !userName.equals("") ){
			param.put("userName", userName );			
		}
		if( AllScenes != null && !AllScenes.equals("") ){
			param.put("AllScenes", AllScenes );			
		}

		if( orgId != null && !orgId.equals("") ){
			param.put("orgId", orgId );			
		}
		
		
		if( dateVal != null && !dateVal.equals("") ){
			String beginTime=dateVal.substring(0, 19);
			String endTime= dateVal.substring(22);
			param.put("beginTime", beginTime);		
			param.put("endTime", endTime );	
		}

		
		try{
			currentPage = 1;
			List<User> staffList = null;
			pageListData = onHookSmsRecordService.findOnHookSmsRecord(param, 1, 1);
		    
		    if(pageListData.getTotalcount() > 15000){
		    	result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出数据量大于15000,请选择查询条件!\"}";
		    	return "success";
		    }
		    int totalPageCount = 2;
		    staffList = new ArrayList<User>();
			while(currentPage <= totalPageCount){
				
				pageListData = onHookSmsRecordService.findOnHookSmsRecord(param, currentPage++, 7500);
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
		    String fileName = onHookSmsRecordService.notifyRecordExportExcel(staffList,targetDirectory,code);
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
	
	
	
	
	
	
	
	
	/**通知记录—挂机记录更多查看
	 * 
	 */
	public String getfindRecordMore(){
		
		
		HttpServletRequest request= ServletActionContext.getRequest();
		String callId = request.getParameter("id");

		 List list =  onHookSmsRecordService.findCdrCallById(callId);
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		
		
		
		for (int i = 0; i < list.size(); i++) {
            Object[] o=(Object[]) list.get(i);
           System.out.println(o[6]+"--------type");
           
            String s1= (String) o[6];
  			if(!s1.equals("1")){
   			jo.put("prtms", o[0]);
   			jo.put("acms", o[1]);
   			jo.put("otherms", o[2]);	
   			jo.put("callTime", o[3]);	
   			jo.put("releaseTime", o[4]);	
   			jo.put("userName", o[5]);	
           }else{
     			jo.put("otherms", o[0]);
      			jo.put("acms", o[1]);
      			jo.put("prtms", o[2]);	
      			jo.put("callTime", o[3]);	
      			jo.put("releaseTime", o[4]);	
      			jo.put("userName", o[5]);
           }   
	

		}
	
		  String orgTree = jo.toString(); //ja.toString();
		  setResult(orgTree);
		  return SUCCESS;	
		
		
		
	}
	
	
	
	
	   
	public OnHookSmsRecord getOnHookSmsRecord() {
		return onHookSmsRecord;
	}
	public void setOnHookSmsRecord(OnHookSmsRecord onHookSmsRecord) {
		this.onHookSmsRecord = onHookSmsRecord;
	}
	public OnHookSmsRecordService getOnHookSmsRecordService() {
		return onHookSmsRecordService;
	}
	public void setOnHookSmsRecordService(
			OnHookSmsRecordService onHookSmsRecordService) {
		this.onHookSmsRecordService = onHookSmsRecordService;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getTargetNumber() {
		return targetNumber;
	}
	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChargeInfo() {
		return chargeInfo;
	}
	public void setChargeInfo(String chargeInfo) {
		this.chargeInfo = chargeInfo;
	}



	public String getPlist() {
		return plist;
	}


	public void setPlist(String plist) {
		this.plist = plist;
	}

	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public CdrCall getCdrCall() {
		return cdrCall;
	}


	public void setCdrCall(CdrCall cdrCall) {
		this.cdrCall = cdrCall;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
	
    
}