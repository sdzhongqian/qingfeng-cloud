package com.qingfeng.entity;

/**
 * @ProjectName RegexpConstant
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
public class RegexpConstant {

    // 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
    public static final String MOBILE_REG = "[1]\\d{10}";
}