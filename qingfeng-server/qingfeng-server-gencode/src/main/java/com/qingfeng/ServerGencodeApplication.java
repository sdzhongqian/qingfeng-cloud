package com.qingfeng;

import com.qingfeng.annotation.EnableCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
* @title: ServerTestApplication
* @projectName: ServerTestApplication
* @description: TODO
* @author: qingfeng
* @date: 2021/2/23 0023 22:04
*/
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCloudApplication
@EnableTransactionManagement
@MapperScan("com.qingfeng.mapper")
public class ServerGencodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerGencodeApplication.class, args);
    }

}