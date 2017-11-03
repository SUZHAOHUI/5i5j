package com.oio.wawj.struts.action;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

import com.oio.wawj.service.DepartmentService;
import com.oio.wawj.util.WebServiceTest;

public class ERPAction extends BaseAction {
	 private DepartmentService deptService;
	 private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public DepartmentService getDeptService() {
		return deptService;
	}

	public void setDeptService(DepartmentService deptService) {
		this.deptService = deptService;
	}

	public  String getERP(){
	    	  
	    	  try {
	    
	              String endpoint_1 = "http://101.251.207.10/5i5j/services/UsersMobilePhone?wsdl";

	              String namespace="http://website.webService.erp.wiwj.com/";
	              Service service = new Service();
	              Call call = (Call) service.createCall();
	              SOAPHeaderElement ss = WebServiceTest.getSoapHeader("shanghaiws", "shbacic5i5j");

	              //print(ss.getAsDocument());

	              call.addHeader(ss);

	              call.setTargetEndpointAddress(new java.net.URL(endpoint_1));
	              //������
	              call.setOperationName(new QName(namespace, "getUsersMobilePhone"));

	              call.setUseSOAPAction(true);
	              //Action��ַ
	              call.setSOAPActionURI(endpoint_1);
	              //System.out.println(call.g("arg0"));
	              //call.setReturnType(new QName(namespace, "arg0"));
	              Object[] obj = {};
//	              Object[] obj = {arg0,arg1,arg2,arg3};
	              String result = (String) call.invoke(obj);
	              JSONArray ja=JSONArray.fromObject(result);
	              JSONObject json=(JSONObject) ja.get(0);
	              String data=json.getString("value");
	              System.out.println("------------"+data);
	              JSONArray bl=JSONArray.fromObject(data);
	              for (int i = 0; i <bl.size(); i++) {
	            	  //USERSID  HANDSET  DEPTNAME USERNAME
	            	  JSONObject js=  JSONObject.fromObject(bl.get(i));
	            	  
	            	  System.out.println(js.toString());
	                	String emplId=js.getString("USERSID");
	                	String mobile=js.getString("HANDSET");
	                	String dn=js.getString("DEPTNAME");
	                	String un=js.getString("USERNAME");
	                	deptService.saveErp(emplId, mobile, dn, un);
	                	 if(i%20 == 19){
	                		 deptService.flush();
	  			        }
	                    
	              }
	            
	              System.out.println("result= 接收完了");
	          }
                   
	          catch (Exception e) {
	              System.err.println(e.toString());
	          }
	         
	         JSONObject json=new JSONObject();
	         json.put("result","成功储存" );
	    	  return SUCCESS;
	      }
		
}
