
package com.oio.wawj.util;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;





	public class FTPClicentHelper {
	
	public static boolean download(String callId,String targetDirectory)throws IOException {
		FTPClient ftpClient = new FTPClient();
		boolean result = false;
		String ftpUrl = OVLoadProperties.getInstance().getProperties("ftpHostIp");
		String userName=OVLoadProperties.getInstance().getProperties("ftpUser");
		String password=OVLoadProperties.getInstance().getProperties("ftpPassword");
		FileOutputStream fs=null;
		try {
		ftpClient.connect(ftpUrl,21);
		ftpClient.login(userName, password);
		System.out.println("ftp connect success");
		ftpClient.enterLocalPassiveMode();
		ftpClient.setBufferSize(1024*512);
		ftpClient.setControlEncoding("GBK");
		ftpClient.setDataTimeout(2000);
		//转到当前目录
		ftpClient.changeToParentDirectory();
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	
	
		String downloadName=targetDirectory+"/"+callId+".mp3";
		System.out.println(downloadName);
		 
		 String patternStr ="HWH_*"+callId+"*.mp3";
		 FTPFile[] f = ftpClient.listFiles(patternStr, new FileFilterHelper());
		
		 fs = new FileOutputStream(downloadName); 
		 String fileName=f[0].getName();
		 System.out.println(fileName);
		 result = ftpClient.retrieveFile(fileName,fs);
	
		 fs.close();
		 
		 return result;
		} catch(NumberFormatException e){
			e.printStackTrace();
		throw e;
		} catch(FileNotFoundException e){
			e.printStackTrace();
		throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		throw new IOException(e);
		} finally {
		try {
			
		    ftpClient.disconnect();
		 
		} catch (IOException e) {
		throw new RuntimeException("ftp关闭异常");
		}
		}
		}
		    public static void main(String[] args) {  

		    	   try {
		    		   boolean flag = download("635980299e180214","");
		    		   System.out.println(flag);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		    }  
  }
