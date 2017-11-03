package com.oio.wawj.struts.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.Ringtone;
import com.oio.wawj.service.ParameterService;
import com.oio.wawj.service.RingtoneService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

public class RingtoneAction extends BaseAction{

	
	private Ringtone ringtone ;
	
	private RingtoneService ringtoneService;
	
	private ParameterService parameterService;
	
	private short id;
	private String code;
	private String name;
	private String savepath;
	private String state;
	private Date stateDate;
	private Integer operator;
	private String setId;
	private String plist;
	private String result;
	
	private String custName;
	private String fileFileName;
    private File bgm;
    private String clName;
    


	public String getClName() {
		return clName;
	}


	public void setClName(String clName) {
		this.clName = clName;
	}


	public String getfileFileName() {
		return fileFileName;
	}


	public void setfileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public File getBgm() {
		return bgm;
	}


	public void setBgm(File bgm) {
		this.bgm = bgm;
	}


	/**系统管理-彩铃管理查看
	 * 
	 */
	public String getfindRingtone() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String currentPage1=request.getParameter("currentPage");
		
		String fp = String.valueOf(AboutOperator.getUser().getFunctionPermission());
		String setId="";
		
		if(!fp.equals("1")){
			 setId=AboutOperator.getUser().getSetId();
		}
		
		
		Map<String, String> param = new HashMap<String, String>();

		if(currentPage1 != null && !currentPage1.equals("") ){
			currentPage=Integer.valueOf(currentPage1);
		}
		
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}
		
		pageListData = ringtoneService.findRingtoneAll(param,currentPage, pageSize);
		
		JSONObject pageviewjson = pageListData.parseJSON(0,"id","name","state","url","descrShort");	
		setResult(pageviewjson.toString());
		return "success";
	}
	/**
	 * callIn ringtone A drop-down box
	 * @return
	 */
	public String callInfindRingtone() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String fp = String.valueOf(AboutOperator.getUser().getFunctionPermission());
		String setId="";
		
		if(!fp.equals("1")){
			setId=AboutOperator.getUser().getSetId();
		}
		
		
		Map<String, String> param = new HashMap<String, String>();
		
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}
		
		result = ringtoneService.callInfindRingtone(param);

		return "success";
	}
	
	
	/**
	 * 上传彩铃
	 */

/*	public void uploadRingtone(){
		 ActionContext ct = ActionContext.getContext();
		 HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		 
		 System.out.println("-----------"+clName);
		// String name= file.getName();
        
         System.out.println(bgm.getName()+"------------------");
	     saveFile(name,bgm,clName); 

	}*/
	
	
	/**系统管理-彩信管理提交/上传
	 * 
	 */
	public String uploadRingtone() {

		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String form = request.getParameter("form");
		
		JSONObject jo = JSONObject.fromObject(form);
		
		String setId=String.valueOf(jo.get("city"));
	    if(setId.equals("")||setId==null){
	    	result="{\"result\":\"failed\",\"cause\":\"上传彩铃失败，请选择您的所属公司哦\"}";
	    	return "success";
	    }
		String name=String.valueOf(jo.get("name"));
	    if(name.equals("")||name==null){
	    	result="{\"result\":\"failed\",\"cause\":\"上传彩铃失败，请填写您的彩铃名称哦\"}";
	    	return "success";
	    }

			
        String loadPath = null;
        String directory = "/mp3";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory); 
		System.out.println(targetDirectory);
		
		String str = targetDirectory+"/"+name+".wav";
		
		Ringtone r = new Ringtone();
		String str2= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/mp3/"+name+".wav";		

		r.setSavepath(str2);	
		r.setName(name);
		r.setCode(name);
		r.setState("V");
		r.setStateDate(DateTime.getCurrentDateTime());
		r.setOperator(Integer.parseInt(String.valueOf(AboutOperator.getOperatorId())));
		r.setSetId(setId);
		ringtoneService.save(r);
        
        File target = new File(str);

        if (target.exists()) {
            target.delete();
        }
        bgm.renameTo(target);
        loadPath = targetDirectory;
        
        result="{\"result\":\"success\",\"cause\":\"上传彩铃成功，刷新状态即可查看哦\"}";
        return "success";
		
	}
	
	
  /**
   * 下载彩铃
   * @return
   */

	public String getfindRingtoneUrl() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		
		  
		
          String  duration=jo.getString("duration"); 
          short Id=Short.valueOf(jo.getString("Id")); 
		
          result = ringtoneService.findRingtoneUrl(Id);
          
		return "success";
	}
	
	/**
	 * delete ringtone
	 * @return
	 */
	public String deleteRingtone() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String Id=request.getParameter("id");
		
		ringtoneService.deleteById(Short.valueOf(Id));
		setResult("success");
		return "success";
	}


	

	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
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





	public Ringtone getRingtone() {
		return ringtone;
	}





	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}





	public RingtoneService getRingtoneService() {
		return ringtoneService;
	}





	public void setRingtoneService(RingtoneService ringtoneService) {
		this.ringtoneService = ringtoneService;
	}




	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public ParameterService getParameterService() {
		return parameterService;
	}


	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}


	public String getSetId() {
		return setId;
	}


	public void setSetId(String setId) {
		this.setId = setId;
	}


	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	
}
