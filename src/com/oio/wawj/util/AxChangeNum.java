package com.oio.wawj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;

import org.json.JSONObject;




public class AxChangeNum {
	 
	public static String axChangeNum (String subId,String acms,String oldprtms,String newprtms,String ringcalled,
			String ringcalling){  
          

	    	String msgid = UUIDGenerator.getUUID();
	    
	 
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String chgts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	        TreeMap<String, String> inmap = new TreeMap<String, String>();
			  inmap.put("ver","1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service","acbss");
			  inmap.put("msgtype","chgprtmsreq");
			  inmap.put("appkey","wywawj");
			  inmap.put("subid",subId);
			  inmap.put("rsvd","0");
			  inmap.put("acms",acms);
			  inmap.put("oldprtms",oldprtms);
			  inmap.put("newprtms",newprtms); 
			  inmap.put("chgts",chgts);
			  inmap.put("callrecording","1");
			 // inmap.put("anucode","218,218,218");
			  inmap.put("anucodecalled",ringcalled);
			  inmap.put("anucodecaller",ringcalling);
			  inmap.put("smsmtchannel", "3");
			  System.out.println(inmap.entrySet());
				
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");		
			  JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("ver","1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service","acbss");
					jsonObject.put("msgtype","chgprtmsreq");
					jsonObject.put("appkey","wywawj");
					jsonObject.put("subid",subId);
					jsonObject.put("rsvd","0");
	     			jsonObject.put("acms",acms);
					jsonObject.put("oldprtms",oldprtms);
					jsonObject.put("newprtms",newprtms);
					jsonObject.put("sid", sid);
					jsonObject.put("chgts",chgts);
					jsonObject.put("callrecording","1");
					//jsonObject.put("anucode","218,218,218");
					jsonObject.put("anucodecalled",ringcalled);
					jsonObject.put("anucodecaller",ringcalling);
					jsonObject.put("smsmtchannel", "3");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String requestContent = jsonObject.toString();

				System.out.println("请求参数"+requestContent.toString());
	  
			
				String respCon = null;
				try {
					Map<String,String> map = new HashMap<String,String>();
					map.put("Content-Type", "application/json");
					map.put("charset", "gbk");
					respCon=HttpClientUtil.sendPost(requestContent);
				  // 返回参数
					System.out.println("返回参数"+respCon);
	             	
				} catch (Exception e) {
					e.printStackTrace();
				}
				return respCon;
						
	    } 
	public static void main(String[] args){
		String  acms="18600403881";
		String   prtms="13810326774";
		//axChangeNum(prtms,acms);
		axChangeNum("73b046fed52f47dda8a3b92644e79a1e","13162483134","15821493991","17621536640","307","303");
	}
}
