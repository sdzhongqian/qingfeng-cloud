package com.qingfeng;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @title: MonitorAdminApplication
* @projectName: MonitorAdminApplication
* @description: TODO
* @author: Administrator
* @date: 2021/2/23 0023 21:51
*/
@EnableAdminServer
@SpringBootApplication
public class MonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminApplication.class, args);
    }

}
