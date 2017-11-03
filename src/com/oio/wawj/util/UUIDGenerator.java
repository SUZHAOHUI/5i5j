package com.oio.wawj.util;

import java.util.UUID;

public class UUIDGenerator { 
    public UUIDGenerator() { 
    } 
    /** 
     * 获得�?��UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
   
    public static void main(String[] args){ 
        String ss = getUUID(); 
   
    }
}   
