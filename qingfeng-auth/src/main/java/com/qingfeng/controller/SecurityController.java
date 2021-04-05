package com.qingfeng.controller;

import com.qingfeng.entity.MyResponse;
import com.qingfeng.exception.AuthException;
import com.qingfeng.exception.ValidateCodeException;
import com.qingfeng.service.ValidateCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @ProjectName SecurityController
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:09
 */
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * @title captcha
     * @description 获取验证码
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:10
     * @throws
     */
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    /**
     * @title testOauth
     * @description 测试权限
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:10
     * @throws
     */
    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    /**
     * @title currentUser
     * @description 获取当前用户
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:10
     * @throws
     */
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * @title signout
     * @description 推出登录
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:11
     * @throws
     */
    @DeleteMapping("signout")
    public MyResponse signout(HttpServletRequest request) throws AuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        MyResponse qfResponse = new MyResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new AuthException("退出登录失败");
        }
        return qfResponse.message("退出登录成功");
    }
}
