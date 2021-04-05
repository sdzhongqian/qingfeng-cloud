package com.qingfeng.annotation;

import com.qingfeng.configure.MyAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ProjectName EnableMyAuthExceptionHandler
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyAuthExceptionConfigure.class)
public @interface EnableMyAuthExceptionHandler {

}