package com.oio.wawj.util;
 

 

import java.io.BufferedReader;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;





public class WyHttpClientUtil {
	
	
	public static String sendPost( String requestContent,String pathUrl){
	   
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
	// 设置请求属性   
	// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致   
	byte[] requestStringBytes = requestContent.getBytes("utf8");   
	httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);   
	httpConn.setRequestProperty("Content-Type", "application/json");  
	
	httpConn.setRequestProperty("Charset", "UTF-8");   		   
	// 建立输出流，并写入数据   	  
	
	try {
		OutputStream outputStream = httpConn.getOutputStream();   
		outputStream.write(requestStringBytes);   
		outputStream.close(); 
		responseCode = httpConn.getResponseCode();
		System.out.println(responseCode);
		if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功   
			// 当正确响应时处理数据   
			StringBuffer sb = new StringBuffer();   
			String readLine;   
			BufferedReader responseReader;   
			// 处理响应流，必须与服务器响应流输出的编码一致   
			 responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf8"));   
			while ((readLine = responseReader.readLine()) != null) {   
			sb.append(readLine).append("\n");   
			}  
			result=sb.toString();
			
			responseReader.close();     
			}  
	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }   	  
	} catch (IOException e) {
	 	// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	  return result;
	}
	}  

