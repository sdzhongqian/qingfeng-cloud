package com.qingfeng.service;

import com.qingfeng.entity.ServerConstant;
import com.qingfeng.service.fallback.TestServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName ITestService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:29
 */
@FeignClient(value = ServerConstant.QINGFENG_SERVER_SYSTEM, contextId = "testServiceClient", fallbackFactory = TestServiceFallback.class)
public interface ITestService {


    @GetMapping("hello")
    public String hello(@RequestParam("name") String name);

}