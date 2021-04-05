package com.qingfeng.annotation;

import com.qingfeng.configure.MyServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ProjectName EnableMyServerProtect
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyServerProtectConfigure.class)
public @interface EnableMyServerProtect {

}