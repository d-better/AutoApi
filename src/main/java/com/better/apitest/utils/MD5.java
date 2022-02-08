package com.better.apitest.utils;

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class MD5 {

	public static boolean isMD5(String str) {
        String pattern = "^[0-9a-fA-F]{32}$";
        boolean isMatch = Pattern.matches(pattern, str);
		return isMatch;
	}

	 public static String encrytMD5(String data){
		try{
	    	//指定加密算法
			MessageDigest digest=MessageDigest.getInstance("MD5");
			digest.update(data.getBytes());
			return encryptMD5toString(digest.digest());	
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	 }
	//将加密后的字节数组转化为固定长度的字符串
	private static String encryptMD5toString(byte[] data){
		try{
			String str="";
			String str16; 
			for(int i=0;i<data.length;i++){
				//转换为16进制数据
				//Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
				//例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
				//和0xff相与后，高24比特就会被清0了，结果就对了。
				str16=Integer.toHexString(0xFF & data[i]);
				if(str16.length()==1){
					str=str+"0"+str16;
		 		}else{
		 			str=str+str16;
		 		}
			}
			return str;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[]){
		System.out.println(MD5.encrytMD5("page=1"));
		System.out.println(MD5.encrytMD5(MD5.encrytMD5("Android") + MD5.encrytMD5("page=1")));
		System.out.println(MD5.encrytMD5(MD5.encrytMD5("GreatSale_MiniProgram_10") + MD5.encrytMD5("{\"exhibitionParkId\":101334}")));
		System.out.println(MD5.encrytMD5(MD5.encrytMD5("GreatSale_MiniProgram_10") + MD5.encrytMD5("{\"activityId\":674074,\"exhibitionParkId\":101334,\"pageNo\":1,\"pageSize\":10,\"activityTag\":2}")));
		System.out.println(MD5.encrytMD5(MD5.encrytMD5("GreatSale_MiniProgram_10") + MD5.encrytMD5("{\"activityId\":{{activityId1}},\"exhibitionParkId\":101334,\"pageNo\":1,\"pageSize\":10,\"activityTag\":2}")));
		System.out.println(MD5.encrytMD5(MD5.encrytMD5("GreatSale_IOS_30") + MD5.encrytMD5("{\n" +
				"\t\"pageNo\": \"1\",\n" +
				"\t\"pageSize\": \"20\"\n" +
				"}")));

		String pattern = "^[0-9a-fA-F]{32}$";
		boolean isMatch = Pattern.matches(pattern, "fd47612a73b43c286f26c1f884f114d1");
        System.out.println("-------------" + isMatch);
	}
}
