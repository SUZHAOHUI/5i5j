package com.oio.wawj.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class relasTransfer implements Runnable {

	private String batchNum;
	private String code;
	private String setId;
	private String acms;
	public relasTransfer(String batchNum,String code, String setId,String acms) { 
	        this.batchNum = batchNum; 
	        this.code = code; 
	        this.setId = setId; 
	        this.acms = acms; 
	    } 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		   json.put("batchNum", DateTime.createBatchNum());
		   jo.put("brokerid", code);
		   jo.put("cityid", setId);
		   jo.put("virtualtel", "PC_"+acms);
		   ja.add(jo);
		   json.put("batchLines", ja.toString());
		   System.out.println(json.toString());
//		   String pathUrl="http://10.10.116.42:8082/broker-server/broker/v1/virtulTel";
//		   WyHttpClientUtil.sendPost(json.toString(),pathUrl);
	}
	
	public static void main(String arg[]){
		relasTransfer tf=new relasTransfer("12","33","44","55");
		   Thread t1 = new Thread(tf);
		   t1.start();
	}
}
