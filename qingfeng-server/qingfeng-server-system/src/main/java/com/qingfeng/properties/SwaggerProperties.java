package com.qingfeng.properties;

import lombok.Data;

/**
 * @author qingfeng
 * @title: SwaggerProperties
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/2/21 002122:55
 */
@Data
public class SwaggerProperties {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;

    private String grantUrl;
    private String name;
    private String scope;

}