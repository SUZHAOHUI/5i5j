package com.oio.wawj.struts.action;

import java.util.List;

import com.oio.wawj.service.SubsRelaTransferService;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.WyHttpClientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubsRelaTransferAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject result;
	private SubsRelaTransferService service;
	




	public void relasTranfer(){
		
		service.creatTempSubsRela();
		service.insetTempSubsRela();
		List<Object> list = service.findSubsRelaList();
				JSONObject json=new JSONObject();
				JSONObject jo=new JSONObject();
				JSONArray ja=new JSONArray();		
		         json.put("batchNum", DateTime.createBatchNum());
		      
		 for(int i=0;i<2;i++){
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
		 System.out.println(json.toString());
		 String pathUrl="http://10.1.17.140:8080/broker-server/broker/v1/virtulTel";
		 String resq = WyHttpClientUtil.sendPost(json.toString(),pathUrl);
		  System.out.println(resq);
		 service.deleteTempSubsRela();
         //return SUCCESS;
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
