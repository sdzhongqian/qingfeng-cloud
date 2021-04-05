package com.qingfeng.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author qingfeng
 * @title: ServerSystemProperties
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 002122:56
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:qingfeng-server-system.properties"})
@ConfigurationProperties(prefix = "qingfeng.server.system")
public class ServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private SwaggerProperties swagger = new SwaggerProperties();
}