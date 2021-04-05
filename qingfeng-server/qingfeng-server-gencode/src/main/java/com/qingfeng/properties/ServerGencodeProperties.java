package com.qingfeng.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName ServerGencodeProperties
 * @author qingfeng
 * @version 1.0.0
 * @Description 参数
 * @createTime 2021/4/3 0003 20:01
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:qingfeng-server-gencode.properties"})
@ConfigurationProperties(prefix = "qingfeng.server.gencode")
public class ServerGencodeProperties {


    private String gencodeField;

    private String anonUrl;




}