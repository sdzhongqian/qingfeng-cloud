package com.qingfeng.exception;

/**
 * @ProjectName AuthException
 * @author Administrator
 * @version 1.0.0
 * @Description 权限异常
 * @createTime 2021/4/3 0003 19:26
 */
public class AuthException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public AuthException(String message){
        super(message);
    }
}
