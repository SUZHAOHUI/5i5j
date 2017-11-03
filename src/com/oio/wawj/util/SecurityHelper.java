
package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import javax.persistence.Entity;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Entity
public class SecurityHelper 
{
	/**
	 * md5加密
	 * 
	 * @param plainText 待加密内容
	 * @param len 长度
	 * @return
	 */
	public static String Md5(String plainText, int len)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (len == 16)
				return buf.toString().substring(8, 24);
			else
				return buf.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 默认md5的32加密
	 * 
	 * @param src 待加密内容
	 * @return String
	 */
	public static String Md5(String src)
	{
		return Md5(src, 32);
	}

	/**
	 * 加密
	 * @param text 待加密内容
	 * @param key 公钥
	 * @return
	 */
	public static String DESEncrypt(String text, String key)
	{
		try
		{
			// ����3-DES���ܺ�����ݵ��ֽ�
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
			byte[] keys =null;
			
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey skey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			byte[] encryptedData = cipher.doFinal(text.getBytes());
			// ����3-DES���ܺ�����ݽ���BASE64����
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(encryptedData);
		} catch (Exception e){
			e.printStackTrace();
			return text;
		}
	}
	
	/**
	 * 解密
	 * @param text 待解密内容
	 * @param key 公钥
	 * @return
	 */
	public static String DESDecrypt(String text, String key)
	{
		try
		{
			// 进行3-DES加密后的内容进行BASE64解码
			BASE64Decoder base64Decode = new BASE64Decoder();
			byte[] base64DValue = base64Decode.decodeBuffer(text);
			// 进行3-DES解密后的内容的字节
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey skey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			byte[] encryptedData = cipher.doFinal(base64DValue);
			return new String(encryptedData);
		} catch (Exception e)
		{
			e.printStackTrace();
			return text;
		}
	}
	
	public static void startWork() {
		BufferedReader br = null;
		String key = "9i!3w5r6t%9x8n7b&gx3.-63js?59sp+=-";
		try {
			br = new BufferedReader(new InputStreamReader(System.in, "GBK"));
			
			System.out.println("*********Welcome**********\n");
			System.out.println("Please input password (Input Q to quit.):");
			String inputString = null;
			while (true) {
				inputString = br.readLine();
				if ("ucrm".equalsIgnoreCase(inputString)) {
					break ;
				} else if ("Q".equalsIgnoreCase(inputString))
					return ;
				else
					System.out.println("Wrong password, please input again, or input Q to quit.");
			}
			
			String banks[]={};
			while (true) {
				System.out.println("Please input the type of operation:") ;
				System.out.println("(Input E: for encryption, D: for decryption, Q: for quit.)");
				inputString = br.readLine();
				if ("Q".equalsIgnoreCase(inputString))
					return ;
				if ("E".equalsIgnoreCase(inputString)) {
					System.out.println("Please input the string for encryption, and use ',' to separate multiple strings:");
					inputString = br.readLine();
					banks = inputString.split(",");
					for(String a:banks){
						a = DESEncrypt(a, key);
						System.out.println(a);
					}
				} else if ("D".equalsIgnoreCase(inputString)) {
					System.out.println("Please input the string for decryption, and use ',' to separate multiple strings:");
					inputString = br.readLine();
					banks = inputString.split(",");
					for(String a:banks){
						a = DESDecrypt(a, key);
						System.out.println(a);
					}
				} else {
					System.out.println("Wrong Input��");
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		startWork();
	}
	

}