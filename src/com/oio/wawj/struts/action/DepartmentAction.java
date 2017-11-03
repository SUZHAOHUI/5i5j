package com.oio.wawj.struts.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.oio.wawj.bean.DeptInfo;
import com.oio.wawj.service.DepartmentService;
import com.oio.wawj.service.StaffService;
import com.opensymphony.xwork2.ActionContext;


public class DepartmentAction extends BaseAction {

	private DepartmentService service;
	public DepartmentService getService() {
		return service;
	}
	public void setService(DepartmentService service) {
		this.service = service;
	}
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
		public String info(){
			
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);		
			ServletInputStream inputStrem=null;	
			String batchNum=null;
			JSONObject resqJo=new JSONObject();
			try {
				request.setAttribute("Content-Type", "application/json");
				request.setCharacterEncoding("UTF8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			    String resContent=sb.toString();
			    System.out.println("department --info---------"+sb.toString());	
			    JSONObject resjo= JSONObject.fromObject(resContent);
			        batchNum=resjo.getString("batchNum");
			    String transferId =resjo.getString("transferId");
			    String pushDataType = resjo.getString("pushDataType");
			    String transferDateTime= resjo.getString("transferDateTime");
			    
			    String batchLines=resjo.getString("batchLines");
			    JSONArray ja=JSONArray.fromObject(batchLines);
			    for(int i=0;i<ja.size();i++){
			       JSONObject batchJo = (JSONObject) ja.get(i);
			       String setId= batchJo.getString("setId");
			       String deptId=batchJo.getString("deptId");
			       String effdt=batchJo.getString("effdt");
			       String effStatus=batchJo.getString("effStatus");
			       String descr=batchJo.getString("descr");
			       String cDeptType=batchJo.getString("cDeptType");
			       String cDeptStype=batchJo.getString("cDeptStype");
			       String company=batchJo.getString("company");
			       String managerId=batchJo.getString("managerId");
			       String managerPosn=batchJo.getString("managerPosn");
			       String cDeptIdType=batchJo.getString("cDeptIdType");
			       String descr4= batchJo.getString("descr4");
			       service.saveDeptInfo(batchNum, transferId, transferDateTime,
			    		   pushDataType, setId, deptId, effdt, effStatus, descr, 
			    		   cDeptType,cDeptStype, company, managerId, managerPosn, cDeptIdType, descr4); 
			        if(i%20 == 19){
			    	   service.flush();
			        }
			    }   
			    //成功响应
			    response.setContentType("application/json;charset=utf-8");      
			    
			    resqJo.put("success", "S");
			    resqJo.put("msg", "ESB部门信息接口同步成功");
			    resqJo.put("reason", "");
			    resqJo.put("code", "000000");
			    resqJo.put("batchNum",batchNum);
			    resqJo.put("data", "");
			    result=resqJo;
			}catch(Exception e){
			
				e.printStackTrace();
			}
			    		    
			 
				return SUCCESS;
		}
		public String tree(){	
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
			ServletInputStream inputStrem=null;
			String   batchNum=null;
			 JSONObject resqJo=new JSONObject();
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
				    JSONObject resJo=JSONObject.fromObject(sb.toString());
			        batchNum= resJo.getString("batchNum");
				    System.out.println("tree---------"+sb.toString()+"batchNum------"+batchNum);
				   
				    String transferId =resJo.getString("transferId");
				    String pushDataType = resJo.getString("pushDataType");
				    String transferDateTime= resJo.getString("transferDateTime");			    
				    String batchLines=resJo.getString("batchLines");
				    JSONArray ja=JSONArray.fromObject(batchLines);
				    for(int i=0;i<ja.size();i++){
				       JSONObject batchJo = (JSONObject) ja.get(i);
				       String treeName= batchJo.getString("treeName");
				       String setId=batchJo.getString("setId");
				       String effdt=batchJo.getString("effdt");
				       String treeNode=batchJo.getString("treeNode");
				       String parentNodeName=batchJo.getString("parentNodeName");
				       String treeLevelNum=batchJo.getString("treeLevelNum");

				       service.saveDeptTree(batchNum, transferId, transferDateTime,
				    		   pushDataType, treeName, effdt, setId, treeNode, parentNodeName, treeLevelNum); 
				       if(i%20 == 19){
				    	   service.flush();
				        }
				    } 
				   
				    resqJo.put("success", "S");
				    resqJo.put("msg", "ESB部门树接口同步成功");
				    resqJo.put("reason", "");
				    resqJo.put("code", "000000");
				    resqJo.put("batchNum", batchNum);
				    resqJo.put("data", "");
				  
					responseReader.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					    e.printStackTrace();
				} 	
			  result=resqJo;
			return SUCCESS;
		}
		public String rela(){
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			ServletInputStream inputStrem=null;
			String   batchNum=null;
			JSONObject resqJo=new JSONObject();
			HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
			try {
				 inputStrem = request.getInputStream();
			    // 响应时处理数据   
						StringBuffer sb = new StringBuffer();   
						String readLine;   
						BufferedReader responseReader;   
			    responseReader = new BufferedReader(new InputStreamReader(inputStrem, "utf8"));
			    while ((readLine = responseReader.readLine()) != null) {   
				     sb.append(readLine).append("\n");   
				   } 
			    response.setContentType("application/json;charset=utf-8");      
			    JSONObject resJo=JSONObject.fromObject(sb.toString());
			       batchNum= resJo.getString("batchNum");
			    System.out.println("rela---------"+sb.toString()+"batchNum-----------"+batchNum);
			    String transferId =resJo.getString("transferId");
			    String pushDataType = resJo.getString("pushDataType");
			    String transferDateTime= resJo.getString("transferDateTime");			    
			    String batchLines=resJo.getString("batchLines");
			    JSONArray ja=JSONArray.fromObject(batchLines);
			    for(int i=0;i<ja.size();i++){
			       JSONObject batchJo = (JSONObject) ja.get(i);
			     
			       String setId=batchJo.getString("setId");
			       String cValGather=batchJo.getString("cValGather");
			       String cFatherVal=batchJo.getString("cFatherVal");
			       String cFatherDescr=batchJo.getString("cFatherDescr");
			       String cSonVal=batchJo.getString("cSonVal");
			       String cSonDescr=batchJo.getString("cSonDescr");
			       String cDeptidType=batchJo.getString("cDeptidType");
			       String descr4=batchJo.getString("descr4");
			       service.saveDeptRela(batchNum, transferId, transferDateTime, pushDataType, setId,
			    		   cValGather, cFatherVal, cFatherDescr, cSonVal, cSonDescr, cDeptidType, descr4); 
			       if(i%20 == 19){
			    	   service.flush();
			        }
			    }
			    
				    resqJo.put("success", "S");
				    resqJo.put("msg", "ESB级联关系接口同步成功");
				    resqJo.put("reason", "");
				    resqJo.put("code", "000000");
				    resqJo.put("batchNum", batchNum);
				    resqJo.put("data", "");
				    
					responseReader.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					    e.printStackTrace();
				} 
			  result=resqJo;
			 return SUCCESS;
		}
		
	  
}
