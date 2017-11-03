
package com.oio.wawj.struts.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.oio.wawj.service.NumberService;
import com.oio.wawj.service.StaffService;
import com.oio.wawj.service.UserAcmsRelaService;
import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.PageListData;
import com.opensymphony.xwork2.ActionContext;

/**
 * 主要功能：Acms相关的action，用于充当与acms相关的用户请求和业务逻辑的桥梁
 * 
 * @author
 */
public class NumberAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NumberService service;
	private UserAcmsRelaService uarService;
	private File numFile;
	private StaffService staffService;


	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	private String result;	

	/**
	 * 锟斤拷锟紸cms
	 * @return add
	 */
	public String add() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String acms =  request.getParameter("acms");
		Acms ac = new Acms();
		ac.setAcms(acms);
		ac.setStatus("I");
		ac.setStatusDate(AboutOperator.getCurrentDate());
		ac.setOperatorId((int)AboutOperator.getOperatorId());
     	service.save(ac);
		return "success";
	}
		
	/**
	 * 删除Acms
	 * @return successDelete
	 */
	public String delete() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		//前端页面传值
		String acmsList=request.getParameter("acms");
		//根据acms号码将状态置为'I'
	    JSONArray ja=JSONArray.fromObject(acmsList);
	    for(int i=0;i<ja.size();i++){
	      String acms=(String) ja.get(i);
		  List<Acms> acList = service.findAcmsByNo(acms);
		  Acms ac=acList.get(0);
		  service.deleteById(ac.getAcmsId());
	    }

		return "success";
	}
   
	public String  channel(){
		return result;
		
	}
		
	public String queryNumberList() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		System.out.println("----------------"+jstr);
		//String bind = jo.getString("bind");   //绑定
		//String line = jo.getString("trumpet");//上线、下线
		String status = jo.getString("state"); // 使用、未使用
		String channel = jo.getString("channel"); //渠道
		String nameOrNum = jo.getString("nameOrNum"); // 
		String UnameOrNum =jo.getString("UnameOrNum");
		//String purpose = jo.getString("Outbound");   // 锟角凤拷锟斤拷锟�
		String  currentPage1=jo.getString("currentPage");
		if(currentPage1 != null && !currentPage1.equals("") ){
			System.out.println(currentPage1);
			currentPage=Integer.valueOf(currentPage1);
	}
		Map<String, String> param = new HashMap<String, String>();

		//param.put("bind",bind);
		//param.put("line",line);
		if(status!=null&&status.equals("Y"))
			status="V";
		if(status!=null&&status.equals("I"))
			status="I";
		param.put("status",status);
		param.put("channel",channel);
//		try {
//			UnameOrNum = new String(UnameOrNum.getBytes("iso8859-1"),"UTF-8");
//			 } catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		param.put("UnameOrNum",UnameOrNum);
		param.put("nameOrNum",nameOrNum);
		param.put("purpose",null);
		String setId=AboutOperator.getUser().getSetId();
		param.put("setId",setId);
		pageListData = service.findNumberList(param, currentPage,pageSize);
	    
		JSONObject pageviewjson = pageListData.parseJSON(0,"acms","channel_name", "state","code" ,"name","city");
		//锟斤拷值锟斤拷前台
		result = pageviewjson.toString();
	
      	System.out.print(result);

	    return SUCCESS;
}

	public String queryStaffByAcms(){
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		//jsonData={"acms":241341,'currentPage':1}
		String joStr= request.getParameter("jsonData");
		JSONObject jo=JSONObject.fromObject(joStr);
		String acms=jo.getString("acms");
		String currentPage1=jo.getString("currentPage");
		Map<String, String> param = new HashMap<String, String>();
		param.put("acms",acms);
		if(currentPage1 != null && !currentPage1.equals("") ){
			System.out.println(currentPage1);
			currentPage=Integer.valueOf(currentPage1);
	   }
		PageListData ac = service.findStaffByAcms(param, currentPage,pageSize);
		 List staffList = ac.getDataList();
	     JSONObject json = new JSONObject();
	     JSONObject relas = new JSONObject();
	     JSONArray records = new JSONArray();
	     JSONObject pageviewjson = new JSONObject();
	     pageviewjson.put("currentpage", ac.getCurrentPage());
	     pageviewjson.put("totalrecord",  ac.getTotalcount());
	     pageviewjson.put("page_size", pageSize);
	     JSONArray rlist=new JSONArray();
	     for(int i=0;i<staffList.size();i++){
	    	
	    	 Object[] o=(Object[]) staffList.get(i);
	    	 json.put("name", o[0]);
	    	 json.put("code", o[1]);
	    	 User user=null;
		    	
	    	 if(o[1]!=null)
	    		 user=staffService.findUserByCode((String)o[1]);
	    	 else
	    		 user=staffService.findUserByName((String)o[0],(String)o[9]);
	    	 List relaList=null;
	    	 if(user!=null)
	    	     relaList=staffService.getUserAcmsRela(user.getUserId(),"");
	    	//查出此员工的所有绑定关系
	    	 if(relaList!=null)
	    	 for(int j=0;j<relaList.size();j++){
	    		 Object[] ro = (Object[]) relaList.get(j);
	    		 SubsRela sr= (SubsRela) ro[3];
	    		 UserAcmsRela uar= (UserAcmsRela) ro[1];
	    		 String xnum = sr.getXnum();
	    		 String anum=sr.getAnum();
	    		 Integer channelId=uar.getChannelId();
	    		 if(channelId!=null){
	    		     Channel ch= staffService.findChannelById(uar.getChannelId());
	    		     relas.put("channelName", ch.getId().getChannelName());
	    		 }else{
	    			 relas.put("channelName", "外呼");
	    		 }
		    		 relas.put("xnum", xnum);
		    		 relas.put("anum", anum);
		    		 Acms acm = staffService.getAcmsByacms(xnum);
		    		 String state = acm.getState();
		    		 if(state.equals("D")){
		    			 relas.put("upline", "下线"); 
		    		 }else{
		    			 relas.put("upline", "上线");
		    		 }
		    		
		    		 rlist.add(relas);	 
	    	 }
	    	 if(relaList!=null&&relaList.size()==0)
			    	json.put("subrelas", relaList);
			    	else
			        json.put("subrelas", rlist);
		    	json.put("duty", o[2]);
		    	json.put("status", o[3]); 	
		    	json.put("org_name", o[4]);
		    	json.put("data_permission", o[5]);
		    	records.add(json);
                 if(i>=0){
		    		
		    		rlist.clear();
	    		  }
	     }     
		//传值
	     pageviewjson.put("records", records);
	     result = pageviewjson.toString();
         
	return SUCCESS;
		
	}
	
	/**
	 * 导入excel文件
	 */
	@SuppressWarnings("unused")
	public String importExcel() {
		HttpServletRequest request=ServletActionContext.getRequest();
	   
		String excelFile=numFile.getName();		
	
		String excelFilename = "number.xls";
		String directory = "/upload";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory); 
		
		try {
			service.importExcel(AboutOperator.getUser(), targetDirectory, excelFilename,numFile);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 导出excel文件
	 */
	@SuppressWarnings("unchecked")
	public String exportExcel(){
   	 	User su = (User) ActionContext.getContext().getSession().get("user");
   	 	if(su == null){
		    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败，请重新登录！\"}";
   			return "success";
   	 	}
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String jsonData = request.getParameter("jsonData");		     	
		  //jsonData="{'state':'all','channel':'','nameOrNum':'name','UnameOrNum':'','currentPage':1}";
		try {
			JSONObject jsonObj = JSONObject.fromObject(jsonData);
			String status = jsonObj.getString("state"); // 使用、未使用
			String channel = jsonObj.getString("channel"); //渠道
			String nameOrNum = jsonObj.getString("nameOrNum"); 
			String UnameOrNum =jsonObj.getString("UnameOrNum");
			Map<String, String> param = new HashMap<String, String>();
			if(status!=null&&status.equals("Y"))
				status="V";
			if(status!=null&&status.equals("I"))
				status="I";
			param.put("status",status);
			param.put("channel",channel);
			param.put("UnameOrNum",UnameOrNum);
			param.put("nameOrNum",nameOrNum);
			param.put("purpose",null);
			String setId=AboutOperator.getUser().getSetId();
			param.put("setId",setId);
			currentPage = 1;
			List<User> numberList = null;
		    int totalPageCount = 2;
		    numberList = new ArrayList<User>();
			while(currentPage <= totalPageCount){
				pageListData = service.findNumberList(param, currentPage++, 5000);
				if(pageListData.getDataList().size() == 0){
					break;
				}
				totalPageCount = pageListData.getTotalPageCount();
				numberList.addAll(pageListData.getDataList());
			}
			
	        String code=AboutOperator.getUser().getUserId().toString() + "_" + AboutOperator.getUser().getCode();
			String directory = "/downfile";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		    String fileName = service.exportExcel(numberList,targetDirectory,code,status);
		    if(fileName == null){
			    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
		    }else{
		    	String url = request.getContextPath();
			    result="{\"result\":\"success\", \"filepath\":\"" + url + directory + "/" + fileName + "\",\"cause\":\"\"}";
		    }
			numberList = null;
		  } catch (RuntimeException e) {
			e.printStackTrace();
		    result="{\"result\":\"failed\", \"filepath\":\"\",\"cause\":\"导出失败！\"}";
		  }
		return "success";
	}
	public UserAcmsRelaService getUarService() {
		return uarService;
	}

	public void setUarService(UserAcmsRelaService uarService) {
		this.uarService = uarService;
	}

	public NumberService getService() {
		return service;
	}

	public void setService(NumberService service) {
		this.service = service;
	}
  

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public File getNumFile() {
		return numFile;
	}

	public void setNumFile(File numFile) {
		this.numFile = numFile;
	}
}
