package com.oio.wawj.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.ServletContext;

import ognl.DefaultTypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class DateConverter extends DefaultTypeConverter {

//	private static final Log logger = LogFactory.getLog(DateConverter.class);
	private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

//	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

//	private static final String DATE_PATTERN = "yyyy-MM-dd";
	
//	private static final String MONTH_PATTERN = "yyyy-MM";

	public static String getPattern() {
		ServletContext sc = ServletActionContext.getServletContext();
		return (String) sc.getAttribute("dateFmt");
	}
	
	/**
	 * Convert value between types
	 */
	public Object convertValue(Map ognlContext, Object value, Class toType) {
		Object result = null;
		if (toType == Date.class) {
			result = doConvertToDate(value);
		} else if (toType == String.class) {
			result = doConvertToString(value);
		}
		return result;
	}

	/**
	 * Convert String to Date
	 * 
	 * @param value
	 * @return
	 */
	public static Date doConvertToDate(Object value) {
		Date result = null;

		if (value == null || value.equals(""))
			return null;
		
		if (value instanceof String) {

			// TODO add date converter parse order here
			try {
				result = DateUtils.parseDate((String) value, new String[] { getPattern() });
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// all patterns failed, try a milliseconds constructor
			if (result == null && StringUtils.isNotEmpty((String)value)) {

				try {
					result = new Date(new Long((String) value).longValue());
				} catch (Exception e) {
					logger.error("Converting from milliseconds to Date fails!");
					e.printStackTrace();
				}

			}

		} else if (value instanceof Object[]) {
			// let's try to convert the first element only
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDate(value);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}
		return result;
	}

	/**
	 * Convert Date to String
	 * 
	 * @param value
	 * @return
	 */
	private String doConvertToString(Object value) {
		
		if (value == null || value.equals(""))
			return null;
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getPattern());
		String result = null;
		if (value instanceof Date) {
			result = simpleDateFormat.format(value);
		}
		return result;
	}
}
