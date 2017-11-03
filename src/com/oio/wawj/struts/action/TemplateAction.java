package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Template;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.TemplateService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.OVLoadProperties;
import com.oio.wawj.util.SecurityHelper;
import com.opensymphony.xwork2.ActionContext;




/**
 * 
 */
public class TemplateAction extends BaseAction{

	private Template template ;
	
	private TemplateService templateService;
	
	private short templateId;
	private String name;
	private String content;
	private String status;
	private String auditfailedreason;
	private String type;
	private Integer operator;
	private String plist;
	private String result;
	
	
	/**
	 * Displays the notification record scenario drop-down boxes
	 */
	
	public String getfindNotificationScenario() {
		plist=templateService.findNotificationScenario();
		setResult(plist);
		return SUCCESS;
	}
	
	
	
	/**
	 * 
	 */
/*	public String getfindTemplate() {
		plist = templateService.findTemplate();
		result = plist;
		return "success";
	}*/
	
		public String getfindTemplate() {
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			String currentPage1=request.getParameter("currentPage");
			
			String fp = String.valueOf(AboutOperator.getUser().getFunctionPermission());
			String setId="";
			
/*			if(!fp.equals("1")){
				 setId=AboutOperator.getUser().getSetId();
			}
			*/
			
			Map<String, String> param = new HashMap<String, String>();

			if(currentPage1 != null && !currentPage1.equals("") ){
				currentPage=Integer.valueOf(currentPage1);
			}
			
			if( setId != null && !setId.equals("") ){
				param.put("setId", setId );			
			}
			
			pageListData = templateService.findTemplateAll(param,currentPage, pageSize);
			
			JSONObject pageviewjson = pageListData.parseJSON(0,"templateId","name","content","status","type");	
			setResult(pageviewjson.toString());
			return "success";
	}
	
	
	
	public String getfindTemplateName() {
		plist = templateService.findTemplateName();
		result = plist;
		return "success";
	}
	
	
	/**
	 * 
	 */
	public String add() {

		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String myJson= request.getParameter("jsonData");
		
		JSONObject jo=JSONObject.fromObject(myJson);
		
		String name=jo.getString("name");
		String content=jo.getString("content");

		System.out.println(name+"----------name");
        System.out.println(content+"----------content");
		Template template = new Template();

		template.setName(name);
		template.setContent(content);
		template.setStatus("c");
		template.setType("s");
		
		template.setOperator(Integer.valueOf(String.valueOf(AboutOperator.getOperatorId())));
		
		
		templateService.save(template);
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		result = "success";
		return "success";
	}
	
	/**
	 */
	public String delete() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String Id=request.getParameter("templateId");
//		userService.deleteUserById(Long.parseLong(id));
		templateService.deleteById((short) Integer.parseInt(Id));
		setOperationLogResult("result:'" + getText("operatelog.role.delete.success") +"', reason:''");
		result="success";
		return "success";
	}
	
	/**
	 * 
	 */
	public String update() {

		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String myJson= request.getParameter("jsonData");
		
		JSONObject jo=JSONObject.fromObject(myJson);
		
		String Id=jo.getString("templateId");
		String name=jo.getString("name");
		String content=jo.getString("content");

		System.out.println(name+"----------name");
        System.out.println(content+"----------content");
       
        
	    Template template =  templateService.findById(Short.valueOf(Id));
		
		template.setName(name);
		template.setContent(content);
		
		
		templateService.update(template);
		setOperationLogResult("result:'" + getText("operatelog.role.update.success") +"', reason:''");
		result="success";
		return "success";
	}
	
	
	
	
	
	
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public TemplateService getTemplateService() {
		return templateService;
	}
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	public short getTemplateId() {
		return templateId;
	}
	public void setTemplateId(short templateId) {
		this.templateId = templateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuditfailedreason() {
		return auditfailedreason;
	}
	public void setAuditfailedreason(String auditfailedreason) {
		this.auditfailedreason = auditfailedreason;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
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

	
	
}
