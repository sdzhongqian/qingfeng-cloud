package com.qingfeng.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName CloudGatewayProperties
 * @author Administrator
 * @version 1.0.0
 * @Description 参数工具类
 * @createTime 2021/4/3 0003 19:55
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:qingfeng-gateway.properties"})
@ConfigurationProperties(prefix = "qingfeng.gateway")
public class CloudGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}