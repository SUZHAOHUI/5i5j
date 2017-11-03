
package com.oio.wawj.struts.action;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.RoleFunctionsId;
import com.oio.wawj.dao.RoleDAO;
import com.oio.wawj.service.FunctionService;
import com.oio.wawj.service.RoleFunctionsService;
import com.oio.wawj.service.RoleService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;

/**
 *
 */
public class RoleFunctionAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleFunctionsService service;
	private RoleFunctions RoleFunction;
	private String roleId;
	private RoleDAO roleDao;

	private RoleService RoleService;

	private FunctionService functionService;
	
	private String radiobutton;

	private String status;

	private String roleName;

	private String comments;


	private String result;

	
	/**
	 * get����
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * set����
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * get����
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * set����
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * get����
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * set����
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	/**
	 * get����
	 * @return roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * set����
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	

	/**
	 * ��ѯ�б�
	 * @return success
	 */

	public String queryList(){
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		String roleId = request.getParameter("roleId");
		
		List<RoleFunctions> RoleFunctionList = service.findByRoleId(Short.parseShort(roleId));
	

		JSONArray ja = new JSONArray();
       for (RoleFunctions srf : RoleFunctionList ) {
			JSONObject jo = new JSONObject();
			Function sf=functionService.findFunctionById(srf.getId().getFunctionId());
			if(sf!=null){
			jo.put("id", srf.getId().getFunctionId());
			jo.put("name", sf.getFunctionName());
			ja.add(jo);
			}
		}
	
		setResult(ja.toString());
		System.out.print(result);
		return SUCCESS;
	}
	
	/**
	 * ��ѯ�б�
	 * @return success
	 */

	@SuppressWarnings({ "unchecked" })
	public String queryNoUseFuncList(){
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);

		String roleId = request.getParameter("roleId");
		
		List<RoleFunctions> RoleFunctionList = service.findByRoleId(Short.parseShort(roleId));
	

		JSONArray ja = new JSONArray();
		@SuppressWarnings("rawtypes")
		List<Short> functionIds = new ArrayList<Short>();
       for (RoleFunctions srf : RoleFunctionList ) {		 
    	  short functionId = srf.getId().getFunctionId();
    	  functionIds.add(functionId);
		} 
    
       List<Function> sf=functionService.findNoUseFunctionById(functionIds);

       for (Function sfc : sf ) {	
       JSONObject jo = new JSONObject();
			jo.put("id", sfc.getFunctionId());
			jo.put("name", sfc.getFunctionName());
			ja.add(jo);
			}
		setResult(ja.toString());
		System.out.print(result);
		return SUCCESS;
	}
	/**
	 * ���sysRoleFunction
	 * @return success
	 */
	public String saveRoleFunctions() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		
        String  roleId=jo.getString("roleId"); 
        String  functionIds1=jo.getString("functionIds"); 
        
		String functionIds = functionIds1.substring(functionIds1.indexOf('[')+1, functionIds1.lastIndexOf(']'));
		
		System.out.println(functionIds+"---------------functionIds");
		
/*		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	
		String roleId = request.getParameter("roleId");
		String functionIds = request.getParameter("functionIds");*/
		
		
		List<RoleFunctions> RoleFunctionList = service.findByRoleId(Short.parseShort(roleId));
		for (RoleFunctions srf : RoleFunctionList ){
		       service.delete(srf);
		}
		   String[] id = functionIds.split(",");

		   List<Short> functionId = new ArrayList<Short>();
	    if(functionIds!=null&&!functionIds.equals("")){
		   for (String funcId : id)
		   {	
			  
			   RoleFunctionsId srfId=new RoleFunctionsId();
			   srfId.setRoleId(Short.valueOf(roleId));
			   srfId.setFunctionId(Short.valueOf(funcId));
			   
			   RoleFunctions sRoleFunc=new RoleFunctions();
			   sRoleFunc.setId(srfId);	
			   sRoleFunc.setStatus("V");
			   sRoleFunc.setStatusDate(DateTime.getCurrentDateTime());
			   sRoleFunc.setUserid(Long.valueOf(String.valueOf(AboutOperator.getOperatorId())));
			   service.save(sRoleFunc);
		   }
		  } 
		
        result ="success";
		return "success";
	}



	

	
	
	
	/**
	 * get����
	 * @return RoleFunction
	 */
	public RoleFunctions getRoleFunction() {
		return RoleFunction;
	}

	/**
	 * set����
	 */
	public void setRoleFunction(RoleFunctions RoleFunction) {
		this.RoleFunction = RoleFunction;
	}
	
	/**
	 * get����
	 * @return roleDao
	 */
	public RoleDAO getRoleDao() {
		return roleDao;
	}

	/**
	 * set����
	 */
	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}
	
	/**
	 * get����
	 * @return RoleService
	 */
	public RoleService getRoleService() {
		return RoleService;
	}

	/**
	 * set����
	 */
	public void setRoleService(RoleService RoleService) {
		this.RoleService = RoleService;
	}
	
 

	
	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	
	public RoleFunctionsService getService() {
		return service;
	}

	public void setService(RoleFunctionsService service) {
		this.service = service;
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

}
