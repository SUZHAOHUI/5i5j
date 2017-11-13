package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

public class DismissionPerDay {

			public static final String url = "jdbc:mysql://101.201.101.70:3306/icrm?useUnicode=true&characterEncoding=utf-8";
		    public static final String name = "com.mysql.jdbc.Driver";  
		    public static final String user = "dev";  
		    public static final String password = "1q2w!Q@W"; 		  
		    public static Connection conn = null;  
		    public static Statement pst = null;  
		    public static   DatabaseMetaData meta=null;
		    public static Connection conn1 = null;  
		    public static Statement pst1 = null;  
		    public static   DatabaseMetaData meta1=null;
//		    public void close() {  
//		        try {  
//		            this.conn.close();  
//		            this.pst.close();  
//		        } catch (SQLException e) {  
//		            e.printStackTrace();  
//		        }  
//		    } 
			public static void  handleData(){
				    int count=1;
				    String sql="";
				    try {  
			            Class.forName(name);//ָ����������  
			            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
			            conn1 = DriverManager.getConnection(url, user, password);
			            pst = conn.createStatement();//׼��ִ�����  
			            pst1 = conn1.createStatement();
			            meta = conn.getMetaData();
			            meta1 = conn1.getMetaData();
			            sql=" SELECT sr.xnum, sr.anum FROM subs_rela sr ,(select acms from acms where status ='V' and acms_id not in(select acms_id from user_acms_rela where state ='V'))ac" +
			            		" ,user u " +
			            		"  WHERE  sr.xnum =  ac.acms AND u.user_id=sr.user_id AND sr.state ='B'";
						    ResultSet rst = pst.executeQuery(sql);
				           
				           while (rst.next()) {
				        	   String acms=rst.getString(1);
				        	   String prtms=rst.getString(2);
			              
				        	   System.out.println("acms-----"+acms+"---prtms------"+prtms);
				        	   axUnsub(prtms,acms);
				        	   
				        	   String sql1="update subs_rela set state='U' where state ='B' and  xnum = "+acms+" and anum ="+prtms;
				        	   String sql2="update acms set status='I' where acms="+acms;
				        	   pst1.addBatch(sql1);
				        	   pst1.addBatch(sql2);
				        	   pst1.executeBatch();
				        	  System.out.println("database update ");
				           }
				           System.out.println("没有数据");
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
				   
				}
	
	
	
	

	public static String sendPost( String requestContent){
		String pathUrl = "http://101.201.101.70:5000";   
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
	 public static void axUnsub(String prtms,String acms){
     	   
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	        String unsubts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
	        String msgid=UUIDGenerator.getUUID();
	        TreeMap<String, String> inmap = new TreeMap<String, String>();
			  inmap.put("ver", "1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service", "acbss");
			  inmap.put("msgtype", "unsubreq");
			  inmap.put("appkey","wywawj");
			  inmap.put("prtms",prtms);
			  inmap.put("rsvd","0");
			  inmap.put("acms",acms);
			  inmap.put("unsubts",unsubts); 
			  System.out.println(inmap.entrySet());	
			  String sid = signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");
			
			  JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("ver", "1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service", "acbss");
					jsonObject.put("msgtype", "unsubreq");
					jsonObject.put("appkey", "wywawj");
					jsonObject.put("prtms",prtms);
					jsonObject.put("rsvd","0");
					jsonObject.put("acms",acms);
					jsonObject.put("unsubts",unsubts);
					jsonObject.put("sid", sid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String requestContent = jsonObject.toString();

				System.out.println("请求参数"+requestContent.toString());
				String respCon = null;
			
				respCon=sendPost(requestContent);
					System.out.println("返回值"+respCon);	
				
	  }
		public static String signature(Map<String,String>params,String secret,String singName)
		{
			String result=null;
			if(params==null)
				return result;
		 //把字典按key的字母顺序排列	
		  Map<String,String>  treeMap=new TreeMap<String,String>();
		  treeMap.putAll(params);
			
		 //remove sign parameter
	     	  treeMap.remove(singName);
		   //把参数串起来
		   Iterator<String> iter=treeMap.keySet().iterator();
		   StringBuffer orgin=new StringBuffer(secret);
		   while(iter.hasNext()){
			   String name=(String)iter.next();
			   orgin.append(name).append(params.get(name));
			
		   }   
		   System.out.println(orgin);
		   //加密 ：MD5+大写&十六进制
		   try {
			   MessageDigest md = MessageDigest.getInstance("MD5");
			   result=byte2hex(md.digest(orgin.toString().trim().getBytes("UTF-8")));
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			 throw new java.lang.RuntimeException("sign error");
		    }

		   return result; 
		   
		 }
		
		

		private static String byte2hex(byte[] b){
			StringBuffer hs=new StringBuffer();
			String stmp="";
			
			for(int n=0;n<b.length;n++){
			
				stmp=(java.lang.Integer.toHexString(b[n]&0XFF));
				if(stmp.length()==1)
					hs.append("0").append(stmp);
				else
					hs.append(stmp);
			}
			return hs.toString().toUpperCase();
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		handleData();
	}
}
