
package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

//import java.util.List;

/**
* <p>��Ҫ���ܣ�������������
* <p>T��id, pId����
* <p>V: value
* 
*/
public class TreeNode<T, V> {
	
	private Integer cid;
	private String cname;
	private Long pid;
	private List nodes = new ArrayList();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public List getNodes() {
		return nodes;
	}
	public void setNodes(List nodes) {
		this.nodes = nodes;
	}
	

	public static void test(){
		String pathUrl = "http://101.200.221.216:3555/";   
	    String result = "";
	    int responseCode = 0;
		// 建立连接   
		try{
		URL url = new URL(pathUrl);   
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();   
	    //设置连接属性   
		httpConn.setDoOutput(true);// 使用 URL 连接进行输出   
		httpConn.setDoInput(true);// 使用 URL 连接进行输入   
		httpConn.setUseCaches(false);// 忽略缓存   
		httpConn.setRequestMethod("POST");// 设置URL请求方法        
		
		JSONObject jo=new JSONObject();
		jo.put("result", "200");
		jo.put("api", "nihao");
		String requestContent=jo.toString();
		// 设置请求属性   
		// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致   
		byte[] requestStringBytes = requestContent.getBytes("utf8");   
		httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);   
		httpConn.setRequestProperty("Content-Type", "application/json");   
		httpConn.setRequestProperty("Charset", "UTF-8");   		   
		// 建立输出流，并写入数据   	  
			OutputStream outputStream = httpConn.getOutputStream();   
			outputStream.write(requestStringBytes);   
			outputStream.close(); 
			responseCode = httpConn.getResponseCode();
			System.out.println(responseCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		 	
	}
	public static void test1(){
	System.out.println(Runtime.getRuntime().freeMemory());
	System.out.println(Runtime.getRuntime().totalMemory());
	}
	
	public static void main(String[] args){
	
		//insertData();
		//test();
//		String test="??";
//		try {
//			String ac = new String(test.getBytes("utf8"),"utf8");
//			System.out.println(ac);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
}

