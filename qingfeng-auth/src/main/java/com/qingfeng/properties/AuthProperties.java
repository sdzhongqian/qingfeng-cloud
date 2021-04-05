package com.qingfeng.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName AuthProperties
 * @author Administrator
 * @version 1.0.0
 * @Description 权限参数配置
 * @createTime 2021/4/3 0003 19:12
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:qingfeng-auth.properties"})
@ConfigurationProperties(prefix = "qingfeng.auth")
public class AuthProperties {

    private ClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private ValidateCodeProperties code = new ValidateCodeProperties();

}