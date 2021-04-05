package com.qingfeng.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;


/**
 * @ProjectName JsonToMap
 * @author Administrator
 * @version 1.0.0
 * @Description Json Map 互相转换工具类
 * @createTime 2021/4/3 0003 19:53
 */
public class JsonToMap {


	public static <T> String objToJson(T obj) throws JsonProcessingException {
		return JSON.toJSONString(obj);
	}

	public static <T> T jsonToObj(String json,Class<T> clazz) throws IOException {
		return JSON.parseObject(json,clazz);
	}

	/**
	 * @Description: parseJSON2List
	 * @Param: [jsonStr]
	 * @return: java.util.List<com.qingfeng.utils.PageData>
	 * @Author: anxingtao
	 * @Date: 2020-9-25 23:11
	 */
    public static List<PageData> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<PageData> list = new ArrayList<PageData>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Pd(json2.toString()));
        }
        return list;
    }

	/**
	 * @Description: parseJSON2Pd
	 * @Param: [jsonStr]
	 * @return: com.qingfeng.utils.PageData
	 * @Author: anxingtao
	 * @Date: 2020-9-25 23:11
	 */
    public static PageData parseJSON2Pd(String jsonStr){
    	PageData pd = new PageData();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<PageData> list = new ArrayList<PageData>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Pd(json2.toString()));
                }
                pd.put(k.toString(), list);
            } else {
            	pd.put(k.toString(), v);
            }
        }
        return pd;
    }
    
   /**
    * @Description: getListByUrl
    * @Param: [url]
    * @return: java.util.List<com.qingfeng.utils.PageData>
    * @Author: anxingtao
    * @Date: 2020-9-25 23:11
    */
    public static List<PageData> getListByUrl(String url){
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
   
    /**
     * @Description: getMapByUrl
     * @Param: [url]
     * @return: com.qingfeng.utils.PageData
     * @Author: anxingtao
     * @Date: 2020-9-25 23:11
     */
	public static PageData getMapByUrl(String url){
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2Pd(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
//    //test
//    public static void main(String[] args) {
////        String url = "http://...";
////        List<Map<String,Object>> list = getListByUrl(url);
////        System.out.println(list);
//    	String a = "{'dd':'22'}";
//    	String b = "{'session_id':'1','body':{'login_name':'18953739048','login_password':'gdhdhdhd'}}";
//    	System.out.println(parseJSON2Pd(b));
//    	PageData p = parseJSON2Pd(b);
//
//    	PageData p1 = parseJSON2Pd(p.get("body").toString());
//    	System.out.println(p1.getString("login_name"));
//
//    }


	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(
							bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}


	/**
	 * 转换特殊字符，将json串转换为JS能直接识别的json
	 * @param oldJson
	 * @return
	 */
	public static String getJsonForJS(String oldJson) {
		String newJson = oldJson;
		newJson = newJson.replaceAll("\\\\","\\\\\\\\");
		newJson = newJson.replaceAll("\\'","\\\\'");
		newJson = newJson.replaceAll("\\\"","\\\\\"");
		return newJson;
	}

	
}