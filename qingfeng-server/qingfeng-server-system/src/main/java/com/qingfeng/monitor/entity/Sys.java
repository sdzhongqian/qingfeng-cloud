package com.qingfeng.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @title Sys
 * @description Sys
 * @author qingfeng
 * @updateTime 2021/4/3 0003 21:00
 */
@Data
public class Sys implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}