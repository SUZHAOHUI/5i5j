package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

public class AxbUnSub {
	 
	public static void axbUnsub ( String prtms,String acms,String otherms){          
	  	String producttype = "3";
    	String productcat = "11";
	    	String msgid = UUIDGenerator.getUUID();
	    	String unsubts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String subts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	        TreeMap<String, String> inmap = new TreeMap<String, String>();
	          inmap.put("ver","1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service","acbss");
			  inmap.put("msgtype","axbunsubreq");
			  inmap.put("appkey","wywawj");
			  inmap.put("rsvd","0");
			  inmap.put("prtms",prtms);
			  inmap.put("subid","c502d9c7d0fc443d91bfb6c483fec7c4");
			  inmap.put("acms",acms);
			  inmap.put("productcat","11");
			  inmap.put("otherms",otherms);
			  inmap.put("unsubts",unsubts); 

			  System.out.println(inmap.entrySet());
				
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");		
			  JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("ver","1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service","acbss");
					jsonObject.put("msgtype","axbunsubreq");
					jsonObject.put("appkey","wywawj");
					jsonObject.put("rsvd","0");
					jsonObject.put("prtms",prtms);
					jsonObject.put("subid","c502d9c7d0fc443d91bfb6c483fec7c4");				
					jsonObject.put("acms",acms);
					jsonObject.put("productcat","11");
					jsonObject.put("otherms",otherms);				
					jsonObject.put("unsubts",unsubts);	
					jsonObject.put("sid",sid);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String requestContent = jsonObject.toString();

				System.out.println("请求参数"+requestContent.toString());
				String respCon = null;
				respCon=HttpClientUtil.sendPost(requestContent);
				System.out.println(respCon);
				
				
	    } 
	public static void main(String[] args){
		String  acms="13100402367";
		String   prtms="15037900958";
		String  otherms="13526636962";
		axbUnsub(prtms,acms,otherms);
	}
}
