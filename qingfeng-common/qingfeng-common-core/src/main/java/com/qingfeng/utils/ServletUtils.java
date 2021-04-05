package com.qingfeng.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName ServletUtils
 * @author Administrator
 * @version 1.0.0
 * @Description 客户端工具类
 * @createTime 2021/4/3 0003 19:54
 */
public class ServletUtils
{

    /**
     * 是否是Ajax异步请求
     * 
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (Verify.inStringIgnoreCase(uri, ".json", ".xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (Verify.inStringIgnoreCase(ajax, "json", "xml"))
        {
            return true;
        }
        return false;
    }

}
