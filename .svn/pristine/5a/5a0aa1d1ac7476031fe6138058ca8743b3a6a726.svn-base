package com.oio.wawj.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.bean.UserRoleId;
import com.oio.wawj.service.UserRoleService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.opensymphony.xwork2.ActionContext;




public class UserRoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private UserRoleService UserRoleService;
	private UserRole UserRole;
	
	private long userId;
	private short roleId;
	private String status;
	private Date statusDate;
	private String result;

	/**
	 * 查询列表
	 * @return success
	 */

		public String queryList(){
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			
			String roleId=request.getParameter("roleId");       

			String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
			String setId=AboutOperator.getUser().getSetId();

			Map<String, String> param = new HashMap<String, String>();
			
			if( fp != null && !fp.equals("") ){
				param.put("fp", fp );			
			}
			if( setId != null && !setId.equals("") ){
				param.put("setId", setId );			
			}
			if( roleId != null && !roleId.equals("") ){
				param.put("roleId", roleId );			
			}

			
	        //result=UserRoleService.findUserByIds(Short.valueOf(roleId));
	        result=UserRoleService.findUserByIds(param);

	        System.out.println(result+"-----已分配");
			
			return SUCCESS;
		}


	/**
	 * 查询列表
	 * @return success
	 */

		public String queryNoUseFuncList(){
			
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	        
            result=UserRoleService.findNoUseFunctionByIds();



		System.out.println(result+"-----未分配");
		return SUCCESS;
	}
	/**
	 * 添加RoleFunction
	 * @return success
	 */
	
		public String saveRoleFunctions() {
			
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
	        
			String jstr=request.getParameter("jsonData");
			JSONObject jo = JSONObject.fromObject(jstr);
			
	        String  roleId=jo.getString("roleId"); 
	        JSONArray  userIds1=jo.getJSONArray("userIds"); 
            
	        String fp=String.valueOf(AboutOperator.getUser().getFunctionPermission());
	        String setId=AboutOperator.getUser().getSetId();
	        
	        List<UserRole> UserRoleList=null;
	        if(fp.equals("1")){
	        	 UserRoleList = UserRoleService.findByRoleIds(Short.parseShort(roleId));
	        	 System.out.println("--------管理员-------");
	 			for (UserRole srf : UserRoleList ){
					UserRoleService.delete(srf);
				}
	        }else{
	        	 List userIds= UserRoleService.findByRoleIdsfp(Short.parseShort(roleId),setId);
	        
	 				
	        	 for (int i = 0; i < userIds.size(); i++) {
		 				UserRole ur=UserRoleService.findById(Long.valueOf(String.valueOf(userIds.get(i)))); 
		 				UserRoleService.delete(ur);                
					
				}
		        
	        }
	        
			
/*			for (UserRole srf : UserRoleList ){
				UserRoleService.delete(srf);
			}*/
	   
			   for (int i = 0; i < userIds1.size(); i++)
			   {		   
				   UserRoleId UserRoleId = new UserRoleId();
				   UserRoleId.setRoleId(Short.valueOf(roleId));
				   UserRoleId.setUserId(Long.valueOf(String.valueOf(userIds1.get(i))));
				   UserRole UserRole=new UserRole();
				   UserRole.setId(UserRoleId);
				   UserRole.setStatus("V");
				   UserRole.setStatusDate(DateTime.getCurrentDateTime());
				   UserRoleService.save(UserRole);
			   }

		    result = "success";
			return "success";
		}
		
	/**
	 * @return the UserRoleService
	 */
	public UserRoleService getUserRoleService() {
		return UserRoleService;
	}

	/**
	 * @param UserRoleService the UserRoleService to set
	 */
	public void setUserRoleService(UserRoleService UserRoleService) {
		this.UserRoleService = UserRoleService;
	}

	/**
	 * @return the UserRole
	 */
	public UserRole getUserRole() {
		return UserRole;
	}

	/**
	 * @param UserRole the UserRole to set
	 */
	public void setUserRole(UserRole UserRole) {
		this.UserRole = UserRole;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public short getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(short roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusDate
	 */
	public Date getStatusDate() {
		return statusDate;
	}

	/**
	 * @param statusDate the statusDate to set
	 */
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}