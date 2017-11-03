package com.oio.wawj.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import net.sf.json.JSONObject;
import net.sf.json.util.PropertyFilter;


public class PageListData {


	
	private int page_size = Integer.valueOf(OVLoadProperties.getInstance().getProperties("pageSize"));
	private List<?> dataList = null;	// 锟斤拷页锟斤拷锟�
	private int totalcount = 0;			// 锟斤拷录锟斤拷锟斤拷
	private int pageSize = 0;			// 每页锟斤拷示锟斤拷录锟斤拷
	private int currentPage = 1;		// 锟斤拷前页锟斤拷

	@SuppressWarnings("unused")
	private int totalPageCount = 1;		// 锟斤拷页锟斤拷
	@SuppressWarnings("rawtypes")
	public PageListData(int totalcount, int pageSize, int currentPage,
			List dataList) {	
		setTotalcount(totalcount);
		setPageSize(pageSize);
		setCurrentPage(currentPage);
		setDataList(dataList);	
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return dataList
	 */
	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}
	/**
	 * set锟斤拷锟斤拷
	 */
	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return totalcount
	 */
	public int getTotalcount() {
		return totalcount;
	}

	/**
	 * set锟斤拷锟斤拷
	 */
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * set锟斤拷锟斤拷
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * get锟斤拷锟斤拷
	 * @return currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * set锟斤拷锟斤拷
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 锟斤拷锟斤拷锟斤拷页锟斤拷锟斤拷锟轿�锟斤拷锟斤拷为1
	 */
	public int getTotalPageCount() {
		int p;
		if (totalcount % pageSize == 0) {
			p = totalcount / pageSize;
		} else {
			p = totalcount / pageSize + 1;
		}
		return p == 0 ? 1 : p;
	}
	/**
	 * set锟斤拷锟斤拷
	 */
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	private Object getCol(Class<?> tableCls, String colName, Object value) {
		Object o = null;
		StringBuffer buffer = new StringBuffer(colName);
		char firstChar = buffer.charAt(0);
		char UpperChar = Character.toUpperCase(firstChar);
		buffer.setCharAt(0, UpperChar);
		buffer = buffer.insert(0, "get");
		try {
			if (value != null)
			{
				Method m = tableCls.getMethod(buffer.toString());
				o = m.invoke(value);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	public JSONObject parseJSON(Map<Class<?>, String> tableColPairs) {
		JSONObject json = new JSONObject();
		if (this.getDataList()!= null && this.getDataList().size() > 0) {
			JSONObject pageviewjson = new JSONObject();
			pageviewjson.put("currentpage", this.getCurrentPage());
			pageviewjson.put("previousPage",
					this.getCurrentPage() > 1 ? this.getCurrentPage() - 1
							: this.getCurrentPage());
			pageviewjson.put("totalpage", this.getTotalPageCount());
			pageviewjson.put("totalrecord", this.getTotalcount());
			pageviewjson.put("page_size", pageSize);
			
			Set<Entry<Class<?>, String>> tableColSet = tableColPairs.entrySet();
			JSONArray records = new JSONArray();
			for (int i = 0; i < this.getDataList().size(); i++) {
				JSONObject record = new JSONObject();
				for (Entry<Class<?>, String> tableColPair : tableColSet) {
					tableColPair.getKey();
				}
				records.add(record);
			}
		}
		return json;
	}

	public JSONObject parseJSON(String... tableCol) {

		JSONObject json = new JSONObject();
		
		JSONObject pageviewjson = new JSONObject();
		pageviewjson.put("currentpage", this.getCurrentPage());
		pageviewjson.put("previousPage",
				this.getCurrentPage() > 1 ? this.getCurrentPage() - 1
						: this.getCurrentPage());
		pageviewjson.put("totalpage", this.getTotalPageCount());
		pageviewjson.put("totalrecord", this.getTotalcount());
		pageviewjson.put("page_size", pageSize);
	
		JSONArray records = new JSONArray();
		if (this.getDataList()!= null && this.getDataList().size() > 0) {
			
			for (int i = 0; i < this.getDataList().size(); i++) {

				JSONObject record = new JSONObject();
				for (String perTableCol : tableCol) {
					perTableCol = perTableCol.replaceAll(" ", "");
					String[] handle = perTableCol.split(",");
					try {
						Class<?> tableCls = Class.forName("com.oio.wawj.bean." + handle[0].toString());
						Integer index = Integer.parseInt(handle[1]);
						String colName = handle[2];
						Object o =getCol(tableCls, colName, ((Object[])this.getDataList().get(i))[index]);
						String name = handle[3];
						record.put(name, o);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				records.add(record);
			}
		}
		pageviewjson.put("records", records);
		json.put("pageview", pageviewjson);
		return json;		
	}

	public JSONObject parseJSON(Class<?> tableCls, String... tableCol) {

		JSONObject json = new JSONObject();
		JSONObject pageviewjson = new JSONObject();
		pageviewjson.put("currentpage", this.getCurrentPage());
		pageviewjson.put("previousPage",
				this.getCurrentPage() > 1 ? this.getCurrentPage() - 1
						: this.getCurrentPage());
		pageviewjson.put("totalpage", this.getTotalPageCount());
		pageviewjson.put("totalrecord", this.getTotalcount());
		pageviewjson.put("page_size", pageSize);


		JSONArray records = new JSONArray();
		if (this.getDataList()!= null && this.getDataList().size() > 0) {
			
			for (int i = 0; i < this.getDataList().size(); i++) {
				JSONObject record = new JSONObject();
				for (String colName : tableCol) {
					Object o =getCol(tableCls, colName, this.getDataList().get(i));
					record.put(colName, o);
				}
				records.add(record);
			}
		}
		pageviewjson.put("records", records);
		json.put("pageview", pageviewjson);
		return json;
	}


	public JSONObject parseJSON(int flg, Class<?> tableCls, String colName, final String... tableCol) {

		JSONObject json = new JSONObject();
		JSONObject pageviewjson = new JSONObject();
		pageviewjson.put("currentpage", this.getCurrentPage());
		pageviewjson.put("previousPage",
				this.getCurrentPage() > 1 ? this.getCurrentPage() - 1
						: this.getCurrentPage());
		pageviewjson.put("totalpage", this.getTotalPageCount());
		pageviewjson.put("totalrecord", this.getTotalcount());
		pageviewjson.put("page_size", pageSize);


		JSONArray records = new JSONArray();
		if (this.getDataList()!= null && this.getDataList().size() > 0) {
			JsonConfig config = new JsonConfig();
			config.setJsonPropertyFilter(new PropertyFilter() {
				
			public boolean apply(Object source, String name, Object value) {
					for (String perTableCol : tableCol) {
						perTableCol = perTableCol.replaceAll(" ", "");
						String[] handle = perTableCol.split(",");
						if (name.equals(handle[0])) 
							return true;
					}
					return false;
				}
			});
			
			try {
				config.setExcludes(new String[] { "handler",
						"hibernateLazyInitializer" });
				config.registerJsonValueProcessor(Date.class,
						new JsonDateValueProcessor());
				for(int i = 0; i < this.getDataList().size(); i++) {
					Object o =getCol(tableCls, colName, this.getDataList().get(i));
					JSONObject record = (JSONObject) JSONArray.fromObject(o, config).get(0);// 锟斤拷map锟斤拷锟斤拷转锟斤拷锟斤拷json锟斤拷锟斤拷锟斤拷锟�
					records.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pageviewjson.put("records", records);
		json.put("pageview", pageviewjson);
		return json;
	}
	
	public JSONObject parseJSON(int flg, String... tableCol) {

		JSONObject json = new JSONObject();
		
		JSONObject pageviewjson = new JSONObject();
		pageviewjson.put("currentpage", this.getCurrentPage());
		pageviewjson.put("previousPage",
				this.getCurrentPage() > 1 ? this.getCurrentPage() - 1
						: this.getCurrentPage());
		pageviewjson.put("totalpage", this.getTotalPageCount());
		pageviewjson.put("totalrecord", this.getTotalcount());
		pageviewjson.put("page_size", pageSize);
	
	    
		JSONArray records = new JSONArray();
		if (this.getDataList()!= null && this.getDataList().size() > 0) {
			
			for (int i = 0; i < this.getDataList().size(); i++) {

				JSONObject record = new JSONObject();
				int j = 0;
				for (String perTableCol : tableCol) {
					perTableCol = perTableCol.replaceAll(" ", "");
					String[] handle = perTableCol.split(",");
					
					String name = handle[0];
					Object[] o = (Object[]) this.dataList.get(i);
					record.put(name, o[j++]);
					
				}
				
				records.add(record);
			}
		}
		pageviewjson.put("records", records);
		json.put("pageview", pageviewjson);
		return json;		
	}

}
