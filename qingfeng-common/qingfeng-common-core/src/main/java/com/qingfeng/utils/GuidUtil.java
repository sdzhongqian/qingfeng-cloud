package com.qingfeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @ProjectName GuidUtil
 * @author Administrator
 * @version 1.0.0
 * @Description 获得Id等字符串
 * @createTime 2021/4/3 0003 19:52
 */
public class GuidUtil {

	private static final Random ran = new Random();

	/**
	 * 获取UUID
	 * @return
     */
	public static String getUuid(){
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		return uuid;
	}
	
	public static String getGuid()
	  {
		StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
	    int n = (int)(Math.random() * 90000.0D + 10000.0D);
	    return now.append(n)+"";
	  }
	public static String getDateGuid()
	{
		StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		return now+"";
	}
	
	public static String getGuidDdh()
	  {
		StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
	    return now+"";
	  }
	
	
	public static String getGuidBillno()
	  {
		StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyymmdd").format(new Date()));
		String code = "0123456789";
		StringBuffer cc = new StringBuffer();
		for(int i=0;i<10;i++) {
			cc.append(code.charAt(ran.nextInt(code.length())));
		}
	    return now+cc.toString();
	  }
	
	
	public static String getSmailGuid() {
		StringBuffer cc = new StringBuffer();
		cc.append("6");
		String code = "0123456789";
		for(int i=0;i<8;i++) {
			cc.append(code.charAt(ran.nextInt(code.length())));
		}
		return cc.toString();
	}
	
	public static String getCodeNum() {
		StringBuffer cc = new StringBuffer();
		String code = "0123456789";
		for(int i=0;i<6;i++) {
			cc.append(code.charAt(ran.nextInt(code.length())));
		}
		return cc.toString();
	}
	
	public static String getStrGuid(){
		StringBuffer sb = new StringBuffer();
//		StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
//		sb.append(now);
		String code = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<8;i++) {
			sb.append(code.charAt(ran.nextInt(code.length())));
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(getGuidDdh());

		String uuid = UUID.randomUUID().toString().replaceAll("-","");

		System.out.println(uuid);

	}
	
}
