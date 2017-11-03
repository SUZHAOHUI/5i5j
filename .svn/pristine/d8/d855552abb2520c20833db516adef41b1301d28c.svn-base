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

public class TransferNumSet {
	 
	public static void transferNumSet( String acms,String transferms){          

	    	String msgid = UUIDGenerator.getUUID();
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

	        TreeMap<String, String> inmap = new TreeMap<String, String>();
	          inmap.put("ver", "1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service", "acbss");
			  inmap.put("msgtype", "outaxbtransfersetreq");
			  inmap.put("appkey","wywawj");
			  inmap.put("rsvd","0");
			  inmap.put("acms",acms);
			  inmap.put("transferms",transferms);
			  inmap.put("transfervoicecode","218"); 
			  inmap.put("transfercalldisplay","1"); 
			  inmap.put("transfercallrecording","1"); 

			  System.out.println(inmap.entrySet());
				
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");		
			  JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("ver", "1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service", "acbss");
					jsonObject.put("msgtype", "outaxbtransfersetreq");
					jsonObject.put("appkey", "wywawj");
					jsonObject.put("rsvd","0");
					jsonObject.put("acms",acms);
					jsonObject.put("transferms",transferms);
					jsonObject.put("transfervoicecode","218");
					jsonObject.put("transfercalldisplay","1");	
					jsonObject.put("transfercallrecording","1");	
					jsonObject.put("sid", sid);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String requestContent = jsonObject.toString();

				System.out.println("请求参数"+requestContent.toString());
			
				
				String respCon = null;
				respCon=HttpClientUtil.sendPost(requestContent);
				
				
	    } 
	public static void main(String[] args){
		String  acms="13100402367";
		String   prtms="15037900958";

		transferNumSet(acms,prtms);
	}
}
