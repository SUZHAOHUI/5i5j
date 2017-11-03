package com.oio.wawj.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oio.wawj.dao.CallInAnalyzeDAO;
import com.oio.wawj.service.CallInAnalyzeService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.PageListData;
import com.oio.wawj.util.UUIDGenerator;

public class CallInAnalyzeServiceImpl implements CallInAnalyzeService {

	private CallInAnalyzeDAO dao;
	
	
	
	public CallInAnalyzeDAO getDao() {
		return dao;
	}

	public void setDao(CallInAnalyzeDAO dao) {
		this.dao = dao;
	}

	@Override
	public PageListData findList(Map param, int currentPage, int pageSize) {
		
		
		// TODO Auto-generated method stub
	 // String sql = "select c_t.channel_name ,sum(c_t.connect),sum(c_t.disconnect),sum(c_t.cnt),round(sum(c_t.connect)/sum(c_t.cnt)*100,2) ";
		String sql = "select  c_t.channel_name as channelName,ifnull(sum(c_t.connect),0) as connTotal,ifnull(sum(c_t.disconnect),0) as disconnTotal,ifnull(sum(c_t.cnt),0) as callTotal," +
		 		" ifnull(round(sum(c_t.connect)/sum(c_t.cnt)*100,2),0) as connRate  ";
		String sql2= " from (select c.channel_name as channel_name ," +
				     " case when cc.release_time>cc.start_time then count(*) end as connect," +
				     " case when cc.release_time<=cc.start_time then count(*) end as disconnect,count(*) as cnt "; 
            
	  	String sql3= " inner join acms on cc.acms=acms.acms " +
	  		" inner join (select channel_id,acms_id,user_id from user_acms_rela where purpose='C' ) uar on acms.acms_id= uar.acms_id " +
	  		" inner join channel c on c.channel_id=uar.channel_id " +
	  		" inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
	  		" inner join org o on o.org_id=u.org_id and o.set_id=u.set_id " ;
	    
		 if (param.get("checkBox") != null 
					    && ((String) param.get("checkBox")).equals("true")
				  ) {

			 sql2 += " from (select distinct prtms,acms,otherms,release_cause,release_time,start_time,call_time  from cdr_call where call_type in ('1')) as cc " ;
		  }else{
			  
			 sql2 += " from cdr_call cc " ;
		  }
		
	    String sql1 = sql2+sql3;
		 
		
	   return dao.getList(sql,sql1,param, currentPage, pageSize);
	}

	
	@Override
	public PageListData findDeptList(Map param, int currentPage, int pageSize) {
		
		
		// TODO Auto-generated method stu
		 String sql = " select c_t.org_name as orgName,ifnull(sum(connect),0) as connTotal ,ifnull(sum(disconnect),0) as disconnTotal ,ifnull(sum(cnt),0) as callTotal , ifnull(round(sum(connect)/sum(cnt)*100,2),0) as connRate ";
		 
		 String sql2 = " from (select o.org_name, " +
				" case when cc.release_time>cc.start_time then count(*) end  as connect, " +
				" case when cc.release_time<=cc.start_time then count(*) end  as disconnect," +
				" count(*) cnt" ;
				
				
		 String sql3 =	" inner join acms on cc.acms=acms.acms" +
				" inner join (select channel_id,acms_id,user_id from user_acms_rela ) uar on acms.acms_id= uar.acms_id" +
				" inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
				" inner join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" inner join channel c on c.channel_id=uar.channel_id " ;
		 
		 
		 if (param.get("checkBox") != null 
				    && ((String) param.get("checkBox")).equals("true")
			  ) {

		 sql2 += " from (select distinct prtms,acms,otherms,release_cause,release_time,start_time,call_time from cdr_call where call_type in ('1')) as cc " ;
	  }else{
		  
		 sql2 += " from (select * from cdr_call where call_type in ('1')) cc " ;
	  }
		 
 
		 
		 String sql1 = sql2+sql3;
	   return dao.getDeptList(sql,sql1,param, currentPage, pageSize);
	}
	
	

	@Override
	public PageListData findStaffList(Map param, int currentPage, int pageSize) {
		
		
		// TODO Auto-generated method stub
		String sql = " select c_t.name as name ,c_t.channel_name as channelName ,c_t.code as code ,ifnull(sum(connect),0) as connTotal ," +
				     " ifnull(sum(disconnect),0) as disconnTotal ,ifnull(sum(cnt),0) as callTotal , ifnull(round(sum(connect)/sum(cnt)*100,2),0) as connRate ";
		String sql2= " from (select u.name,case when cc.release_time>cc.start_time then count(*) end  as connect," +
				     " case when cc.release_time<=cc.start_time then count(*) end  as disconnect, count(*) cnt, c.channel_name, u.code "; 
            
	  	String sql3= " inner join acms on cc.acms=acms.acms " +
	  			     " inner join (select channel_id,acms_id,user_id from user_acms_rela ) uar on acms.acms_id= uar.acms_id " +
	  			     " inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
	  			     " inner join org o on o.org_id=u.org_id and o.set_id=u.set_id" +
	  			     " inner join channel c on c.channel_id=uar.channel_id " ;
	    
		 if (param.get("checkBox") != null 
					    && ((String) param.get("checkBox")).equals("true")
				  ) {

			 sql2 += " from (select distinct prtms,acms,otherms,release_cause,release_time,start_time,call_time  from cdr_call where call_type in ('1')) as cc " ;
		  }else{
			  
			 sql2 += " from (select * from cdr_call where call_type in ('1')) cc " ;
		  }
		
	    String sql1 = sql2+sql3;
		 
		
	   return dao.getStaffList(sql,sql1,param, currentPage, pageSize);
	}
	
	@Override
	public String findChannelName() {
		// TODO Auto-generated method stub
		return dao.getfindChannelName();
	}
	
	
	
	
	static Logger logger = LoggerFactory.getLogger(CallInAnalyzeServiceImpl.class);
	/**
	 * dept excel
	 */
	@SuppressWarnings({ "rawtypes" })
	public String deptExportExcel(List list, String targetDirectory, String code) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("虚拟号导入绑定");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("部门");
		al.add("已接通");
		al.add("未接通");
		al.add("总电话量");
		al.add("接通率(%)");
		
		for (int i = 0; i < al.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(al.get(i));
			cell.setCellStyle(style);
		}

		if (list != null) {
			//list = getDeptExportData(list,al.size());
			
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

		// 增加 渠道ID提醒行
		row = sheet.createRow((int) list.size() + 4);

		HSSFCellStyle style1 = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style1.setFont(font);

		try {
			fileName = code + "_呼入分析_按部门"  + ".xls";
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
	
/*
	@SuppressWarnings("rawtypes")
	private List getDeptExportData(List list, int num){
		List<String> numList = new ArrayList<String>();
		List<Object[]> dataList = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			        numList.clear();
			        Object[] o = (Object[]) list.get(i);
			        
			        o[0] = o[0].toString();
					o[1] = o[1].toString();
					o[2] = o[2].toString();
					o[3] = o[3].toString();
					
					dataList.add(o);
				}
			
		return dataList;
	}
*/
	
	
	/**
	 * staff excel
	 */
	
	@SuppressWarnings({ "rawtypes" })
	public String staffExportExcel(List list, String targetDirectory, String code) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("虚拟号导入绑定");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("员工姓名");
		al.add("渠道");
		al.add("员工编号");
		al.add("已接通");
		al.add("未接通");
		al.add("总电话量");
		al.add("接通率(%)");
		
		for (int i = 0; i < al.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(al.get(i));
			cell.setCellStyle(style);
		}

		if (list != null) {
			//list = getStaffExportData(list,al.size());
			
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

		// 增加 渠道ID提醒行
		row = sheet.createRow((int) list.size() + 4);

		HSSFCellStyle style1 = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style1.setFont(font);

		try {
			fileName = code + "_呼入分析_按坐席" + ".xls";
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
	
	
	
	
   /**
    * channel excel
    */
	
	@SuppressWarnings({ "rawtypes" })
	public String channelExportExcel(List list, String targetDirectory, String code) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("虚拟号导入绑定");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("渠道");
		al.add("已接通");
		al.add("未接通");
		al.add("总电话量");
		al.add("接通率(%)");
		
		for (int i = 0; i < al.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(al.get(i));
			cell.setCellStyle(style);
		}

		if (list != null) {
			//list = getChannelExportData(list,al.size());
			
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

		// 增加 渠道ID提醒行
		row = sheet.createRow((int) list.size() + 4);

		HSSFCellStyle style1 = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style1.setFont(font);

		try {
			fileName = code + "_呼入分析_按媒体" + ".xls";
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