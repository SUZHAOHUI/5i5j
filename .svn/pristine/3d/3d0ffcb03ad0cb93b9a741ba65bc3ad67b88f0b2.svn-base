package com.oio.wawj.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.dao.OnHookSmsRecordDAO;
import com.oio.wawj.service.OnHookSmsRecordService;
import com.oio.wawj.util.PageListData;
@Entity
public class OnHookSmsRecordServiceImpl implements OnHookSmsRecordService {

	@ManyToOne
	private OnHookSmsRecordDAO dao;
	
	
	
	
	public OnHookSmsRecordDAO getDao() {
		return dao;
	}




	public void setDao(OnHookSmsRecordDAO dao) {
		this.dao = dao;
	}




	@Override
	public PageListData findOnHookSmsRecord(Map param,int currentPage, int pageSize) {
		
		String sql = " select o.timestamp ,o.target_number as targetNumber ,o.state , o.scenario ,o.content ,o.call_id as callId," +
				"o.user_name as userName,o.charge_info as chargeInfo ,o.cnt_sms as cntsms";
	   String sql1=	" from on_hook_sms_record o" +
	   		        " inner join user u on u.user_id=o.user_id " ;  
		

		// TODO Auto-generated method stub
	   
		return dao.getOnHookSmsRecord(sql,sql1,param, currentPage, pageSize);
	}
	
	@Override
	public List findCdrCallById(String callId) {
	
	   
		return dao.getCdrCallById(callId);
	}
	
	
	static Logger logger = LoggerFactory.getLogger(CallRecordsServiceImpl.class);
	@SuppressWarnings({ "rawtypes" })
	public String notifyRecordExportExcel(List list, String targetDirectory,String code) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("通知记录-挂机记录");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("时间");
		al.add("客户号码");
		al.add("状态(S成功、F失败)");
		al.add("场景");
		al.add("内容");
		al.add("短信ID");
		al.add("员工姓名");
		al.add("费用形式");
		al.add("计费(条)");
		
		for (int i = 0; i < al.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(al.get(i));
			cell.setCellStyle(style);
		}

		if (list != null) {
			
			try{
				for(int i = 0; i < list.size(); i++){
					Object[] obj = (Object[]) list.get(i);
					row = sheet.createRow(i + 1);
					for(int j = 0; j < obj.length; j++){
						row.createCell(j).setCellValue(obj[j] != null ? obj[j].toString():"");
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}



		try {
			fileName = code + "_通知记录_挂机记录"  + ".xls";
			File file = new File(targetDirectory, fileName);
			if (!file.isDirectory()) {
				file.delete();
			}
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			fileName = null;
		}
		return fileName;
	}
	
	
	
}
