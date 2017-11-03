/**
 * Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
 * All rights reserved.
 */
package com.oio.wawj.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

//����ģʽʵ�ֶ�ȡzxc.properties�ļ�������
@Entity
public class OVLoadProperties {
	// ����һ���Լ���ʵ��
	@OneToOne(mappedBy = "instance")
	private static OVLoadProperties instance = new OVLoadProperties();
	final static String fileName = "/config.properties";
	// ���ظ�ʵ��
	public static synchronized OVLoadProperties getInstance() {
		return instance;
	}

	// ��ȡkey���Ӧ��ֵ
	public String getProperties(String key) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			// zxc.properties�ļ�����srcĿ¼���±�
			is = OVLoadProperties.class.getResourceAsStream(fileName);
			if (is == null)
				is = new FileInputStream(fileName);
			p.load(is);
		} catch (Exception e) {
			System.out.println("�����ļ�������!" + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		return p.getProperty(key);
	}
	public void setProperties(String key,String value) {
		Properties p = new Properties();
		FileOutputStream fos = null;
		InputStream is=null;
		try {
			// zxc.properties�ļ�����srcĿ¼���±�
			  is = OVLoadProperties.class.getResourceAsStream(fileName); 
		      p.load(is); 
			if (fos == null)
			     fos = new FileOutputStream(fileName);
			     p.setProperty(key,value);
			     p.store(fos, null);
		} catch (Exception e) {
			System.out.println("文件加载错误!" + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
}