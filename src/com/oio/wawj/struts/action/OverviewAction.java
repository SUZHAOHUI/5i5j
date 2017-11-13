
package com.oio.wawj.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.User;

import com.oio.wawj.service.OverviewService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

/**
 * 主要功能：Overview相关的action，用于充当与Overview相关的用户请求和业务逻辑的桥梁
 * 
 * @author 
 */
public class OverviewAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OverviewService service;
	private CdrCall cdrCall;
	private String result;
	public static Logger logger = Logger.getLogger(OverviewAction.class);



	public String overview() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		JSONObject jo=new JSONObject();
		//个人信息
        long orgId = user.getOrgId(); 
       // System.out.println("overview----"+orgId);
        long operatorId = user.getUserId();
        String roleName=service.findRoleNameByUserId(operatorId);
        
        Org org=service.findOrgNameByOrgId((int)orgId);
		String name=user.getName(); 
		String set_id=user.getSetId();
		//String duty=user.getDuty();
		
		
		String orgName=org.getOrgName();
			jo.put("name", name);
			jo.put("duty", roleName);
			jo.put("orgName", orgName);
		 //小号使用
			System.out.println("小号开始时间-----"+DateTime.getCurrentDateTime());
		List list = service.getSecretNoCondition(orgId,operatorId,roleName);
			jo.put("total", list.get(0));
			jo.put("itotal", list.get(1));
			jo.put("vtotal", list.get(2));
		    jo.put("ototal", list.get(3));
		    jo.put("ctotal", list.get(4));
		    System.out.println("小号结束时间-----"+DateTime.getCurrentDateTime());
		 //今日数据
		    System.out.println("开始时间-----"+DateTime.getCurrentDateTime());
		    List cslist = service.getCallStatistic(orgId,operatorId,roleName);  
		    long callInTotal = 0;
		    if(cslist!=null){
		    for(int i=0;i<cslist.size();i++)
			{
			  	 Object[] o=(Object[]) cslist.get(i);
			  	 String purpose=(String) o[0];
			  	 String callType=(String) o[1];
			  	 Long total=(Long) o[2];
			  	 if(purpose!=null&&purpose.equals("C")&&callType.equals("1")){
			  		 jo.put("channelCallIn", total);
			  	 }
			  	 if(purpose!=null&&purpose.equals("O")&&callType.equals("0")){
			  		 jo.put("callOut", total);
			  	 }
				 if(callType.equals("1")){
					 callInTotal=callInTotal+total;
			  		 jo.put("custCallIn", callInTotal);
			  	 }
			}
		    }else{
		    	jo.put("channelCallIn", "0");
		    	jo.put("custCallIn", "0");
		    }
		    result=jo.toString();
		    System.out.println("结束时间-----"+DateTime.getCurrentDateTime());
	    return SUCCESS;

	}

	public String delete() {
		return "success";
	}

	
	public OverviewService getService() {
		return service;
	}

	public void setService(OverviewService service) {
		this.service = service;
	}
	
	public CdrCall getCdrCall() {
		return cdrCall;
	}

	public void setCdrCall(CdrCall cdrCall) {
		this.cdrCall = cdrCall;
	}

	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
