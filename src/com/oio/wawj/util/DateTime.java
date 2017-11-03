/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author guoqingning 2013-10-22
 * 
 */
public class DateTime {

	// java�л�ȡ��ǰ���ں�ʱ��ķ���
	public static Timestamp getCurrentDateTime() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Date nowDateTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// ���Է�����޸����ڸ�ʽ
		
		now = Timestamp.valueOf(dateFormat.format(nowDateTime.getTime()));

		return now;
	}
	
	public static String DateToString(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "";
		if(date != null)
			str = dateFormat.format(date);
		return str;
	}
	
}
