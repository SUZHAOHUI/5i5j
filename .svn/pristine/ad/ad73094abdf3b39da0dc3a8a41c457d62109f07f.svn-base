package com.oio.wawj.util;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 */
public class SignDigest {

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
	

	
	public static void main(String[] args) throws Exception {
		
		 TreeMap<String, String> inmap = new TreeMap<String, String>();
//		  inmap.put("ver", "1.0");
//		  inmap.put("msgid","1d5006a76e1f421a96d2c7b872e6aea0");
//		  inmap.put("ts", "20161227150634466");
//		  inmap.put("service", "acbss");
//		  inmap.put("msgtype", "subreq");
//		  inmap.put("appkey","hwh");
//		  inmap.put("prtms","15037900958");
//		  inmap.put("rsvd","0");
////		  inmap.put("acms","");
//		  inmap.put("subts","20161227150634");
//		  inmap.put("producttype","13"); 
//		  inmap.put("name","test");
//		  inmap.put("cardtype","1");
//		  inmap.put("cardno","410381199112041520");
	 // 'sid': '2AC482459F7B856B0534820FE004E0FD'
		
			 inmap.put("ver","1.0");
		     inmap.put("otherms", "15037900958");
		     inmap.put("rsvd", "0");
		     inmap.put("msgid", "5742461579df5400606dce74sub");
		     inmap.put("ts", "20160816142221231");
		     inmap.put("service", "acbss");
		     inmap.put("callrestrict", "1");
		     inmap.put("acms","13152515747");
		     inmap.put("msgtype", "axbsubupdreq");
		     inmap.put( "appkey", "hwh");
		     inmap.put("prtms", "13810326774");
		     inmap.put("subts","201608161422");
		     inmap.put("productcat","11");
             inmap.put("subid", "52afb521bcb94ea3804a475094d8ec35");
//		  inmap.put("subid","3174fa43ce9d4dffb3bccf0a4d41f6fd");
//		  inmap.put("callrecording","0");
		  System.out.println(inmap.entrySet());
			
		  String sid = SignDigest.signature(inmap, "smallapple","sid");
		
		  System.out.print(sid);
	}
}
