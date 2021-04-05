/**    
 * 文件名：DateTimeUtil.java    
 *    
 * 版本信息：    
 * 日期：2014-2-17    
 * Copyright 足下 Corporation 2014     
 * 版权所有    
 *    
 */
package com.qingfeng.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName DateTimeUtil
 * @author Administrator
 * @version 1.0.0
 * @Description 时间工具类
 * @createTime 2021/4/3 0003 19:51
 */
public class DateTimeUtil {
	static String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };


	/**
	 * 根据日期字符串判断当月第几周
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static int getWeek(String str) throws Exception{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date =sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//第几周
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		//第几天，从周日开始
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	public static String getDateTimeStr()
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);
	    return curTime;
	  }

	public static String getDateTimeStr(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    //格式化规则
		String strDate= sdf.format(date ); //格式化成yyyy-MM-dd格式的时间字符串
		Date newDate =sdf.parse(strDate);
		return sdf.format(newDate);
	}

	/**
	 * @Description:获得当前日期是分娩
	 * @Param:  
	 * @return:  
	 * @Author: zhaojishun
	 * @Date:   
	 */ 
	public static String getTimeStr()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);
		return curTime;
	}
	
	public static Date getDateTime() throws ParseException
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currTime = new Date();
		Date curTime = formatter.parse(formatter.format(currTime));
	    return curTime;
	  }

	public static Date getDDate() throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		Date curTime = formatter.parse(formatter.format(currTime));
		return curTime;
	}
	
	public static Date getDateTimeGsh() throws ParseException
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date currTime = new Date();
		Date curTime = formatter.parse(formatter.format(currTime));
	    return curTime;
	  }
	
	
	
	public static String getDate()
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);
	    return curTime;
	  }


	public static String getGsDate()
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);
	    return curTime;
	  }
	//2017-2-2
	public static String getDateToStr(String str) throws ParseException
	  {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		   Date date = sdf.parse(str);
		  String formatDate = sdf.format(date);
		  return formatDate;
	  }

	public static String getMonthToStr(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = sdf.parse(str);
		String formatDate = sdf.format(date);
		return formatDate;
	}

	//转换yyyy-MM-dd
	public static String getDateStr(String str) throws ParseException
	  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = new Date(str);
		String formatDate = sdf.format(date);
	       return formatDate;
	  }

	public static String getDateType1(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(str);
		String formatDate = sdf.format(date);
		return formatDate;
	}

	public static String getDateTimeMonth() throws ParseException
	  {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M");
		Date currTime = new Date();
		String curTime = formatter.format(currTime);
	    return curTime;
	  }
	 public static String getWeekFirstDate(String date) {  
		 Calendar c=Calendar.getInstance();
		 c.set(Integer.parseInt(date.split("-")[0]),
	     Integer.parseInt(date.split("-")[1]) - 1,
	     Integer.parseInt(date.split("-")[2]));
	     DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	     c.add(Calendar.DAY_OF_WEEK,(-1)*c.get(Calendar.DAY_OF_WEEK)+1);
		return df.format(c.getTime());
	 }  
	 
	 
	 public static String getWeekEndDate(String date) {  
		 Calendar c=Calendar.getInstance();
		 c.set(Integer.parseInt(date.split("-")[0]),
	     Integer.parseInt(date.split("-")[1]) - 1,
	     Integer.parseInt(date.split("-")[2]));
	     DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	     c.add(Calendar.DAY_OF_WEEK,7-c.get(Calendar.DAY_OF_WEEK));
		return df.format(c.getTime());
	 }

	/**
	 * 获取本周的第一天
	 * @return String
	 * **/
	public static String getWeekStart(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, 0);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		Date time=cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 获取本周的最后一天
	 * @return String
	 * **/
	public static String getWeekEnd(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date time=cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}
	 public static void main(String[] args) throws Exception{
	       /* Calendar c=Calendar.getInstance();
//	        c.add(Calendar.DAY_OF_MONTH,-6);
	        String date = getDate();
	  c.set(Integer.parseInt(date.split("-")[0]),
	    Integer.parseInt(date.split("-")[1]) - 1,
	    Integer.parseInt(date.split("-")[2]));
	        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//	        c.set(Calendar.DAY_OF_WEEK,0);
	        c.add(Calendar.DAY_OF_WEEK,(-1)*c.get(Calendar.DAY_OF_WEEK)+1);
	        System.out.println("本周第一天："+df.format(c.getTime()));
	         c.add(Calendar.DAY_OF_WEEK,7-c.get(Calendar.DAY_OF_WEEK));
	        System.out.println("本周最后一天："+df.format(c.getTime()));*/
		 
//		 String str = "03/12/2017";
		 
		/* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	Date d = sdf.parse(str);   
	       Date d1 = new Date(d.getTime());
		  // Date date = sdf.parse(str); 
		   //String strDate = sdf.format(str);   
		   System.out.println(d);
		   System.out.println(d1);*/
	        
//	        
//	        String tt = "03/12/2017";
//	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-Mm-dd");
//	        Date currTime = new Date(tt);
//	        System.out.println(formatter.format(currTime));
//		 	int[] dd = temp();
//		 	
//		 	for(int d:dd){
//		 		 System.out.println(d);
//		 	}
//		 	System.out.print(getWeek(getDateTimeStr()));
//		 System.out.println(getDaty());
//		 System.out.println(getAroundMonth(-1));

		 System.out.println(getWeekEnd());

		 System.out.println(getAroundMonth(1));
		 System.out.println(getAroundMonth(-1));

	    }

	public static String getDaty(){
		GregorianCalendar gc=new GregorianCalendar();
		int day=gc.get(Calendar.DAY_OF_MONTH);

		String d = "";
		if(day<10){
			d="0"+day;
		}else{
			d=""+day;
		}
		return d;
	}
	 
	 /**
	  * 
	 * @Title: compareDatesByCompareTo
	 * @Description: 时间日期的比较
	 * @param @param df
	 * @param @param oldDate
	 * @param @param newDate
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	  */
	 public static String compareDatesByCompareTo(DateFormat df, Date oldDate, Date newDate) {
		 String flag = "";
	        //how to check if date1 is equal to date2
	        if (oldDate.compareTo(newDate) == 0) {
//	            System.out.println(df.format(oldDate) + " and " + df.format(newDate) + " are equal to each other");
	        	flag = "dengyu";
	        }
	 
	        //checking if date1 is less than date 2
	        if (oldDate.compareTo(newDate) < 0) {
//	            System.out.println(df.format(oldDate) + " is less than " + df.format(newDate));
	        	flag = "xiaoyu";
	        }
	 
	        //how to check if date1 is greater than date2 in java
	        if (oldDate.compareTo(newDate) > 0) {
//	            System.out.println(df.format(oldDate) + " is greater than " + df.format(newDate));
	        	flag = "dayu";
	        }
			return flag;
	    }
	 
	    public static String compareDatesByDateMethods(DateFormat df, Date oldDate, Date newDate) {
	    	 String flag = "";
	        //how to check if two dates are equals in java
	        if (oldDate.equals(newDate)) {
	            System.out.println(df.format(oldDate) + " and " + df.format(newDate) + " are equal to each other");
	            flag = "dengyu";
	        }
	 
	        //checking if date1 comes before date2
	        if (oldDate.before(newDate)) {
	            System.out.println(df.format(oldDate) + " comes before " + df.format(newDate));
	            flag = "xiaoyu";
	        }
	 
	        //checking if date1 comes after date2
	        if (oldDate.after(newDate)) {
	            System.out.println(df.format(oldDate) + " comes after " + df.format(newDate));
	        	flag = "dayu";
	        }
			return flag;
	    }
	 
	    public static String compareDatesByCalendarMethods(DateFormat df, Date oldDate, Date newDate) {
	    	 String flag = "";
	        //creating calendar instances for date comparision
	        Calendar oldCal = Calendar.getInstance();
	        Calendar newCal = Calendar.getInstance();
	 
	        oldCal.setTime(oldDate);
	        newCal.setTime(newDate);
	 
	        //how to check if two dates are equals in java using Calendar
	        if (oldCal.equals(newCal)) {
//	            System.out.println(df.format(oldDate) + " and " + df.format(newDate) + " are equal to each other");
	        	flag = "dengyu";
	        }
	 
	        //how to check if one date comes before another using Calendar
	        if (oldCal.before(newCal)) {
//	            System.out.println(df.format(oldDate) + " comes before " + df.format(newDate));
	        	flag = "xiaoyu";
	        }
	 
	        //how to check if one date comes after another using Calendar
	        if (oldCal.after(newCal)) {
//	            System.out.println(df.format(oldDate) + " comes after " + df.format(newDate));
	        	flag = "dayu";
	        }
			return flag;
	    }
	    
	    /**
	     * 
	    * @Title: getNowBeforeTime
	    * @Description: 计算多少分钟之前的时间
	    * @param @param 
	    * @param @return    设定文件
	    * @return String    返回类型
	    * @throws
	     */
	    public static String getNowBeforeTime(int minminute){
	    	Date now = new Date();
	    	Date now_10 = new Date(now.getTime() - minminute*60*1000); //10分钟前的时间
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
	    	String nowTime_10 = dateFormat.format(now_10);
			return nowTime_10;
	    }
	    
	    
	    public static String getBeforeDaty(int i){
	    	GregorianCalendar gc=new GregorianCalendar();
			gc.add(Calendar.DAY_OF_MONTH,-i);
			int year=gc.get(Calendar.YEAR);
			int month=gc.get(Calendar.MONTH)+1;
			int day=gc.get(Calendar.DAY_OF_MONTH);
			System.out.println("前150天是："+year+"-"+month+"-"+day);
			
			String m = "";
			String d = "";
			if(month<10){
				m="0"+month;
			}else{
				m=""+month;
			}
			if(day<10){
				d="0"+day;
			}else{
				d=""+day;
			}
			return year+"-"+m+"-"+d;
	    }
	    
	    public static void main1(String[] args) throws ParseException {
//	    	System.out.println(getNowBeforeTime(120));
//	    	
//			GregorianCalendar gc=new GregorianCalendar();
//			gc.add(Calendar.DAY_OF_MONTH,-10);
//			int year=gc.get(Calendar.YEAR);
//			int month=gc.get(Calendar.MONTH)+1;
//			int day=gc.get(Calendar.DAY_OF_MONTH);
//			System.out.println("前150天是："+year+"-"+month+"-"+day);
//			
//			System.out.println(DateTimeUtil.getDateTimeStr().substring(0,10));
////			
//			
//			System.out.println(getDateTimeGsh());
	    	
	    	
	    	//System.out.println(getdatetimetounix(DateTimeUtil.getDateTimeStr()));
	    	
			//System.out.println(DateTimeUtil.TimeStampDate("1415242236"));
			
			//System.out.println(new Date().getTime());
//	    	String str = "03/12/2017";
//	    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//			   Date date = sdf.parse(str);  
//			   System.out.println(date);
			    //String formatDate = DateFormat.getDateInstance().format(date);  


		}
	    
	    public static String getdatetimetounix(String datetime){
	    	Timestamp appointTime=Timestamp.valueOf(datetime) ; 
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	    	Date date = null;
			try {
				date = df.parse(String.valueOf(appointTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
	    	long s=date.getTime();
	    	System.out.println(s);
			return String.valueOf(s).substring(0, 10); 
	    }
	    
	    public static String TimeStampDate(String timestampString){    
	    	  Long timestamp = Long.parseLong(timestampString)*1000;    
	    	  String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
	    	  return date;    
	    	} 
	    
	    
	    
	    /**
		 * 今年中的第几周
		 * @param date
		 * @return
		 */
		 public static int getWeekNums(Date date) {  
			 GregorianCalendar g = new GregorianCalendar();  
			 g.setTime(date);  
			 return g.get(Calendar.WEEK_OF_YEAR);//获得周数  
		 }



		 /**
		  * 周几（今天是周几）
		  * @return
		  */
		 public static String getWeeks(){
			 Calendar c = Calendar.getInstance();
//			  int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
			  int day = c.get(Calendar.DAY_OF_WEEK);//获致是本周的第几天地, 1代表星期天...7代表星期六
//			  System.out.println("今天是本月的第" + week + "周");
//			  System.out.println("今天是星期" + weeks[day-1]);
			  String str = weeks[day-1];
			return str;
		 }
		 
		 /**
		  * 获得当前年
		  * @return
		  */
		 public static String getCurrentYear(){
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
			return sdf.format(new Date());
		 }
		 
		 /**
		  * 获得当前年前一年
		  * @return
		  */
		 public static String getCurrentYear(int num){
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
			 Calendar c = Calendar.getInstance();
			c.setTime(new Date());
	        c.add(Calendar.YEAR, -num);
	        Date y = c.getTime();
	        String year = sdf.format(y);
//	        System.out.println("过去一年："+year);
	        return year;
		 }
		 /**
		  * 获得当前月
		  * @return
//		  */
//		 public static int getCurrentMonth(){
//			 Calendar calendar = Calendar.getInstance();
////			 calendar.add(Calendar.DATE, -1);    //得到前一天
////			 calendar.add(Calendar.MONTH, -1);    //得到前一个月
//			 int year = calendar.get(Calendar.YEAR);
//			 int month = calendar.get(Calendar.MONTH)+1;
//			return month;
//		 }
		 
		 /**
		  * 获得当前年月
		  * @return
		  */
		 public static String getCurrentYearMonth(){
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			return sdf.format(new Date());
		 }
		 
		 /**
		  * 
		   * @Title: temp
		   * @Description: 获取当月的天数并以数组的形式展现出来
		   * @param @return
		   * @param @throws Exception    设定文件
		   * @return int[]    返回类型
		   * @throws
		  */
		 public static int[] temp() throws Exception{
//			   String strDate = getCurrentYearMonth();
//			   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); 
//			   Calendar calendar = new GregorianCalendar();   
//			   Date date = sdf.parse(strDate); 
//			   calendar.setTime(date);   
//			   int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			   Calendar a = Calendar.getInstance();  
			    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
			    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
			    int maxDate = a.get(Calendar.DATE);  
//			    return maxDate;  
			   int[] d = new int[maxDate];
			   
			   for(int i=0;i<maxDate;i++){
				   d[i]=i+1;
				   //System.out.print(d[i]);
			   }
			   return d;
			}
			/**
			 * @Description:  根据输入的月份获得上一个月
			 * @Param:  String yyyy-mm
			 * @return: String yyyy-mm
			 * @Author: zhaojishun
			 * @Date:	2018.11.6
			 */
			public static  String getLastMonth(String yearMonth){

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Calendar calendar = Calendar.getInstance();
				Date curDate = java.sql.Date.valueOf(yearMonth+"-01");
				calendar.setTime(curDate);
				//取得上一个时间
				calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
				//取得上一个月的下一天
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
				return sdf.format(calendar.getTime());

			}

	public static  String getAroundMonth(String yearMonth,int num){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		Date curDate = java.sql.Date.valueOf(yearMonth+"-01");
		calendar.setTime(curDate);
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) + num);
		return sdf.format(calendar.getTime());

	}


	/** 
	 * @Description: 获取前后月
	 * @Param: [i] 
	 * @return: java.lang.String 
	 * @Author: anxingtao
	 * @Date: 2019-1-10 10:56 
	 */ 
	public static String getAroundMonth(int i){
		GregorianCalendar gc=new GregorianCalendar();
		gc.add(Calendar.MONTH,i);
		int year=gc.get(Calendar.YEAR);
		int month=gc.get(Calendar.MONTH)+1;

		String m = "";
		String d = "";
		if(month<10){
			m="0"+month;
		}else{
			m=""+month;
		}
		System.out.println(year+"-"+m);
		return year+"-"+m;
	}


	/** 
	 * @Description: 获取前后天
	 * @Param: [i] 
	 * @return: java.lang.String 
	 * @Author: anxingtao
	 * @Date: 2019-1-10 11:29
	 */ 
	public static String getAroundDate(int i){
		GregorianCalendar gc=new GregorianCalendar();
		gc.add(Calendar.DAY_OF_MONTH,i);
		int year=gc.get(Calendar.YEAR);
		int month=gc.get(Calendar.MONTH)+1;
		int day=gc.get(Calendar.DAY_OF_MONTH);
		String m = "";
		String d = "";
		if(month<10){
			m="0"+month;
		}else{
			m=""+month;
		}
		if(day<10){
			d="0"+day;
		}else{
			d=""+day;
		}
		return year+"-"+m+"-"+d;
	}


	public static String getNextMonth(String month){
		String[] months = month.split("-");
		int year = Integer.parseInt(months[0]);
		int mon = Integer.parseInt(months[1]);
		if(mon==12){
			year++;
			mon = 1;
		}else{
			mon=mon+1;
		}
		String ym = "";
		if(mon<10){
			ym = year+"-0"+mon;
		}else{
			ym = year+"-"+mon;
		}
		return ym;
	}


	/**
	 * @Description: getMonthBetween
	 * @Param: [minDate, maxDate]
	 * @return: java.util.List<java.lang.String>
	 * @Author: anxingtao
	 * @Date: 2020-1-17 13:40
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}


	public static List<String> getYearBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR),1, 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), 1, 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.YEAR, 1);
		}

		return result;
	}

	public static int getMonthBetweenNum(String minDate, String maxDate) throws ParseException {
		int num = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			num++;
			curr.add(Calendar.MONTH, 1);
		}

		return num;
	}

	public static int getDaysOfMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期格式转换
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static String getNianYue(String month) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM").parse(month);
		String now = new SimpleDateFormat("yyyy年MM月").format(date);
		return  now;
	}
	/**
	 * 日期格式转换
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getNianYueRi(String day){
		Date date = null;
		String now = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
			now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return  now;
	}


	/**
	 * @Description: 获取交至月份的前一天
	 * @Param: [yearMonth, num]
	 * @return: java.lang.String
	 * @Author: anxingtao
	 * @Date: 2020-2-26 11:17
	 */
	public static String getAroundMonthBeforeDate(String yearMonth, int num) {
		//获取当前时间
		Date date = new Date();
		//获得当前时间前一天时间
		Calendar calendar = Calendar.getInstance();
		Date curDate = java.sql.Date.valueOf(yearMonth);
		calendar.setTime(curDate);
		calendar.add(Calendar.MONTH, num);
		calendar.add(Calendar.DATE, -1);
//		calendar.add(Calendar.DATE, 1);
		Date resDate = calendar.getTime();

		//将得到的时间以字符串形式输出控制台
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(resDate);

	}


	public static String getLastDayOfMonth(String yearMonth) {
		int year = Integer.parseInt(yearMonth.split("-")[0]);  //年
		int month = Integer.parseInt(yearMonth.split("-")[1]); //月
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		// cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.MONTH, month); //设置当前月的上一个月
		// 获取某月最大天数
		//int lastDay = cal.getActualMaximum(Calendar.DATE);
		int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
		// 设置日历中月份的最大天数
		//cal.set(Calendar.DAY_OF_MONTH, lastDay);
		cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}


	public static String getDateNext(String date) {
		if (date.length() > 7) {
			Calendar c = Calendar.getInstance();
			Date cdate = null;
			try {
				cdate = new SimpleDateFormat("yy-MM-dd").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(cdate);
			int day1 = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day1 + 1);
			String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			return dayAfter;
		} else {
			Calendar c = Calendar.getInstance();
			Date cdate = null;
			try {
				cdate = new SimpleDateFormat("yy-MM").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(cdate);
			int day1 = c.get(Calendar.MONTH);
			c.set(Calendar.MONTH, day1 + 1);
			String dayAfter = new SimpleDateFormat("yyyy-MM").format(c.getTime());
			return dayAfter;
		}
	}


	public static boolean isLastDayOfMonth(String day) throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH) == calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Description: getDatePoor 计算两个时间差
	 * @Param: [endDate, nowDate]
	 * @return: java.lang.String
	 * @Author: anxingtao
	 * @Date: 2020-10-2 21:28
	 */
	public static String getDatePoor(Date startDate,Date endDate)
	{
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - startDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}


}
