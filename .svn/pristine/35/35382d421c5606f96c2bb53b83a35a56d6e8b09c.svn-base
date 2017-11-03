package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.Role;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.CollectionIdService;
import com.oio.wawj.service.RoleService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��Ҫ���ܣ�SysFunction��ص�action�����ڳ䵱��SysFunction��ص��û������ҵ���߼�������
 * 
 * @author
 */
public class RoleAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleService roleService;
	private CollectionIdService collectionIdService;
	private Role role;
	private short roleId;//��ɫID
	private String roleName;//��ɫ��
	private short userId;//����ԱID
	private String status;//״̬
	private Date statusDate;//״̬����
	private String comment;//ע��
	private String result;
	private String roleList;

	/**
	 * Show the drop-down boxes for all companies
	 * @return
	 */
	public String showCollectionId() {
		
		roleList = collectionIdService.findCollectionId();
		
		setResult(roleList);
		return SUCCESS;
		
	}
	
	/**
	 * Show the drop-down boxes for all companies By set_id
	 * @return
	 */
	public String showCollectionIdBySetId() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String fp = String.valueOf(AboutOperator.getUser().getFunctionPermission());
		String setId=AboutOperator.getUser().getSetId();
		
		if(fp.equals("1")){
			roleList = collectionIdService.findCollectionId();
		}else{
			roleList = collectionIdService.findCollectionIdBySetId(setId);
		}
		
		
		
		setResult(roleList);
		return SUCCESS;
		
	}
	
	/**
	 * Show the drop-down boxes for all companies from addUser
	 * @return
	 */
	public String showCollectionIdAddUser() {
		
//		String setId="10001";
//		String duty="超级管理员";
		String setId=AboutOperator.getUser().getSetId();
		String duty=AboutOperator.getUser().getDuty();
		
		
		
		Map<String, String> param = new HashMap<String, String>();
		if( setId != null && !setId.equals("") ){
			param.put("setId", setId );			
		}
		if( duty != null && !duty.equals("") ){
			param.put("duty", duty );			
		}
		
        System.out.println(setId+"-----------setId");
        System.out.println(duty+"-----------duty");
        
		roleList = collectionIdService.findCollectionIdAddUser(param);
		
		setResult(roleList);
		return SUCCESS;
		
	}
	
	
	
	/**
	 * 
	 * @return add
	 */
	public String add() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);

		   
        String  roleName=jo.getString("roleName"); 
        String  status=jo.getString("status");      
        String  comment=jo.getString("comment"); 
//         try {
//       	  roleName = new String(roleName.getBytes("iso8859-1"),"UTF-8");
//          comment = new String(comment.getBytes("iso8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

        
		
		Role role=new Role();	
		role.setRoleName(roleName);
		role.setUserid(Long.valueOf(String.valueOf(AboutOperator.getOperatorId())));
		role.setStatus(status);
		role.setStatusDate(DateTime.getCurrentDateTime());
		role.setComment(comment);

		//��ӷ���
		roleService.save(role,Long.valueOf(String.valueOf(AboutOperator.getOperatorId())));
		return "success";
	}
		
	
	
	/**
	 * 
	 * @return successDelete
	 */
	public String delete() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String roleId=request.getParameter("roleId");
		
//		String roleId=ServletActionContext.getRequest().getParameter("roleId");
		System.out.println(roleId);
		
		//ͨ��IDɾ��
		roleService.deleteById((short) Integer.parseInt(roleId));
		setOperationLogResult("result:'" + getText("operatelog.role.delete.success") +"', reason:''");
		return "success";
	}

	
	/**
	 * ת����ҳ��
	 * @return update
	 */
	public String toUpdate() {
		
		role = roleService.findById(roleId);
		return "update";
	}

	/**
	 *
	 * @return successUpdate
	 */
	public String update() {

		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);

		String  roleId=jo.getString("roleId"); 
        String  roleName=jo.getString("roleName"); 
        String  status=jo.getString("status"); 
        String  comment=jo.getString("comment");
//         try {
//       	  roleName = new String(roleName.getBytes("iso8859-1"),"UTF-8");
//          comment = new String(comment.getBytes("iso8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
 
		
		Role role=roleService.findById(Short.valueOf(roleId));
		
		role.setUserid(Long.valueOf(String.valueOf(AboutOperator.getOperatorId())));
		role.setRoleName(roleName);
		role.setStatusDate(DateTime.getCurrentDateTime());
		role.setComment(comment);
		role.setStatus(status);
		
		roleService.update(role);
		setOperationLogResult("result:'" + getText("operatelog.role.update.success") +"', reason:''");
		return "success";
	}
	

	
//	/**
//	 * ��ѯ���б�
//	 * @return
//	 */
//	
//	public String select() {
//		List<role> role = roleService.findAll();
//		
//		//ת����json��ʽ
//		JSONArray json = JSONArray.fromObject(role);
//		
//		System.out.println(json);
//
//		//��result��ֵ�����ݸ�ҳ��
//		setResult(json.toString());//��result��ֵ�����ݸ�ҳ��
//		
//		return "success";
//	}
	
	/**
	 * 
	 * @return success
	 */
	public String queryList() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
        
		String jstr=request.getParameter("jsonData");
		JSONObject jo = JSONObject.fromObject(jstr);
		
   
         String  roleName=jo.getString("roleName"); 
          
//          try {
//        	  roleName = new String(roleName.getBytes("iso8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
          String  status=jo.getString("status"); 
          String  currentPage1=jo.getString("currentPage");
	       
		 Map<String, String> param = new HashMap<String, String>();
			
			if( roleName != null && !roleName.equals("") ){
				param.put("roleName", roleName );			
			}
		   	
			if( status != null && !status.equals("") ){
				param.put("status", status );			
			}
			
			if(currentPage1 != null && !currentPage1.equals("") ){
				currentPage=Integer.valueOf(currentPage1);
			}
			
			
		    PageListData pageListData = roleService.findList(param,currentPage,pageSize);		

		    System.out.println(pageListData.getDataList()+"----------------");
/*			if(pageListData.getTotalcount()!=0)
				{		
					JSONObject pageviewjson = pageListData.parseJSON(0,"roleName","roleId", "status","statusDate","comment");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());
				}else{
						  
				setResult("[]");	
			    }*/
		   	
					JSONObject pageviewjson = pageListData.parseJSON(0,"roleName","roleId", "status","statusDate","comment");	
					  System.out.println(pageviewjson.toString());
					  setResult(pageviewjson.toString());
	    
			return SUCCESS;
	}
	
	/**
	 * get����
	 * @return parentFunctionList
	 */
	public String getRoleList() {
		roleList = roleService.findRoleList();
		setResult(roleList);
		
		return "success";
	}

	
	
	
	/**
	 * set����
	 */
	public void setRoleList (String roleList) {
		this.roleList = roleList;
	}
	

	public RoleService getroleService() {
		return roleService;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserid(short userId) {
		this.userId =  userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}



	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUserId(short userId) {
		this.userId = userId;
	}

	public void setroleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getrole() {
		return role;
	}

	public void setrole(Role role) {
		this.role = role;
	}



	public short getRoleId() {
		return roleId;
	}

	public void setRoleId(short roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}




	public CollectionIdService getCollectionIdService() {
		return collectionIdService;
	}




	public void setCollectionIdService(CollectionIdService collectionIdService) {
		this.collectionIdService = collectionIdService;
	}
	

}
