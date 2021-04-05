package com.qingfeng.exception;

/**
 * @author Administrator
 * @title: ValidateCodeException
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 002120:56
 */
public class ValidateCodeException extends Exception{

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}