package com.qingfeng.annotation;

import com.qingfeng.configure.MyLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ProjectName EnableMyLettuceRedis
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyLettuceRedisConfigure.class)
public @interface EnableMyLettuceRedis {

}