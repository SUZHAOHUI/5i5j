package com.oio.wawj.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.oio.wawj.service.SubsRelaTransferService;
import com.oio.wawj.util.WyHttpClientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubsRelaTransferAction {

	private JSONObject result;
	private SubsRelaTransferService service;
	




	public String relasTranfer(){
		
		List<Object> list = service.findSubsRelaList();
				JSONObject json=new JSONObject();
				JSONObject jo=new JSONObject();
				JSONArray ja=new JSONArray();
				Object[] o=(Object[]) list.get(0);
			     String batchNum=(String) o[3];
			     System.out.println("batchNum--------"+batchNum);
		         json.put("batchNum", batchNum);
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				   String code = (String)obj[0];
				   String acms = (String)obj[1];
				   String setId = (String)obj[2];
				   jo.put("brokerid", code);
				   jo.put("cityid", setId);
				   jo.put("virtualtel", "PC_"+acms);
				   ja.add(jo);
			}
			json.put("batchLines", ja.toString());
			String pathUrl="";
			//WyHttpClientUtil.sendPost(json.toString(),pathUrl );
			result=json;
			System.out.println("end");
		   return "success";
	}
	

	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	public SubsRelaTransferService getService() {
		return service;
	}
	public void setService(SubsRelaTransferService service) {
		this.service = service;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
