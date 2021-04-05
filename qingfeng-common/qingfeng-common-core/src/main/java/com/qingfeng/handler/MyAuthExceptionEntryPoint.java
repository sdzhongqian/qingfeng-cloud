package com.qingfeng.handler;

import com.qingfeng.entity.MyResponse;
import com.qingfeng.utils.MyUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @title: MyAccessDeniedHandler
 * @projectName qingfeng-cloud
 * @description: 响应码为HttpServletResponse.SC_UNAUTHORIZED，即401
 * @date 2021/2/21 00210:28
 */
public class MyAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        MyResponse myResponse = new MyResponse();
        MyUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, myResponse.message("token无效")
        );
    }
}