
package com.oio.wawj.struts.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;


//import com.oio.wawj.bean.Attr;
//import com.oio.wawj.bean.AttrValue;
import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.FunctionService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.PageListData;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��Ҫ���ܣ�Function��ص�action�����ڳ䵱��Function��ص��û������ҵ���߼�������
 * 
 * @author 

 */

@Entity
@SuppressWarnings("rawtypes")
public class FunctionAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String undefind = null;
	@ManyToOne
	private FunctionService functionService;
	@ManyToOne
	private Function Function;
	private Integer functionId;
	private JSONObject jsonObj;
	private String parentFunctionList;
	private String result;
	private String popQuery;
	
	/**
	 * ���Function
	 * @return add
	 */
	/*public String add() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		Object json =  request.getParameterNames().nextElement();
		JSONObject jo = null;
		jo = (JSONObject) JSONSerializer.toJSON(json); 
		
		User user=(User)ActionContext.getContext().getSession().get("user");
		Function sf=new Function();		
		sf.setFunctionName(jo.getString("functionName"));
		sf.setFunctionCode(jo.getString("functionCode"));
		sf.setTarget(jo.getString("target"));
		sf.setUserId(user.getUserId());
		sf.setParentFunctionId(Short.valueOf(jo.getString("parentId")));
		sf.setLevelId(Short.valueOf(jo.getString("levelId")));
		sf.setStatus("V");
		sf.setStatusDate(AboutOperator.getCurrentDate());
		functionService.save(sf,user.getUserId());
		tem.out.println(jo.get("parentId"));            
		  return "success";
	}*/



	
	/**
	 * ��ѯ�б�
	 * @return success
	 * @throws UnsupportedEncodingException 
	 */
	public String queryList() {
		  ActionContext ct = ActionContext.getContext();
		  HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
         
		  String jstr=request.getParameter("jsonData");
		  JSONObject jo = JSONObject.fromObject(jstr);
   
         String  functionName=jo.getString("functionName"); 
         String  currentPage1=jo.getString("currentPage");
//          try {
//        	  functionName = new String(functionName.getBytes("iso8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	    
	      
	       //tring functionCode=request.getParameter("functionCode");
			Map<String, String> param = new HashMap<String, String>();
			
			if( functionName != null && !functionName.equals("") ){
				param.put("functionName", functionName );			
			}
			if(currentPage1 != null && !currentPage1.equals("") ){
				currentPage=Integer.valueOf(currentPage1);
			}
				
		    PageListData pageListData = functionService.findList(param,currentPage,pageSize);		

		    System.out.println(pageListData.getDataList()+"----------------");
/*			if(pageListData.getTotalcount()!=0)
				{		
					JSONObject pageviewjson = pageListData.parseJSON(0,"functionId","functionName", "status","statusDate");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());
				}else{
						  
				setResult("[]");	
			    }*/
		
					JSONObject pageviewjson = pageListData.parseJSON(0,"functionId","functionName", "status","statusDate");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());
	    
			return SUCCESS;
	}
	
	/**
	 * ������Ϣ
	 * @return success
	 */
	/*public String update() {
		
		// ��ȡҳ�� �ϵĿͻ���Ϣ
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		Object json =  request.getParameterNames().nextElement();
		JSONObject jo = null;
		jo = (JSONObject) JSONSerializer.toJSON(json); 
	
		Function temp = functionService.findFunctionById(Short.valueOf(jo.getString("functionId")));
		User user=(User)ActionContext.getContext().getSession().get("user");
		
		temp.setFunctionName(jo.getString("functionName"));
		temp.setFunctionCode(jo.getString("functionCode"));
		
		temp.setTarget(jo.getString("target"));
		temp.setStatusDate(AboutOperator.getCurrentDate());
		temp.setLevelId(Short.valueOf(jo.getString("levelId")));
		temp.setParentFunctionId(Short.valueOf(jo.getString("parentId")));
		temp.setVisible("1");
		temp.setStatus("V");
		
		functionService.update(temp);

		setOperationLogResult("result:'" + getText("operatelog.function.update.success") +"', reason:''");
		return "success";
	}*/
	

	/**
	 * ɾ��Function
	 * @return success
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id=request.getParameter("id");
		List list = functionService.findSubListById(id);
		if(list.size() > 0)
		{
			this.addActionMessage(getText("common.delete.fail") + getText("operatelog.function.delete.fail.reason"));
			setOperationLogResult("result:'" + getText("operatelog.function.delete.fail") +"', reason:'" + getText("operatelog.function.delete.fail.reason") +"'");
			return "success";
		}else{
			functionService.deleteById(Short.parseShort(id));
			setOperationLogResult("result:'" + getText("operatelog.function.delete.success") +"', reason:''");
			return "success";
		}
	}
	
	/**
	 * get����
	 * @return parentFunctionList
	 */
	public String getParentFunctionList() {
		parentFunctionList = functionService.findParentList();
		setResult(parentFunctionList);
		
		return "success";
	}
	
	/**
	 * set����
	 */
	public void setParentFunctionList (String parentFunctionList) {
		this.parentFunctionList = parentFunctionList;
	}
	
	
	/**
	 * get����
	 * @return Function
	 */
	public Function getFunction() {
		return Function;
	}

	/**
	 * set����
	 */
	public void setFunction(Function Function) {
		this.Function = Function;
	}
	
	/**
	 * get����
	 * @return functionId
	 */
	public Integer getFunctionId() {
		return functionId;
	}

	/**
	 * set����
	 */
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	


	
	/**
	 * get����
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * set����
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * get����
	 * @return jsonObj
	 */
	public JSONObject getJsonObj() {
		return jsonObj;
	}

	/**
	 * set����
	 */
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}


}
