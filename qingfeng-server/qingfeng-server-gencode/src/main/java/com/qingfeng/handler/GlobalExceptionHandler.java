package com.qingfeng.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ProjectName GlobalExceptionHandler
 * @author qingfeng
 * @version 1.0.0
 * @Description 全局异常
 * @createTime 2021/4/3 0003 19:59
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}