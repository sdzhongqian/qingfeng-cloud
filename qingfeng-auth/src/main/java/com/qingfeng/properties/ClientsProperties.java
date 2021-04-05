package com.qingfeng.properties;

import lombok.Data;

/**
 * @ProjectName ClientsProperties
 * @author Administrator
 * @version 1.0.0
 * @Description 客户端参数配置
 * @createTime 2021/4/3 0003 19:12
 */
@Data
public class ClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}