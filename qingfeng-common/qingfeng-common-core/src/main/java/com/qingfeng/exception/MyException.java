package com.qingfeng.exception;

/**
 * @author Administrator
 * @title: MyException
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 002122:13
 */
public class MyException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public MyException(String message){
        super(message);
    }
}