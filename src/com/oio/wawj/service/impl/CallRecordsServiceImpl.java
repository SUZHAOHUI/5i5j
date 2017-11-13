
package com.oio.wawj.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oio.wawj.bean.CdrCall;
import com.oio.wawj.dao.CallRecordsDAO;
import com.oio.wawj.service.CallRecordsService;
import com.oio.wawj.util.PageListData;




    public  class CallRecordsServiceImpl implements CallRecordsService {
	private CallRecordsDAO dao;
	

	public void save(CdrCall entity) {
		// TODO Auto-generated method stub
	}
	

	public void delete(CdrCall entity) {
		// TODO Auto-generated method stub
	}
	

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
	}
	

	public void update(CdrCall entity) {
		// TODO Auto-generated method stub
	}
	
	
	
	/**
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
	
		String sql = " select  u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId,rd.name as releaseDir,cs.desc as csDesc ";
	   String sql1=	" from cdr_call cc " +
	   		    " inner join acms a on a.acms=cc.acms" +
				" inner join user_acms_rela uar on uar.acms_id=a.acms_id " +
				" inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
				" inner join channel c on c.channel_id=uar.channel_id " +
				" inner join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause " +
				" left join release_dir rd on rd.id = cc.release_dir";  
	   
	   
		 if (param.get("isDiff") != null 
				    && ((String) param.get("isDiff")).equals("true")
			  ) {
		      sql = " select distinct  ds.name ,ds.otherms ,ds.channel_name ," +
				" ds.prtms ,ds.code, min(ds.call_time) as call_time ,ds.acms " +
				" from (select u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" a.acms,u.code,cc.call_id as callId  ";
		      
		      sql1=	" from ( select cc1.* from (select prtms,acms,otherms,min(call_time) call_time" +
		      		" from cdr_call group by prtms,acms,otherms)  cc2   " +
		      		" inner join cdr_call cc1  on cc1.prtms=cc2.prtms and cc1.acms=cc2.acms " +
		      		" and cc2.otherms=cc1.otherms and cc2.call_time=cc1.call_time) cc " +
			   		    " inner join acms a on a.acms=cc.acms" +
						" inner join  user_acms_rela  uar on uar.acms_id=a.acms_id " +
						" inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
						" inner join channel c on c.channel_id=uar.channel_id " +
						" inner join org o on o.org_id = u.org_id and u.set_id=o.set_id " +
						" left join call_state cs on cs.id=cc.release_cause ";  
		 }

	   
		return dao.getList(sql,sql1,param, currentPage, pageSize);
	}
	 
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallInMessage( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		 
		String sql = " select  u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration , cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId ,cc.sms_content as content ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join user_acms_rela  uar on uar.acms_id=a.acms_id  " +
				" inner join user u on u.user_id=uar.user_id and u.tel=cc.prtms " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";       
		return dao.getCallInMessage(sql,sql1,param, currentPage, pageSize);
	}
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutRecord( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select  distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId  ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date " +
				" left join user u on u.user_id=uar.user_id  " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";
		
		 if (param.get("isDiff") != null 
				    && ((String) param.get("isDiff")).equals("true")
			  ) {
		      sql = " select distinct  ds.name ,ds.otherms ,ds.channel_name ," +
				" ds.prtms ,ds.code, min(ds.call_time) as call_time " +
				" from (select u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" a.acms,u.code,cc.call_id as callId  ";
		      
		      sql1=	" from ( select cc1.* from (select prtms,acms,otherms,min(call_time) call_time" +
		      		" from cdr_call group by prtms,acms,otherms)  cc2   " +
		      		" inner join cdr_call cc1  on cc1.prtms=cc2.prtms and cc1.acms=cc2.acms " +
		      		" and cc2.otherms=cc1.otherms and cc2.call_time=cc1.call_time) cc " +
			   		    " left join acms a on a.acms=cc.acms" +
						" left join user_acms_rela uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
						" left join user u on u.user_id=uar.user_id " +
						" left join channel c on c.channel_id=uar.channel_id " +
						" left join org o on o.org_id = u.org_id and u.set_id=o.set_id " +
						" left join call_state cs on cs.id=cc.release_cause ";  
		 }

		
		return dao.getCallOutRecord(sql,sql1,param, currentPage, pageSize);
	}
	
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutComeRecord( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId  ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
				" left join user u on u.user_id=uar.user_id  " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";      
		
		
		 if (param.get("isDiff") != null 
				    && ((String) param.get("isDiff")).equals("true")
			  ) {
		      sql = " select distinct  ds.name ,ds.otherms ,ds.channel_name ," +
				" ds.prtms ,ds.code, min(ds.call_time) as call_time " +
				" from (select u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" a.acms,u.code,cc.call_id as callId  ";
		      
		      sql1=	" from ( select cc1.* from (select prtms,acms,otherms,min(call_time) call_time" +
		      		" from cdr_call group by prtms,acms,otherms)  cc2   " +
		      		" inner join cdr_call cc1  on cc1.prtms=cc2.prtms and cc1.acms=cc2.acms " +
		      		" and cc2.otherms=cc1.otherms and cc2.call_time=cc1.call_time) cc " +
			   		    " left join acms a on a.acms=cc.acms" +
						" left join user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
						" left join user u on u.user_id=uar.user_id " +
						" left join channel c on c.channel_id=uar.channel_id " +
						" left join org o on o.org_id = u.org_id and u.set_id=o.set_id " +
						" left join call_state cs on cs.id=cc.release_cause ";  
		 }
		return dao.getCallOutComeRecord(sql,sql1,param, currentPage, pageSize);
	}
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutStranger( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId  ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
				" left join user u on u.user_id=uar.user_id " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";  
		
		
		 if (param.get("isDiff") != null 
				    && ((String) param.get("isDiff")).equals("true")
			  ) {
		      sql = " select distinct  ds.name ,ds.otherms ,ds.channel_name ," +
				" ds.prtms ,ds.code, min(ds.call_time) as call_time " +
				" from (select u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.start_time) as callDuration ,cs.name as csName ," +
				" a.acms,u.code,cc.call_id as callId  ";
		      
		      sql1=	" from ( select cc1.* from (select prtms,acms,otherms,min(call_time) call_time" +
		      		" from cdr_call group by prtms,acms,otherms)  cc2   " +
		      		" inner join cdr_call cc1  on cc1.prtms=cc2.prtms and cc1.acms=cc2.acms " +
		      		" and cc2.otherms=cc1.otherms and cc2.call_time=cc1.call_time) cc " +
			   		    " left join acms a on a.acms=cc.acms" +
						" left join  user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
						" left join user u on u.user_id=uar.user_id " +
						" left join channel c on c.channel_id=uar.channel_id " +
						" left join org o on o.org_id = u.org_id and u.set_id=o.set_id " +
						" left join call_state cs on cs.id=cc.release_cause ";  
		 }
		return dao.getCallOutStranger(sql,sql1,param, currentPage, pageSize);
	}
	
	
	
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutMessage( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId ,cc.sms_content as content ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  user_acms_rela  uar on uar.acms_id=a.acms_id  and uar.state_date=a.state_date  " +
				" left join user u on u.user_id=uar.user_id " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";       
		return dao.getCallOutMessage(sql,sql1,param, currentPage, pageSize);
	}
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutReplyRecord( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId ,cc.sms_content as content ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  from user_acms_rela  uar on uar.acms_id=a.acms_id and uar.state_date=a.state_date  " +
				" left join user u on u.user_id=uar.user_id  " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";       
		return dao.getCallOutReplyRecord(sql,sql1,param, currentPage, pageSize);
	}

	
	
	@SuppressWarnings("rawtypes")
	public PageListData findCallOutStrangerRecords( Map param,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = " select  distinct u.name ,cc.otherms ,c.channel_name ," +
				" cc.prtms ,cc.call_time ," +
				" UNIX_TIMESTAMP(cc.release_time) - UNIX_TIMESTAMP(cc.call_time) as duration,cs.name as csName ," +
				" o.org_name as oname,a.acms,u.code,cc.call_id as callId ,cc.sms_content as content ";
		String sql1=	" from cdr_call cc " +
				" left join acms a on a.acms=cc.acms" +
				" left join  user_acms_rela  uar on uar.acms_id=a.acms_id  and uar.state_date=a.state_date  " +
				" left join user u on u.user_id=uar.user_id " +
				" left join channel c on c.channel_id=uar.channel_id " +
				" left join org o on o.org_id=u.org_id and o.set_id=u.set_id " +
				" left join call_state cs on cs.id=cc.release_cause ";       
		return dao.getCallOutStrangerRecords(sql,sql1,param, currentPage, pageSize);
	}
	
	
	

	@SuppressWarnings("rawtypes")
	public boolean isExistSameProperty(Map param) {
		// TODO Auto-generated method stub
		return true;
	}
	

	
	public CallRecordsDAO getDao() {
		return dao;
	}

	public void setDao(CallRecordsDAO dao) {
		this.dao = dao;
	}

	static Logger logger = LoggerFactory.getLogger(CallRecordsServiceImpl.class);
	@SuppressWarnings({ "rawtypes" })
	public String callInListExportExcel(List list, String targetDirectory,String code) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("呼入记录-通话记录");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("员工姓名");
		al.add("客户号码");
		al.add("渠道");
		al.add("员工号码");
		al.add("拨打时间");
		al.add("拨打时长(秒)");
		al.add("状态");
		al.add("部门");
		al.add("小号");
		al.add("员工编号");
		al.add("录音ID");
		al.add("通话时长(秒)");
		al.add("释放方式");
		
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
			fileName = code + "_呼入记录_通话记录"  + ".xls";
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


	

