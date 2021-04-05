package com.qingfeng;

import com.qingfeng.annotation.EnableCloudApplication;
import com.qingfeng.annotation.EnableMyAuthExceptionHandler;
import com.qingfeng.annotation.EnableMyServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
* @title: ServerSystemApplication
* @projectName: ServerSystemApplication
* @description: TODO
* @author: qingfeng
* @date: 2021/2/23 0023 21:58
*/
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCloudApplication
@EnableTransactionManagement
@MapperScan("com.qingfeng.*.mapper")
public class ServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSystemApplication.class, args);
    }
}