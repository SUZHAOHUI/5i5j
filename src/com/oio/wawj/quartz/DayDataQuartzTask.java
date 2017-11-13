
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
		
		
	  }
	public SubsRelaTransferService getSubsRelaTransferService() {
		return subsRelaTransferService;
	}

	public void setSubsRelaTransferService(
			SubsRelaTransferService subsRelaTransferService) {
		this.subsRelaTransferService = subsRelaTransferService;
	}
}
