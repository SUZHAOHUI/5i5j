package com.oio.wawj.util;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public  class FileFilterHelper implements FTPFileFilter {
	

        public boolean accept(File pathname) {  
            String filename = pathname.getName();  
            if(filename.contains("635980299e180214")){  
                return true;  
            }else{  
                return false;  
            }  
       
        }

		@Override
		public boolean accept(FTPFile pathname) {
			// TODO Auto-generated method stub
		       String filename = pathname.getName();  
	           // System.out.println(filename);
	                return true;  
	           
		}

	
       
}
