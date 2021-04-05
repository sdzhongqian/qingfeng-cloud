package com.qingfeng;

import com.qingfeng.annotation.EnableCloudApplication;
import com.qingfeng.annotation.EnableMyLettuceRedis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCloudApplication
@EnableMyLettuceRedis
@MapperScan("com.qingfeng.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}