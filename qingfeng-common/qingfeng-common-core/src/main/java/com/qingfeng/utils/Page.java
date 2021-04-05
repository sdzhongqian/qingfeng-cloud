package com.qingfeng.utils;


/**
 * @ProjectName Page
 * @author Administrator
 * @version 1.0.0
 * @Description 分页
 * @createTime 2021/4/3 0003 19:53
 */
public class Page {

	/**
	 * 每页显示记录数
	 */
	private int showCount = 15;

	/**
	 * 当前页
	 */
	private int index;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 总记录数
	 */
	private int totalResult;

	/**
	 *object对象
	 */
	private Object object;

	/** 
	 * @Description: 参数 
	 * @Param:
	 * @return:  
	 * @Author: anxingtao
	 * @Date: 2018-9-3 7:52 
	 */ 
	private String param;
	
	private PageData pd = new PageData();

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotalPage() {
		if(totalResult%showCount==0){
			totalPage = totalResult/showCount;
		} else {
			totalPage = totalResult/showCount+1;
		}
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
