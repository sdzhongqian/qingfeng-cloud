package com.qingfeng;

import com.qingfeng.annotation.EnableCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
* @title: ServerJobApplication
* @projectName: ServerJobApplication
* @description: TODO
* @author: qingfeng
* @date: 2021/2/23 0023 22:04
*/
@EnableFeignClients
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCloudApplication
@EnableTransactionManagement
@MapperScan("com.qingfeng.mapper")
public class ServerJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJobApplication.class, args);
    }

}