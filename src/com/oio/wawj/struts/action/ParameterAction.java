package com.oio.wawj.struts.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.bean.TemplateOrgRela;
import com.oio.wawj.service.ChannelService;
import com.oio.wawj.service.ParameterOrgRelaService;
import com.oio.wawj.service.ParameterService;
import com.oio.wawj.service.RingtoneService;
import com.oio.wawj.service.TemplateOrgRelaService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

public class ParameterAction extends BaseAction {

	private Parameter parameters ;
	
	private ParameterService parameterService;
	private ParameterOrgRelaService parameterOrgRelaService;
	private TemplateOrgRelaService templateOrgRelaService;
	private RingtoneService ringtoneService;
	private ChannelService channelService;
	
	private Integer parameterId;
	private String section;
	private String item;
	private String value;
	private String parameter;
	private short sectionId;
	private short itemId;
	private String plist;
	private String result;
	
	


	public String getResult() {
		return result;
	}




	public void setResult(String result) {
		this.result = result;
	}




	/**查询呼入设置号显
	 * 
	 */
	public String getCallInNumber() {
		plist = parameterService.findCallInNumber();
		result = plist;
		return "success";
	}
	
	


	/**
	 * 保存呼入设置号显
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addCallInNumber() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
			  
			   String id = key.substring(2);
			  
	    	   Parameter p11 = parameterService.findById(Integer.parseInt(id));
	    	   p11.setValue(json.getString(key));
	    	   parameterService.update(p11);
			
			
			}

		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}

	
	
	/**查询呼入设置彩铃
	 * 
	 */
//	public String getCallInMusic() {
//		
//		
//		
//		plist = parameterService.findCallInMusic();
//		//plist =ringtoneService.findRingtone();
//		System.out.println(plist+"----------");
//		result = plist;
//		return "success";
//	}
	
	/**查询呼入设置彩铃
	 * 
	 */
	public String getCallInMusic() {
	
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String setId= request.getParameter("setId");
		String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
		
		JSONObject jo = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>();
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}
		String s1 = ringtoneService.findList(param);
		String s2 = null;		
        if(fp.equals("1")){
        	s2 = ringtoneService.findChannelRingtone();	
        }
		
		jo.put("parameterRing", s1);
		jo.put("channelRing", s2);
		
		result = jo.toString();
		return SUCCESS;
	}


	/**
	 * 保存呼入设置彩铃
	 * @return 
	 */
	public String addCallInMusic() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);

		JSONArray s1=JSONArray.fromObject(json.get("parameterRing"));
		for (int i = 0; i < s1.size(); i++) {
			JSONObject s2=JSONObject.fromObject(s1.get(i)) ;
			
			ParameterOrgRela ParameterOrgRela1 = parameterOrgRelaService.findPORById(Integer.valueOf(String.valueOf(json.get("setId"))) ,Integer.valueOf(String.valueOf(s2.get("parameterId"))));
			if(ParameterOrgRela1==null){
				System.out.println("null ------ null");
				ParameterOrgRela1=new ParameterOrgRela();
				
				ParameterOrgRela1.setValueId(Short.valueOf(String.valueOf(s2.get("rid"))));
				ParameterOrgRela1.setOrgId(Integer.valueOf(String.valueOf(json.get("setId"))));
				ParameterOrgRela1.setParameterId(Integer.valueOf(String.valueOf(s2.get("parameterId"))));
				ParameterOrgRela1.setStateDate(DateTime.getCurrentDateTime());
				ParameterOrgRela1.setState("V");
				ParameterOrgRela1.setOperator(Integer.valueOf(String.valueOf(AboutOperator.getOperatorId())));
				parameterOrgRelaService.save(ParameterOrgRela1);
			}else{
			
			ParameterOrgRela1.setValueId(Short.valueOf(String.valueOf(s2.get("rid"))));
			ParameterOrgRela1.setOrgId(Integer.valueOf(String.valueOf(json.get("setId"))));
			ParameterOrgRela1.setParameterId(Integer.valueOf(String.valueOf(s2.get("parameterId"))));
			ParameterOrgRela1.setStateDate(DateTime.getCurrentDateTime());
			ParameterOrgRela1.setOperator(Integer.valueOf(String.valueOf(AboutOperator.getOperatorId())));
			parameterOrgRelaService.update(ParameterOrgRela1);
			}
		}
		
		
		if(json.get("channelRing")!=null){
		    JSONArray s11=JSONArray.fromObject(json.get("channelRing"));
			for (int i = 0; i < s11.size(); i++) {
				JSONObject s22=JSONObject.fromObject(s11.get(i)) ;
				
				ChannelId cId=new ChannelId();
				cId.setChannelId((Integer) s22.get("channelId"));
				System.out.println("cId++++++------"+cId.getChannelId());
				Channel channel1 = channelService.findById(cId);
				
				System.out.println(s22.get("rid")+"---------rid");
		
				channel1.setRingId( Integer.valueOf(String.valueOf(s22.get("rid"))) );
				channelService.update(channel1);
			
		}
	  }
		result = "success";
		return "success";
	}
	
	/**查询呼入挂机短信
	 *
	 */
/*	public String getCallInMessage() {
		plist = parameterService.findCallInMessage();
		result = plist;
		System.out.println("haha--------"+plist);
		return "success";
	}*/
	
		public String getCallInMessage() {
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			String setId= request.getParameter("setId");
			String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
		
			Map<String, String> param = new HashMap<String, String>();
			if( setId != null && !setId.equals("") ){
				param.put("setId", setId );			
			}
			
			result = parameterService.findTemplateTORList(param);	

			return SUCCESS;
	}
	
	/**
	 * 保存呼入挂机短信
	 * @return 
	 */
/*	public String addCallInMessage() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
		
			   String id = key.substring(2);
			  
	    	   Parameter p3 = parameterService.findById(Integer.parseInt(id));
	    	   p3.setValue(json.getString(key));
	    	   parameterService.update(p3);
			
			
			}
		result = "success";
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}*/
		
	public String addCallInMessage() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");		
		JSONObject json=JSONObject.fromObject(myJson);
		String setId = String.valueOf(json.get("setId"));
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
			   String id = key.substring(2);
			   if(!key.equals("setId")){
					TemplateOrgRela templateOrgRela1 = parameterOrgRelaService.findTORById(Integer.valueOf(setId),Integer.valueOf(id));
					if(templateOrgRela1==null){
						System.out.println("null ------ null");
						templateOrgRela1=new TemplateOrgRela();
						
						
						if(json.getString(key).equals("")||json.getString(key)==null){
							templateOrgRela1.setValueId((short)2);
						}else{
							templateOrgRela1.setValueId(Short.valueOf(json.getString(key)));
						}	
						
						templateOrgRela1.setOrgId(Integer.valueOf(setId));
						templateOrgRela1.setParameterId(Integer.valueOf(id));
						templateOrgRela1.setStateDate(DateTime.getCurrentDateTime());
						templateOrgRela1.setState("V");
						templateOrgRela1.setOperator(Integer.valueOf(String.valueOf(AboutOperator.getOperatorId())));
						templateOrgRelaService.save(templateOrgRela1);
					}else{
						
						if(json.getString(key).equals("")||json.getString(key)==null){
							templateOrgRela1.setValueId((short)2);
						}else{
							templateOrgRela1.setValueId(Short.valueOf(json.getString(key)));
						}	
						templateOrgRela1.setOrgId(Integer.valueOf(setId));
						templateOrgRela1.setParameterId(Integer.valueOf(id));
						templateOrgRela1.setStateDate(DateTime.getCurrentDateTime());
						templateOrgRela1.setOperator(Integer.valueOf(String.valueOf(AboutOperator.getOperatorId())));					
				     	templateOrgRelaService.update(templateOrgRela1);
					}
			   
			   }

			}
		result = "success";
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}
	
	
	
	/**查询外呼设置号显
	 * 
	 */
	public String getCallOutNumber() {
		plist = parameterService.findCallOutNumber();
		result = plist;
		return "success";
	}
	
	/**
	 * 保存外呼设置号显
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addCallOutNumber() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
		
			   String id = key.substring(2);
			  
	    	   Parameter p3 = parameterService.findById(Integer.parseInt(id));
	    	   p3.setValue(json.getString(key));
	    	   parameterService.update(p3);
			
			
			}

		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}
	
	
	
	
	
	
	/**查询外呼设置彩铃
	 *
	 */
	public String getCallOutMusic() {
		plist = parameterService.findCallOutMusic();
		result = plist;
		return "success";
	}
	
	/**
	 * 保存外呼设置彩铃
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addCallOutMusic() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
		
			   String id = key.substring(2);
			  
	    	   Parameter p3 = parameterService.findById(Integer.parseInt(id));
	    	   p3.setValue(json.getString(key));
	    	   parameterService.update(p3);
			
			
			}

		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}
	
	/**查询外呼挂机短信
	 *
	 */
	public String getCallOutMessage() {
		plist = parameterService.findCallOutMessage();
		result = plist;
		return "success";
	}
	
	/**
	 * 保存外呼挂机短信
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addCallOutMessage() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
		
			   String id = key.substring(2);
			  
	    	   Parameter p3 = parameterService.findById(Integer.parseInt(id));
	    	   p3.setValue(json.getString(key));
	    	   parameterService.update(p3);
			
			
			}

		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}
	
	
	
	

	/**查询外呼绑定设置
	 *
	 */
	public String getCallOutBinding() {
		plist = parameterService.findCallOutBinding();
		result = plist;
		return "success";
	}
	
	/**
	 * 保存外呼绑定设置
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addCallOutBinding() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		
		String myJson= request.getParameter("jsonData");
		
		JSONObject json=JSONObject.fromObject(myJson);
		
		Iterator<String> iterator = json.keys();
		while (iterator.hasNext()) {
			
			   String key = iterator.next();	
			  
			   String id = key.substring(2);
			  
	    	   Parameter p3 = parameterService.findById(Integer.parseInt(id));
	    	   p3.setValue(json.getString(key));
	    	   parameterService.update(p3);
			
			
			}

		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
	}
	
	
	
	
	public Parameter getParameters() {
		return parameters;
	}
	public void setParameters(Parameter parameters) {
		this.parameters = parameters;
	}
	public ParameterService getParameterService() {
		return parameterService;
	}
	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
	public Integer getParameterId() {
		return parameterId;
	}
	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public short getSectionId() {
		return sectionId;
	}
	public void setSectionId(short sectionId) {
		this.sectionId = sectionId;
	}
	public short getItemId() {
		return itemId;
	}
	public void setItemId(short itemId) {
		this.itemId = itemId;
	}
	public String getPlist() {
		return plist;
	}
	public void setPlist(String plist) {
		this.plist = plist;
	}

	public RingtoneService getRingtoneService() {
		return ringtoneService;
	}


	public void setRingtoneService(RingtoneService ringtoneService) {
		this.ringtoneService = ringtoneService;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}


	public ParameterOrgRelaService getParameterOrgRelaService() {
		return parameterOrgRelaService;
	}


	public void setParameterOrgRelaService(
			ParameterOrgRelaService parameterOrgRelaService) {
		this.parameterOrgRelaService = parameterOrgRelaService;
	}

	public TemplateOrgRelaService getTemplateOrgRelaService() {
		return templateOrgRelaService;
	}

	public void setTemplateOrgRelaService(
			TemplateOrgRelaService templateOrgRelaService) {
		this.templateOrgRelaService = templateOrgRelaService;
	}

	
}
