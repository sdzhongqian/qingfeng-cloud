package com.qingfeng.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName Verify
 * @author Administrator
 * @version 1.0.0
 * @Description Verify验证
 * @createTime 2021/4/3 0003 19:54
 */
public class Verify {

	/**
	 * 数字
	 */
	private static Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,12})?$");

	/**
	 * 
	* @Title: verifyIsNull
	* @Description: true不为空，false为空
	* @param @param str
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean verifyIsNotNull(String str){
		if(str==null||str.equals("")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	* @Title: verifyIsNull
	* @Description: 验证对象
	* @param @param obj
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean verifyIsNotNull(Object obj){
		if(obj==null||obj.equals("")||obj=="undefined"){
			return false;
		}else{
			return true;
		}
	}


	public static boolean verifyIsNull(Object obj){
		if(obj!=null&&!obj.equals("")&&obj!="undefined"){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   }
	   return true; 
	}
	
	/**
	 * 保留两位小数
	 * @param str
	 * @return
	 */
	public static String NumberFormatDouble(String str){ 
	   DecimalFormat df = new DecimalFormat("0.00");
//	   System.out.println(df.format(Double.parseDouble(str)));
	   return df.format(Double.parseDouble(str)); 
	}
		
	/**
	 * 保留整数
	 * @param str
	 * @return
	 */
	public static String NumberFormatInteger(String str){ 
	   DecimalFormat df = new DecimalFormat("#");
//		   System.out.println(df.format(Double.parseDouble(str)));
	   return df.format(Double.parseDouble(str)); 
	}
	
	public static void main(String[] args) {
		System.out.println(NumberFormatDouble("110210.12323"));
	}


	public static String verifyNumToZero(Object obj){
		if(obj==null||obj.equals("")||obj=="undefined"||obj.equals("/")){
			return "0";
		}else{
			return obj.toString();
		}
	}

	public static boolean verifyIsNotNullAndZero(Object obj){
		if (obj == null || obj.equals("") || obj.equals("0") || obj.equals(0) || obj.equals("0.0") || obj.equals(0.0) || obj.equals(0.000000) || obj.equals("0.000000")) {
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 是否包含字符串
	 *
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs)
	{
		if (str != null && strs != null)
		{
			for (String s : strs)
			{
				if (str.equalsIgnoreCase(trim(s)))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static String trim(String str)
	{
		return (str == null ? "" : str.trim());
	}

}
