package com.oio.wawj.util;

import java.util.Random;

import javax.persistence.Entity;
@Entity
public class RandomPassword {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pasword = random();
		System.out.println(pasword);
	}
	//���9λ�����
	   public static String random(){
		   String val = "";
		   Random random = new Random();
		   for (int i = 0; i < 9; i++) {
		    // �����ĸ��������
		    String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
		    // �ַ�
		    if ("char".equalsIgnoreCase(charOrNum)) {
		     // ȡ�ô�д��ĸ����Сд��ĸ
		     int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
		       val += (char) (choice + random.nextInt(26));
		 
		    } else if ("num".equalsIgnoreCase(charOrNum)) { // ����
		       val += String.valueOf(random.nextInt(10));
		       
		    }
		    
		   }
		return val;
	   }
		  
}

