package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;

import org.json.JSONObject;

import redis.clients.jedis.Jedis;




public class AxOrder {
	 
	public static String axOrder (String prtms,String acms,String ringcalled,String ringcalling ){ 
	//public static String axOrder (String prtms,String acms){  
          
      
	    	String name ="123";
	    	String cardno = "232444";
	    	String cardType="1";
	    	String msgid = UUIDGenerator.getUUID();
	    
	 
		    String ts= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String subts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	        TreeMap<String, String> inmap = new TreeMap<String, String>();
			  inmap.put("ver","1.0");
			  inmap.put("msgid",msgid);
			  inmap.put("ts", ts);
			  inmap.put("service","acbss");
			  inmap.put("msgtype","subreq");
		      inmap.put("appkey","wywawj");
			  inmap.put("prtms",prtms);
			  inmap.put("rsvd","0");
			  inmap.put("acms",acms);
			  inmap.put("subts",subts);
			  inmap.put("producttype","4"); 
			  inmap.put("name",name);
			  inmap.put("cardtype",cardType);
			  inmap.put("cardno",cardno);
			  inmap.put("callrecording","1");
			  //inmap.put("anucode","305,309,309");
			  inmap.put("anucodecalled",ringcalled);
			  inmap.put("anucodecaller",ringcalling);
			  inmap.put("smsmtchannel", "3");
			  System.out.println(inmap.entrySet());
				
			  String sid = SignDigest.signature(inmap, "+ZOLLJ/EFIQEVIWM","sid");		
			 // String sid = SignDigest.signature(inmap, "L9HASRNCM0IQ","sid");	
			  JSONObject jsonObject = new JSONObject();

				try {
					jsonObject.put("ver","1.0");
					jsonObject.put("msgid",msgid);
					jsonObject.put("ts", ts);
					jsonObject.put("service","acbss");
					jsonObject.put("msgtype","subreq");
					jsonObject.put("appkey","wywawj");
					jsonObject.put("prtms",prtms);
					jsonObject.put("rsvd","0");
	     			jsonObject.put("acms",acms);
					jsonObject.put("subts",subts);
					jsonObject.put("producttype","4");
					jsonObject.put("sid", sid);
					jsonObject.put("name", name);
					jsonObject.put("cardtype",cardType);
					jsonObject.put("cardno", cardno);
					jsonObject.put("callrecording","1");
					//jsonObject.put("anucode","305,309,309");
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
	 public static void  insertData(){
			String pathName = "C:\\data\\333.txt";
		    File filename = new File(pathName);
		    InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(  
				        new FileInputStream(filename));
			    BufferedReader br = new BufferedReader(reader);
			    String line = "";
			    int count=1;
		  
				 while ((line = br.readLine()) != null) {  
			           //一次读入一行数据 
					  String acms="";
			            String[] strArray = line.split(" ");
			            String prtms=strArray[0];
			            if(strArray.length>1){
			              acms=strArray[1];
			              axOrder(prtms,acms,"307","303");
			            }
			           
					    System.out.println("count-----"+count+":"+acms);
			           
			            count++;
			            
			        }
				 br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	      
			
		}
	 public static Jedis  JedisRun70(){
		   Jedis jedis = new Jedis("101.201.101.70");
		   
		   jedis.auth("acsystem");
		   return jedis;
				}
	 public static void  redis(){
			String pathName = "C:\\data\\456.txt";
		    File filename = new File(pathName);
		    InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(  
				        new FileInputStream(filename));
			    BufferedReader br = new BufferedReader(reader);
			    String line = "";
			    int count=1;
	            File writename = new File("C:\\data\\redisKey.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
                writename.createNewFile(); // 创建新文件  
                BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				 while ((line = br.readLine()) != null) {  
			           //一次读入一行数据 
					  String acms="";
					  acms = line;
			        
			         
					
					    Jedis jedis = JedisRun70();
			            count++;
			            if(line !=null) {
			            	 System.out.println("count-----"+count+":"+acms);
			            Set<String> keys = jedis.keys("*"+acms+"*s");
			            for (String str : keys) {
			            	 System.out.println("count-----"+count+":"+str);
//		                 out.write(str+"\r\n");
//		                 out.flush();
			            }
			            }
			        }
				  out.close();
				 br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	      
			
		}
	public static void main(String[] args){
		String  acms="13162463424";
		String   prtms="13303400523";
     	axOrder(prtms,acms,"305","303");
		// redis();
	}
}
