
package com.oio.wawj.struts.action;



import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.OrgId;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.service.OrgService;
import com.oio.wawj.service.StaffService;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 
 * @author 
 * @lastmodify 
 */
public class OrgAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrgService service;
	private StaffService staffService;
	private String result;
    
	private File orgFile;

	public File getOrgFile() {
		return orgFile;
	}
	public void setOrgFile(File orgFile) {
		this.orgFile = orgFile;
	}
	public String orgTreeInit() {
		
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");

		List<Org> orgs = service.findAllBySetId();
		
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		System.out.println("start------"+DateTime.getCurrentDateTime());
		for(Org o:orgs){
			Org pOrg = o;
			Integer number=service.getStaffNumberByOrgId(pOrg.getId().getOrgId(),pOrg.getId().getSetId(),pOrg.getParentOrgId());
			jo.put("name",pOrg.getOrgName()+"("+number+"人"+")");
			jo.put("value",pOrg.getId().getOrgId()+pOrg.getId().getSetId());
			jo.put("setId",pOrg.getId().getSetId());
			ja.add(jo); 
		}
		System.out.println("end-----"+DateTime.getCurrentDateTime());
		  JSONArray jsonObject = JSONArray.fromObject(ja);
		  String orgTree = jsonObject.toString();
		
		  setResult(orgTree);
		return SUCCESS;
	}
	public String test(){
		System.out.println("start------"+DateTime.getCurrentDateTime());
		List<Org> orgs = service.findAllBySetId();
		System.out.println("end------"+DateTime.getCurrentDateTime());
		return SUCCESS;
	}
	public String getChildNodesByPId(){
	
		HttpServletRequest request= ServletActionContext.getRequest();
		Integer number;
		String jStr=request.getParameter("jsonData");
		JSONObject json=JSONObject.fromObject(jStr);
		String orgParentId = json.getString("id").substring(0, 10);
		
		String setId = json.getString("setId");
		List<Org> orgs=service.findOrgChildNodesById(Integer.valueOf(orgParentId),setId);	    
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		for(Org o:orgs){	           
		    number=service.getStaffNumberByOrgId((int)o.getId().getOrgId(),setId,o.getParentOrgId());	    
			jo.put("name", o.getOrgName()+"("+number+"人"+")");
			jo.put("value",o.getId().getOrgId()+o.getId().getSetId());
			jo.put("setId",o.getId().getSetId());
			ja.add(jo);
		}  	 
		  String orgTree = ja.toString();
		  setResult(orgTree);
		  return SUCCESS;		
	}

	public String add() {
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			//锟斤拷取页锟斤拷锟斤拷锟斤拷

			String jStr=request.getParameter("jsonData"); 
			JSONObject jo = JSONObject.fromObject(jStr);
		
			String content=jo.getString("content");
//			try {
//				content = new String(content.getBytes("iso8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			String Parentnode= jo.getString("ParentNode").substring(0, 10);
			Long parentId=Long.valueOf(Parentnode);
				
			System.out.println(parentId);		
			Org sysOrg = new Org();
			OrgId orgId= new OrgId();
			String m=String.valueOf(Math.random()*10);	
			String math=m.substring(2,7);
			String head="10100";
			  math=head+math;
			Integer oId = Integer.valueOf(math);
			orgId.setOrgId(oId);
			String setId=jo.getString("ParentNode").substring(10, 15); 
			orgId.setSetId(setId);
			//锟斤拷取锟斤拷织锟斤拷
			sysOrg.setId(orgId);
			sysOrg.setParentOrgId(parentId);
			sysOrg.setOrgName(content);
			//锟斤拷取状态
			sysOrg.setStatus("V");
			//锟斤拷取时锟斤拷
			sysOrg.setStatusDate(DateTime.getCurrentDateTime());
			sysOrg.setOperator((long)AboutOperator.getOperatorId());
			//锟斤拷拥姆锟斤拷锟�
			service.save(sysOrg);			
			return "success";
		
	}
	/**
	 * 根据orgId找到org 进行重命名
	 * @return success
	 */
	public String rename(){
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String jStr=request.getParameter("jsonData"); 
		JSONObject jo = JSONObject.fromObject(jStr);
	
		String orgName=jo.getString("content");
//		try {
//			orgName = new String(orgName.getBytes("iso8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String Parentnode= jo.getString("ParentNode");
		//Integer orgId=Integer.valueOf(Parentnode);

		Org o=service.findById(Parentnode);
		o.setOrgName(orgName);
		o.setStatusDate(DateTime.getCurrentDateTime());
		o.setOperator((long)AboutOperator.getOperatorId());	
		service.update(o);
		
		return SUCCESS;
	}
    
	public String transferOrg(){
	   
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String jstr = request.getParameter("jsonData");
		System.out.println(jstr);
		JSONObject jo=JSONObject.fromObject(jstr);
		
		String orgid= jo.getString("oNode");
		String tranOrgid= jo.getString("nNode").substring(0, 10);
		
		Integer tranOrgId = Integer.valueOf(tranOrgid);
	
		Org o=service.findById(orgid);
		System.out.println(o.getOrgName());
		o.setParentOrgId((long)tranOrgId);
		service.update(o);
		return SUCCESS;
		
	}
	
	public String queryStaffListByOrgId() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	    String joStr= request.getParameter("jsonData");
	    JSONObject jo=JSONObject.fromObject(joStr);
		String id=jo.getString("id").substring(0, 10);	
        String setId=jo.getString("id").substring(10, 15);
        String currentPage1=jo.getString("currentPage");
		Map<String, String> param = new HashMap<String, String>();
		param.put("duty",null);
		param.put("permission",null);
		param.put("status",null);
		param.put("channel",null);
		param.put("name",null);
		param.put("nameOrNum",null);
		 //职务
		 param.put("orgId",id);
		 param.put("setId",setId);
			param.put("type",AboutOperator.getUser().getType());

			if(currentPage1 != null && !currentPage1.equals("") ){
					System.out.println(currentPage1);
					currentPage=Integer.valueOf(currentPage1);
			}
		 pageListData = staffService.findList(param, currentPage,pageSize);
	     List staffList = pageListData.getDataList();
	     JSONObject json = new JSONObject();
	     JSONObject relas = new JSONObject();
	     JSONArray records = new JSONArray();
	     JSONObject pageviewjson = new JSONObject();
	     pageviewjson.put("currentpage", pageListData.getCurrentPage());
	     pageviewjson.put("totalrecord",  pageListData.getTotalcount());
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
		    		 Acms ac = staffService.getAcmsByacms(xnum);
		    		 String state = ac.getState();
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
        System.out.println(result);
	return SUCCESS;
}
	

	/**
	 * 删除Org
	 * @return success
	 */
	public String delete() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		//获取orgID
		String orgId=request.getParameter("id");
		System.out.println(orgId);
		//根据orgId获取org  将状态置为I
		Org o=service.findById(orgId);
		o.setStatus("I");
		o.setStatusDate(DateTime.getCurrentDateTime());
		service.update(o);
	
		return "success";
	}

	/**
	 * 清除Org
	 * @return success
	 */
	public String clear() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		//根据orgId获取org  将状态置为I
		
		List<Org> oList=service.findAll();
		for(Org o:oList)
		{
			o.setStatus("I");
			o.setStatusDate(DateTime.getCurrentDateTime());
			service.update(o);
		}
		return "success";
	}
	
	/**
	 * 查询org列表
	 * @return query
	 */
	public String queryList() {
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			
			String orgName = request.getParameter("orgName");
			String status = request.getParameter("status");
			
			Map<String, String> param = new HashMap<String, String>();
			
			if (orgName != null && !"".equals(orgName) && !orgName.equals("undefined")) {
//				 try {
//					 orgName = new String(orgName.getBytes("iso8859-1"),"UTF-8");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
				 //锟斤拷锟斤拷
				param.put("orgName like ", "%" + orgName + "%");
			}
			if (!"".equals(status) &&  null !=status && !status.equals("undefined")) {
				//锟斤拷锟斤拷 
				param.put("status like ", "%" + status + "%");
			}
			
			pageListData = service.findList(param, 1, 5);
			
			JSONObject pageviewjson = pageListData.parseJSON(Org.class,"orgName", "status","orgId","statusDate");

			result = pageviewjson.toString();
		
		System.out.print(result);

		return "success";
	}
	
	/**
	 * 导入excel文件
	 */
	@SuppressWarnings("unused")
	public String importExcel(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String excelFilename= orgFile.getName();
		System.out.println("-------------"+excelFilename);
		excelFilename = "orgList.xlsx";
		String directory = "/upload";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory); 
		
		try {
			service.importExcel(AboutOperator.getUser(), targetDirectory,excelFilename,orgFile);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "query";
	}
	//点击选择时，弹出部门框的查询
	public String orgTreeLoad() {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
        System.out.println("---------org--------");
		Long orgId=AboutOperator.getUser().getOrgId();
		//Long orgId=(long)1000000001;
		
		List<Org> orgs = service.findAll(orgId);
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		   for(Org o:orgs){
			Org pOrg = o;
			Integer number=service.getStaffNumberByOrgId(pOrg.getId().getOrgId(),pOrg.getId().getSetId(),pOrg.getParentOrgId());
			jo.put("name",pOrg.getOrgName()+"("+number+"人"+")");
			jo.put("value",pOrg.getId().getOrgId()+pOrg.getId().getSetId());
			jo.put("setId",pOrg.getId().getSetId());
			ja.add(jo); 
		   }
		  JSONArray jsonObject = JSONArray.fromObject(ja);
		  String orgTree = jsonObject.toString();
		  setResult(orgTree);
		return SUCCESS;
	}
	
	public String childNodesLoad(){
	
		HttpServletRequest request= ServletActionContext.getRequest();
		Integer number;
		String jStr=request.getParameter("jsonData");
		JSONObject json=JSONObject.fromObject(jStr);
		String orgParentId = json.getString("id").substring(0, 10);
		
		String setId = json.getString("setId");
		List<Org> orgs=service.findOrgChildNodesById(Integer.valueOf(orgParentId),setId);	    
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		for(Org o:orgs){	           	    
			 number=service.getStaffNumberByOrgId((int)o.getId().getOrgId(),setId,o.getParentOrgId());	    
			jo.put("name", o.getOrgName()+"("+number+"人"+")");
			jo.put("value",o.getId().getOrgId()+o.getId().getSetId());
			jo.put("setId",o.getId().getSetId());
			ja.add(jo);
		}  	 
		  String orgTree = ja.toString();
		  setResult(orgTree);
		  return SUCCESS;		
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return service
	 */
	public OrgService getService() {
		return service;
	}

	/**
	 * set锟斤拷锟斤拷
	 */
	public void setService(OrgService service) {
		this.service = service;
	}
	
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public String getResult() {
		return result;
	}

	/**
	 * set锟斤拷锟斤拷
	 */
	public void setResult(String result) {
		this.result = result;
	}
	


}
