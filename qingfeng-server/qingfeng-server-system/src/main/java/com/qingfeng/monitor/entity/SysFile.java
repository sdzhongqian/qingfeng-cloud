package com.qingfeng.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @title SysFile
 * @description SysFile
 * @author qingfeng
 * @updateTime 2021/4/3 0003 21:00
 */
@Data
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}
