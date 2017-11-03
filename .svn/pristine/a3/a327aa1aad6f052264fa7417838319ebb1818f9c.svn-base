/**
 * 
 */
package com.oio.wawj.struts.action;

import javax.persistence.Entity;
import net.sf.json.JSONObject;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author admin
 *
 */
@Entity
public class HibernateStatisticAction extends ActionSupport{
	private SessionFactory sessionFactory;
	private String hibernateinfo;
	private Long sessiondiff;
	private String result;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String getHibernateinfo() {
		return hibernateinfo;
	}

	public void setHibernateinfo(String hibernateinfo) {
		this.hibernateinfo = hibernateinfo;
	}

	public Long getSessiondiff() {
		return sessiondiff;
	}

	public void setSessiondiff(Long sessiondiff) {
		this.sessiondiff = sessiondiff;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String execute() {
		// TODO Auto-generated method stub
		return "success";
	}
	
	public String statistic() {
		Statistics st = sessionFactory.getStatistics();
		hibernateinfo = st.toString();
		sessiondiff = st.getSessionOpenCount() - st.getSessionCloseCount();
		result = JSONObject.fromObject(st).toString();
//		result = "{sessiondiff:" + sessiondiff + ", " +
//				"hibernateinfo:" + hibernateinfo + "}";
		return SUCCESS;
	}
}
