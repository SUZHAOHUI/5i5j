package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import com.oio.wawj.util.HttpClientUtil;
import net.sf.json.JSONObject;


public class AxbSub {
	 
	public static String axbOrder ( String prtms,String acms,String otherms){  

	  	   String producttype ="3";
    	   String productcat = "11";
    	   String name="abc";
    	   String cardno="4103811993021890023";
    	   String msgid = UUIDGenerator.getUUID(); 
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String subts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	        TreeMap<String, String> inmap = new TreeMap<String, String>();
	          inmap.put("ver", "1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service", "acbss");
			  inmap.put("msgtype", "axbsubreq");
			  inmap.put("appkey","wywawj");
			  inmap.put("prtms",prtms);
//			  inmap.put("rsvd","0");
			  inmap.put("acms",acms);
			  inmap.put("otherms",otherms);
			  inmap.put("subts",subts);
			  inmap.put("unsubts", "");
			  inmap.put("unsubmethod", "1");
			  inmap.put("producttype",producttype);
			  inmap.put("productcat", productcat);
			  inmap.put("name",name);
			  inmap.put("cardtype","1");
			  inmap.put("cardno",cardno);
              inmap.put("callrecording","1");
              inmap.put("anucode","218,218,218");
			  System.out.println(inmap.entrySet());
				
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");		
			  JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("ver", "1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service", "acbss");
					jsonObject.put("msgtype", "axbsubreq");
					jsonObject.put("appkey", "wywawj");
					jsonObject.put("prtms",prtms);
//					jsonObject.put("rsvd","0");
					jsonObject.put("acms",acms);
					jsonObject.put("otherms",otherms);
					jsonObject.put("subts",subts);
					jsonObject.put("unsubts","");
					jsonObject.put("unsubmethod", "1");
					jsonObject.put("producttype","3");
					jsonObject.put("productcat", "11");
					jsonObject.put("sid",sid);
					jsonObject.put("name",name);
					jsonObject.put("cardtype","1");
					jsonObject.put("cardno", cardno);
					jsonObject.put("callrecording","1");
					jsonObject.put("anucode","218,218,218");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String requestContent = jsonObject.toString();
				System.out.println("请求参数"+requestContent.toString());
				String respCon = null;
				
				respCon=HttpClientUtil.sendPost(requestContent);		
				return respCon;
	    } 
	public static void main(String[] args){
		String  acms="13100402367";
		String   prtms="15037900958";
		String  otherms="13526636962";
		axbOrder(prtms,acms,otherms);
	}
}
