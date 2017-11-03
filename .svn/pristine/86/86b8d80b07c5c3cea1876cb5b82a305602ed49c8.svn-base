
package com.oio.wawj.listener;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oio.wawj.util.OVLoadProperties;




public class TimerListener implements ServletContextListener {


	// ��ȡ�����ļ��е���������
	static public long periodCycle = (Integer.parseInt(OVLoadProperties
			.getInstance().getProperties("provisionTaskPeroid")) < 10 ? 10
			: Integer.parseInt(OVLoadProperties.getInstance().getProperties(
					"provisionTaskPeroid"))) * 1000;
	/**
	*��Ҫ���ܣ���д�ӿڵ�contextDestroyed������ֹͣ�����Զ�ʱ��
	* 
	* 
	* @author 
	*/

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("timer application is Destroyed.");

		ServletContext context = sce.getServletContext();
		Timer timer = (Timer) context.getAttribute("timer");
		timer.cancel();
	}
	/**
	*��Ҫ���ܣ���д�ӿڵ�contextInitialized���������������Զ�ʱ��
	* 
	* 
	* @author 
	*/
	public void contextInitialized(ServletContextEvent sce) {
//		 TODO Auto-generated method stub

		ServletContext context = sce.getServletContext();
		
		String df = "zh";
		
		if (df.equalsIgnoreCase("en-us")) {
			context.setAttribute("dateFmt", "MM/dd/yyyy HH:mm:ss");
		} else if (df.equalsIgnoreCase("en-gb")) {
			context.setAttribute("dateFmt", "dd/MM/yyyy HH:mm:ss");
		} else {
			context.setAttribute("dateFmt", "yyyy-MM-dd HH:mm:ss");
		}
		
		System.out.println("timer application is Initialized.");

		Timer timer = new Timer();
		context.setAttribute("timer", timer);
	
	}
}
