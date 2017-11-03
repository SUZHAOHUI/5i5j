package com.oio.wawj.filter;

import java.io.IOException;
import java.security.Principal;

import javax.persistence.Entity;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import com.oio.wawj.bean.User;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.AboutOperator;
import com.opensymphony.xwork2.ActionContext;



@Entity
public class AppendSession implements Filter{
	static ApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml");
	 UserService userService=(UserService)context.getBean("userService");
	public void destroy(){
	}
	@Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        
 		String ticket=request.getParameter("ticket");
 		
        if (session != null) {     
        	 User su = (User) ActionContext.getContext().getSession().get("user");
                Principal principal = request.getUserPrincipal();
                if (principal != null) {
                    String identifying = principal.getName();// 返回值为员工编号
                     User user = userService.findUserByCode(identifying);
                	ActionContext.getContext().getSession().put("user", user);
                    System.out.println(identifying+"-------identifying");
                    if(su==null){
                       // request.getRequestDispatcher("http://sso.bacic5i5j.com").forward(request,response);
                    	request.getRequestDispatcher("tologin").forward(request,response);
                        return;
                    }

                }
        }
        chain.doFilter(req, resp);
    }
	


    public void init(FilterConfig filterConfig)throws ServletException{
    	
    }
}
