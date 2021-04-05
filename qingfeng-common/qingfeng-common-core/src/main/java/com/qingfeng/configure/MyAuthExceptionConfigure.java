package com.qingfeng.configure;

import com.qingfeng.handler.MyAccessDeniedHandler;
import com.qingfeng.handler.MyAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @ProjectName MyAuthExceptionConfigure
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
public class MyAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public MyAccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public MyAuthExceptionEntryPoint authenticationEntryPoint() {
        return new MyAuthExceptionEntryPoint();
    }
}