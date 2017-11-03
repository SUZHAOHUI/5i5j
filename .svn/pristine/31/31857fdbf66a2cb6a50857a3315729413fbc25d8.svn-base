package com.oio.wawj.convertor;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Entity;
import ognl.DefaultTypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class BigDecimalConverter extends DefaultTypeConverter {

//	private static final Log logger = LogFactory.getLog(DateConverter.class);
	private static final Logger logger = LoggerFactory.getLogger(BigDecimalConverter.class);

	/**
	 * Convert value between types
	 */
	public Object convertValue(Map ognlContext, Object value, Class toType) {
		Object result = null;
				
		if (toType == BigDecimal.class) {
			result = doConvertToBigDecimal(value);
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
	public static BigDecimal doConvertToBigDecimal(Object value) {
		BigDecimal result = null;
		
		if (value == null || value.equals(""))
			return null;

		if (value instanceof String) {
			// TODO add date converter parse order here
			try {
				result = new BigDecimal((String) value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Converting from String to BigDecimal fails!");
				e.printStackTrace();
			}
			
		} else if (value instanceof Object[]) {
			// let's try to convert the first element only
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToBigDecimal(value);
			}

		} else if (value instanceof BigDecimal) {
			result = (BigDecimal) value;
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
		
		String result = null;
		if (value instanceof BigDecimal) {
			result = ((BigDecimal) value).toString();
		}
		return result;
	}
}
