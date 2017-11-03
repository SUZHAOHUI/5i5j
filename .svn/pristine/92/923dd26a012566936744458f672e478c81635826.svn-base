package com.oio.wawj.struts.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.CollectionId;
import com.oio.wawj.service.CollectionIdService;
import com.oio.wawj.util.WebServiceTest;
import com.opensymphony.xwork2.ActionContext;


public class CollectionAction extends BaseAction {

	/**
	 * 
	 */
	private CollectionId collectionId;
	private CollectionIdService collectionIdService;
	
	private String transferId;
	private String batchNum;
	private String transferDateTime;
	private String pushDataType;
	private String setId;
	private String descr;
	private String descrShort;
	private JSONObject result;
	private String list;
	
	 
	
	private static final long serialVersionUID = 1L;
	
	public String info() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response=(HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);		
		ServletInputStream inputStrem=null;	
		
		String charact = request.getCharacterEncoding();
		System.out.println("---------------"+charact);
		try {
			 inputStrem = request.getInputStream();
		    // 响应时处理数据   
					StringBuffer sb = new StringBuffer();   
					String readLine;   
					BufferedReader responseReader;   
		    responseReader = new BufferedReader(new InputStreamReader(inputStrem, charact));
		    while ((readLine = responseReader.readLine()) != null) {   
			     sb.append(readLine).append("\n");   
			   }  
		        String resContent=sb.toString();
		        JSONObject resjo=JSONObject.fromObject(resContent);
		        System.out.println("collection---------"+sb.toString());
				responseReader.close();
				
				JSONArray bl=resjo.getJSONArray("batchLines");
				
			    String batchNum=resjo.getString("batchNum");
			    String transferId =resjo.getString("transferId");
			    String pushDataType = resjo.getString("pushDataType");
			    String transferDateTime= resjo.getString("transferDateTime");

                for (int i = 0; i <bl.size(); i++) {
                	JSONObject js=  JSONObject.fromObject(bl.get(i));
                	String setId=js.getString("setId");
                	String descr=js.getString("descr");
                	String descrShort=js.getString("descrShort");
    			    
    			    collectionIdService.save(setId,descr,descrShort,transferId,batchNum,pushDataType,transferDateTime);
			        if(i%20 == 19){
			        	collectionIdService.flush();
			        }
                	
				}
       
			    
			    response.setContentType("application/json;charset=utf-8");  
			    
			    JSONObject resqJo=new JSONObject();

			    resqJo.put("success", "S");  
			    resqJo.put("msg", "ESB集合ID接口同步成功");  
			    resqJo.put("reason", "");  
			    resqJo.put("code", "000000");  
			    resqJo.put("batchNum", batchNum);  
			    resqJo.put("data", "");
			    
				result=resqJo;

				
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		

		return SUCCESS;
				
		
	}

   
	
	
	
	public CollectionId getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(CollectionId collectionId) {
		this.collectionId = collectionId;
	}

	public CollectionIdService getCollectionIdService() {
		return collectionIdService;
	}

	public void setCollectionIdService(CollectionIdService collectionIdService) {
		this.collectionIdService = collectionIdService;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getTransferDateTime() {
		return transferDateTime;
	}

	public void setTransferDateTime(String transferDateTime) {
		this.transferDateTime = transferDateTime;
	}

	public String getPushDataType() {
		return pushDataType;
	}

	public void setPushDataType(String pushDataType) {
		this.pushDataType = pushDataType;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDescrShort() {
		return descrShort;
	}

	public void setDescrShort(String descrShort) {
		this.descrShort = descrShort;
	}
	
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}


	public String getList() {
		return list;
	}


	public void setList(String list) {
		this.list = list;
	}
	
	
}

