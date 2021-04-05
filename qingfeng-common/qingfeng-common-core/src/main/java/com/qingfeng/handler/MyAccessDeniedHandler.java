package com.qingfeng.handler;

import com.qingfeng.entity.MyResponse;
import com.qingfeng.utils.MyUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @title: MyAccessDeniedHandler
 * @projectName qingfeng-cloud
 * @description: 响应码为HttpServletResponse.SC_FORBIDDEN，即403
 * @date 2021/2/21 00210:30
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        MyResponse myResponse = new MyResponse();
        MyUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_FORBIDDEN, myResponse.message("没有权限访问该资源"));
    }
}