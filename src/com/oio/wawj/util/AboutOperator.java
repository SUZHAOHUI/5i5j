package com.oio.wawj.util;

import java.util.Date;

import com.oio.wawj.bean.User;
import com.opensymphony.xwork2.ActionContext;

public class AboutOperator {

	public static Date getCurrentDate() {
		Date currDate = new Date(System.currentTimeMillis()/1000*1000);
		return currDate;
	}
	
	public static long getOperatorId() {
		Long operatorId = (long) 0;
		User user = (User)ActionContext.getContext().getSession().get("user");
		if (user != null && user.getUserId() != 0)
			operatorId = user.getUserId();
		return operatorId;
	}
	
	public static User getUser() {
		User user = (User)ActionContext.getContext().getSession().get("user");
		return user;
	}
	public static String getSetId() {
		String setId="";
		User user = (User)ActionContext.getContext().getSession().get("user");
		if (user != null && user.getSetId().equals(""))
		     setId = user.getSetId();
		return setId;
	}
}
