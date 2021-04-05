package com.qingfeng.base.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qingfeng.utils.Page;
import com.qingfeng.utils.PageData;
import com.qingfeng.utils.Verify;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ProjectName BaseController
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:18
 */
public class BaseController {

    
    /**
     * @title writeJson
     * @description TODO
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:27
     */
    public void writeJson(HttpServletResponse response, Object object) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper objMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objMapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        jsonGenerator.writeObject(object);
        jsonGenerator.flush();
        jsonGenerator.close();
    }

}
