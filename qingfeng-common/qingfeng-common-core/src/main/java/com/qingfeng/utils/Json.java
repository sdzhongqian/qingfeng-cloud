package com.qingfeng.utils;

import java.io.Serializable;

/**
 * @ProjectName Json
 * @author Administrator
 * @version 1.0.0
 * @Description JSON工具类
 * @createTime 2021/4/3 0003 19:53
 */
public class Json implements Serializable {

	private static final long serialVersionUID = -4540167775871168514L;
	
	private boolean success = true;
	private String msg = "";
	private Object object;
	private String sessionId = "";
	private boolean verifySessionId = false;
	private String flag="";
	//分页数量
	private int count = 0;
	//对象
	private Object data;

	private int code = 200;
	private String url;
	private String loseSession;
	private String token;

	public Json() {

	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Json(boolean success, Object object, String sessionId, int code) {
		this.success = success;
		this.msg = "";
		this.object = object;
		this.sessionId = sessionId;
		this.flag = "";
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isVerifySessionId() {
		return verifySessionId;
	}

	public void setVerifySessionId(boolean verifySessionId) {
		this.verifySessionId = verifySessionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoseSession() {
		return loseSession;
	}

	public void setLoseSession(String loseSession) {
		this.loseSession = loseSession;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
