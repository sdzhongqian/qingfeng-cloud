package com.qingfeng.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ProjectName TestController
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:29
 */
@Slf4j
@RestController
public class TestController {

//    @Autowired
//    private IHelloService helloService;


    @GetMapping("hello")
    public String hello(String name){
        log.info("Feign调用qingfeng-server-system的/hello服务");
        System.out.println("Feign调用qingfeng-server-system的/hello服务");
//        return this.helloService.hello(name);
        return "我爱中国";
    }

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1(){
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2(){
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}