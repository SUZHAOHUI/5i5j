package com.oio.wawj.struts.action;

import com.opensymphony.xwork2.ActionSupport;

public class NotFoundErrerAction extends ActionSupport  {
	 private static final long serialVersionUID = 6218614965524501080L; 
  
	    public String execute() throws Exception {  
	        System.out.println("没有访问到action!");  
	        return super.execute();  
	    }  
}
