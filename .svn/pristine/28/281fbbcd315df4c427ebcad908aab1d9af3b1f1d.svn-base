
package com.oio.wawj.struts.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.oio.wawj.service.AxbService;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.TransferNumberSet;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.AxbSub;
import com.oio.wawj.util.AxbUnSub;
import com.oio.wawj.util.TransferNumDel;
import com.oio.wawj.util.TransferNumSet;
import com.opensymphony.xwork2.ActionContext;

/**
 * 锟斤拷要锟斤拷锟杰ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷诺陌蠖ê徒锟斤拷锟斤拷锟�
 * 
 * @author
 */
public class AxbAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private AxbService service;
	private String result;
	
	  /**
		 * AXB锟斤拷
		 * 
		 */
    public String axbSub(){
    	ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String anum = request.getParameter("anum");
		String xnum = request.getParameter("xnum");
		String bnum = request.getParameter("bnum");
		AxbSub.axbOrder(anum ,xnum ,bnum);
	
		SubsRela asr= new SubsRela();
		asr.setAnum(anum);
		asr.setBnum(bnum);
		asr.setXnum(xnum);
		String operator = String.valueOf(AboutOperator.getOperatorId());
		asr.setOperator(Integer.valueOf(operator));
		asr.setState("B");
		asr.setStateDate(AboutOperator.getCurrentDate());
			
		return SUCCESS;
    }
    /**
	 * AXB解绑
	 * 
	 */
    public String axbUnSub(){
    	ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String id = request.getParameter("id");
		System.out.println("id---------"+id);
		SubsRela asr= service.findById(Long.valueOf(id));
		String anum = asr.getAnum();
		String xnum = asr.getXnum();
		String bnum = asr.getBnum();
		System.out.println(anum+"------"+xnum);
		AxbUnSub.axbUnsub(anum ,xnum ,bnum);	
		TransferNumDel.transferNumDel(xnum);
		asr.setState("U");
		asr.setStateDate(AboutOperator.getCurrentDate());
		service.update(asr);	
		return SUCCESS;
    } 
    /**
	 * 锟斤拷锟斤拷转锟接猴拷锟斤拷
	 * 
	 */
    public String setTransferNumSet(){
    	ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String xnum = request.getParameter("xnum");
		String tnum = request.getParameter("tnum");
		TransferNumSet.transferNumSet(xnum,tnum );
		TransferNumberSet tfs=new TransferNumberSet();
		tfs.setXnum(xnum);
		tfs.setTransfernum(tnum);
		tfs.setState("S");
		tfs.setStateDate(AboutOperator.getCurrentDate());
			
		return SUCCESS;
    } 
    /**
	 * 设置转接号码
	 * 
	 */
    public String setTransferNumDel(){
    	ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String xnum = request.getParameter("xnum");
		TransferNumDel.transferNumDel(xnum);
		TransferNumberSet tfs=new TransferNumberSet();
		tfs.setXnum(xnum);
		tfs.setState("D");
		tfs.setStateDate(AboutOperator.getCurrentDate());
			
		return SUCCESS;
    } 
	/**
	 * 查询AXB绑定关系
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



	
}
