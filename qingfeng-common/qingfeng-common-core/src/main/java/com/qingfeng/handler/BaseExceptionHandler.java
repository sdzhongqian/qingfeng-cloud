package com.qingfeng.handler;

import com.qingfeng.entity.MyResponse;
import com.qingfeng.exception.AuthException;
import com.qingfeng.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * @title: BaseExceptionHandler
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 00210:53
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new MyResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyResponse handleQingfengAuthException(AuthException e) {
        log.error("系统错误", e);
        return new MyResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MyResponse handleAccessDeniedException(){
        return new MyResponse().message("没有权限访问该资源");
    }

    /**
     * @title: 自定义异常
     * @description: TODO
     * @author: Administrator
     * @date: 2021/2/21 0021 22:22
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyResponse handleException(MyException e) {
        log.error("系统错误", e);
        return new MyResponse().message(e.getMessage());
    }


    /**
    * @title: BaseExceptionHandler
    * @projectName: BaseExceptionHandler
    * @description: 统一处理请求参数校验(普通传参)
    * @author: Administrator
    * @date: 2021/2/23 0023 21:58
    */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new MyResponse().message(message.toString());
    }

    /**
    * @title: BaseExceptionHandler
    * @projectName: BaseExceptionHandler
    * @description: 统一处理请求参数校验(实体对象传参)
    * @author: Administrator
    * @date: 2021/2/23 0023 21:59
    */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new MyResponse().message(message.toString());
    }

}