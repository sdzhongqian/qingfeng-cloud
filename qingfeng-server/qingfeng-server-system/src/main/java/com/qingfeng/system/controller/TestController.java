package com.qingfeng.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ProjectName TestController
 * @author qingfeng
 * @version 1.0.0
 * @Description 测试案例
 * @createTime 2021/4/3 0003 21:10
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "qingfeng-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用");
        return "hello" + name;
    }

}
