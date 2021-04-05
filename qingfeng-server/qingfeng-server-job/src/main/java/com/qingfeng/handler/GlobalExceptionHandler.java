package com.qingfeng.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author qingfeng
 * @title: GlobalExceptionHandler
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 00210:56
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}