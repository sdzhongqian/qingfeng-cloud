/**    
 * 文件名：PageData.java    
 *    
 * 版本信息：    
 * 日期：2014-1-6    
 * Copyright 足下 Corporation 2014     
 * 版权所有    
 *    
 */
package com.qingfeng.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

/**
 * @ProjectName PageData
 * @author Administrator
 * @version 1.0.0
 * @Description PageData
 * @createTime 2021/4/3 0003 19:53
 */
public class PageData extends HashMap implements Map{
  private static final long serialVersionUID = 1L;
  Map map = null;
  HttpServletRequest request;
  private static PageData pagedata;
  
  public static PageData getInstance() {
		if(pagedata==null){
           pagedata = new PageData();
        }
		return pagedata;
	}

  public PageData(HttpServletRequest request, String type)
  {
    this.request = request;
    this.map = request.getParameterMap();

    if ("ParameterMap".equals(this.map.getClass().getSimpleName())){
      try
      {
        Method method = this.map.getClass().getMethod("setLocked",
                new Class[] { Boolean.TYPE });
        method.invoke(this.map, new Object[] { new Boolean(false) });
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


public PageData(HttpServletRequest request)
  {
    this.request = request;
    Map properties = request.getParameterMap();
    Map returnMap = new HashMap();
    Iterator entries = properties.entrySet().iterator();

    String name = "";
    String value = "";
    while (entries.hasNext()) {
      Entry entry = (Entry)(Entry)entries.next();
      name = (String)entry.getKey();
      Object valueObj = entry.getValue();
      if (valueObj == null) {
        value = "";
      } else if ((valueObj instanceof String[])) {
        String[] values = (String[])valueObj;
        for (int i = 0; i < values.length; i++) {
          value = values[i] + ",";
        }
        value = value.substring(0, value.length() - 1);
      } else {
        value = valueObj.toString();
      }
      returnMap.put(name, value);
    }
    this.map = returnMap;
  }

  public PageData()
  {
    this.map = new HashMap();
  }

  @Override
  public Object get(Object key)
  {
    Object obj = null;
    if ((this.map.get(key) instanceof Object[])) {
      Object[] arr = (Object[])this.map.get(key);
      obj = 
        this.request.getParameter((String)key) == null ? arr : this.request == null ? arr : 
        arr[0];
    } else {
      obj = this.map.get(key);
    }
    return obj;
  }

  public Number getNumber(Object key)
  {
    Object obj = this.map.get(key);
    if (obj == null){
      return Integer.valueOf(0);
    }
    if ((obj instanceof Number)) {
      Number objNumber = (Number)obj;
      return objNumber;
    }
    return Integer.valueOf(0);
  }

  public String[] getValues(Object key)
  {
    return this.request == null ? null : 
      this.request.getParameterValues((String)key);
  }

  public String getString(Object key)
  {
    Object obj = this.map.get(key);
    if (obj == null) {
      return "";
    }
    if ((obj instanceof String)) {
      return (String)get(key);
    }
    if ((obj instanceof Clob)) {
      Clob objClob = (Clob)obj;
      try {
        return objClob.getSubString(1L, (int)objClob.length());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return "";
  }

  public String getClob(Object key)
    throws Exception
  {
    if (key == null){
      return "";
    }
    Object obj = this.map.get(key);
    if (obj == null){
      return "";
    }
    if ((obj instanceof Clob)) {
      Clob objClob = (Clob)obj;
      try {
        return objClob.getSubString(1L, (int)objClob.length());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return "";
  }

  @Override
  public Object put(Object key, Object value)
  {
    if ((value instanceof Clob)) {
      Clob objClob = (Clob)value;
      try {
        return this.map.put(key, objClob.getSubString(1L, (int)objClob.length()));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return this.map.put(key, value);
  }

  @Override
  public Object remove(Object key)
  {
    return this.map.remove(key);
  }

  @Override
  public void clear() {
    this.map.clear();
  }

  @Override
  public boolean containsKey(Object key)
  {
    return this.map.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value)
  {
    return this.map.containsValue(value);
  }

  @Override
  public Set entrySet()
  {
    return this.map.entrySet();
  }

  @Override
  public boolean isEmpty()
  {
    return this.map.isEmpty();
  }

  @Override
  public Set keySet()
  {
    return this.map.keySet();
  }

  @Override
  public void putAll(Map t)
  {
    this.map.putAll(t);
  }

  @Override
  public int size()
  {
    return this.map.size();
  }

  @Override
  public Collection values()
  {
    return this.map.values();
  }
  
  public String[] getKeys(PageData pd){
	  Set entries = pd.entrySet();
	  String[] str = new String[entries.size()];
	  if (entries != null) {
		  int i = 0;
	      Iterator iterator = entries.iterator();
	      while (iterator.hasNext()) {
	          Entry entry = (Entry) iterator.next();
	          String key = entry.getKey().toString();
	          str[i++] = key;
	      }
	  }
	return str;
  }
  
  public String[] getValues(PageData pd){
	  Set entries = pd.entrySet();
	  String[] str = new String[entries.size()];
	  if (entries != null) {
		  int i = 0;
	      Iterator iterator = entries.iterator();
	      while (iterator.hasNext()) {
	          Entry entry = (Entry) iterator.next();
	          String val = entry.getValue().toString();
	          str[i++] = val;
	      }
	  }
	return str;
  }
  
  public List<String> getValuesForKeyPrefix(PageData pd,String prefix){
	  Set entries = pd.entrySet();
	  
	  List<String> list = new ArrayList<String>();
	  if (entries != null) {
	      Iterator iterator = entries.iterator();
	      while (iterator.hasNext()) {
	          Entry entry = (Entry) iterator.next();
	          String val = entry.getValue().toString();
	          String key = entry.getKey().toString();
	          if(key.startsWith(prefix)){
	        	  list.add(val);
	          }
	      }
	  }
	return list;
  }


  /**
   * @Description: map2Pd
   * @Param: [map]
   * @return: com.qingfeng.base.util.PageData
   * @Author: anxingtao
   * @Date: 2020-8-15 14:10
   */
  public PageData map2Pd(Map<String,Object> map){
    PageData pd = new PageData();
    Set entries = map.entrySet();
    if (entries != null) {
      int i = 0;
      Iterator iterator = entries.iterator();
      while (iterator.hasNext()) {
        Entry entry = (Entry) iterator.next();
        String key = entry.getKey().toString();
        String val = entry.getValue().toString();
        pd.put(key,val);
      }
    }
    return pd;
  }


  /**
   * @Description: objToMap
   * @Param: [obj]
   * @return: java.util.Map<java.lang.String,java.lang.Object>
   * @Author: anxingtao
   * @Date: 2020-8-15 14:10
   */
  public Map<String, Object> objToMap(Object obj) {
    Map<String, Object> map = new HashMap<String, Object>();
    Field[] fields = obj.getClass().getDeclaredFields();	// 获取f对象对应类中的所有属性域
    for (int i = 0, len = fields.length; i < len; i++) {
      String varName = fields[i].getName();
      varName = varName.toLowerCase();					// 将key置为小写，默认为对象的属性
      try {
        boolean accessFlag = fields[i].isAccessible();	// 获取原来的访问控制权限
        fields[i].setAccessible(true);					// 修改访问控制权限
        Object o = fields[i].get(obj);					// 获取在对象f中属性fields[i]对应的对象中的变量
        if (o != null){
          map.put(varName, o.toString());
        }
        fields[i].setAccessible(accessFlag);			// 恢复访问控制权限
      } catch (IllegalArgumentException ex) {
        ex.printStackTrace();
      } catch (IllegalAccessException ex) {
        ex.printStackTrace();
      }
    }
    return map;
  }


  public PageData objToPd(Object obj) {
    PageData pd = new PageData();
    Field[] fields = obj.getClass().getDeclaredFields();	// 获取f对象对应类中的所有属性域
    for (int i = 0, len = fields.length; i < len; i++) {
      String varName = fields[i].getName();
//      System.out.println("varName:"+varName);
      varName = varName.toLowerCase();					// 将key置为小写，默认为对象的属性
      try {
        boolean accessFlag = fields[i].isAccessible();	// 获取原来的访问控制权限
        fields[i].setAccessible(true);					// 修改访问控制权限
        Object o = fields[i].get(obj);					// 获取在对象f中属性fields[i]对应的对象中的变量
        if (o != null){
          pd.put(varName, o.toString());
        }
        fields[i].setAccessible(accessFlag);			// 恢复访问控制权限
      } catch (IllegalArgumentException ex) {
        ex.printStackTrace();
      } catch (IllegalAccessException ex) {
        ex.printStackTrace();
      }
    }
    return pd;
  }




  public List<PageData> listObjToPd(List<?> list) {
    List<PageData> ls = new ArrayList<PageData>();
    for (Object obj:list){
      PageData pd = new PageData();
      Field[] fields = obj.getClass().getDeclaredFields();	// 获取f对象对应类中的所有属性域
      for (int i = 0, len = fields.length; i < len; i++) {
        String varName = fields[i].getName();
        varName = varName.toLowerCase();					// 将key置为小写，默认为对象的属性
        try {
          boolean accessFlag = fields[i].isAccessible();	// 获取原来的访问控制权限
          fields[i].setAccessible(true);					// 修改访问控制权限
          Object o = fields[i].get(obj);					// 获取在对象f中属性fields[i]对应的对象中的变量
          if (o != null){
            pd.put(varName, o.toString());
          }
          fields[i].setAccessible(accessFlag);			// 恢复访问控制权限
        } catch (IllegalArgumentException ex) {
          ex.printStackTrace();
        } catch (IllegalAccessException ex) {
          ex.printStackTrace();
        }
      }
      ls.add(pd);
    }
    return ls;
  }




}





























