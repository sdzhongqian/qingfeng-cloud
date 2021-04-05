package com.qingfeng.service.fallback;

import com.qingfeng.service.ITestService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qingfeng
 * @title: HelloServiceFallback
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 002117:19
 */
@Slf4j
@Component
public class TestServiceFallback implements FallbackFactory<ITestService> {
    @Override
    public ITestService create(Throwable throwable) {
        return new ITestService() {
            @Override
            public String hello(String name) {
                log.error("调用qingfeng-server-system服务出错", throwable);
                return "调用出错";
            }
        };
    }
}