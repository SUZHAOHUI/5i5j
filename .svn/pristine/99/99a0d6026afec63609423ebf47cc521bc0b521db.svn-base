package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;


import org.json.JSONObject;



public class AxUnSubs {
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
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");
			
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
			
				respCon=HttpClientUtil.sendPost(requestContent);
					System.out.println("返回值"+respCon);	
				
	  }
	 public static void  insertData(){
			String pathName = "C:\\data\\456.txt";
		    File filename = new File(pathName);
		    InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(  
				        new FileInputStream(filename));
			    BufferedReader br = new BufferedReader(reader);
			    String line = "";
			    int count=1;
			    String acms="";
				 while ((line = br.readLine()) != null) {  
			           //一次读入一行数据 
					 if(line !=null && !line.equals("")){
			            String[] strArray = line.split(" ");
			            
			            if(strArray.length>1){
			               String prtms=strArray[0];
			               acms=strArray[1];
			               String[] acmsArray = acms.split(",");
				            for(int i=0;i<acmsArray.length;i++){
				                axUnsub(prtms,acmsArray[i]);
				            System.out.println("count-----"+count+":"+acmsArray[i]+"prtms------"+prtms);
				            }
			            }   
					 }
					    ;
			           
			            count++;
			            
			        }
				 br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
			
		}
	 public static void main(String[] args){
			String  acms="13162482454";
			String  prtms="15317255585";
		     axUnsub(prtms,acms);
		 // insertData();
		 //"subs_rela_ax:13526636962:15525401469:wywawj:s"
		 //axUnsub("15037900958","17186246682");
		// axUnsub("150034381110","15525421094");





	 }
}
