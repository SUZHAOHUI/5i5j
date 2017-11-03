
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



import com.oio.wawj.bean.User;
import com.oio.wawj.util.AboutOperator;
import com.opensymphony.xwork2.ActionContext;



@Entity
public class LoginFilter implements Filter{
	public void destroy(){
	}
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{
    	HttpSession session=((HttpServletRequest)request).getSession();
    	((HttpServletResponse)response).setHeader("Pragma","No-cache");          
    	((HttpServletResponse)response).setHeader("Cache-Control","no-cache");   
    	((HttpServletResponse)response).setHeader("Cache-Control", "no-store");   
    	((HttpServletResponse)response).setDateHeader("Expires",0);
    //	User user=(User)session.getAttribute("user");
    	String url=(((HttpServletRequest)request).getRequestURI());
    	if(url.endsWith("/5i5j/")){
    		chain.doFilter(request, response);
    	}
    }
    
/*	@Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        
 		String ticket=request.getParameter("ticket");
 		System.out.println(ticket+"-------ticket");
        
        if (session != null) {
            //  SessionUser su = (SessionUser) session.getAttribute(SessionUser.SESSION_PARAMETER_NAME);
            //if (su == null) {
                Principal principal = request.getUserPrincipal();
                if (principal != null) {
                    String identifying = principal.getName();// 返回值为员工编号
                    System.out.println(identifying+"-------identifying");
                }
            //}
        }
        chain.doFilter(req, resp);
    }*/


    
    public void init(FilterConfig filterConfig)throws ServletException{
    	
    }
}
