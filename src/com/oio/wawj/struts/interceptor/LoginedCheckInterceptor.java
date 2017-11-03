
package com.oio.wawj.struts.interceptor;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oio.wawj.bean.User;
import com.oio.wawj.service.UserService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/** session过期、登录有效性及操作的权限验证拦截器 */
@Entity
@SuppressWarnings("serial")
public class LoginedCheckInterceptor extends AbstractInterceptor {
	static ApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml");
	 UserService userService=(UserService)context.getBean("userService");
	/** session过期、登录有效性及操作的权限验证拦截器 */
	public String intercept(ActionInvocation ai) throws Exception {
		//取锟斤拷锟斤拷锟斤拷锟経RL
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Pragma","No-cache");          
		response.setHeader("Cache-Control","no-cache");   
		response.setHeader("Cache-Control", "no-store");   
		response.setDateHeader("Expires",0);
		User user = null;
		//锟皆碉拷录锟斤拷注锟斤拷锟斤拷锟斤拷直锟接凤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷
		if (url.indexOf("sysUser_login.action")!=-1 || url.indexOf("logout.action")!=-1|| url.indexOf("login")!=-1)
	    {
			return ai.invoke();
		}
		//验证Session是否过期
//		if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
//			//session过期,转向session过期提示页,最终跳转至登录页面
//			return "tologin";
//		}
//		else{
//			user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
//			//验证是否已经登录
//			if (user==null){
				//尚未登录,跳转至登录页面
//				return "tologin";
//			}else{					
				return ai.invoke();
							
//			}				
//		}	
	}
}