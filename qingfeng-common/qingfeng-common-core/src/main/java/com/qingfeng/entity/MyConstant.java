package com.qingfeng.entity;

/**
 * @ProjectName MyConstant
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
public class MyConstant {


    //=================zuul验证===================
    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "qingfeng:zuul:123456";


    //==================验证码=======================
    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "qingfeng.captcha.";
    /**
     * 异步线程池名称
     */
    public static final String ASYNC_POOL = "qingfengAsyncThreadPool";

    /**
     * 排序规则：降序
     */
    public static final String ORDER_DESC = "descending";

}