
package com.oio.wawj.struts.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.oio.wawj.service.AxbService;
import com.oio.wawj.service.NumberService;
import com.oio.wawj.service.StaffService;
import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.AxUnSubs;
import com.oio.wawj.util.AxbUnSub;
import com.opensymphony.xwork2.ActionContext;

/**
 * 关于号码ax的绑定和解绑
 * 
 * @author
 */
public class AxAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private AxbService service;
    private StaffService staffService;
	private NumberService nService;
	

	private String result;	
    /**
	 * AX解绑
	 * 
	 */
    public String axUnSub(){
    	ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String id = request.getParameter("id");
		System.out.println("id---------"+id);
		SubsRela asr= service.findById(Long.valueOf(id));
		String anum = asr.getAnum();
		String xnum = asr.getXnum();
		System.out.println(anum+"------"+xnum);
		AxUnSubs.axUnsub(anum ,xnum);	
		asr.setState("U");
		asr.setStateDate(AboutOperator.getCurrentDate());
		service.update(asr);	
		Acms ac = staffService.getAcmsByacms(xnum);
		ac.setState("D");
		ac.setStateDate(AboutOperator.getCurrentDate());
		nService.update(ac);
		
		return SUCCESS;
    } 
   
	/**
	 * 查询AX绑定关系
	 * @return query
	 */
	public String queryList() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		//锟斤拷取页锟斤拷锟窖拷牟锟斤拷锟�
		String json = request.getParameter("jsonData");
		
	    JSONObject jo= JSONObject.fromObject(json);
		Map<String, String> param = new HashMap<String, String>();
		String  number=jo.getString("number");
		String  currentPage1=jo.getString("currentPage");
		param.put("number",number);
		if(currentPage1 != null && !currentPage1.equals("") ){
			System.out.println(currentPage1);
			currentPage=Integer.valueOf(currentPage1);
	   }
		pageListData = service.findList(param, currentPage,
				pageSize);
	
		JSONObject pageviewjson = pageListData.parseJSON("SubsRela,0,id,id",
				"SubsRela,0,xnum,xnum",
				"SubsRela,0,bnum,bnum", "SubsRela,0,anum,anum","User,1,code,code",
				"User,1,name,name");
      //			//传值到前台
		result = pageviewjson.toString();
	
	    //System.out.print(result);

	return SUCCESS;
	}

    
	public AxbService getService() {
		return service;
	}
	public void setService(AxbService service) {
		this.service = service;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public NumberService getnService() {
		return nService;
	}

	public void setnService(NumberService nService) {
		this.nService = nService;
	}

    public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
