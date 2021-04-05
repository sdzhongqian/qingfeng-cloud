package com.qingfeng.controller;

import com.qingfeng.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ProjectName TestController
 * @author qingfeng
 * @version 1.0.0
 * @Description 测试
 * @createTime 2021/4/3 0003 20:06
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private ITestService testService;


    @GetMapping("info")
    public String test(){
        return "qingfeng-server-job";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        System.out.println(testService.hello("我爱中国"));;
        log.info("/hello服务被调用");
        return "hello" + name;
    }

}
