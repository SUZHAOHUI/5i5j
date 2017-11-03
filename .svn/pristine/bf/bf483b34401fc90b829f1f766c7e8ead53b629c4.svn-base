
package com.oio.wawj.quartz;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.oio.wawj.service.SubsRelaTransferService;
import com.oio.wawj.service.impl.SubsRelaTransferServiceImpl;






public class DayDataQuartzTask extends QuartzJobBean {
	
	
	 private SubsRelaTransferService subsRelaTransferService; 



	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		subsRelaTransferService.creatTempSubsRela();
		subsRelaTransferService.insetTempSubsRela();
		List<Object> list = subsRelaTransferService.findSubsRelaList();
				JSONObject json=new JSONObject();
				JSONObject jo=new JSONObject();
				JSONArray ja=new JSONArray();
				Object[] o=(Object[]) list.get(0);
			     String batchNum=(String) o[3];
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
		 System.out.println(json.toString());
		 subsRelaTransferService.deleteTempSubsRela();
	  }
	public SubsRelaTransferService getSubsRelaTransferService() {
		return subsRelaTransferService;
	}

	public void setSubsRelaTransferService(
			SubsRelaTransferService subsRelaTransferService) {
		this.subsRelaTransferService = subsRelaTransferService;
	}
}
