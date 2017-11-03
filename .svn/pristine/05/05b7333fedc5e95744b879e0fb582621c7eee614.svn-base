
package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.oio.wawj.util.OVLoadProperties;
import com.oio.wawj.util.PageListData;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��Ҫ���ܣ�BaseAction
 * 
 * @author 
 */
@Entity
public class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;

	private static String dateCtrlLang;
	private static String i18nLang;
	
	@ManyToOne
	protected PageListData pageListData;//��ҳ���
	protected int currentPage=1;//��ǰҳ
	protected int page=1;
	protected int pageSize=Integer.parseInt(OVLoadProperties.getInstance().getProperties("pageSize"));//ÿҳ��ʾ����Ŀ
	protected int querytype;//��ѯ����
	protected String condition;//��ѯ����
	private String operationLogResult;

	
	
	private Short sysRoleId;

	public void setSysRoleId(Short sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	private String currFunc;
	private String operateType;
	private String nameStyle;
	private static final int DEF_DIV_SCALE = 3;
    private static MathContext mc = new MathContext(DEF_DIV_SCALE,  RoundingMode.HALF_UP);
	/**
	 * get����  ��ȡ������ʽ
	 * @return nameStyle
	 */
	public String getNameStyle() {
		return nameStyle;
	}
	/**
	 * set����  ����������ʽ
	 * @return nameStyle
	 */
	public void setNameStyle(String style) {
		nameStyle = style;
	}
	
	//Ĭ�϶�ȡ
	//��ȡ������ʽ
	public BaseAction() {
		setNameStyle(getText("common.name.style.is_surname_in_first"));
	}
	
	/**
	 * get����
	 * @return pageListData
	 */
	public PageListData getPageListData() {
		return pageListData;
	}
	
	/**
	 * set����
	 */
	public void setPageListData(PageListData pageListData) {
		this.pageListData = pageListData;
	}
	
	/**
	 * get����
	 * @return currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * set����
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * get����
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * set����
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * get����
	 * @return querytype
	 */
	public int getQuerytype() {
		return querytype;
	}
	
	/**
	 * set����
	 */
	public void setQuerytype(int querytype) {
		this.querytype = querytype;
	}
	
	/**
	 * get����
	 * @return condition
	 */
	public String getCondition() {		
		return condition==null?"":condition.trim();
	}
	
	/**
	 * set����
	 */
	public void setCondition(String condition) {
		this.condition=(condition==null?"":condition.trim());
	}

	public String getCurrFunc() {
		return currFunc;
	}
	
	public void setCurrFunc(String currFunc) {
		this.currFunc = currFunc;
	}

	public void setCurrFunc0(String currFunc) {
		try {
			currFunc = new String(currFunc.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.currFunc = currFunc;
	}

	public String getOperationLogResult() {
		return operationLogResult;
	}

	public void setOperationLogResult(String operationLogResult) {
		this.operationLogResult = operationLogResult;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	

	



	
	/**
	 * ������þ���
	 * @return ����ֵ
	 */

	

	
	

	

	public String getDateCtrlLang() {

		return dateCtrlLang;		
	}

	public String getI18nLang() {
		return i18nLang;		
	}

	
}
